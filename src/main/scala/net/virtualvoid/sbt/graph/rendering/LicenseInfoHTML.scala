package net.virtualvoid.sbt.graph.rendering

import sbt._
import Keys._
import scala.xml.XML

import net.virtualvoid.sbt.graph._

object LicenseInfoHTML {

  def saveLicenseInfoHtml(graph: ModuleGraph, outFile: File, excludedOrgs: Set[String], streams: TaskStreams) = {

    val noLicense = "<No license specified>"
    def licId(lic: String) = java.net.URLEncoder.encode(lic, "utf-8")
    def licHref(lic: String) = s"#${licId(lic)}"

    val externalModules = graph.nodes.filter(_.isUsed).filter(m ⇒ !excludedOrgs(m.id.organisation))

    streams.log.info("Getting license info for %d modules. Excluded orgs: %s" format
      (externalModules.size, excludedOrgs.mkString(",")))

    val licenses =
      externalModules.map(_.license).distinct.sorted.map { license ⇒
        val lic = license.getOrElse(noLicense)
        <li><a href={ licHref(lic) }>{ lic }</a></li>
      }

    val licenceModules =
      externalModules.groupBy(_.license).toSeq.sortBy(_._1).map {
        case (license, modules) ⇒
          val lic = license.getOrElse(noLicense)
          val mods = modules.map(m ⇒ <li>{ m.id.idString }</li>)
          <div>
            <h2 id={ licId(lic) }>{ lic }</h2>
            <ul>{ mods }</ul>
          </div>
      }

    val html =
      <html>
        <head><title>License Info</title></head>
        <body>
          <div><h1>All licences</h1><ul>{ licenses }</ul></div>
          <div><h1>Modules by license</h1>{ licenceModules }</div>
        </body>
      </html>

    XML.save(outFile.getAbsolutePath, html)
    streams.log.info("Wrote license info to '%s'" format outFile)
    outFile
  }

}
