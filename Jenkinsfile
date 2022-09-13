def dockerImageName = "elearning_api"
def dockerImageTag = "aymanedaif/${dockerImageName}:latest"

pipeline {
   agent any
   stages {
      stage('Tests') {
        steps {
            //sh("mvn clean test")
            sh("echo test")
        }
      }
      stage('build') {
         steps {
            sh("mvn clean install -DskipTests")
         }
      }
      stage('Docker Build and Tag') {
         steps {
            sh("docker build -f docker/Dockerfile -t ${dockerImageName} .")
         }
      }
      stage('Push') {
         environment {
            DOCKER_USERNAME = credentials("DOCKER_USERNAME")
            DOCKER_PASSWORD = credentials("DOCKER_PASSWORD")
         }
         steps {
            sh("docker tag ${dockerImageName} ${dockerImageTag}")
            sh('docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD')
            sh("docker push ${dockerImageTag}")
         }
      }
      stage('Deploy') {
        environment {
            AWS_NAME = credentials("AWS_NAME")
            AWS_HOST = credentials("AWS_HOST")
            AWS_USER = credentials("AWS_USER")
            AWS_PASSWORD = credentials("AWS_PASSWORD")
        }
        steps {
            script {
                def remote = [: ]
                remote.name = "$AWS_NAME"
                remote.host = "$AWS_HOST"
                remote.user = "$AWS_USER"
                remote.password = "$AWS_PASSWORD"
                remote.allowAnyHosts = true
                sshCommand remote: remote, command: 'mkdir test'
            }
        }
      }
   }
}