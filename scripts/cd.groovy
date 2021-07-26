node('slave') {
    container('jnlp-kubectl') {

        stage('Clone YAML') {
            echo "5. Git Clone YAML To Slave"
            git branch: "main", url: "https://github.com/FertileFragrance/cloud_native.git"
        }

        stage('YAML') {
            echo "6. Change YAML File Stage"
            sh 'sed -i "s#{VERSION}#${BUILD_ID}#g" ./scripts/cloud_native.yaml'
        }

        stage('Deploy') {
            echo "7. Deploy To K8s Stage"
            sh "docker login --username=cn202102 harbor.edu.cn -p cn202102"
            sh "docker pull harbor.edu.cn/cn202102/cloud_native:${BUILD_ID}"
            sh 'kubectl apply -f ./scripts/cloud_native.yaml'
        }
    }
}
