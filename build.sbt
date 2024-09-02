ThisBuild / organization := "michalz"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.2"

lazy val `scala-types` = (project in file("scala-types"))
  .enablePlugins(ScalaJSPlugin)

lazy val `dominion` = (project in file("mythras"))
  .dependsOn(`scala-types`)
  .enablePlugins(SbtLess)
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(YamlProcessorPlugin)
  .settings(
    scalaJSUseMainModuleInitializer        := true,
    Assets / LessKeys.less / includeFilter := "mythras.less",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom"   % "2.2.0",
      "org.querki"   %%% "jquery-facade" % "2.1",
    ),
  )

lazy val `dominion-system` = (project in file("."))
  .aggregate(`scala-types`, `dominion`)
