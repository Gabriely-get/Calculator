pipeline {

    agent any

    stages {
        stage('Get Artifact from JFrog') {
            steps {
                rtDownload (
                    serverId: "Calculator Artifactory",
                    spec: """{
                        "files": [
                            {
                                "pattern": "generic-calculator-build/com.gabrielyget/Calculator/1.0/Calculator-shadow-1.0.tar",
                                "flat": "true",
                                "target": "exploded_-calculator/",
                                "explode": "true"
                            }
                        ]
                    }"""
                )
            }
        }

        stage('Build Packer Image') {
            steps {
                    sh '''
                      packer build -var "REPOSITORY=gabsss/calculator-rxnetty" -var "USERNAME=gabsss" -var "PASSWORD=Caminhoilegra21!" /var/jenkins_home/workspace/Get_Calculator_Artifactory/build_image.pkr.hcl
                    '''
            }
        }
    }
}