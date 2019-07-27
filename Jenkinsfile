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
        input(message: 'sure?', ok: 'YES')
      }
    }
  }
}