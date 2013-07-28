resolvers += Resolver.typesafeRepo("snapshots")

libraryDependencies <+= (sbtVersion) {
  sv => "org.scala-sbt" % "scripted-plugin" % sv
}

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")
