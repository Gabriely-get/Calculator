packer {
  required_plugins {
    docker = {
      version = ">= 0.0.7"
      source  = "github.com/hashicorp/docker"
    }
  }
}

variables {
  REPOSITORY = "{{env `REPOSITORY`}}"
  USERNAME   = "{{env `USERNAME`}}"
  PASSWORD   = "{{env `PASSWORD`}}"
}

source "docker" "ubuntu" {
  image  = "ubuntu:18.04"
  changes = [
    "EXPOSE 8000",
    "ENTRYPOINT  [\"java\", \"-jar\", \"calculator.jar\"]"
  ]
  commit = "true"
  pull   = "true"
}

build {
  sources = ["source.docker.ubuntu"]

  provisioner "shell" {
    inline = ["echo AAAA"]
  }

  provisioner "shell" {
    script = "/var/jenkins_home/workspace/Get_Calculator_Artifactory/install-ansible.sh"
  }

  provisioner "ansible-local" {
    playbook_file = "/var/jenkins_home/workspace/Get_Calculator_Artifactory/common.yml"
  }

  provisioner "file" {
    source      = "/var/jenkins_home/workspace/Get_Calculator_Artifactory/extracted-calculator/Calculator-shadow-1.0/lib/Calculator-1.0-all.jar"
    destination = "/calculator.jar"
  }

  post-processors {
    post-processor "docker-tag" {
      repository = "${var.REPOSITORY}"
      tags       = ["latest"]
    }

    post-processor "docker-push" {
      login          = true
      login_username = "${var.USERNAME}"
      login_password = "${var.PASSWORD}"
    }
  }
}

