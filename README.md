sbt-jumi
========

An [sbt](http://www.scala-sbt.org/) plugin for running tests with the
[Jumi](http://jumi.fi/) test runner.

The binaries of this plugin are available in the [sbt plugin repository](http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/fi.jumi.sbt/sbt-jumi/)
where sbt will find them automatically.


Quick Start
-----------

Requires sbt 0.13.0

Add the following line to your `project/plugins.sbt` file:

    addSbtPlugin("fi.jumi.sbt" % "sbt-jumi" % "0.1.1")

Add the following line to your `build.sbt` file:

    jumiSettings

You may want to customize the default settings. Here are some common ones:

    jumiJvmOptions := Seq("-ea", "-Xmx512M")

    jumiIncludedTestsPattern := "glob:com/example/**{Test,Spec}.class"

A full list of the available settings is in the [plugin source](https://github.com/orfjackal/sbt-jumi/blob/master/src/main/scala/fi/jumi/sbt/JumiPlugin.scala).

For Jumi to run also JUnit based tests, you will additionally need to have
JUnit in your test classpath (which you probably already have for your
tests to compile).

After everything is configured, you can run tests using the command:

    sbt jumi-test


### Using a different Jumi version ###

When new versions of Jumi are released, this plugin may not get a new
release if it's compatible with the new Jumi version. In that case you can
override sbt's plugin dependencies to change the Jumi version being used.

Add the following line to your `project/plugins.sbt` file:

    dependencyOverrides += "fi.jumi" % "jumi-launcher" % "<the new version>"

See [sbt's documentation](http://www.scala-sbt.org/release/docs/Detailed-Topics/Library-Management.html)
for more information.


Building
--------

Build locally: `sbt publish-local`

Run tests: `sbt test scripted`

Create new release: `./scripts/release.sh <VERSION>`


Version History
---------------

### sbt-jumi 0.1.1 (2013-08-07)

- Upgraded to Jumi 0.5.390. Fixes `jumiDebugModeEnabled := true` closing the standard output stream

### sbt-jumi 0.1.0 (2013-08-05)

- Initial release
- Runs tests using Jumi 0.5.376
