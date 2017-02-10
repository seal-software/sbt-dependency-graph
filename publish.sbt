/* Seal notes:
 *   Releases are published manually by doing `sbt publishLocal`
 *   and then uploading the artifact jar using the generated pom file for metadata
 *
 */
publishTo <<= version { v: String =>
  val nexus = "https://nexus.seal-software.net/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else                             Some("releases" at nexus + "content/repositories/releases")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra :=
  Helpers.generatePomExtra("git@github.com:jrudolph/sbt-dependency-graph.git",
                           "scm:git:git@github.com:jrudolph/sbt-dependency-graph.git",
                           "jrudolph", "Johannes Rudolph")

useGpg := true
