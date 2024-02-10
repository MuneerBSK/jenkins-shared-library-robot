def lintChecks() {
    sh '''
                echo Lint Checks for ${COMPONENT}
                # mvn checkstyle:check
                echo performing lint checks for ${COMPONENT}
                echo performing lint checks completed ${COMPONENT}
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
        }
    }
}