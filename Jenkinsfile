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
                withSonarQubeEnv(credentialsId: 'SonarToken') {  // Asegúrate de que este nombre coincida con la configuración de tu Jenkins
                    sh 'mvn sonar:sonar'
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