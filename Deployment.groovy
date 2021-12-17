pipeline {
    agent any
    stages {
        stage('echo parameters') {
            steps {
                echo "DEBUG: parameter artifactId = ${env.artifactId}"
                echo "DEBUG: parameter appVersion = ${env.appVersion}"
                echo "DEBUG: parameter IdClusterNamespaceApi = ${IdClusterNamespaceApi}"
                echo "DEBUG: parameter ClusterLocation = ${clusterLocation}"
                echo "DEBUG: parameter api_01_appVersion = ${appVersion}"
            }
        }
    }
}