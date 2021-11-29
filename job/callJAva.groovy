//https://www.eficode.com/blog/jenkins-groovy-tutorial
pipeline {
    node any
    

    stages {
        stage('Call WS') {
            script {
                callRESTapi()
            }
        }
    }
}

def callRESTapi() {
    def url = "${params.REST_URL}${params.REST_SERVICE}"
    httpRequest(
            acceptType: 'APPLICATION_JSON',
            contentType: 'APPLICATION_JSON',
            consoleLogResponseBody: DEBUG_HTTP,
            ignoreSslErrors: true,
            httpMode: 'GET',
            url: url
    )
}
