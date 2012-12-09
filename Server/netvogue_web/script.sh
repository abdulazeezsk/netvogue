export NEO4J_REST_URL=http://localhost:7474/db/data
export NEO4J_LOGIN=""
export NEO4J_PASSWORD=""
export env=local
mvn clean install
java -jar target/netvogueweb.jar 
