package net.virtualvoid.sbt.graph.rendering

import net.virtualvoid.sbt.graph._
import sbt.Keys._
import sbt._

import com.github.tototoshi.csv.CSVWriter

object LicenseInfoCSV {

  def saveLicenseInfoCsv(graph: ModuleGraph, outFile: File, excludedOrgs: Set[String], streams: TaskStreams) = {

    val noLicense = "<No license specified>"

    val externalModules = graph.nodes.filter(_.isUsed).filter(m ⇒ !excludedOrgs(m.id.organisation))

    streams.log.info("Getting license info for %d modules. Excluded orgs: %s" format
      (externalModules.size, excludedOrgs.mkString(",")))

    val writer = CSVWriter.open(outFile)
    externalModules
      .sortBy(_.license)
      .foreach { mod ⇒
        writer.writeRow(Seq(mod.id.idString, mod.license.getOrElse(noLicense)))
      }

    streams.log.info("Wrote license info to '%s'" format outFile)
    outFile
  }

}
