mvn test -Dtest=RunCucumberTest "-Dcucumber.filter.tags=@NewGame"
mvn test -Dtest=RunCucumberTest "-Dcucumber.filter.tags=@AddPlayer"
mvn test -Dtest=RunCucumberTest "-Dcucumber.filter.tags=@StartGame"
mvn test -Dtest=RunCucumberTest "-Dcucumber.filter.tags=@CanStep"
