package astar

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.KClass

@JsName("GridNode")
class GridNode(
  val x: Int,
  val y: Int,
): Node {

  override fun toString() = "GridNode[$x,$y]"

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || this::class != other::class) return false
    other as GridNode
    if (x != other.x) return false
    if (y != other.y) return false
    return true
  }

  override fun hashCode(): Int {
    var result = x
    result = 31 * result + y
    return result
  }
}

@JsName("GridMap")
class GridMap(
  val lonW: Int,
  val latH: Int,
): Graph {
  // TODO: factor Graph impl out of GridMap itself?
  override fun heuristic(from: Node, goal: Node): Int {
    val a = from as GridNode
    val b = goal as GridNode
    return abs(a.x - b.x) + abs(a.y - b.y) // Manhattan distance
  }

  override fun neighbors(node: Node): List<Node> {
    val n = node as GridNode
    val minX = max(0, n.x - 1)
    val minY = max(0, n.y - 1)
    val maxX = min(lonW - 1, n.x + 1)
    val maxY = min(latH - 1, n.y + 1)
    val list = mutableListOf<Node>()
    for (x in minX..maxX) {
      for (y in minY..maxY) {
        val gn = GridNode(x,y) // TODO: use existing instance if x,y tracked in map
        if (n != gn && !isWall(gn)) list.add(gn)
      }
    }
    return list
  }

  private val wallNodes = ArrayList<GridNode>() // Set would be better, but for JS

  @JsName("walls")
  val walls get(): List<GridNode> = wallNodes

  fun addWall(x: Int, y: Int) = apply {
    addWall(GridNode(x,y))
  }

  @JsName("addWall")
  fun addWall(node: GridNode) {
    if (!isWall(node)) wallNodes.add(node)
  }

  @JsName("removeWall")
  fun removeWall(node: GridNode) {
    wallNodes.remove(node)
  }

  @JsName("toggleWall")
  fun toggleWall(node: GridNode) {
    if (isWall(node)) removeWall(node) else addWall(node)
  }

  @JsName("isWall")
  fun isWall(node: GridNode) = wallNodes.contains(node)
}

// TODO: instead, use Kotlin Serialization (JSON or ProtoBuf)
/**
 * Helper to export/parse GridMap to/from string form.
 * Do not use this for persistent long-lived data; format may change.
 * Only use for current runtime transforms.
 *
 * Specific use case is to post data to/from Service Worker process.
 * Not using JSON because it's much more verbose than needed.
 * Could integrate with respective classes, but separating serialization.
 */
@JsName("GridParser")
object GridParser {
  @JsName("exportGridMap")
  fun export(grid: GridMap): String {
    val list = mutableListOf(grid.lonW, grid.latH)
    grid.walls.forEach { list.add(it.x); list.add(it.y) }
    return list.joinToString(",")
  }

  @JsName("parseGridMap")
  fun parseGridMap(str: String): GridMap {
    val list = str.split(',').toMutableList()
    val grid = GridMap(list.removeFirst().toInt(), list.removeFirst().toInt())
    while (list.isNotEmpty()) {
      grid.addWall(list.removeFirst().toInt(), list.removeFirst().toInt())
    }
    return grid
  }

  @JsName("exportGridNode")
  fun export(node: GridNode) = "${node.x},${node.y}"

  @JsName("parseGridNode")
  fun parseGridNode(str: String) = str.split(',').let {
    GridNode(it.first().toInt(), it.last().toInt())
  }

  /*
  fun <T:Any> parse(str: String, type: KClass<T>): T = when (type) {
    GridMap::class -> parseGridMap(str) as T
    GridNode::class -> parseGridNode(str) as T
    else -> throw IllegalArgumentException("Unsupported type $type")
  }
  */
}