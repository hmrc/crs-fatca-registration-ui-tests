lazy val testSuite = (project in file("."))
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    name := "crs-fatca-registration-ui-tests",
    version := "0.1.0",
    scalaVersion := "3.3.5",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Dependencies.test
  )
