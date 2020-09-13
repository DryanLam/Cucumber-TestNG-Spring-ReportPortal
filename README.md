# Cucumber Spring TestNG ReportIO
### Introduce
This framework supports for automated test both Web UI & Web services using Cucumber BDD, Java Spring using TestNG as an unit testing framework. Generate cucumber report (json, html) and a configuration to integrate with ReportPortal



### Services
- Cucumber BDD
- Java & Groovy languages
- Maven package managament
- Spring Framework 
- Web UI testing (POM pattern)
- Webservice testing (DTO pattern using Rest Assured)
- Database manupulation (via Spring JDBC Template)
- Analysis tool (via Sonar plugin)
- Reporting tool (via ReportPortal)


### Prerequisite!

  - Install [OpenJDK 1.8.0_242]
  - Install [Groovy 2.5.x]
  - Install [Maven 3.6.x]

### Tech Stacks
![Screenshot](https://github.com/DryanLam/Cucumber-TestNG-Spring-ReportPortal/blob/master/images/stack.PNG)

### Structure
![Screenshot](https://github.com/DryanLam/Cucumber-TestNG-Spring-ReportPortal/blob/master/images/structure.PNG)


### Run App

* Clone project
* Navigate to **source** directory where containings 'pom.xml' file
* Open the command line and run with certain options

```sh
# Default for UI run all regression suite (without ReportPortal)
mvn clean test

# WS
mvn clean test -Dtest.type=WS -Dtest.name=WS

# Report WS to ReportPortal.io
mvn clean test -Dtest.type=WS -Dtest.name=WSIO -Drp.launch=WS_ReportPortal

# Report UI to ReportPortal.io
mvn clean test -Dtest.type=UI -Dtest.name=UIIO -Drp.launch=UI_ReportPortal
```

Analysis scan code via Sonar (change url below to your SonarQube server)
```sh
mvn  sonar:sonar -Dsonar.host.url=http://localhost:9000
```


<note>**!** All features are configurable through configuration file or maven CLI properties</note>

```sh
mvn clean test -Dtest.type=WS -Dtest.name=WSIO -Drp.launch=WS_TADP
```

Options:
- test.type: WS, UI
- test.name: WS, WSIO, UI, UIIO
- rp.launch: name of project on Report Portal


Note: There are docker files in docker folder to serve you in building container for SonarQube and ReportPortal.

### Configuration
All necessary configurations for testing are publicly in properties file under resources folder such as config.properties, sonar-project.properties, etc


Besides, we also support gradle in 'gradle' branch. Check it out and try it.