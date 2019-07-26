pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''chmod 700 ./gradlew

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
nohup java -jar ./build/libs/package-booking-backend-0.0.1-SNAPSHOT.jar > out.log &'''
      }
    }
  }
}