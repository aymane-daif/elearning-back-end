def dockerImageName = "elearning-api"
def dockerImageTag = "${dockerRepoUrl}/elearning/${dockerImageName}:latest"

pipeline {
   agent any
   stages {
      stage('Tests') {
         steps {
            sh("mvn clean test")
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
            DOCKER_EMAIL = credentials("DOCKER_EMAIL")
            DOCKER_PASSWORD = credentials("DOCKER_PASSWORD")
         }
         steps {
            sh("docker tag ${dockerImageName} ${dockerImageTag}")
            sh("docker login -u $DOCKER_EMAIL -p $DOCKER_PASSWORD")

            sh("docker push ${dockerImageTag}")
         }
      }
      stage('Deploy') {
         sh("echo deploy")
      }

   }

}