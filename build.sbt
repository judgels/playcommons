import de.johoop.testngplugin.TestNGPlugin
import de.johoop.jacoco4sbt.JacocoPlugin.jacoco

lazy val playcommons = (project in file("."))
    .enablePlugins(PlayJava, SbtWeb)
    .disablePlugins(plugins.JUnitXmlReportPlugin)
    .dependsOn(judgelscommons)
    .aggregate(judgelscommons)
    .settings(
        name := "playcommons",
        version := IO.read(file("version.properties")).trim,
        scalaVersion := "2.11.7",
        libraryDependencies ++= Seq(
            javaJdbc,
            javaWs,
            javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
            filters,
            cache,
            evolutions,
            "javax.inject" % "javax.inject" % "1",
            "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
            "org.springframework" % "spring-context" % "4.1.6.RELEASE",
            "mysql" % "mysql-connector-java" % "5.1.26",
            "com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24" exclude("org.webjars", "jquery"),
            "org.webjars" % "bootstrap" % "3.3.4" exclude("org.webjars", "jquery"),
            "org.webjars" % "jquery" % "2.1.4",
            "org.webjars" % "jquery-ui" % "1.11.4" exclude("org.webjars", "jquery"),
            "org.webjars" % "less" % "2.5.0",
            "org.webjars" % "requirejs" % "2.1.18",
            "org.webjars" % "momentjs" % "2.10.3",
            "org.webjars" % "bootstrap-select" % "1.6.3" exclude("org.webjars", "bootstrap"),
            "org.webjars" % "Eonasdan-bootstrap-datetimepicker" % "4.7.14" exclude("org.webjars", "bootstrap"),
            "org.eclipse.jgit" % "org.eclipse.jgit" % "3.7.0.201502260915-r",
            "com.nimbusds" % "c2id-server-sdk" % "2.0",
            "com.amazonaws" % "aws-java-sdk" % "1.9.28.1" exclude("joda-time", "joda-time"),
            "joda-time" % "joda-time" % "2.3",
            "org.seleniumhq.selenium" % "selenium-java" % "2.46.0"
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
    .settings(
        publishArtifact in (Compile, packageDoc) := false,
        publishArtifact in packageDoc := false,
        sources in (Compile,doc) := Seq.empty
    )

lazy val judgelscommons = RootProject(file("../judgels-commons"))
