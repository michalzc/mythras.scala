import YamlProcessorPlugin.autoImport.{generateJson, jsonOutputDir}
import com.typesafe.sbt.less.Import.LessKeys.less
import com.typesafe.sbt.less.SbtLess
import com.typesafe.sbt.web.Import.Assets
import org.scalajs.linker.interface.Report
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport.{fastLinkJS, fullLinkJS, scalaJSLinkerOutputDirectory}
import sbt.Keys.*
import sbt.io.Path.{flat, rebase, relativeTo}
import sbt.{Def, *}

object FoundryScalaJsPlugin extends AutoPlugin {

  override def requires = ScalaJSPlugin && SbtLess && YamlProcessorPlugin
  override def trigger  = NoTrigger

  object autoImport {
    lazy val devDist          = taskKey[Seq[(File, File)]]("Prepare local version of system to include in FoundryVTT")
    lazy val devDistOutput    = settingKey[File]("Location for development dist output")
    lazy val dist             = taskKey[Seq[File]]("Prepare distribution version of system to include in FoundryVTT")
    lazy val distOutput       = settingKey[File]("Location for distribution file hierarchy")
    lazy val distFile         = settingKey[File]("Distribution file name and location")
    lazy val distManifestFile = settingKey[File]("Manifest file name and location")
  }

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[_]] = {

    inConfig(Assets)(
      Seq(
        sourceDirectory := (Compile / less / sourceDirectory).value / "less",
        less / includeFilter   := "*.less",
      ),
    ) ++ inConfig(Compile)(
      Seq(
        devDist          := copyFiles(fastLinkJS, devDistOutput).value,
        devDistOutput    := target.value / s"${moduleName.value}-dev",
        distOutput       := target.value / moduleName.value,
        distFile         := target.value / s"${moduleName.value}-${version.value}.zip",
        distManifestFile := target.value / s"${moduleName.value}-${version.value}.json",
        dist             := Seq(distZip.value),
      ),
    )
  }

  def distZip = Def.task {
    val files                           = copyFiles(fullLinkJS, distOutput).value
    val flatter: File => Option[String] = relativeTo(distOutput.value)
    val zipFileMappings = files.flatMap { case (_, outFile) =>
      flatter(outFile).map(outFile -> _)
    }
    IO.zip(zipFileMappings, distFile.value, None)
    distFile.value
  }

  def copyFiles(
      jsTask: TaskKey[Attributed[Report]],
      output: SettingKey[File],
  ): Def.Initialize[Task[Seq[(File, File)]]] = Def.task {
    val jsOutDir = (jsTask / scalaJSLinkerOutputDirectory).value
    val scripts = jsTask.value.data.publicModules.toList.flatMap { module =>
      List(Option(jsOutDir / module.jsFileName), module.sourceMapName.map(jsOutDir / _))
    }.flatten

    val lessOutDir = (Assets / less / resourceManaged).value
    val jsonOutDir = jsonOutputDir.value

    // more soon
    val generatedJsonFiles = transformPaths(Set(jsonOutDir), output.value)(generateJson.value)
    val lessFiles          = transformPaths(Set(lessOutDir), output.value / "css")((Assets / less).value)
    val jsFiles       = transformPaths(Set(jsOutDir), output.value / "modules")(scripts)
    val resourceFiles = transformPaths(resourceDirectories.value.toSet, output.value)((Compile / resources).value)

    val mappedFiles = generatedJsonFiles ++: lessFiles ++: jsFiles ++: resourceFiles
    IO.copy(mappedFiles)
    mappedFiles
  }

  def transformPaths(sourceDirectories: Set[File], target: File)(files: Seq[File]): Seq[(File, File)] = {
    val flatter: File => Option[File]     = flat(target)
    val transformer: File => Option[File] = (f: File) => rebase(sourceDirectories, target)(f).orElse(flatter(f))
    files.flatMap {
      case f if !sourceDirectories(f) => transformer(f).map(f -> _)
      case _                          => None
    }
  }
}
