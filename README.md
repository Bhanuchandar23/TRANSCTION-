# TRANSCTION- 
java JDBC Bank Application
This is a simple console-based banking application developed using Java JDBC (Java Database Connectivity) to interact with a MySQL database. 
The application allows users to log in, check their account balance, and transfer funds to another account.

Getting Started
To run this application, you need to have the following prerequisites:

Java Development Kit (JDK): Ensure that you have JDK installed on your system.
You can download it from the official Oracle website or use OpenJDK.

MySQL Database: Install and configure MySQL on your machine.
You can download MySQL Community Server from the official MySQL website.

MySQL Connector/J: Download the MySQL Connector/J JDBC driver from the official MySQL website and include it in your project's classpath.
IDE (Integrated Development Environment): You can use any Java IDE of your choice, such as IntelliJ IDEA, Eclipse, or NetBeans.
Setup

Database Setup:
Create a MySQL database named banksbi.
Create a table named account with columns Acc_num, Name, pin, and balance.






Application Configuration:

Update the url, USER_NAME, and PASSWORD variables in the BankApp class with your MySQL database connection details.
Running the Application
Compile the BankApp.java file using your Java compiler:
bash
Copy code
javac BankApp.java
Run the compiled Java class:
bash
Copy code
java BankApp
Follow the on-screen instructions to interact with the application.
Features
Login Module: Users can log in using their account number and PIN.
Check Balance: Users can view their account balance after logging in.
Transfer Module: Users can transfer funds to another account.
Transaction Handling: The application uses transaction management to ensure data consistency during fund transfers.
Contributing


