pipeline {
    agent any
    stages {
        stage('echo parameters') {
            steps {
                echo "DEBUG: parameter projectIdClusterNamespaceApiProxy = ${projectIdClusterNamespaceApiProxy}"
                echo "DEBUG: parameter clusterLocation = ${clusterLocation}"
                echo "DEBUG: parameter artifactId = ${artifactId}"
                echo "DEBUG: parameter appVersion = ${appVersion}"
            }
        }
        stage('testing Input') {
            steps {
                input message: 'UAT/Prod Deployments', parameters: [string(defaultValue: "${ticketNum}", description: 'Enter a SNOW ticket number', name: 'ticketNum', trim: false)]
                echo "deployed to production!"
            }
        }
    }
}
