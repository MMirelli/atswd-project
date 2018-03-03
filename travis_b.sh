#!/bin/bash

mvn -f student-project/pom.xml clean verify coveralls:report 
mvn -f student-project/pom.xml clean compile sonar:sonar
mvn -f student-project/pom.xml clean verify -P IT-auto-docker-start-up