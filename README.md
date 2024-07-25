# UI Automation Test exercise Airalo

# Run test instructions:

1. Clone repository using the following command:
   git clone https://github.com/vojtenkor/airaloTestUI.git
2. Navigate to project directory using command line
3. Build project using the following command: 
   mvn clean install
4. Run all test using the following command:
   mvn test -Dtest="AllTests"
5. To run only UI test use following command:
   mvn test -Dtest="ValidateESimOptions"
6. To run only API tests use following command:
   mvn test -Dtest="ApiTests"

# Approach for tests implementation:

# UI test:

- Test created using Java and Selenium
- Created set of pages, that contains element locators and methods
- ValidateESimOptions class with junit annotation @Test contains calls of methods from pages and validation steps

# API test:

- Tests created using Java HTTP Client and WebSocket APIs
- Created set of helpers classes
- ApiTests class with junit annotation @Test contains calls of methods from helpers and validation steps