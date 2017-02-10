crossBuildingSettings

CrossBuilding.crossSbtVersions := Seq("0.13")

CrossBuilding.scriptedSettings

libraryDependencies += "com.github.mdr" %% "ascii-graphs" % "0.0.3"

libraryDependencies <++= scalaVersion { version =>
  if (version startsWith "2.1") Seq("org.scala-lang" % "scala-reflect" % version % "provided")
  else Nil
}

libraryDependencies <+= scalaVersion { version =>
  if (version startsWith "2.9") "org.specs2" % "specs2_2.9.3" % "1.12.4.1" % "test"
  else "org.specs2" %% "specs2" % "2.3.11" % "test"
}

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.4"

scalacOptions ++= Seq("-deprecation", "-unchecked")

sbt.CrossBuilding.latestCompatibleVersionMapper ~= {
  original => {
    case "0.13" => "0.13.6"
    case x => original(x)
  }
}

ScalariformSupport.formatSettings