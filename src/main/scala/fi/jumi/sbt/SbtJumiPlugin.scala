// Copyright © 2013, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package fi.jumi.sbt

import sbt._

object SbtJumiPlugin extends Plugin {

  val jumiTest = TaskKey[Unit]("jumi-test", "Run tests using Jumi")

  override val settings = Seq(
    jumiTest := runTests
  )

  def runTests() {
    // TODO
    println("Hello form sbt-jumi")
  }
}
