FROM massimilianomirelli/ubuntu16.04-openjdk8

ARG fatjarName

COPY /maven/$fatjarName /app/student-app.jar

WORKDIR /app

CMD ["java", "-jar", "student-app.jar", "mysql-host"]
