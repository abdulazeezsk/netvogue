server="/Azeez/code/netvoguegit/Server"
jboss="/Azeez/Softwares/jboss-as-7.1.1.Final"

cd $server
echo " ************** Running Maven install ********* "
mvn clean install

echo " ************** Copying the build to JBoss Server ********* "
cp netvogue_web/target/netvogueweb.war $jboss/standalone/deployments/
