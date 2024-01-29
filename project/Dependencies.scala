import sbt._

object Dependencies {

  val test = Seq(
    "com.typesafe"         % "config"         % "1.4.2"         % Test,
    "com.vladsch.flexmark" % "flexmark-all"   % "0.62.2"        % Test,
    "org.scalatest"       %% "scalatest"      % "3.2.17"        % Test,
    "org.scalatestplus"   %% "selenium-4-12"  % "3.2.17.0"      % Test,
    "uk.gov.hmrc"         %% "ui-test-runner" % "0.15.0"        % Test,
    "uk.gov.hmrc"         %% "domain"         % "8.3.0-play-28" % Test
  )

}
