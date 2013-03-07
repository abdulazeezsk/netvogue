call SET NEO4J_REST_URL=http://localhost:7474/db/data
call SET NEO4J_LOGIN=""
call SET NEO4J_PASSWORD=""
call echo " ************** Running Maven install ********* "
call mvn clean install
call echo " ************** Running the build using webapp runner ********* "
call java -jar target/dependency/webapp-runner.jar --port 9080 target/netvogue-admin.war