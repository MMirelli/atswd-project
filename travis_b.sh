#!/bin/bash

mvn -f student-project/pom.xml clean verify -Pcode-analysis coveralls:report sonar:sonar 
mvn -f student-project/pom.xml clean verify -P IT-auto-docker-start-up