pipeline {   
    agent any
    tools {       
        jdk 'java'
        maven 'maven'
    }
    stages {
        stage('Install') {
            steps {
                dir('/Lab4/p1'){
                    sh 'mvn clean install'
                }
            }
            post{
                always{
                    junit '**/target/*-reports/TEST-*.xml'
                }
            }
        }
    }
}
 