FROM openjdk:13
ADD target/Intern-REST-API.jar Intern-REST-API.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "Intern-REST-API.jar"]