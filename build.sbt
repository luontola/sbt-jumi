organization := "fi.jumi"

name := "sbt-jumi"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.2"

sbtPlugin := true

libraryDependencies += "fi.jumi" % "jumi-launcher" % "0.5.365"

ScriptedPlugin.scriptedSettings

scriptedBufferLog := false
