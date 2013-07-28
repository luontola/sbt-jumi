// Copyright © 2013, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package fi.jumi.sbt

import sbt._
import sbt.Keys._
import fi.jumi.launcher.JumiBootstrap

object SbtJumiPlugin extends Plugin {

  val jumiTest = TaskKey[Unit]("jumi-test", "Run tests using Jumi")

  override val settings = Seq(
    jumiTest <<= (fullClasspath in Test) map jumiTestTask dependsOn (compile in Test)
  )

  def jumiTestTask(testClasspath: Classpath) {
    val bootstrap = new JumiBootstrap()
    bootstrap.suite.
      setClassPath(testClasspath map (_.data.toPath): _*).
      addJvmOptions("-ea"). // TODO: parameterize
      setIncludedTestsPattern("glob:**Test.class") // TODO: parameterize
    bootstrap.
      //enableDebugMode().
      runSuite() // TODO: make fail without throwing the exception from here
  }
}
