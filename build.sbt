import ScalaJSHelper._

lazy val V = new {
  val doobie = "0.13.4"
  val playJson = "2.9.4"
  val akka = "2.6.18"
  val akkaHttp = "10.2.10"
  val elastic4s = "8.11.5"
  val nscalaTime = "2.32.0"
  val scalatest = "3.2.17"
  val circe = "0.14.6"
  val json4s = "4.0.7"
  val coursier = "2.1.6"
}

lazy val scalacOptionsSettings = Def.settings(
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked"
  )
)

inThisBuild(
  List(
    scalaVersion := "3.3.1",
    // semanticdbEnabled := true,
    // semanticdbVersion := scalafixSemanticdb.revision,
    // scalafixScalaBinaryVersion := "2.13",
    // scalafixDependencies ++= List(
    //   "com.github.liancheng" %% "organize-imports" % "0.6.0"
    // ),
    organization := "ch.epfl.scala",
    // version := s"0.2.0+${githash()}"
  )
)

lazy val scaladex = project
  .in(file("."))
  // .aggregate(webclient, data, core.jvm, core.js, infra, server, template)
  .aggregate(core.jvm, core.js)
//   .settings(Deployment(data, server))

lazy val core = crossProject(JSPlatform, JVMPlatform)
  .in(file("modules/core"))
  .settings(
    scalacOptionsSettings,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "fastparse" % "3.0.2",
      "io.github.cquiroz" %%% "scala-java-time" % "2.5.0",
      "org.endpoints4s" %%% "algebra" % "1.11.1",
      "org.endpoints4s" %% "json-schema-playjson" % "1.11.1" % Test,
      "org.scalatest" %%% "scalatest" % V.scalatest % Test,
      "org.jsoup" % "jsoup" % "1.17.2"
    ) ++ Seq(
      "io.circe" %%% "circe-core",
      "io.circe" %%% "circe-generic",
      "io.circe" %%% "circe-parser"
    ).map(_ % V.circe)
  )