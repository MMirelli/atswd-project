FROM openjdk:8

COPY /target/student-project-0.0.1-SNAPSHOT-all.jar /app/student-app.jar

WORKDIR /app

CMD ["java", "-jar", "student-app.jar", "mysql-host"]
