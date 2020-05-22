# Final Project
Last project in Coders Lab

## About
Generally speaking the aim of application is to find the shortest route between given addresses. 
Although it was designed mainly for  delivering packages or goods, it can be used by anyone who wants to move fast and economic.

## Before start
Final project uses Google APIs. Before you start using application make sure that you have access to the following items:
* Maps JavaScript API
* Distance Matrix API
* Geocoding API

## Quick configuration
Below you find steps to run application:
1. Download files from GitHub.
2. In file *persistence.xml* set your own properties **user** and **password** to have access to your database.
    ```
    <property name="javax.persistence.jdbc.user" value=""/>
    <property name="javax.persistence.jdbc.password" value=""/> 
    ```
3. Create a new database. You can use code below.
    ```
    create database final_project
    character set utf8mb4
    collate utf8mb4_unicode_ci;
    ```
    Instead using *final_project* name, you can use your own name, but you have to change this line in file *persistence.xml* properly:
    ```
    <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/final_project?serverTimezone=UTC"/>
   ```
4. Create connection with your local server. 
5. After first running, tables for every entity should be automatically created. Run below code in your database console:
    ```
    INSERT INTO roles (id, name) VALUES (NULL, 'ROLE_USER');
    ```
6. Put your own API_KEY for Google APIs in the following places:
    * in class **DistanceMatrixGenerator**
      ```
      private static final String API_KEY = "";
      ```
    * in class **CoordinatesManager**
      ```
      private static final String API_KEY = "";
      ```
    * in **route-details.jsp**
      ```
      <script src="https://maps.googleapis.com/maps/api/js?key=API_KEY&callback=initMap"></script>
      ```
7. Enjoy using application!