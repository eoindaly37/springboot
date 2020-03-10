FROM openjdk:13
ADD target/employee-mysql.jar employee-mysql.jar
EXPOSE 8282
ENTRYPOINT ["java", "-jar", "employee-mysql.jar"]