sbt-jumi
========

An sbt plugin for running tests with [Jumi](http://jumi.fi/) in [sbt](http://www.scala-sbt.org/)

**UNDER DEVELOPMENT**


User Guide
----------

Build this plugin locally using `sbt publish-local` (it's still pre-alpha).

Add the following line to your `project/plugins.sbt` file:

    addSbtPlugin("fi.jumi.sbt" % "sbt-jumi" % "0.1.0")

Run the tests using the command:

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
