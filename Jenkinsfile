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
                sh 'packer init Get_Calculator_Artifactory/build_image.pkr.hcl'
                sh 'packer fmt Get_Calculator_Artifactory/build_image.pkr.hcl'
                sh 'packer validate Get_Calculator_Artifactory/build_image.pkr.hcl'
                sh 'packer build -var "REPOSITORY=$DOCKERHUB_REPOSITORY" -var "USERNAME=$DOCKERHUB_CREDENTIALS_USR" -var "PASSWORD=$DOCKERHUB_CREDENTIALS_PSW" "Get_Calculator_Artifactory/build_image.pkr.hcl"'
            }
        }
    }
}