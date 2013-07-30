organization := "fi.jumi"

name := "sbt-jumi"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.2"

sbtPlugin := true

resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL+"/.m2/repository"

libraryDependencies += "fi.jumi" % "jumi-launcher" % "0.5-SNAPSHOT"

ScriptedPlugin.scriptedSettings

scriptedBufferLog := false
