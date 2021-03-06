# APT project AY 17-18 
[![Build Status](https://travis-ci.org/MMirelli/atswd-project.svg?branch=master)](https://travis-ci.org/MMirelli/atswd-project) [![Coverage Status](https://coveralls.io/repos/github/MMirelli/atswd-project/badge.svg?branch=master)](https://coveralls.io/github/MMirelli/atswd-project?branch=master) [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=it.fi.mirelli%3Astudent-project&metric=alert_status)](https://sonarcloud.io/dashboard?id=it.fi.mirelli%3Astudent-project)

##  Introduction

This project is thought to be a small and simplified demonstration of the power of JPA and Hibernate technologies.   It offers an easy  interface to communicate with SQL  DBs, implementing the MVC pattern. The final jar file, produced by the following building instructions, gives evidence of the application correct behaviour in persisting and fetching data to and from a MySQL database image, running on a Docker container, as an end-to-end integration test. 

The main development technique explored in this project is **Test Driven Development** (*TDD*), which was continuously monitored with the support of useful tools such as Travis CI, Coveralls, SonarCloud. These, together with other technologies employed during the development, guarantee a high-quality and fully-tested deliverable.   

The project was conducted as an individual project for the [Advanced Programming Techniques](https://www.unifi.it/p-ins2-2017-480807-0.html) master's level course, held by professor L. Bettini in 2017-18 at [Università degli Studi di Firenze](https://www.unifi.it), Florence, Italy. 

##  Building

###	Requirements

Here is what you need to successfully build the project, we assume your OS is a Linux Derivative:

- The latest [docker-ce](https://docs.docker.com/install/linux/docker-ce/ubuntu/) version;

- The latest maven version:

	```
	$ sudo apt-get install maven
	```
	
- The latest git version git:

	```	
	$ sudo apt-get install git
	```

- JDK 8 (we remind you to set JAVA_HOME so as to it references your JDK installation folder) . 


### Step by step instructions

	 
1. Activate the docker daemon:

	```	
	$ sudo systemctl start docker
	``` 


2. Clone our repository:
	
	```	
	$ git clone https://github.com/MMirelli/atswd-project.git
	```
	
3. Move to the folder `atwsd-project/student-project` and just run (you may need su permissions due to docker daemon):

	```	
	$ cd atwsd-project/student-project && sudo ./run.sh
	``` 
## What does `run.sh` should do?

The script simply starts a maven build: it executes the maven `clean` lifecycle and `default` one until the `verify` phase, using the `docker-compose-runner` maven profile. This builds the jar of the application, with all its dependencies, and starts the `docker-compose.yml`. For another thing the compose file will execute the `student-project/Docker/Dockerfile`, generating the `student-app` image. In conclusion the app docker container will be linked to a different docker container, running a MySQL docker image.
At the end of the build this is the expected behaviour:	

![](pics/compose-success.png)


