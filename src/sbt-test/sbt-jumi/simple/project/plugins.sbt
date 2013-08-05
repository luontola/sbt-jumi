addSbtPlugin("fi.jumi.sbt" % "sbt-jumi" % "0.1-SNAPSHOT")

resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL+"/.m2/repository"
