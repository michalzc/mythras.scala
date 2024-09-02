import _root_.io.circe.yaml.parser.parse
import _root_.io.circe.syntax._
import sbt.Keys._
import sbt.io.Path.rebase
import sbt.io.RegularFileFilter
import sbt.{AutoPlugin, Def, _}

import java.lang

object YamlProcessorPlugin extends AutoPlugin {
  override def trigger = noTrigger

  object autoImport {
    lazy val yamlSourceDir = settingKey[File]("Directory with yaml files")
    lazy val jsonOutputDir = settingKey[File]("Output directory for generated json files")
    lazy val generateJson  = taskKey[Seq[File]]("Task that generate jsons from yamls")
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] = {
    inConfig(Compile)(
      Seq(
        jsonOutputDir := target.value / "json",
        yamlSourceDir := sourceDirectory.value / "yaml",
        generateJson  := generateJsonTask.value,
      ),
    )
  }

  def generateJsonTask = Def.task {

    val log                           = streams.value.log
    val yamlFiles                     = (yamlSourceDir.value ** "*.yaml").get()
    val rebaser: File => Option[File] = rebase(yamlSourceDir.value, jsonOutputDir.value)
    val mappings = (yamlFiles pair rebase(yamlSourceDir.value, jsonOutputDir.value)).map { case (left, right) =>
      left -> file(right.toString.replace(".yaml", ".json"))
    }
    mappings.map { case (source, destination) => generateJsonFile(version.value, source, destination) }
  }

  def generateJsonFile(version: String, source: File, destination: File): File = {

    val json = orThrow(parse(IO.read(source)))
    val processedJson = if (source.base == "system") {
      val versionPart = Map("version" -> version).asJson
      json.deepMerge(versionPart)
    } else json
    val string = processedJson.spaces2
    IO.write(destination, string)

    destination
  }

  def orThrow[A, E <: lang.Throwable](value: Either[E, A]): A = value match {
    case Right(value) => value
    case Left(error)  => throw error
  }
}
