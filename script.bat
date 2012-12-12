cd netvogue-master-pom
call mvn clean install
cd ../netvogue-restfulservices-master-pom
call mvn clean install
cd ../ecommerce-domain-model
call mvn clean install
cd ../ecommerce-persistence
call mvn clean install
cd ../netvogue-rest-services-utils
call mvn clean install
cd ../netvogue-ecommerce-persistence-mongo
call mvn clean install -DskipTests=true
cd ../Server/
call mvn clean install
cd ../netvogue-customer-service
call mvn clean install
call SET NEO4J_REST_URL=http://localhost:7474/db/data
call SET NEO4J_LOGIN=""
call SET NEO4J_PASSWORD=""
call SET env=local
call java -jar ../Server/netvogue_web/target/netvogueweb.jar