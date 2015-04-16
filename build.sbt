import de.johoop.testngplugin.TestNGPlugin
import de.johoop.jacoco4sbt.JacocoPlugin.jacoco

lazy val playcommons = (project in file("."))
    .enablePlugins(PlayJava, SbtWeb)
    .disablePlugins(plugins.JUnitXmlReportPlugin)
    .settings(
        name := "playcommons",
        version := "0.2.1",
        scalaVersion := "2.11.1",
        libraryDependencies ++= Seq(
            javaJdbc,
            javaWs,
            javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
            filters,
            cache,
            "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
            "org.webjars" % "bootstrap" % "3.3.4",
            "com.adrianhurt" %% "play-bootstrap3" % "0.4",
            "com.google.code.gson" % "gson" % "2.3.1",
            "commons-io" % "commons-io" % "2.4",
            "com.google.guava" % "guava" % "r05",
            "mysql" % "mysql-connector-java" % "5.1.26",
            "org.webjars" % "jquery" % "2.1.1",
            "org.webjars" % "jquery-ui" % "1.11.1",
            "org.webjars" % "less" % "1.7.5",
            "org.webjars" % "requirejs" % "2.1.15",
            "org.webjars" % "bootstrap-select" % "1.6.3",
            "com.puppycrawl.tools" % "checkstyle" % "6.1",
            "org.eclipse.jgit" % "org.eclipse.jgit" % "3.7.0.201502260915-r",
            "com.nimbusds" % "c2id-server-sdk" % "2.0",
            "com.amazonaws" % "aws-java-sdk" % "1.9.28.1"
        )
    )
    .settings(TestNGPlugin.testNGSettings: _*)
    .settings(
        TestNGPlugin.testNGSuites := Seq("test/resources/testng.xml")
    )
    .settings(jacoco.settings: _*)
    .settings(
        parallelExecution in jacoco.Config := false
    )
    .settings(
        excludeFilter in (Assets, LessKeys.less) := "_*.less"
    )
