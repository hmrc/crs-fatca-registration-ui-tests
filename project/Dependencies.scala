import sbt._

object Dependencies {

  val test = Seq(
    "com.vladsch.flexmark" % "flexmark-all"   % "0.62.2"        % Test,
    "org.scalatest"       %% "scalatest"      % "3.2.17"        % Test,
    "org.slf4j"            % "slf4j-simple"   % "1.7.36"        % Test,
    "uk.gov.hmrc"         %% "ui-test-runner" % "0.15.0"        % Test,
    "uk.gov.hmrc"         %% "domain"         % "8.3.0-play-28" % Test
  )

}
