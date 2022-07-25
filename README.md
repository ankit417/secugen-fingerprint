# Secugen fingerprint REST API

spring-boot rest api for secugen fingerprint scanner.

- Capture and Verify fingerprints

## Project Setup

- Install JDK_17.02 or above
- Add Java to System Variables
  - Add java path
  - Add JAVA_HOME
- Install Secugen WBFD driver

- Copy secugen DLL files to windows

## Running Project

```
mvn spring-boot:run
```

http://localhost:9090/fingerprint \
Method : Get

Creates a new fingerprint. It will return base64 image of the fingerprint and also the Minutae data which can be stored in database to verify fingerprints.

http://localhost:9090/verify \
Method: Post\
body: fingerprint data\
Verifies fingerprint. You need to pass minutae data saved in database to verify fingerprint.

## Sources

https://secugen.com

## Documentation

[Software Installation and Drivers](https://webapi.secugen.com/docs/SECUGEN_WEB_SERVICE_API_DOC.pdf)\
[Downloads](https://secugen.com/drivers/)\
[Guides](https://secugen.com/guides/)
