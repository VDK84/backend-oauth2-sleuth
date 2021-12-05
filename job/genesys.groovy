//https://www.digitalocean.com/community/tutorials/how-to-automate-jenkins-job-configuration-using-job-dsl
pipelineJob('test_rest3') {
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