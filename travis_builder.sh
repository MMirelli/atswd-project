#!/bin/bash

mvn -f com.project.examsystem/pom.xml clean verify coveralls:report 
mvn -f com.project.examsystem/pom.xml clean package sonar:sonar 
