pipeline {
    agent any


    stages {
        stage('Checkout') {
            steps {
                // Descarga del código fuente
                git branch: 'main', url: 'https://github.com/PaarXul/ges-event.git'
            }
        }
/*
        stage('Build') {
            steps {
                // Compilación del proyecto con Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Ejecución de pruebas
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Análisis de SonarQube
                /*withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'SonarToken') {
                    sh 'mvn sonar:sonar'
                }

                script {
                    def scannerHome = tool 'sonar-scanner';
                    withSonarQubeEnv('sonar-scanner') {
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=pipeline-jenkins-sonar -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }
*/
        stage('build && SonarQube analysis') {
             steps {
                 withSonarQubeEnv('<sonarqubeInstallation>') {
                           // Optionally use a Maven environment you've configured already
                      withMaven(maven:'Maven 3.5') {
                               sh 'mvn clean package sonar:sonar'
                           }
                       }
                   }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }

    }

    post {
        always {
            // Limpieza y notificación
            deleteDir()
            echo 'Pipeline completado'
        }
    }
}