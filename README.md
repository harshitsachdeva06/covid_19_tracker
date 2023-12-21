****Covid19 Tracker Application Spring Boot, Hibernate and Thymeleaf, MySql****

Prerequisites

1. JDK 11 and JAVA_HOME environment variable set
Database

2. Install local mysql server -v 8.0.25, then create a user with credentials (you can use workbench ui) and specify spring.datasource.username and spring.datasource.password in application.properties file. On building the project, the DB should be created automatically

3. **Building the project**

    Clone the repository:
    
    git clone https://github.com/purshink/Spring-Boot-Thymeleaf-App
    Navigate to the newly created folder:
    
    cd Spring-Boot-Thymeleaf-App
    Run the project with:
    
    ./mvnw clean spring-boot:run
    Or on Windows:
    
    mvnw.cmd clean spring-boot:run
    Navigate to:
    
    http://localhost:8080

