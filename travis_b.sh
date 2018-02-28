#!/bin/bash

mvn -f com.project.examsystem/pom.xml clean verify coveralls:report 
mvn -f com.project.examsystem/pom.xml clean compile sonar:sonar
mvn -f com.project.examsystem/pom.xml clean verify -P auto-docker-start-up
