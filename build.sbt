lazy val sparkVersion = "1.4.0"

name := "scala-spark-twitter-plotting"

lazy val commonSettings = Seq(
  version := "0.1.0",
  organization := "com.apbau",
  scalaVersion := "2.12.1"
)

/** Assembly Plugin Configuration **/

// Skip tests when packaging
test in assembly := {}

// Conflicting path resolution
assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", xs @ _*)         => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "scala-spark-twitter-plotting",
    libraryDependencies ++= Seq(
      "org.apache.spark" % "spark-core_2.10" % "2.0.2",
      "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.6.3" % "provided",
      "org.apache.spark" % "spark-streaming_2.10" % "2.0.2" % "provided",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
    )
  )

