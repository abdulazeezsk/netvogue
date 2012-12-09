export NEO4J_REST_URL=http://localhost:7474/db/data
export NEO4J_LOGIN=""
export NEO4J_PASSWORD=""
export env=local
mvn clean install
kill $(ps -eo pid,command | grep "netvogueweb.jar" | grep -v grep | awk '{print $1}')
java -jar target/netvogueweb.jar &
