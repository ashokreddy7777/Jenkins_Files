pipeline {
    agent any
    parameters {
        booleanParam defaultValue: false, description: '', name: 'pgp-encryption-util'
        string defaultValue: '', name: 'pgp_encryption_util_appVersion'

        booleanParam defaultValue: false, description: '', name: 'pay-dev-util'
        string defaultValue: '', name: "pay_dev_util_appVersion"

        choice choices: ['us-a', 'us-b', 'us-c', 'us-d'], description: '', name: 'IdClusterNamespaceApi'
        choice choices: ['region/us-east1', 'region/us-west1'], description: '', name: 'clusterLocation'
        choice choices: ['Job-B', 'Job-C'], description: '', name: 'job'              
    }  
    stages {
        stage('api-deployment') {
            parallel {
                stage('pgp-encryption-util') {
                    when { environment name: 'pgp-encryption-util', value: 'true' }
                    steps {
                        build job:"${job}", parameters: [
                            string(name:'artifactId', value:'pgp-encryption-util'),
                            string(name:'appVersion', value:"${pgp_encryption_util_appVersion}"),
                            string(name:'IdClusterNamespaceApi', value: "${IdClusterNamespaceApi}"),
                            string(name:'ClusterLocation', value: "${clusterLocation}" )
                        ]
                    }
                }
                stage('pay-dev-util') {
                    when { environment name: 'pay-dev-util', value: 'true' }
                    steps {
                        build job:"${job}", parameters: [
                            string(name:'artifactId', value:'pay-dev-util'),
                            string(name:'appVersion', value:"${pay_dev_util_appVersion}"),
                            string(name:'IdClusterNamespaceApi', value: "${IdClusterNamespaceApi}"),
                            string(name:'ClusterLocation', value: "${clusterLocation}")
                        ]
                    }
                }
            }
        }
    }
}