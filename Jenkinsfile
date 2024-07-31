def COLOR_MAP = [
    'SUCCESS': 'good',
    'FAILURE': 'danger',
]

pipeline {
    agent any

    tools {
        maven 'Maven'  // Asegúrate de que este identificador coincida con tu configuración de Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/PaarXul/ges-event.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
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
            deleteDir()
            echo 'Pipeline completado'

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