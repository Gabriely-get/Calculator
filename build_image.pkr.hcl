/* tirei o required plugin pois rodei a primeira vez no jenkinse e foi baixado. Na segunda vez, disse que ja havia baixado e iria duplicar, então, tirei*/

variables {
  REPOSITORY = "gabsss/calculator-rxnetty"
  USERNAME   = "gabsss"
  PASSWORD   = "Caminhoilegra21!"
}

source "docker" "calculator" {
  image  = "ubuntu:18.04"
  commit = "true"
  changes = [
    "EXPOSE 8888",
    "ENTRYPOINT  [\"java\", \"-jar\", \"calculator.jar\"]"
  ]
}

build {
  name = "build-calculator-packer"

  sources = ["source.docker.calculator"]

  provisioner "shell" {
    script = "/var/jenkins_home/workspace/Download_Calculator_Artifactory/install-ansible.sh"
  }

  provisioner "ansible-local" {
    playbook_file = "/var/jenkins_home/workspace/Download_Calculator_Artifactory/common.yml"
  }

  provisioner "file" {
    source      = "/var/jenkins_home/workspace/Download_Calculator_Artifactory/exploded_calculator/Calculator-shadow-1.0/lib/Calculator-1.0-all.jar"
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

