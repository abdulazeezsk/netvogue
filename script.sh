cd netvogue-master-pom
mvn clean install
cd ../netvogue-restfulservices-master-pom
mvn clean install
cd ../Server/
mvn clean install
export NEO4J_REST_URL=http://localhost:7474/db/data
export NEO4J_LOGIN=""
export NEO4J_PASSWORD=""
export env=local
kill $(ps -eo pid,command | grep "netvogueweb.war" | grep -v grep | awk '{print $1}')
java -jar ../Server/netvogue_web/target/dependency/webapp-runner.jar ../Server/netvogue_web/target/netvogueweb.war