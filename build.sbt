name := "template-site"

//common settings for the project and subprojects
lazy val commonSettings = Seq(
	organization := "eu.tetrao",
	version := "0.1.2",
	scalaVersion := "2.11.8",
	scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-target:jvm-1.8")
)

lazy val root = (project in file("."))
	.settings(commonSettings: _*)
	.settings(routesGenerator := InjectedRoutesGenerator)
	.settings(
		
		libraryDependencies += "com.typesafe.slick" %% "slick" % "3.1.1",
		libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.1.1",
		libraryDependencies += "com.github.tminglei" %% "slick-pg" % "0.14.3",
		libraryDependencies += "com.github.tminglei" %% "slick-pg_date2" % "0.14.3",
		libraryDependencies += "com.typesafe.play" %% "play-slick" % "2.0.2",
		libraryDependencies += "jp.t2v" %% "play2-auth" % "0.14.2",
		libraryDependencies += play.sbt.Play.autoImport.cache,
		libraryDependencies += "com.github.t3hnar" %% "scala-bcrypt" % "2.6",
		libraryDependencies += "org.webjars" %% "webjars-play" % "2.5.0",
		libraryDependencies += "org.webjars" % "foundation" % "6.2.3"
	).enablePlugins(PlayScala)
	
	libraryDependencies ++= Seq(
  
	"com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
	"com.h2database" % "h2" % "1.4.190",
  	"com.enragedginger"    %% "akka-quartz-scheduler"   % "1.4.0-akka-2.3.x",
	"org.sorm-framework" % "sorm" % "0.3.19" excludeAll (ExclusionRule(name = "embrace")),
	"com.typesafe.akka"   %  "akka-testkit_2.11"        % "2.3.11",
	cache,
	ws,
	"org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
	"com.typesafe.akka" %% "akka-stream-kafka" % "0.11-M2",
	"org.webjars" %% "webjars-play" % "2.5.0",
	"org.webjars" % "jquery" % "2.2.3",
	"org.webjars" % "bootstrap" % "3.3.6",
	"org.apache.kafka" % "kafka_2.11" % "0.10.0.0",
	"org.slf4j" % "slf4j-api"       % "1.7.7",
	"org.slf4j" % "jcl-over-slf4j"  % "1.7.7",
	"org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
	"com.typesafe.slick" %% "slick" % "3.1.1"
	
).map(_.force())
  

//to generate models/db/Tables.scala
addCommandAlias("tables", "run-main utils.db.SourceCodeGenerator")
