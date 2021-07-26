pipeline {
    agent none
    stages {
        stage('Clone Code') {
            agent {
                label 'master'
            }
            steps {
                echo "----- Step 1: Cloning Code -----"
                git branch: "main", url: "https://github.com/FertileFragrance/cloud_native.git"
            }
        }
        stage('Maven Build') {
            agent {
                docker {
                    image 'maven:latest'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo "----- Step 2: Building maven project (Skip test) -----"
                sh 'mvn -B clean package -Dmaven.test.skip=true'
            }
        }
        stage('Image Build') {
            agent {
                label 'master'
            }
            steps {
                echo "----- Step 3: Building docker image (Skip test) -----"
                sh 'docker build -f Dockerfile --build-arg jar_name=target/demo-0.0.1-SNAPSHOT.jar -t cloud_native:${BUILD_ID} . '
                sh 'docker tag  cloud_native:${BUILD_ID}  harbor.edu.cn/cn202102/cloud_native:${BUILD_ID}'
            }
        }
        stage('Push') {
            agent {
                label 'master'
            }
            steps {
                echo "----- Step 4: Pushing docker image to habour -----"
                sh "docker login --username=cn202102 harbor.edu.cn -p cn202102"
                sh "docker push harbor.edu.cn/cn202102/cloud_native:${BUILD_ID}"
            }
        }
    }
}
