worker: java -jar target/*.jar
web: java $JAVA_OPTS -Dserver.port=$PORT -jar target/*.jar
heroku ps:scale --app alerter-mail worker=1