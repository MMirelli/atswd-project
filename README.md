# APT project AY 17-18 
[![Build Status](https://travis-ci.org/MMirelli/atswd-project.svg?branch=master)](https://travis-ci.org/MMirelli/atswd-project) [![Coverage Status](https://coveralls.io/repos/github/MMirelli/atswd-project/badge.svg?branch=master)](https://coveralls.io/github/MMirelli/atswd-project?branch=master) [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=it.fi.mirelli%3Astudent-project&metric=alert_status)](https://sonarcloud.io/dashboard?id=it.fi.mirelli%3Astudent-project)

##  Introduction

This project is thought to be a small and simplified demonstration of the power of JPA and Hibernate technologies.   It offers an easy  interface to communicate with SQL  DBs, implementing the MVC pattern. The final jar file, produced by the following building instructions, gives evidence of the application correct behaviour in persisting and fetching data to and from a MySQL database image, running on a Docker container, as an end-to-end integration test. 

##  Building instructions

Here is what you need to successfully run the project, we assume your OS is a Linux Derivate:

1. Latest version of [docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/);

2. JDK-8 (we remember to set JAVA_HOME so as to it references your JDK installation folder) ; 

3. Latest maven version:

	```
	$ sudo apt-get install maven
	``` 
	
4. Activate the docker daemon; 

	```	
	$ sudo systemctl start docker
	``` 

5. Latest git version git:

	```	
	$ sudo apt-get install git
	``` 

5. Clone our repository:
	
	```	
	$ git clone https://github.com/MMirelli/atswd-project.git
	``` 
	
6. Move to the folder `atwsd-project/student-project` and just run (you may need su permissions due to docker daemon):

	```	
	$ cd atwsd-project/student-project && sudo ./run.sh
	``` 
## What does `run.sh` do?

The script simply starts a maven build: it executes a maven `clean` lifecycle and `default` until `verify` phase, using the `docker-compose-runner` maven profile. This builds the jar of the application and its dependencies and starts the `docker-compose.yml`. In conclusion this file will execute the `Docker/Dockerfile` generating and linking the `student-app` image to MySQL docker image, running on different docker container.
	



