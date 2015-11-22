name := "scala-snippets"

version := "0.1"


// libraryDependencies += "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"

libraryDependencies += "org.scala-lang" % "scala-library" % "2.10.4"

libraryDependencies += "org.apache.lucene" % "lucene-core" % "3.0.1"

libraryDependencies += "com.netflix.rxjava" % "rxjava-scala" % "0.16.1"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.2.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.0-M2"

libraryDependencies += "org.scala-lang" % "scala-actors" % "2.10.4"

// libraryDependencies += "org.apache.mahout" % "mahout-examples" % "0.8"

libraryDependencies += "de.tudarmstadt.ukp.wikipedia" % "de.tudarmstadt.ukp.wikipedia.parser" % "0.9.2"

libraryDependencies += "de.tudarmstadt.ukp.wikipedia" % "de.tudarmstadt.ukp.wikipedia.api" % "0.9.2"

libraryDependencies += "org.mongodb" %% "casbah" % "2.6.3"

libraryDependencies += "org.asciidoctor" % "asciidoctor-java-integration" % "0.1.4"

libraryDependencies += "org.apache.activemq" % "activemq-all" % "5.10.0"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.2.2"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"

scalaVersion := "2.10.4"
