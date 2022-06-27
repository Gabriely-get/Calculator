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
                      /var/jenkins_home/tools/biz.neustar.jenkins.plugins.packer.PackerInstallation/packer_job02/packer validate /var/jenkins_home/workspace/Download_Calculator_Artifactory/build_image.pkr.hcl
                    '''
                }
            }
        }
    }
}