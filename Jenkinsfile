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


        stage('Upload Artifact') {
            steps {
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: '172.25.96.1:8081',
                    groupId: 'QA',
                    version: """${env.BUILD_ID}-${env.BUILD_TIMESTAMP}""",
                    repository: 'td-maven.repo',
                    credentialsId: 'NexusLogin',
                    artifacts: [
                        [artifactId: 'gestioneventos',
                        classifier: '',
                        file: 'target/gestioneventos-0.0.1-SNAPSHOT.jar',
                        type: 'jar']
                    ]
                )
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