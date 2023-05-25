pipeline{
    agent any
    tools{
        maven "maven"
    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/hfaravenak/Proyecto-Mingeso-1']])
                dir("Proyecto-Mingeso-1"){
                    sh "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps{
                dir("Proyecto-Mingeso-1"){
                    sh "mvn test"
                }
            }
        }
        stage("Build Docker Image"){
            steps{
               dir("Proyecto-Mingeso-1"){
                    sh "docker build -t hfaravenak/proyectomingeso1 ."
                }
            }
        }
        stage("Push Docker Image"){
            steps{
                  dir("Proyecto-Mingeso-1"){
                    withCredentials([string(credentialsId: 'dckrhubpassword', variable: 'dckpass')]){
                        sh "docker login -u hfaravenak -p ${dckpass}"

                    }
                    sh "docker push hfaravenak/proyectomingeso1"

                }

            }
        }
    }
    post{
        always{
            dir("Proyecto-Mingeso-1"){
                sh "docker logout"
            }
        }
    }
}