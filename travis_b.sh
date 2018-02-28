#!/bin/bash

mvn -f com.project.examsystem/pom.xml clean verify -P auto-docker-start-up
