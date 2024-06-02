pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scmGit(branches: [[name: 'TestJenkins']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Shubhangi786/JenkinsTasks.git']])
                
            }
        }
        stage('Build'){
            steps {
                dir('\\jenkinstask') {
                        bat 'mvn clean install'
                    }
            }
        }
        stage('Test'){
            steps {
                dir('\\jenkinstask') {
                        bat 'mvn test'
                    }
            }
        }
        stage('Reporting'){
            steps {
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '\\jenkinstask\\reports', reportFiles: '/*.html', reportName: 'Extent Report', reportTitles: '', useWrapperFileDirectly: true])
            }
        }
        stage('Notify'){
            steps{
                emailext attachmentsPattern: 'C:\\Users\\shubhangimadhukar_sa\\.jenkins\\workspace\\JenkinsTask_Pipeline\\jenkinstask\\reports\\**/*.html', body: '', subject: 'Jenkins-${JOB_NAME}-${BUILD_NUMBER}', to: 'shubhangimsable@gmail.com'
            }
        }
    }
}
