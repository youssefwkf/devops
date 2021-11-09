FROM maven:3.8.2-jdk-8
copy ./target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.1.jar Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.1.jar
CMD ["java","-jar","Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.1.jar"]
