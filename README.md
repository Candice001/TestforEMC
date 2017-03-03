# TestforEMC

For test3, I create a gradle project named emc-test.
Use Jersey framework to implement a storage array simulator .
Env requirement:
jersey 2.19
jdk1.8
tomcat8

I have uploaded the souce_code and packed it as cit-test.war.
You can copy this war file to your local tomcat location, whatever windows or linux
e.g. $tomcat_home/webapps.

Then cd ../bin
./startup

The corresponding rest api list below:
1. create a lun with id and size:
GET: localhost:8080/emc-test/api/v1.0/createlun?id=1&lunSize=100

2. resize a lun with id an size
GET: localhost:8080/emc-test/api/v1.0/resizelun?id=1&lunSize=200

3. remove a lun with lun id
GET: localhost:8080/emc-test/api/v1.0/removelun?id=1

4. export a lun to a host
step1: GET: localhost:8080/emc-test/api/v1.0/createlun?id=1&lunSize=200
step2: GET: localhost:8080/emc-test/api/v1.0/export?id=1&hostName=bej102030.us.emc.com

5. Un-export a lun
GET: localhost:8080/emc-test/api/v1.0/unexport?id=1

6. retrieve information of a lun with id 
GET: localhost:8080/emc-test/api/v1.0/retrieve?id=1

7. persist a lun to database
GET: localhost:8080/emc-test/api/v1.0/persist

