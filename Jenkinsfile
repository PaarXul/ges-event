pipeline {
    agent any


    stages {
        stage('Checkout') {
            steps {
                // Descarga del código fuente
                git branch: 'main', url: 'https://github.com/PaarXul/ges-event.git'
            }
        }

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
                */
                script {
                    def scannerHome = tool 'SonarQube';
                    withSonarQubeEnv('SonarQube') {
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ges-event -Dsonar.sources=src -Dsonar.host.url=http://localhost:9000 -Dsonar.login=4"
                    }
                }
            }
        }
    }

    post {
        always {
            // Limpieza y notificación
            cleanWs()
            echo 'Pipeline completado'
        }
    }
}