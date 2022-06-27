pipeline {

    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKERHUB_REPOSITORY = credentials('repository_name')
    }

    stages {
        stage('Get Artifact from JFrog') {
            steps {
                rtDownload (
                    serverId: "Calculator_Artifactory",
                    spec: """{
                        "files": [
                            {
                                "pattern": "gradle-calculator-build/com.gabrielyget/Calculator/1.0/Calculator-shadow-1.0.tar",
                                "flat": "true",
                                "target": "extracted-calculator/",
                                "explode": "true"
                            }
                        ]
                    }"""
                )
            }
        }

        stage('Build Packer Image') {
            steps {
                withCredentials([ string(credentialsId: 'packer_path', variable: 'PACKER') ]) {
                    sh '''
                      $PACKER build -var REPOSITORY=$REPOSITORY -var USERNAME=$USERNAME -var PASSWORD=$PASSWORD Get_Calculator_Artifactory/build_image.pkr.hcl
                    '''
                }
            }
        }
    }
}