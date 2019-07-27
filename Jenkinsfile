pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''cp /root/backup/application-dev.yml ./src/main/resources/

chmod 700 ./gradlew

./gradlew clean build'''
      }
    }
    stage('Run') {
      steps {
        sh '''PID=$(jps | grep jar | cut -d \' \' -f 1)
if [ ! -n $PID ]; then
  kill -9 $PID
fi'''
        sh '''JENKINS_NODE_COOKIE=dontKillMe
nohup java -jar /var/lib/jenkins/workspace/parking-alfred-backend_master/build/libs/parking-alfred-0.1.jar > out.log &'''
      }
    }
    stage('Wait to deploy') {
      steps {
        input(message: 'sure to delpoy?', ok: 'YES')
      }
    }
    stage('Deploy') {
      steps {
        sh '''LOCAL_FILE=\'/var/lib/jenkins/workspace/parking-alfred-backend_master/build/libs/parking-alfred-0.1.jar\'
PROD_USER=\'root\'
PROD_ADDRESS=\'39.100.49.41\'
PROD_DIR=\'/usr/local/app/parking-alfred-backend\'

scp ${LOCAL_FILE} ${PROD_USER}@${PROD_ADDRESS}:${PROD_DIR}

ssh ${PROD_USER}@${PROD_ADDRESS} "cd ${PROD_DIR}; ./depoly.sh"'''
      }
    }
  }
}