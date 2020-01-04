# Spring Boot Data Geode Example

## Description
This project contains the functions for the Spring Boot Data Geode client.

It uses:

- GEODE 1.9.2

## Build Application
Build the application jar using **mvn** like:

```
mvn clean package
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< io.pivotal:test >---------------------------
[INFO] Building test 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ test ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ test ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.3:compile (default-compile) @ test ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 2 source files to /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/target/classes
[INFO] /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/src/main/java/io/pivotal/test/server/function/GetMetricsFunction.java: Some input files use unchecked or unsafe operations.
[INFO] /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/src/main/java/io/pivotal/test/server/function/GetMetricsFunction.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ test ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.3:testCompile (default-testCompile) @ test ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ test ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ test ---
[INFO] Building jar: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/target/test-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.558 s
[INFO] Finished at: 2019-12-13T09:44:10-08:00
[INFO] ------------------------------------------------------------------------
```

## Start Locator and Servers
### Local
#### Start Locator and Servers with Configuration
Start and configure the locator and servers using the **startandconfigurelocal.sh** script like:

```
startandconfigurelocal.sh 
1. Executing - start locator --name=locator

....
Locator in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/locator on 192.168.1.4[10334] as locator is currently online.
Process ID: 95653
Uptime: 5 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/locator/locator.log
JVM Arguments: -Dgemfire.enable-cluster-configuration=true -Dgemfire.load-cluster-configuration-from-dir=false -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

Successfully connected to: JMX Manager [host=192.168.1.4, port=1099]

Cluster configuration service is up and running.

2. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

3. Executing - configure pdx --read-serialized=true

read-serialized = true
ignore-unread-fields = false
persistent = false
Changes to configuration for group 'cluster' are persisted.

4. Executing - start server --name=server-1 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

....
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-1 on 192.168.1.4[64406] as server-1 is currently online.
Process ID: 95772
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-1/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

5. Executing - start server --name=server-2 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

...
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-2 on 192.168.1.4[64428] as server-2 is currently online.
Process ID: 95791
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-2/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

6. Executing - start server --name=server-3 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

...
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-3 on 192.168.1.4[64455] as server-3 is currently online.
Process ID: 95799
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-3/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

7. Executing - start server --name=server-4 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

....
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-4 on 192.168.1.4[64486] as server-4 is currently online.
Process ID: 95804
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-4/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

8. Executing - list members

  Name   | Id
-------- | --------------------------------------------------------------
locator  | 192.168.1.4(locator:95653:locator)<ec><v0>:41000 [Coordinator]
server-1 | 192.168.1.4(server-1:95772)<v1>:41001
server-2 | 192.168.1.4(server-2:95791)<v2>:41002
server-3 | 192.168.1.4(server-3:95799)<v3>:41003
server-4 | 192.168.1.4(server-4:95804)<v4>:41004

9. Executing - deploy --jar=target/test-0.0.1-SNAPSHOT.jar

 Member  |      Deployed JAR       | Deployed JAR Location
-------- | ----------------------- | --------------------------------------------------------------------------------------------------
server-1 | test-0.0.1-SNAPSHOT.jar | /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-1/test-0.0.1-SNAPSHOT.v1.jar
server-2 | test-0.0.1-SNAPSHOT.jar | /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-2/test-0.0.1-SNAPSHOT.v1.jar
server-3 | test-0.0.1-SNAPSHOT.jar | /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-3/test-0.0.1-SNAPSHOT.v1.jar
server-4 | test-0.0.1-SNAPSHOT.jar | /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-4/test-0.0.1-SNAPSHOT.v1.jar

10. Executing - list functions

 Member  | Function
-------- | -------------------
server-1 | GetMetricsFunction
server-1 | UpdateTradeFunction
server-2 | GetMetricsFunction
server-2 | UpdateTradeFunction
server-3 | GetMetricsFunction
server-3 | UpdateTradeFunction
server-4 | GetMetricsFunction
server-4 | UpdateTradeFunction

11. Executing - create region --name=Trades --type=PARTITION_REDUNDANT

 Member  | Status | Message
-------- | ------ | --------------------------------------
server-1 | OK     | Region "/Trades" created on "server-1"
server-2 | OK     | Region "/Trades" created on "server-2"
server-3 | OK     | Region "/Trades" created on "server-3"
server-4 | OK     | Region "/Trades" created on "server-4"




Changes to configuration for group 'cluster' are persisted.

12. Executing - list regions

List of regions
---------------
Trades

13. Executing - create index --name=cusip_index --expression=cusip --region=/Trades

               Member                 | Status | Message
------------------------------------- | ------ | --------------------------
192.168.1.4(server-1:95772)<v1>:41001 | OK     | Index successfully created
192.168.1.4(server-2:95791)<v2>:41002 | OK     | Index successfully created
192.168.1.4(server-3:95799)<v3>:41003 | OK     | Index successfully created
192.168.1.4(server-4:95804)<v4>:41004 | OK     | Index successfully created




Changes to configuration for group 'cluster' are persisted.

14. Executing - list indexes

Member Name |               Member ID               | Region Path |    Name     | Type  | Indexed Expression | From Clause | Valid Index
----------- | ------------------------------------- | ----------- | ----------- | ----- | ------------------ | ----------- | -----------
server-1    | 192.168.1.4(server-1:95772)<v1>:41001 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true
server-2    | 192.168.1.4(server-2:95791)<v2>:41002 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true
server-3    | 192.168.1.4(server-3:95799)<v3>:41003 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true
server-4    | 192.168.1.4(server-4:95804)<v4>:41004 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true

************************* Execution Summary ***********************
Script file: config/startandconfigurelocal.gfsh


Command-1 : start locator --name=locator
Status    : PASSED




Command-2 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED




Command-3 : configure pdx --read-serialized=true
Status    : PASSED




Command-4 : start server --name=server-1 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-5 : start server --name=server-2 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-6 : start server --name=server-3 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-7 : start server --name=server-4 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-8 : list members
Status    : PASSED




Command-9 : deploy --jar=target/test-0.0.1-SNAPSHOT.jar
Status    : PASSED




Command-10 : list functions
Status     : PASSED




Command-11 : create region --name=Trades --type=PARTITION_REDUNDANT
Status     : PASSED




Command-12 : list regions
Status     : PASSED




Command-13 : create index --name=cusip_index --expression=cusip --region=/Trades
Status     : PASSED




Command-14 : list indexes
Status     : PASSED
```
#### Start Locator and Servers without Configuration
Start the locator and servers without configuration using the **startlocal.sh** script like:

```
startlocal.sh 
1. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

2. Executing - start locator --name=locator

....
Locator in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/locator on 192.168.1.4[10334] as locator is currently online.
Process ID: 97096
Uptime: 17 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/locator/locator.log
JVM Arguments: -Dgemfire.enable-cluster-configuration=true -Dgemfire.load-cluster-configuration-from-dir=false -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

Successfully connected to: JMX Manager [host=192.168.1.4, port=1099]

Cluster configuration service is up and running.

3. Executing - start server --name=server-1 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

...
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-1 on 192.168.1.4[64658] as server-1 is currently online.
Process ID: 97126
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-1/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

4. Executing - start server --name=server-2 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

...
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-2 on 192.168.1.4[64682] as server-2 is currently online.
Process ID: 97135
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-2/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

5. Executing - start server --name=server-3 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

...
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-3 on 192.168.1.4[64711] as server-3 is currently online.
Process ID: 97139
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-3/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

6. Executing - start server --name=server-4 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly

...
Server in /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-4 on 192.168.1.4[64743] as server-4 is currently online.
Process ID: 97149
Uptime: 4 seconds
Geode Version: 9.8.3
Java Version: 1.8.0_121
Log File: /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-4/cacheserver.log
JVM Arguments: -Dgemfire.default.locators=192.168.1.4[10334] -Dgemfire.start-dev-rest-api=false -Dgemfire.use-cluster-configuration=true -Dgemfire.statistic-archive-file=cacheserver.gfs -Dgemfire.enable-time-statistics=true -Dgemfire.conserve-sockets=false -Dgemfire.log-file=cacheserver.log -XX:NewSize=189m -XX:MaxNewSize=189m -Xms1897m -Xmx1897m -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:OnOutOfMemoryError=kill -KILL %p -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-core-9.8.3.jar:/Users/boglesby/Dev/GemFire/GitBuilds/gemfire983/geode-assembly/build/install/apache-geode/lib/geode-dependencies.jar

7. Executing - list members

  Name   | Id
-------- | --------------------------------------------------------------
locator  | 192.168.1.4(locator:97096:locator)<ec><v0>:41000 [Coordinator]
server-1 | 192.168.1.4(server-1:97126)<v1>:41001
server-2 | 192.168.1.4(server-2:97135)<v2>:41002
server-3 | 192.168.1.4(server-3:97139)<v3>:41003
server-4 | 192.168.1.4(server-4:97149)<v4>:41004

8. Executing - list functions

 Member  | Function
-------- | -------------------
server-1 | GetMetricsFunction
server-1 | UpdateTradeFunction
server-2 | GetMetricsFunction
server-2 | UpdateTradeFunction
server-3 | GetMetricsFunction
server-3 | UpdateTradeFunction
server-4 | GetMetricsFunction
server-4 | UpdateTradeFunction

9. Executing - describe region --name=Trades

Name            : Trades
Data Policy     : partition
Hosting Members : server-1
                  server-4
                  server-3
                  server-2




Non-Default Attributes Shared By Hosting Members  


  Type    |       Name       | Value
--------- | ---------------- | ---------
Region    | size             | 0
          | data-policy      | PARTITION
Partition | redundant-copies | 1





************************* Execution Summary ***********************
Script file: config/startlocal.gfsh


Command-1 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED




Command-2 : start locator --name=locator
Status    : PASSED




Command-3 : start server --name=server-1 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-4 : start server --name=server-2 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-5 : start server --name=server-3 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-6 : start server --name=server-4 --server-port=0 --enable-time-statistics=true --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.conserve-sockets=false --J=-Dgemfire.log-file=cacheserver.log --eviction-heap-percentage=75 --critical-heap-percentage=95 --J=-XX:NewSize=189m --J=-XX:MaxNewSize=189m --J=-Xms1897m --J=-Xmx1897m --J=-XX:CMSInitiatingOccupancyFraction=60 --J=-XX:+UseConcMarkSweepGC --J=-XX:+UseCMSInitiatingOccupancyOnly
Status    : PASSED




Command-7 : list members
Status    : PASSED




Command-8 : list functions
Status    : PASSED




Command-9 : describe region --name=Trades
Status    : PASSED
```
### PCFOne
Configure the servers using the **configurepcc.sh** script like:

```
configurepcc.sh
1. Executing - connect --url=https://cloudcache-8a2cc6fd-02e0-4a08-ae66-1a3d9566fa89.run.pcfone.io/gemfire/v1 --user=cluster_operator_DybzOYuxj2V1QWyfav6hQ --password=******** --skip-ssl-validation

Successfully connected to: GemFire Manager HTTP service @ https://cloudcache-8a2cc6fd-02e0-4a08-ae66-1a3d9566fa89.run.pcfone.io/gemfire/v1

2. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

3. Executing - list members

                      Name                       | Id
------------------------------------------------ | --------------------------------------------------------------------------------------
locator-672f0674-4972-449f-a9b7-fbbaa25a2e18     | 192.168.13.132(locator-672f0674-4972-449f-a9b7-fbbaa25a2e18:6:locator)<ec><v180>:56152
locator-6ca5d36b-b0dc-4cd8-887d-ea611f672d70     | 192.168.14.90(locator-6ca5d36b-b0dc-4cd8-887d-ea611f672d70:6:locator)<ec><v190>:56152
locator-79d74e75-201a-4479-a351-7795c4962d16     | 192.168.14.91(locator-79d74e75-201a-4479-a351-7795c4962d16:13:locator)<ec><v200>:56152
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | 192.168.14.92(cacheserver-db26021f-3861-4017-b838-16d9e62939fe:6)<v202>:56152
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | 192.168.14.93(cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140:6)<v204>:56152
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | 192.168.14.94(cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5:7)<v206>:56152
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | 192.168.14.95(cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92:7)<v208>:56152

4. Executing - deploy --jar=target/test-0.0.1-SNAPSHOT.jar

                     Member                      |      Deployed JAR       | Deployed JAR Location
------------------------------------------------ | ----------------------- | ---------------------------------------------------------
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar

5. Executing - list functions

                     Member                      | Function
------------------------------------------------ | -------------------
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | GetMetricsFunction
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | UpdateTradeFunction
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | GetMetricsFunction
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | UpdateTradeFunction
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | GetMetricsFunction
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | UpdateTradeFunction
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | GetMetricsFunction
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | UpdateTradeFunction

6. Executing - create region --name=Trades --type=PARTITION_REDUNDANT

                     Member                      | Status | Message
------------------------------------------------ | ------ | ------------------------------------------------------------------------------
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | OK     | Region "/Trades" created on "cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140"
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | OK     | Region "/Trades" created on "cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92"
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | OK     | Region "/Trades" created on "cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5"
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | OK     | Region "/Trades" created on "cacheserver-db26021f-3861-4017-b838-16d9e62939fe"




Changes to configuration for group 'cluster' are persisted.

7. Executing - list regions

List of regions
---------------
Trades

8. Executing - create index --name=cusip_index --expression=cusip --region=/Trades

                                   Member                                     | Status | Message
----------------------------------------------------------------------------- | ------ | --------------------------
192.168.14.92(cacheserver-db26021f-3861-4017-b838-16d9e62939fe:6)<v202>:56152 | OK     | Index successfully created
192.168.14.93(cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140:6)<v204>:56152 | OK     | Index successfully created
192.168.14.94(cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5:7)<v206>:56152 | OK     | Index successfully created
192.168.14.95(cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92:7)<v208>:56152 | OK     | Index successfully created




Changes to configuration for group 'cluster' are persisted.

9. Executing - list indexes

                  Member Name                    |                                   Member ID                                   | Region Path |    Name     | Type  | Indexed Expression | From Clause | Valid Index
------------------------------------------------ | ----------------------------------------------------------------------------- | ----------- | ----------- | ----- | ------------------ | ----------- | -----------
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | 192.168.14.93(cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140:6)<v204>:56152 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | 192.168.14.95(cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92:7)<v208>:56152 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | 192.168.14.94(cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5:7)<v206>:56152 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | 192.168.14.92(cacheserver-db26021f-3861-4017-b838-16d9e62939fe:6)<v202>:56152 | /Trades     | cusip_index | RANGE | cusip              | /Trades     | true

************************* Execution Summary ***********************
Script file: config/configurepcc.gfsh


Command-1 : connect --url=https://cloudcache-8a2cc6fd-02e0-4a08-ae66-1a3d9566fa89.run.pcfone.io/gemfire/v1 --user=cluster_operator_DybzOYuxj2V1QWyfav6hQ --password=tSsw94Rg4IA7vtLyK1Ow --skip-ssl-validation
Status    : PASSED




Command-2 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED




Command-3 : list members
Status    : PASSED




Command-4 : deploy --jar=target/test-0.0.1-SNAPSHOT.jar
Status    : PASSED




Command-5 : list functions
Status    : PASSED




Command-6 : create region --name=Trades --type=PARTITION_REDUNDANT
Status    : PASSED




Command-7 : list regions
Status    : PASSED




Command-8 : create index --name=cusip_index --expression=cusip --region=/Trades
Status    : PASSED




Command-9 : list indexes
Status    : PASSED
```
## Cleanup
### Local
Cleanup the servers using the **cleanuplocal.sh** script like:

```
./cleanuplocal.sh 
1. Executing - connect

Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=192.168.1.4, port=1099] ..
Successfully connected to: [host=192.168.1.4, port=1099]

2. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

3. Executing - undeploy

 Member  |     Un-Deployed JAR     | Un-Deployed From JAR Location
-------- | ----------------------- | --------------------------------------------------------------------------------------------------
server-1 | test-0.0.1-SNAPSHOT.jar | /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-1/test-0.0.1-SNAPSHOT.v2.jar
server-2 | test-0.0.1-SNAPSHOT.jar | /Users/boglesby/Dev/Tests/spring-boot/long_running_test/server/server-2/test-0.0.1-SNAPSHOT.v2.jar

4. Executing - list functions

No Functions Found

5. Executing - destroy region --name=Trades

 Member  | Status | Message
-------- | ------ | ---------------------------------------
server-1 | OK     | Region '/Trades' destroyed successfully
server-2 | OK     | Region '/Trades' destroyed successfully

6. Executing - list regions

No Regions Found

************************* Execution Summary ***********************
Script file: cleanuplocal.gfsh


Command-1 : connect
Status    : PASSED




Command-2 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED




Command-3 : undeploy
Status    : PASSED




Command-4 : list functions
Status    : PASSED




Command-5 : destroy region --name=Trades
Status    : PASSED




Command-6 : list regions
Status    : PASSED
```
### PCFOne
Cleanup the servers using the **cleanuppcc.sh** script like:

```
./cleanuppcc.sh 
1. Executing - connect --url=https://cloudcache-8a2cc6fd-02e0-4a08-ae66-1a3d9566fa89.run.pcfone.io/gemfire/v1 --user=cluster_operator_DybzOYuxj2V1QWyfav6hQ --password=******** --skip-ssl-validation

Successfully connected to: GemFire Manager HTTP service @ https://cloudcache-8a2cc6fd-02e0-4a08-ae66-1a3d9566fa89.run.pcfone.io/gemfire/v1

2. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

3. Executing - undeploy

                     Member                      |     Un-Deployed JAR     | Un-Deployed From JAR Location
------------------------------------------------ | ----------------------- | ---------------------------------------------------------
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | test-0.0.1-SNAPSHOT.jar | /var/vcap/store/gemfire-server/test-0.0.1-SNAPSHOT.v1.jar

4. Executing - list functions

No Functions Found

5. Executing - destroy region --name=Trades

                     Member                      | Status | Message
------------------------------------------------ | ------ | ---------------------------------------
cacheserver-8317e6d4-3912-4f7c-92ac-f01146875140 | OK     | Region '/Trades' destroyed successfully
cacheserver-8693e679-ee85-4ed3-b12c-819e6e81de92 | OK     | Region '/Trades' destroyed successfully
cacheserver-c7af346f-d341-48f6-b2c5-021485e29dc5 | OK     | Region '/Trades' destroyed successfully
cacheserver-db26021f-3861-4017-b838-16d9e62939fe | OK     | Region '/Trades' destroyed successfully

6. Executing - list regions

No Regions Found

************************* Execution Summary ***********************
Script file: cleanuppcc.gfsh


Command-1 : connect --url=https://cloudcache-8a2cc6fd-02e0-4a08-ae66-1a3d9566fa89.run.pcfone.io/gemfire/v1 --user=cluster_operator_DybzOYuxj2V1QWyfav6hQ --password=tSsw94Rg4IA7vtLyK1Ow --skip-ssl-validation
Status    : PASSED




Command-2 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED




Command-3 : undeploy
Status    : PASSED




Command-4 : list functions
Status    : PASSED




Command-5 : destroy region --name=Trades
Status    : PASSED




Command-6 : list regions
Status    : PASSED
```
## Shutdown
### Local
Shutdown the servers and locator using the **shutdownlocal.sh** script like:

```
./shutdownlocal.sh 

(1) Executing - connect

Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=192.168.1.4, port=1099] ..
Successfully connected to: [host=192.168.1.4, port=1099]


(2) Executing - shutdown --include-locators=true

Shutdown is triggered
```