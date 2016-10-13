name := "CryptoTrading"

version := "1.0"

lazy val `cryptotrading` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
  "typesafe-repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  jdbc, cache, ws, specs2 % Test,
  "com.typesafe.akka" %% "akka-actor" % "2.4.10",
  "com.typesafe.akka" %% "akka-remote" % "2.4.10",
  "com.google.guava" % "guava" % "19.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
