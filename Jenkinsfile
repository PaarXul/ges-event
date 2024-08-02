def COLOR_MAP = [
    'SUCCESS': 'good',
    'FAILURE': 'danger',
]

pipeline {
    agent any

    tools {
        maven "Maven"
    }

    environment {
        SONAR_SCANNER_HOME = 'sonar-scanner'
        GIT_BRANCH = 'main'
        GIT_REPO = 'https://github.com/PaarXul/ges-event.git'
        SONAR_PROYECT_KEY = 'pipeline-jenkins-sonar'
        SLACK_CHANNEL = '#tarea-10-gian'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "$GIT_BRANCH", url: "$GIT_REPO"
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
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool "${SONAR_SCANNER_HOME}";
                    withSonarQubeEnv('sonar-scanner') {
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${SONAR_PROYECT_KEY} -Dsonar.java.binaries=target/classes"
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
        stage('Upload to Nexus') {
            steps {
                script {
                    // Definir la ubicación del settings.xml
                    def settingsPath = '/var/jenkins_home/.m2/settings.xml'

                    // Verificar si el archivo settings.xml existe
                    def settingsExists = fileExists settingsPath

                    if (!settingsExists) {
                        // Si no existe, crear el archivo con el contenido necesario
                        writeFile file: settingsPath, text: '''
<settings>
        <servers>
                <server>
                        <id>td-maven-repo</id>
                        <username>gianp</username>
                        <password>admin</password>
                </server>
        </servers>
</settings>
'''
                        echo "Created settings.xml file at ${settingsPath}"
                    } else {
                        echo "settings.xml file already exists at ${settingsPath}"
                    }

                    // Ejecutar Maven con el archivo settings.xml
                    sh "mvn clean deploy -s ${settingsPath}"
                }
            }
        }
    }

    post {
        always {
            deleteDir()
            echo 'Pipeline completado'
            echo 'Sending Slack Notification'
            slackSend channel: "$SLACK_CHANNEL",
                      color: COLOR_MAP[currentBuild.currentResult],
                      message: """
                        *${currentBuild.currentResult}:* Job `${env.JOB_NAME}` build `${env.BUILD_NUMBER}`
                        Branch: `${GIT_BRANCH}`
                        More Info at: ${env.BUILD_URL}
                      """.stripIndent()
        }
    }
}