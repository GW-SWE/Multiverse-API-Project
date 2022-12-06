## Initial Project Template for GW SWE Team Project

#### Project uses: 
* Maven
* JDK 17
* Spring Boot
* Spring Security
* mySQL Database

#### Manually added dependencies of:

* Gson
* Bcrypt
* Dotenv-Java

#### Existing Functionality

* User table stored in MySQL Database
* User model contains hashing of password on creation of a new user utilising Bcrypt through Spring Security
* Full CRUD routing with Authentication on get, put and delete routes.
* Azure hosting and web based mySQL Hosting
* ~~Authentication methods are contained within AuthenticationService class~~
* ~~Junit Tests are in place to check functionality of Authentication Service methods~~ removed after refactor to Spring Security
