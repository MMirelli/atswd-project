#!/bin/bash

mvn -f student-project/pom.xml clean verify coveralls:report 
mvn -f student-project/pom.xml clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar
mvn -f student-project/pom.xml clean verify -P IT-auto-docker-start-up