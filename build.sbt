lazy val commonSettings = Seq(
  name := "scala-spark-twitter-plotting",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val frontendSettings = Seq(
  ivyConfigurations += config("js"),
  persistLauncher := true,
  skip in packageJSDependencies := false,
  jsDependencies ++= Def.setting(Seq[org.scalajs.sbtplugin.JSModuleID](
    "org.webjars" % "jquery" % "2.1.3" / "2.1.3/jquery.js",
    "org.webjars.bower" % "paths-js" % "0.4.4" / "paths.js",
    "org.webjars" % "bootstrap" % "3.3.7" / "bootstrap.js" dependsOn "2.1.3/jquery.js"
  )).value,
  jsDependencies += RuntimeDOM,
  libraryDependencies ++= Seq(
    "eu.unicredit" %%% "paths-scala-js" % "0.4.4",
    "com.github.karasiq" %%% "scalajs-bootstrap" % "1.1.2"
  )
)

lazy val backendSettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList("javax", "activation", xs @ _*)                   => MergeStrategy.first
    case PathList("com", "esotericsoftware", xs @ _*)               => MergeStrategy.first
    case PathList("org", "apache", "hadoop", xs @ _*)               => MergeStrategy.first
    case PathList("org", "apache", "commons", xs @ _*)              => MergeStrategy.first
    case PathList("org", "slf4j", xs @ _*)                          => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith "plugin.properties"  => MergeStrategy.first
    case PathList("META-INF", xs @ _*)                              => MergeStrategy.discard
    case x                                                          => MergeStrategy.last
  },

  libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "2.0.1" % "provided",
    "org.apache.spark" %% "spark-streaming" % "2.0.1" % "provided",
    "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.1",
    "com.github.nscala-time" %% "nscala-time" % "2.14.0"
  )
)

lazy val root = (project in file("."))
    .settings(commonSettings, frontendSettings, backendSettings)
    .enablePlugins(ScalaJSPlugin)
