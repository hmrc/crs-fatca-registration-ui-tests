import sbt._

object Dependencies {

  val test = Seq(
    "com.vladsch.flexmark" % "flexmark-all"   % "0.64.8"        % Test,
    "org.scalatest"       %% "scalatest"      % "3.2.18"        % Test,
    "org.slf4j"            % "slf4j-simple"   % "2.0.9"         % Test,
    "uk.gov.hmrc"         %% "ui-test-runner" % "0.19.0"        % Test,
    "uk.gov.hmrc"         %% "domain"         % "8.3.0-play-28" % Test
  )

}
