/*
 * Copyright 2014 Johannes Rudolph
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.virtualvoid.sbt.graph

import sbt._

trait DependencyGraphKeys {
  val dependencyGraphMLFile = SettingKey[File]("dependency-graph-ml-file",
    "The location the graphml file should be generated at")
  val dependencyGraphML = TaskKey[File]("dependency-graph-ml",
    "Creates a graphml file containing the dependency-graph for a project")
  val dependencyDotFile = SettingKey[File]("dependency-dot-file",
    "The location the dot file should be generated at")
  val dependencyDotNodeLabel = SettingKey[(String, String, String) ⇒ String]("dependency-dot-node-label",
    "Returns a formated string of a dependency. Takes organisation, name and version as parameters")
  val dependencyDotHeader = SettingKey[String]("dependency-dot-header",
    "The header of the dot file. (e.g. to set your preferred node shapes)")
  val dependencyDot = TaskKey[File]("dependency-dot",
    "Creates a dot file containing the dependency-graph for a project")
  val dependencyDotString = TaskKey[String]("dependency-dot-string",
    "Creates a String containing the dependency-graph for a project in dot format")
  val dependencyBrowseGraphTarget = SettingKey[File]("dependency-browse-graph-target",
    "The location dependency browse graph files should be put.")
  val dependencyBrowseGraphHTML = TaskKey[URI]("dependency-browse-graph-html",
    "Creates an HTML page that can be used to view the graph.")
  val dependencyBrowseGraph = TaskKey[URI]("dependency-browse-graph",
    "Opens an HTML page that can be used to view the graph.")
  val moduleGraph = TaskKey[ModuleGraph]("module-graph",
    "The dependency graph for a project")
  val moduleGraphIvyReport = TaskKey[ModuleGraph]("module-graph-ivy-report",
    "The dependency graph for a project as generated from an Ivy Report XML")
  val moduleGraphSbt = TaskKey[ModuleGraph]("module-graph-sbt",
    "The dependency graph for a project as generated from SBT data structures.")
  val asciiGraph = TaskKey[String]("dependency-graph-string",
    "Returns a string containing the ascii representation of the dependency graph for a project")
  val dependencyGraph = InputKey[Unit]("dependency-graph",
    "Prints the ascii graph to the console")
  val asciiTree = TaskKey[String]("dependency-tree-string",
    "Returns a string containing an ascii tree representation of the dependency graph for a project")
  val dependencyTree = TaskKey[Unit]("dependency-tree",
    "Prints an ascii tree of all the dependencies to the console")
  val dependencyList = TaskKey[Unit]("dependency-list",
    "Prints a list of all dependencies to the console")
  val dependencyStats = TaskKey[Unit]("dependency-stats",
    "Prints statistics for all dependencies to the console")
  val ivyReportFunction = TaskKey[String ⇒ File]("ivy-report-function",
    "A function which returns the file containing the ivy report from the ivy cache for a given configuration")
  val ivyReport = TaskKey[File]("ivy-report",
    "A task which returns the location of the ivy report file for a given configuration (default `compile`).")
  val ignoreMissingUpdate = Keys.update in ivyReport
  val filterScalaLibrary = SettingKey[Boolean]("filter-scala-library",
    "Specifies if scala dependency should be filtered in dependency-* output")

  val licenseInfo = TaskKey[Unit]("dependency-license-info",
    "Aggregates and shows information about the licenses of dependencies")

  val licenceInfoHtml = TaskKey[Unit]("dependency-license-info-html",
    "Aggregates information about the licenses of dependencies as an HTML file")
  val licenceInfoHtmlFile = SettingKey[File]("dependency-license-info-html-file",
    "The location the license HTML file should be generated at")
  val licenceInfoHtmlExcludeOrgs = SettingKey[Set[String]]("dependency-license-info-html-exclude-orgs",
    "Aggregates information about the licenses of dependencies as an HTML file")

  val licenceInfoCsv = TaskKey[Unit]("dependency-license-info-csv",
    "Aggregates information about the licenses of dependencies as a CSV file")
  val licenceInfoCsvFile = SettingKey[File]("dependency-license-info-csv-file",
    "The location the license CSV file should be generated at")
  val licenceInfoCsvExcludeOrgs = SettingKey[Set[String]]("dependency-license-info-csv-exclude-orgs",
    "Aggregates information about the licenses of dependencies as an CSV file")

  // internal
  private[graph] val moduleGraphStore = TaskKey[ModuleGraph]("module-graph-store", "The stored module-graph from the last run")
  private[graph] val whatDependsOn = InputKey[Unit]("what-depends-on", "Shows information about what depends on the given module")
  private[graph] val crossProjectId = SettingKey[ModuleID]("dependency-graph-cross-project-id")
}

object DependencyGraphKeys extends DependencyGraphKeys
