ThisBuild / organization := "michalz"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.5"

lazy val `scala-types` = (project in file("scala-types"))
  .enablePlugins(ScalaJSPlugin)

lazy val `mythras` = (project in file("mythras"))
  .dependsOn(`scala-types`)
  .enablePlugins(SbtLess)
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(YamlProcessorPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    Assets / LessKeys.less / includeFilter := "mythras.less",
    libraryDependencies ++= Seq(
      "org.scala-js"  %%% "scalajs-dom"   % "2.2.0",
      "org.querki"    %%% "jquery-facade" % "2.1",
      "org.typelevel" %%% "cats-core"     % "2.13.0",
    ),
  )

lazy val `mythras-system` = (project in file("."))
  .aggregate(`scala-types`, `mythras`)
