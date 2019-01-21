# bingo
This is a Proof of Concepts on Api Automation using Java,Junit


- This is a Maven project which will call the virgin game api get service and validate the response received.
-  Main Class which will validate the api response is ValidateApiResponse.java under com.validate Package.
- GetResponse.java under com.response package will create a HttpurlConnection ,validate and 
   fetch the response from api and return response body as string to ValidateApiResponse.java.
- Two Test cases Validated by below test methods as required
```
  shouldReturn300000WhendefaultGameFrequencyValidated()
  shouldReturnTrueWhenstartTimeIsFuture()
```  
-  Config.properties file will store the url and operation details under resources folder.
- Constants.Java file will store Constants across the Project
- Log4J implemented to log the information and the error occurred in the project.
- pom.xml will store all the maven dependencies
- BingoGamesApiValidationScreenshot is the attached screen shot of successful execution of test cases along with logs displayed.
