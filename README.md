sbt-jumi
========

An [sbt](http://www.scala-sbt.org/) plugin for running tests with [Jumi](http://jumi.fi/).

The binaries of this plugin are available in the [sbt plugin repository](http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/fi.jumi.sbt/sbt-jumi/)
where sbt will find them automatically.


User Guide
----------

Requires sbt 0.13.0

Add the following line to your `project/plugins.sbt` file:

    addSbtPlugin("fi.jumi.sbt" % "sbt-jumi" % "0.1.0")

Add the following line to your `build.sbt` file:

    jumiSettings

Also you will probably want to customize the default settings there.
Here are some common ones, for the rest see [here](https://github.com/orfjackal/sbt-jumi/blob/master/src/main/scala/fi/jumi/sbt/JumiPlugin.scala):

    jumiJvmOptions := Seq("-ea", "-Xmx512M")

    jumiIncludedTestsPattern := "glob:com/example/**Test.class"

For Jumi to run also JUnit based tests, you will additionally need to have
JUnit in your test classpath.

After everything is configured, you can run tests using the command:

    sbt jumi-test


Building
--------

Build locally: `sbt publish-local`

Run tests: `sbt test scripted`

Create new release: `./scripts/release.sh <VERSION>`


Version History
---------------

### sbt-jumi 0.1.0 (2013-08-05)

- Initial release
- Runs tests using Jumi 0.5.376
