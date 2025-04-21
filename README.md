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
 Clone the repository using this URL
git clone https://github.com/KARTHIKA1278/CTS_Project_Automation.git
cd CTS_Project_Automation

Import Project in IDE
Open IntelliJ
Import as Maven Project.

Build the Project
mvn clean install

 How to Run Tests
Option 1: From Command Line
mvn test

Option 2: From IDE
Go to the runners package.
Open TestRunner.java.
Right-click and choose Run.

📂 Test Reports: (I have configure extend reports)
After execution, view the report here:
test-output/index.html
Open this file in a browser for a visual report summary.

Cucumber report:
file:///C:/Users/karth/IdeaProjects/CTS_Project/target/cucumber-reports.html
















