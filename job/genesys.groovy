//https://www.digitalocean.com/community/tutorials/how-to-automate-jenkins-job-configuration-using-job-dsl
pipelineJob('test_rest3') {
    parameters {
            stringParam('REST_URL', '', 'REST API Base URL')
            stringParam('REST_SERVICE', '', "REST service path")
        }
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github('https://github.com/VDK84/backend-oauth2-sleuth.git')
                    }
                }
            }
            scriptPath('job/callJAva.groovy')
        }
    }
}