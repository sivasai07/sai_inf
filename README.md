# Sports Performance Monitoring

This Spring Boot application provides a platform for athletes, coaches, and administrators to manage sports performance and event participation.

**Features:**

* **Athlete Features:**
    * Register for events
    * Request coaching assistance
    * View event details
    * Communicate directly with coaches (new feature)
* **Coach Features:**
    * Accept or reject athlete requests
    * Communicate directly with athletes (new feature)
* **Administrator Features:**
    * Create events
    * Manage registrations
    * Publish results

**Technology Stack:**

* Spring Boot
* MySQL
* JWT (JSON Web Token) for authentication
* Spring Security for authorization
* HTML, CSS, and JavaScript for the frontend

**Dependencies:**

* Spring Boot Starter Data JPA
* Spring Boot Starter Security
* Spring Boot Starter Validation
* Spring Boot Starter Web
* MySQL Connector J
* Spring Boot Starter Test
* JWT (jjwt-api, jjwt-impl, jjwt-jackson)
* Spring Security Test
* Jakarta Persistence API
* Lombok

**Configuration:**

* **Application Properties:**
    * `spring.datasource.username`: **[Your MySQL username]** 
    * `spring.datasource.password`: **[Your MySQL password]** 
    * `spring.datasource.url`: `jdbc:mysql://localhost:3306/SportsManagement?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true` 
        * **Note:** Replace `SportsManagement` with your actual database name if different.

* You can find detailed configuration properties in the `application.properties` file.

**Getting Started:**

* Build and Run the application using `mvn spring-boot:run`

**Contact:**

For any questions or feedback, please contact Gokul Thakral at gokulthakral@gmail.com.
