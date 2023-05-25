pipeline {
    agent any
    tools {
        maven "maven"
    }
    stages {
        stage("Build JAR File") {
            steps {
                sh "mvn clean install"
            }
        }
        stage("Test") {
            steps {
                sh "mvn test"
            }
        }
        stage("Build Docker Image") {
            steps {
                sh "docker build -t hfaravenak/proyectomingeso1 ."
            }
        }
        stage("Push Docker Image") {
            steps {
            withCredentials([string(credentialsId: 'dckrhubpassword', variable: 'dckpass')]) {
                    sh "docker login -u hfaravenak -p ${dckpass}"
                }
                sh "docker push hfaravenak/proyectomingeso1"
            }
        }
    }
    post {
        always {
            sh "docker logout"
        }
    }
}