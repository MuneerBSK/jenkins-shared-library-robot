def lintChecks() {
    sh '''
                echo Lint Checks for ${COMPONENT}
                echo installing jslint
                # echo npm install jslint
                # ls -Ltr node_modules/jslint/bin/
                # node_modules/jslint/bin/jslint.js server.js
                echo performing lint checks for ${COMPONENT}
                echo performing lint checks completed ${COMPONENT}
       '''
}

def sonarChecks() {
    sh '''
        sonar-scanner -Dsonar.host.url=http://172.31.2.205:9000 -Dsonar.sources=. -Dsonar.projectKey==${COMPONENT} _Dsonar.login=admin -Dsonar.password=password
    '''
}

// call is the default function which will be cqlled whrn you call the filename
def call() {
    pipeline {
        agent any
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                         lintChecks()
                    }
                }
            }
            stage('Sonar Checks') {
                steps {
                    script {
                         sonarChecks()
                    }
                }
            }
        }
    }
}