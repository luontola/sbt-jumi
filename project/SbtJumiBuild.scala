// Copyright © 2013, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

import sbt._

object SbtJumiBuild extends Build {

  val newTask = TaskKey[Unit]("new-task", "A dummy task")
  val newSetting = SettingKey[String]("new-setting")

  val newSettings = Seq(
    newSetting := "test",
    newTask <<= newSetting map {
      str => println(str)
    }
  )

  lazy val root = Project(id = "sbt-jumi", base = file("."))
}
