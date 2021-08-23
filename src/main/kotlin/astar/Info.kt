package astar

external val require: dynamic

// TODO: is this right?!? Seems really sketchy.
// https://stackoverflow.com/a/50739724
/**
 * Useful library info
 */
object Info {
  val name = require("../package.json").name as String
  val version = require("../package.json").version as String
  val fullVersion = "$name v$version"
}