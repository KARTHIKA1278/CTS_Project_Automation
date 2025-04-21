This project contains automated UI tests for the TodoMVC React App using Java, Selenium WebDriver, Cucumber(BDD), TestNG, and WebDriverManager.

📂 GitHub Repository: github.com/KARTHIKA1278/CTS_Project_Automation

CTS_Project_Automation
├── src
│   ├── main
│   │   └── java
│   │       └── utils             # DriverManager and utility classes
│   └── test
│       └── java
│           ├── stepdefs           # Cucumber Step Definitions
│           ├── runners           # TestNG + Cucumber Runner
│           └── features          # .feature files
├── pom.xml                       # Project dependencies and build config


Prerequisites
Java 17 or higher
Maven 3.0 or higher
Intellij IDea

 Setup Instructions
 1.Clone the repository using the below command 
2. Launch cmd prompt Execute this command -git clone https://github.com/KARTHIKA1278/CTS_Project_Automation.git
3. TO ensure the project is cloned successfully execute this commandd - cd CTS_Project_Automation 

Example:
Cloning into 'CTS_Project_Automation'...
remote: Enumerating objects: 123, done.
remote: Counting objects: 100% (123/123), done.
remote: Compressing objects: 100% (100/100), done.
remote: Total 123 (delta 12), reused 120 (delta 11), pack-reused 0
Receiving objects: 100% (123/123), 1.23 MiB | 1.00 MiB/s, done.
Resolving deltas: 100% (12/12), done.

Repository 'CTS_Project_Automation' cloned successfully.
You can now navigate into the project folder using:
   cd CTS_Project_Automation
   
    
  4.Build the Project using this command 
mvn clean install
After executing, Maven will clean, compile, test, and install your project. If everything works correctly, you wil see a "BUILD SUCCESS" message in the terminal.


** How to Run Tests**
Option 1: From IDE
Go to the runners package.
Open TestRunner.java.
Right-click and choose Run.

Option 2: From Command Line
mvn test

Test Reports: (I have configure extend reports)
After execution, view the report here:
test-output/index.html
Open this file in a browser for a visual report summary.

Cucumber report:sample report location
file:///C:/Users/karth/IdeaProjects/CTS_Project/target/cucumber-reports.html
















