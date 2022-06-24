#!/bin/sh

curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
sudo apt-get update && sudo apt-get install packer

packer build -var "REPOSITORY=$DOCKERHUB_REPOSITORY" -var "USERNAME=$DOCKERHUB_CREDENTIALS_USR" -var "PASSWORD=$DOCKERHUB_CREDENTIALS_PSW" Get_Calculator_Artifactory/build_image.pkr.hcl