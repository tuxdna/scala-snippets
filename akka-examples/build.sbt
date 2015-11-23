name := "akka-examples"

version := "0.1"

scalaVersion := "2.11.7"

transitiveClassifiers in Global := Seq(Artifact.SourceClassifier)

// libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.0"

