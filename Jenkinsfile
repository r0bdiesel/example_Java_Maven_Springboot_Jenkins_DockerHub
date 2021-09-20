pipeline {
    environment {
	IN_DOCKER_ENV = fileExists('/.dockerenv')
	REGISTRY = "r0bdiesel/example_java_ant_ivy_jenkins"
	DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }
    agent any
    options {
    	buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
    stages {
        stage('Log Ant, Git, and Java version info') {
            steps {
		echo "Starting Pipeline Steps"
                antEchoVersions()
            }
        }
        stage('Build') {
            steps {
                echo "${env.STAGE_NAME}ing.. ${env.BUILD_ID} on ${env.JENKINS_URL}"
		sh 'mvn clean'
		sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                echo "${env.STAGE_NAME}ing.."
		sh 'mvn test'
            }
        }
	stage('Docker Build') {
            steps {
                echo "${env.STAGE_NAME} Stage"
		sh 'docker build -t r0bdiesel/test:latest .'
            }
        }
	stage('DockerHub Login') {
            steps {
                echo "${env.STAGE_NAME} Stage"
		sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
	stage('Push Image to DockerHub') {
      	    steps{
	     	echo "${REGISTRY}"
             }
         }
	 stage('DockerHub Logout') {
  	     steps{
		sh 'docker logout'
		sh 'docker image prune -a'
  	     }
	}
    }
}

void antEchoVersions() {
  echo "UNIX"
	sh 'mvn -version'
	sh 'java -version'
	sh 'git --version'
	echo "inDockerEnv:${IN_DOCKER_ENV}"
}
