enablePlugins(ScalaJSPlugin)

lazy val sparkVersion = "2.0.2"

lazy val commonSettings = Seq(
  name := "scala-spark-twitter-plotting",
  version := "0.1.0",
  organization := "ru.spbau.mit",
  scalaVersion := "2.11.8"
)

/** Assembly Plugin Configuration **/

// Skip tests when packaging
test in assembly := {}

// Conflicting path resolution
assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "scala-spark-twitter-plotting",
    libraryDependencies ++= Seq(
      "org.apache.spark" % "spark-core_2.11" % "2.0.2",
      "org.apache.spark" % "spark-streaming_2.11" % "2.0.2",
      "org.apache.bahir" % "spark-streaming-twitter_2.11" % "2.0.1",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
      "org.scala-js" %%% "scalajs-dom" % "0.9.1"
    )
  )