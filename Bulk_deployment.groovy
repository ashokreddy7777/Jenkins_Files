pipeline {
    agent any
    parameters {
        booleanParam defaultValue: false, description: '', name: 'asa-billing-api-gke'
        string defaultValue: '', name: 'asa_billing_api_gke_appVersion'

        booleanParam defaultValue: false, description: '', name: 'config-api-gke'
        string defaultValue: '', name: 'config_api_gke_appVersion'

        choice choices: ['usis-ac-mc-dev-npe/mc-dev', 'usis-ac-mc-dev-npe/mc-qa'], description: '', name: 'projectIdClusterNamespaceApiProxy'
        choice choices: ['region/us-east1', 'region/us-west1'], description: '', name: 'clusterLocation'
        //choice choices: ['Job-B', 'Job-C'], description: '', name: 'job'  

        string defaultValue: '', name: 'ticketNum'        
    }  
    stages {
        stage('api-deployment') {
            parallel {
                stage('asa-billing-api-gke') {
                    when { environment name: 'asa-billing-api-gke', value: 'true' }
                    steps {
                        build job:'Job-B', parameters: [
                            string(name:'projectIdClusterNamespaceApiProxy', value: "${projectIdClusterNamespaceApiProxy}"),
                            string(name:'ClusterLocation', value: "${clusterLocation}" ),
                            string(name:'artifactId', value:'asa-billing-api-gke'),
                            string(name:'appVersion', value:"${asa_billing_api_gke_appVersion}")
                            string(name: 'ticketNum', value:"${ticketNum}")
                        ]
                    }
                }
                stage('config-api-gke') {
                    when { environment name: 'config-api-gke', value: 'true' }
                    steps {
                        build job:'Job-B', parameters: [
                            string(name:'projectIdClusterNamespaceApiProxy', value: "${projectIdClusterNamespaceApiProxy}"),
                            string(name:'ClusterLocation', value: "${clusterLocation}"),
                            string(name:'artifactId', value:'config-api-gke'),
                            string(name:'appVersion', value:"${config_api_gke_appVersion}")                            
                        ]
                    }
                }
            }
        }
    }
}