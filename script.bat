call SET NEO4J_REST_URL=http://localhost:7474/db/data
call SET NEO4J_LOGIN=""
call SET NEO4J_PASSWORD=""
call echo " ************** Running Maven install ********* "
call mvn clean install

call echo " ************** Running the build using webapp runner ********* "
call java -jar Server/netvogue_web/target/dependency/webapp-runner.jar Server/netvogue_web/target/netvogueweb.war
