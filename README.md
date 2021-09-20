# example_Java_Maven_Springboot_Jenkins_DockerHub


to setup docker use https://www.jenkins.io/doc/book/installing/docker/

but need to add the following to the DockerFile

```
### Install sudo, ant, and ivy
RUN apt-get install -y sudo && apt-get install -y maven && apt-get install -y ivy
### Create a test USER
RUN useradd -rm -d /home/test -s /bin/bash -g root -G sudo -u 1001 test
USER test
WORKDIR /home/test
```


setup credentials to be used for Docker https://www.jenkins.io/doc/book/using/using-credentials/




run with: ./mvnw spring-boot:run or mvnw spring-boot:run mvnw spring-boot:run or mvnw spring-boot:run mvn clean install spring-boot:run

curl localhost:8090

curl -X POST localhost:8090/api/addCompany -d @data.json -H "Content-Type: application/json"

curl -X POST localhost:8090/api/addCompany -d "{"id":2,"name":"you"}" -H "Content-Type: application/json"

curl -X GET localhost:8090/api/company/1
