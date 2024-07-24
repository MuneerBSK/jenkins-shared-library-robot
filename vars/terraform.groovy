def call() {
        properties([
            parameters([
                choice(choices: 'dev\nprod', description: "Chose the environment", name: "ENV"),
                choice(choices: 'apply\ndestroy', description: "Chose the Action", name: "ACTION"),
                
            ]),
        ])
        
        node {
           ansiColor('xterm') {
            sh "rm -rf *"
            git branch: 'main', url: "https://github.com/MuneerBSK/${REPONAME}.git"

            stage('Terraform Init') {
                sh ''' 
                    ls -ltr
                    terrafile -f env-${ENV}/Terrafile
                    terraform init -backend-config=env-${ENV}/${ENV}-backend.tfvars
                '''
            }

            stage('Terraform Plan') {
                sh ''' 
                    terraform plan -var-file=env-${ENV}/${ENV}.tfvars
                '''
            }

            stage('Terraform Apply ') {
                
                   sh '''
                    terraform ${ACTION} -var-file=env-${ENV}/${ENV}.tfvars -auto-approve
                '''
                }
            }
        }
    }