sbt-jumi
========

An sbt plugin for running tests with [Jumi](http://jumi.fi/) in [sbt](http://www.scala-sbt.org/)

**UNDER DEVELOPMENT**


User Guide
----------

Build this plugin locally using `sbt publish-local` (it's still pre-alpha).

Add the following line to your `project/plugins.sbt` file:

    addSbtPlugin("fi.jumi" % "sbt-jumi" % "0.1-SNAPSHOT")

Run the tests using the command:

    sbt jumi-test


Building
--------

Test with: `sbt test scripted`
