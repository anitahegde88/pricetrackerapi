Point1: Assumptions :
 -------------
1) Aim is to demonstrate the functionality within the constraint of time vs full product delivery in an actual project.  
2) Frequency for price check is in minutes. Note: To make the API work for other input frequencies such as morning, afternoon, hourly etc. existing frequency parser can be easily enhanced further.
3) Incoming request parameters validation is limited to only default bean and Lombok validations. (i.e. Not implemented custom validator to validate incoming request parameters).
4) Instead of email service, displayed email content on console.

Point2 : 
----------
Solution diagram is attached in email.
   
Point3: I have used H2 in memory database to store user/incoming details
-----------
Steps to install H2 database:
Open the project in IntelliJ (clean and install maven if required)
Click on database icon that you can see right side of the editor 
click on + sign -> Data source -> H2
enter these details(these details are available in properties file as well) :
spring.datasource.url=jdbc:h2:mem:pricetrackerdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=nhsbsa
spring.datasource.password=
Once database is created, create a USERS table in it. To do that:
 1) go to file under resources folder : src/main/resources/dbscripts/create_users_table.sql  
 2) execute the sql on H2 console 
Now you should be able to see USERS table in H2 DB right side of the editor

Point4:
----------
URL to trigger the endpoint is : http://localhost:8080/product-price-tracker/scheduler

Point5: 
----------
Actual price of the product is stored in a JSON file at - src/main/resources/jsonfiles/actual_price.json

Point6:
----------
Sample request body value to use in Postman :  

{   "productUrl": "abc",
    "desiredPrice": 15,
    "frequency": "1m"
}

Point7:
---------
Sample request header value (anything in string format) of this endpoint is :
nhsuser@nhsbsa.nhs.uk

Point8:
---------
Postman API key and token(available in properties file as well) :

key :  pricetracker-key
value :  pricetrackercode

Point9:
-------
Some Lombok annotations are not working (tried changing the dependency version, maven clean and update etc. but didn't work). So getter,setters,constructors are implemented explicitly. Also, Log4J annotation is not working, so sent output to console.


Solution:
-----------

![image](https://github.com/user-attachments/assets/0b5d38b8-204e-4551-bd51-7e25f3eab024)


Code architecture:
-----------------
![image](https://github.com/user-attachments/assets/85d2639f-ee68-4000-930d-d35e11d10406)



