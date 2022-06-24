#!/bin/sh

packer build -var "REPOSITORY=$DOCKERHUB_REPOSITORY" -var "USERNAME=$DOCKERHUB_CREDENTIALS_USR" -var "PASSWORD=$DOCKERHUB_CREDENTIALS_PSW" Get_Calculator_Artifactory/build_image.pkr.hcl