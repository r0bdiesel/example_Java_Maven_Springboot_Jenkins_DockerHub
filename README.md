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
