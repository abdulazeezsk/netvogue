export env=local
mvn clean install
kill $(ps -eo pid,command | grep "netvogueweb-customer-service-1.0-SNAPSHOT.jar" | grep -v grep | awk '{print $1}')
java java -jar target/netvogue-customer-service-1.0-SNAPSHOT.jar &
