import de.johoop.testngplugin.TestNGPlugin
import de.johoop.jacoco4sbt.JacocoPlugin.jacoco

name := """playcommons"""

version := "1.0-SNAPSHOT"

lazy val playcommons = (project in file("."))
                    .enablePlugins(PlayJava)
                    .disablePlugins(plugins.JUnitXmlReportPlugin)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaWs,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2" +
    ".0-api"),
  filters,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
//  "org.hibernate" % "hibernate-jpamodelgen" % "4.3.7.Final",
  "commons-io" % "commons-io" % "2.4",
  "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.0.2",
  "com.google.guava" % "guava" % "r05",
  "mysql" % "mysql-connector-java" % "5.1.26",
  "org.webjars" % "coffee-script" % "1.8.0",
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "jquery-ui" % "1.11.1",
  "org.webjars" % "less" % "1.7.5",
  "org.webjars" % "prettify" % "4-Mar-2013",
  "org.webjars" % "requirejs" % "2.1.15",
  "com.puppycrawl.tools" % "checkstyle" % "6.1",
  "com.adrianhurt" %% "play-bootstrap3" % "0.3",
  "com.nimbusds" % "c2id-server-sdk" % "2.0"
)

TestNGPlugin.testNGSettings

TestNGPlugin.testNGSuites := Seq("testng.xml")

TestNGPlugin.testNGOutputDirectory := "target/testng"

jacoco.settings

parallelExecution in jacoco.Config := false

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

javaOptions in Test ++= Seq(
  "-Dconfig.resource=test.conf"
)

javacOptions ++= Seq("-s", "app")
