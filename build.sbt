enablePlugins(ScalaJSPlugin)

name := "Scala_project"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.0.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "2.0.1" % "provided",
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.1",
  "com.github.nscala-time" %% "nscala-time" % "2.14.0"
)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "activation", xs @ _*)                   => MergeStrategy.first
  case PathList("com", "esotericsoftware", xs @ _*)               => MergeStrategy.first
  case PathList("org", "apache", "hadoop", xs @ _*)               => MergeStrategy.first
  case PathList("org", "apache", "commons", xs @ _*)              => MergeStrategy.first
  case PathList("org", "slf4j", xs @ _*)                          => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "plugin.properties"  => MergeStrategy.first
  case PathList("META-INF", xs @ _*)                              => MergeStrategy.discard
  case x                                                          => MergeStrategy.last
}