def lintChecks(COMPONENT) {
    sh '''
                echo installing jslint
                # echo npm install jslint
                # ls -Ltr node_modules/jslint/bin/
                # node_modules/jslint/bin/jslint.js server.js
                echo performing lint checks
                echo performing lint checks completed
       '''
}

// call is the default function which will be cqlled whrn you call the filename
def call(COMPONENT) {
    pipeline {
        agent any
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                         lintChecks(COMPONENT)
                    }
                }
            }
        }
    }
}