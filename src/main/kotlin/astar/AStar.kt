package astar

interface Node

/**
 * A graph of Nodes representing the map.
 * The geometry of the Nodes is not the concern of the pathfinding algorithm.
 *
 * Node instances produced by the graph must either be consistent instances (same instance each time)
 * or implement equals(other) such that two Node instances representing the same graph node are equal.
 */
interface Graph {
  fun heuristic(from: Node, goal: Node): Int
  fun neighbors(node: Node): List<Node>
}

/*
interface Pathfinder {
  fun findPath(start: Node, goal: Node): List<Node>
}

class PathLibrary(
  private val graph: Graph,
): Pathfinder {
  fun findPath(start: Node, goal: Node): List<Node> {
    // if already found, return; else use AStar to find new path
    // (ideally reuse results of prior findings)
  }
}
*/

/**
 * A Node with other bookkeeping for the AStar process
 */
class ANode(val node: Node) {
  var from: ANode? = null
  var score: Int = Int.MAX_VALUE // known cost to this node
  var guess: Int = Int.MAX_VALUE // heuristic cost to goal through this node
}

// https://en.wikipedia.org/wiki/A*_search_algorithm
@JsName("AStar")
class AStar(
  private val graph: Graph,
  private val start: Node,
  private val goal: Node,
) {
  private val openSet = mutableListOf(ANode(start).also {
    it.score = 0
    it.guess = graph.heuristic(start, goal)
  })

  @JsName("findPath")
  fun findPath(): List<Node> {
    while (openSet.isNotEmpty()) {
      val current = openSet.minByOrNull(ANode::guess)!! // TODO: openSet as priority queue to avoid O(n) search
      if (current.node == goal) return constructPath(current)
      openSet.remove(current)
      graph.neighbors(current.node).forEach { n ->
        // TODO: this is poor performance; maybe keep scores in Map<Node,Int> or keep Map<Node,ANode>
        //  In either case, Node must have quality equals and hashCode.
        val neighbor = openSet.find { it.node == n } ?: ANode(n)
        val score = current.score + 1 // TODO: graph provides weights from current to neighbors
        if (score < neighbor.score) {
          neighbor.from = current
          neighbor.score = score
          neighbor.guess = score + graph.heuristic(neighbor.node, goal) // TODO: heuristic part of Node?
          if (!openSet.contains(neighbor)) openSet.add(neighbor)
        }
      }
    }
    return emptyList() // no path to goal
  }

  private fun constructPath(goal: ANode): List<Node> {
    val path = mutableListOf<Node>()
    var prev: ANode? = goal
    while (prev != null) {
      path.add(prev.node) //path.add(0, next.node)
      prev = prev.from
    }
    return path.asReversed()
  }
}