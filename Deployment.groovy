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
    }
}
