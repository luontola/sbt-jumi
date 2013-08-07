organization := "fi.jumi.sbt"

name := "sbt-jumi"

version := "0.1.2-SNAPSHOT"

sbtVersion in Global := "0.13.0-RC4"

scalaVersion in Global := "2.10.2"

sbtPlugin := true

resolvers += Opts.resolver.mavenLocalFile

resolvers += Opts.resolver.sonatypeReleases

libraryDependencies += "fi.jumi" % "jumi-launcher" % "0.5.390"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "org.hamcrest" % "hamcrest-core" % "1.3" % "test"

libraryDependencies += "org.hamcrest" % "hamcrest-library" % "1.3" % "test"

ScriptedPlugin.scriptedSettings

scriptedBufferLog := false

scriptedLaunchOpts += "-Dsbt-jumi.version="+version.value

publishTo := {
  if (version.value.trim.endsWith("-SNAPSHOT"))
    Some(Classpaths.sbtPluginSnapshots)
  else
    Some(Classpaths.sbtPluginReleases)
}

publishMavenStyle := false
