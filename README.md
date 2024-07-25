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