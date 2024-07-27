pipeline {
    agent any

    tools {
        maven 'Maven'  // Asegúrate de que este nombre coincida con la configuración de tu Jenkins
        jdk 'JDK17'    // Asegúrate de que este nombre coincida con la configuración de tu Jenkins para Java 17
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/PaarXul/ges-event.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonar-scanner'
                    withSonarQubeEnv('sonar-scanner') {
                        sh """
                            ${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=pipeline-jenkins-sonar \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.java.libraries=target/dependency/*.jar \
                            -Dsonar.java.source=17 \
                            -Dsonar.java.target=17 \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
            echo 'Pipeline completado'
        }
    }
}