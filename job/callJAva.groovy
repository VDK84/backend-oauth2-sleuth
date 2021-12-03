//https://www.eficode.com/blog/jenkins-groovy-tutorial
pipeline {
    agent any   

    stages {
        stage('Call WS') {
            steps{
                script {
                    callRESTapi()
                }
            }
        }
    }
}

def callRESTapi() {
    def url = ${params.REST_URL}${params.REST_SERVICE}
    httpRequest(
            acceptType: 'APPLICATION_JSON',
            contentType: 'APPLICATION_JSON',
            consoleLogResponseBody: true,
            ignoreSslErrors: true,
            httpMode: 'GET',
            url: url
    )
}
