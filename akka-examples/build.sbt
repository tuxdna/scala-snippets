name := "akka-examples"

version := "0.1"

scalaVersion := "2.11.7"

val akkaVersion = "2.4.0"

transitiveClassifiers in Global := Seq(Artifact.SourceClassifier)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "2.2.3"
)
