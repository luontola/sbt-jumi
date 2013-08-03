// Copyright © 2013, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package fi.jumi.sbt

import sbt._
import sbt.Keys._
import fi.jumi.launcher.JumiBootstrap
import fi.jumi.core.config._
import java.nio.file.Path

object SbtJumiPlugin extends Plugin {

  val jumiTest = TaskKey[Unit]("jumi-test", "Run tests using Jumi")

  val jumiSuite = taskKey[SuiteConfigurationBuilder]("Suite configuration")
  val jumiClasspath = settingKey[Seq[Path]]("Classpath for running tests")
  val jumiJvmOptions = settingKey[Seq[String]]("JVM options for running tests")
  val jumiWorkingDirectory = settingKey[Path]("Working directory for running tests")
  val jumiIncludedTestsPattern = settingKey[String]("Test files to run. Same syntax as in java.nio.file.FileSystem#getPathMatcher")
  val jumiExcludedTestsPattern = settingKey[String]("Test files to not run. Same syntax as in java.nio.file.FileSystem#getPathMatcher")

  val jumiDaemon = settingKey[DaemonConfigurationBuilder]("Daemon configuration")
  val jumiIdleTimeout = settingKey[Long]("Timeout after which to shutdown the daemon process")
  val jumiStartupTimeout = settingKey[Long]("Timeout after which to shutdown the daemon process")
  val jumiTestThreadsCount = settingKey[Int]("Number of test threads")
  val jumiHome = settingKey[Path]("Path to Jumi's home directory")
  val jumiDebugModeEnabled = settingKey[Boolean]("Whether to show full debug information")

  override val settings = Seq(
    jumiTest <<= (jumiSuite, jumiDaemon, jumiDebugModeEnabled) map jumiTestTask dependsOn (compile in Test),
    jumiSuite <<= (fullClasspath in Test) map configureSuite,
    jumiDaemon := new DaemonConfigurationBuilder(),
    jumiDebugModeEnabled := false
  )

  private def configureSuite(testClasspath: Classpath): SuiteConfigurationBuilder = {
    new SuiteConfigurationBuilder().
      setClassPath(testClasspath map (_.data.toPath): _*).
      addJvmOptions("-ea"). // TODO: parameterize
      setIncludedTestsPattern("glob:**Test.class") // TODO: parameterize
  }

  private def jumiTestTask(suite: SuiteConfigurationBuilder, daemon: DaemonConfigurationBuilder, debugMode: Boolean) {
    try {
      val bootstrap = new JumiBootstrap()
      bootstrap.suite = suite
      bootstrap.daemon = daemon
      if (debugMode) {
        bootstrap.enableDebugMode()
      }
      bootstrap.runSuite()
    } catch {
      case e: AssertionError => throw new TestsFailedException
    }
  }
}
