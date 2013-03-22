call SET NEO4J_REST_URL=http://localhost:7474/db/data
call SET NEO4J_LOGIN=""
call SET NEO4J_PASSWORD=""
call echo " ************** Running Maven install ********* "
call mvn clean install

call echo " ************** Running the build using webapp runner ********* "
call SET env=local
call java -jar target/dependency/webapp-runner.jar target/netvogueweb.war