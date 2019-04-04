lazy val renaissanceCore = RootProject(uri("../../renaissance-core"))

lazy val scalaStdlib = (project in file("."))
  .settings(
    name := "scala-stdlib",
    organization := "org.renaissance",
    scalafmtConfig := Some(file(".scalafmt.conf")),
    scalaVersion := "2.12.8"
  )
  .dependsOn(
    renaissanceCore
  )
