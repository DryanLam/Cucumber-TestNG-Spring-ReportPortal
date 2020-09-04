# Cucumber Spring TestNG ReportIO
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

  - Install [OpenJDK 1.8.0_242
  - Install [Groovy 2.5.x]
  - Install [Maven 3.6.x]




### Run App

* Clone [TADPparser] project
* Navigate to **source** directory where containings 'TADPparser.groovy'
* Open the command line and run with certain options

Default for UI run all regression suite (without ReportPortal)

```sh
mvn clean test
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