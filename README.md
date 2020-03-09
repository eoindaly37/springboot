## Tutorial
This is our first project received at pivotal. It is based of the rest tutorial that is on GitHub by spring boot

## Motivation
We are doing this as a good introduction to learning spring boot.


## Screenshots
Include logo/demo screenshot etc.

## Tech/framework used
In this we used Spring Boot, Rest API, Hibernate, JPA, Lombok and HATEAOS

## Features
It contains more than the standard tutorial in that the Employee class has an attribute of class Address. There is also a company class. These are all persisted to a MySQL database using JPA and Hibernate.

## Installation
If you download the project you are able to run it locally with
```
./mvnw clean spring-boot:run
```
To push it and run it on PCF you will need to compile it to a jar file
```
./mvnw clean spring-boot:run
```


## Tutorial Reference

[This is the reference to the tutorial](https://https://github.com/spring-guides/tut-rest)


## How to use?
Using the maven wrapper you can run the application automatically if you have the database set up. You can curl to enter more entries using HATEOAS


## Credits
This project was created in March 2020 by Eoin Daly, Kristian Kan and Karol Pawlak 

