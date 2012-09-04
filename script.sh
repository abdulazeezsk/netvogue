server="Server"

cd $server
echo " ************** Running Maven install ********* "
mvn clean install

echo " ************** Running the build using webapp runner ********* "
java -jar netvogue_web/target/dependency/webapp-runner.jar netvogue_web/target/netvogueweb.war
