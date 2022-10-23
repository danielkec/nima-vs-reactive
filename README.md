# Nima-vs-Reactive

Sample Helidon SE/Nima/MP project that includes multiple REST operations.

## Build and run
With JDK19+
```bash
mvn package
java --enable-preview -jar ./target/nima-vs-reactive.jar
```

## Exercise the application

Nima
```
curl -X GET http://localhost:8080/callOtherService
BLOCKING_IS_COOL

curl -X GET http://localhost:8080/mining
Got no bitcoins, but burnt lot of CPU time on Thread[#115,pool-1-thread-1,5,main]!
```
Helidon SE (reactive)
```
curl -X GET http://localhost:8081/callOtherService
BLOCKING_IS_COOL

curl -X GET http://localhost:8081/mining
Got no bitcoins, but burnt lot of CPU time on Thread[#117,pool-2-thread-1,5,main]!
```
Helidon MP
```
curl -X GET http://localhost:8082/callOtherService
BLOCKING_IS_COOL

curl -X GET http://localhost:8082/mining
Got no bitcoins, but burnt lot of CPU time on Thread[#58,helidon-server-2,5,server]!

curl -X GET http://localhost:8082/miningOffloaded
Got no bitcoins, but burnt lot of CPU time on Thread[#79,ft-mp-1,5,ft-mp-thread-pool-2]!
```