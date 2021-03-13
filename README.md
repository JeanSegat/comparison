# Assignment Scalable Web

##Goal
The goal of this assignment is to show your coding skills and what you value in software
engineering. We value new ideas so next to the original requirement feel free to
improve/add/extend.

We evaluate the assignment depending on your role (Developer/Tester) and your level of
seniority.

##The assignment
* Provide 2 http endpoints that accepts JSON base64 encoded binary data on both
endpoints
    * <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
* The provided data needs to be diff-ed and the results shall be available on a third end
point
    * <host>/v1/diff/<ID>
* The results shall provide the following info in JSON format
    * If equal return that 
    * If not of equal size just return that
    * If of same size provide insight in where the diffs are, actual diffs are not needed.
        * So mainly offsets + length in the data
* Make assumptions in the implementation explicit, choices are good but need to be
communicated

##Must haves
* Solution written in Java
* Internal logic shall be under unit test
* Functionality shall be under integration test
* Documentation in code
* Clear and to the point readme on usage

##Nice to haves
* Suggestions for improvement


#Running the project

Clone the repository from GutHub: 

```
 https://github.com/JeanSegat/comparison
```

After cloning run the following command: 
* You have to have the Maven Spring Boot plugin.
```
 mvn spring-boot:run
```

the project, it is necessary to have the Maven Spring Boot plugin

#Testing the project

Inside of project structure, there is a Postman collection. You can import it on Postman app and have fun.

Other way to test the project is via Swagger-UI. For it, on Browser access the following URL:
```
http://localhost:8080/swagger-ui.html
```

Do not forget to run the server before testing. 

#Improvements

* Refactoring the MVP developing to be more generic and accept more kind of files.
* Create a connection to real database and keep the memory database just for test.
* As the project is based on clean architecture, change the packages structure to modules.
* To be scalable should implement some orchestration
* Create a CI/CD