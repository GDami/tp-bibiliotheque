pipeline {
    agent any

    stages {
        stage('Maven Package') {
            agent {
                docker {
                    image 'maven:3.9-amazoncorretto-21'
                    args '''
                    -v $HOME/.m2:/root/.m2:z
                    -u root
                    --network devops
                    '''
                    reuseNode true // Permet de partager le même workspace que l'agent Jenkins
                }
            }

            steps {
                dir('tp-bibliotheque/backend') {
                    withSonarQubeEnv('SonarQube'){
						sh "mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Quest-Boot -Dsonar.projectName='Quest Boot'"
					}
                }
            }
        }

    stage('Angular Build') {
        agent {
            docker {
                image 'node:22-alpine'
                reuseNode true
        }
    }

    steps {
        dir('tp-bibliotheque/frontend/bibliotheque') {
            sh 'npm install'
            sh 'npm run build'
        }
    }
}

        stage('Docker build') {
            steps {
                dir('tp-bibliotheque/backend') {
                    sh 'docker build -t bibili-java .'
                    sh 'docker build -t bibili-front'
                }
            }
        }

        stage('Docker compose') {
            steps {
                dir('tp-bibliotheque') {
                    sh 'docker compose up -d'
                }
            }
        }
    }
}