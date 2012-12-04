export NEO4J_REST_URL=http://localhost:7474/db/data
export NEO4J_LOGIN=""
export NEO4J_PASSWORD=""
echo " ************** Running Maven install ********* "
mvn clean install

export env=local
java -jar target/netvogueweb.jar 
