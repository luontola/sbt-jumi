// Copyright © 2013, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package fi.jumi.sbt

import sbt._
import sbt.Keys._
import fi.jumi.launcher.JumiBootstrap
import fi.jumi.core.config._

object SbtJumiPlugin extends Plugin {

  val jumiTest = TaskKey[Unit]("jumi-test", "Run tests using Jumi")
  val jumiSuiteConfiguration = TaskKey[SuiteConfiguration]("jumi-suite-configuration")
  val jumiDaemonConfiguration = SettingKey[DaemonConfiguration]("jumi-daemon-configuration")

  override val settings = Seq(
    jumiTest <<= (jumiSuiteConfiguration, jumiDaemonConfiguration) map jumiTestTask dependsOn (compile in Test),
    jumiSuiteConfiguration <<= (fullClasspath in Test) map suite,
    jumiDaemonConfiguration := new DaemonConfiguration()
  )

  private def suite(testClasspath: Classpath): SuiteConfiguration = {
    new SuiteConfigurationBuilder().
      setClassPath(testClasspath map (_.data.toPath): _*).
      addJvmOptions("-ea"). // TODO: parameterize
      setIncludedTestsPattern("glob:**Test.class"). // TODO: parameterize
      freeze()
  }

  private def jumiTestTask(suite: SuiteConfiguration, daemon: DaemonConfiguration) {
    try {
      val bootstrap = new JumiBootstrap()
      bootstrap.runSuite(suite, daemon)
    } catch {
      case e: AssertionError => throw new TestsFailedException
    }
  }
}
