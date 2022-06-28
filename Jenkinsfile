pipeline {

    agent any

    environment {

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
                    sh '''
                      /var/jenkins_home/tools/biz.neustar.jenkins.plugins.packer.PackerInstallation/packer_job02/packer build -var "REPOSITORY=gabsss/calculator-rxnetty" -var "USERNAME=gabsss" -var "PASSWORD=Caminhoilegra21!" /var/jenkins_home/workspace/Get_Calculator_Artifactory/build_image.pkr.hcl
                    '''
            }
        }
    }
}