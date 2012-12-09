cd netvogue-master-pom
mvn clean install
cd ../netvogue-restfulservices-master-pom
mvn clean install
cd ../ecommerce-domain-model
mvn clean install
cd ../ecommerce-persistence
mvn clean install
cd ../netvogue-rest-services-utils
mvn clean install
cd ../netvogue-ecommerce-persistence-mongo
mvn clean install -DskipTests=true
cd ../Server/
mvn clean install
cd ../netvogue-customer-service
mvn clean install
export NEO4J_REST_URL=http://localhost:7474/db/data
export NEO4J_LOGIN=""
export NEO4J_PASSWORD=""
export env=local
kill $(ps -eo pid,command | grep "netvogueweb-customer-service-1.0-SNAPSHOT.jar" | grep -v grep | awk '{print $1}')
java -jar target/netvogue-customer-service-1.0-SNAPSHOT.jar &
kill $(ps -eo pid,command | grep "netvogueweb.jar" | grep -v grep | awk '{print $1}')
java -jar ../Server/netvogue_web/target/netvogueweb.jar &
