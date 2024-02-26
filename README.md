**This is a template README.md.  Be sure to update this with project specific content that describes your ui test project.**

# crs-fatca-registration-ui-tests
`crs-fatca-registration-frontend` UI journey tests.  

## Pre-requisites

### Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d -p 27017:27017 --name mongo percona/percona-server-mongodb:5.0
```

Start `crs-fatca-registration-frontend` services as follows:

```bash
sm2 --start CRS_FATCA_ALL
```

### Docker Selenium Grid

Confirm that [docker-selenium-grid](https://github.com/hmrc/docker-selenium-grid) is up-to-date and follow the provided [instructions](https://github.com/hmrc/docker-selenium-grid/blob/main/README.md).


### Test inspection and debugging

Connect to port `7900` on the Grid browser's local IP address (see `localhost:4444/ui` to view each browser's IP) to inspect and debug test execution in a noVNC window.


## Tests

Run tests as follows:

* Argument `<browser>` must be `chrome`, `edge`, or `firefox`.
* Argument `<environment>` must be `local`, `dev`, `qa` or `staging`.

```bash
./run_registration_tests.sh <browser> <environment>
```

### Running ZAP tests

ZAP tests can be automated using the HMRC Dynamic Application Security Testing approach. Running 
automated ZAP tests should not be considered a substitute for manual exploratory testing using OWASP ZAP.

#### Executing a local ZAP test

First [run the DAST tool locally](https://github.com/hmrc/dast-config-manager/blob/main/README.md#running-zap-locally)

The shell script `run_zap_tests.sh` is available to execute ZAP tests. The script proxies the registration journey tests via ZAP.  

For example, to execute ZAP tests locally using Chrome browser:

```
./run_zap_test.sh chrome local
```

### Running tests using BrowserStack
If you would like to run your tests via BrowserStack from your local development environment please refer to the [webdriver-factory](https://github.com/hmrc/webdriver-factory/blob/main/README.md/#user-content-running-tests-using-browser-stack) project.


## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
