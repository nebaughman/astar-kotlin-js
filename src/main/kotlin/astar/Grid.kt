package astar

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

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

class GridMap(
  val latW: Int,
  val lonH: Int,
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
    val maxX = min(latW - 1, n.x + 1)
    val maxY = min(lonH - 1, n.y + 1)
    val list = mutableListOf<Node>()
    for (x in minX..maxX) {
      for (y in minY..maxY) {
        val gn = GridNode(x,y) // TODO: use existing instance if x,y tracked in map
        if (!isWall(gn)) list.add(gn)
      }
    }
    return list
  }

  private val walls = ArrayList<Node>()

  fun addWall(node: GridNode) {
    if (!isWall(node)) walls.add(node)
  }

  fun isWall(node: GridNode) = walls.contains(node)
}