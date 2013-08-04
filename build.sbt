organization := "fi.jumi"

name := "sbt-jumi"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.2"

sbtPlugin := true

resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL+"/.m2/repository"

libraryDependencies += "fi.jumi" % "jumi-launcher" % "0.5.376"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "org.hamcrest" % "hamcrest-core" % "1.3" % "test"

libraryDependencies += "org.hamcrest" % "hamcrest-library" % "1.3" % "test"

ScriptedPlugin.scriptedSettings

scriptedBufferLog := false

publishTo := {
  if (version.value.trim.endsWith("-SNAPSHOT"))
    Some(Classpaths.sbtPluginSnapshots)
  else
    Some(Classpaths.sbtPluginReleases)
}

publishMavenStyle := false
