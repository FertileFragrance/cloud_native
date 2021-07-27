stage('RTF Test'){
    echo "RTF Test Stage"
    sh 'kubectl apply -f ./scripts/rtf.yaml -n cn202102'

}