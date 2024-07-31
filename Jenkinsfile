pipeline {
    agent any
    def COLOR_MAP = [
     ‘SUCCESS’: ‘good’,
     ‘FAILURE’:’danger’,
     ]
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
                script {
                    def scannerHome = tool 'sonar-scanner';
                    withSonarQubeEnv('sonar-scanner') {
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=pipeline-jenkins-sonar -Dsonar.java.binaries=target/classes"
                    }
                }
            }
       }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
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

            echo 'Sending Slack Notification'
                    slackSend channel: '#time-tracker-ci',
                              color: COLOR_MAP[currentBuild.currentResult],
                              message: """
                                *${currentBuild.currentResult}:* Job `${env.JOB_NAME}` build `${env.BUILD_NUMBER}`
                                Branch: `${env.GIT_BRANCH}`
                                More Info at: ${env.BUILD_URL}
                              """.stripIndent()

        }
    }
}