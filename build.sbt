import sbt.Keys.version

val projectSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.postgresql" % "postgresql" % "42.2.16",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
    "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
    "com.h2database" % "h2" % "1.4.200",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  ),
  organization := "com.gigahex.example",
  name := "gigahex-test",
  version := "0.1",
  scalaVersion := "2.12.6"
)

val `gigahex-test` = (project in file("."))
  .settings(projectSettings)
