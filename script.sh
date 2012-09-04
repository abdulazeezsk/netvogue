export NEO4J_REST_URL=http://localhost:7474/db/data
export NEO4J_LOGIN=""
export NEO4J_PASSWORD=""
echo " ************** Running Maven install ********* "
mvn clean install

echo " ************** Running the build using webapp runner ********* "
java -jar netvogue_web/target/dependency/webapp-runner.jar netvogue_web/target/netvogueweb.war
