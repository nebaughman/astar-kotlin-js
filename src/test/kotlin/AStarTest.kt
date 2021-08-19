import astar.AStar
import astar.GridMap
import astar.GridNode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AStarTest {
  @Test
  fun testAStar() {
    val grid = GridMap(10, 10).also {
      it.addWall(GridNode(1,1))
    }
    val astar = AStar(grid, GridNode(0,0), GridNode(9,9))
    val path = astar.findPath()
    assertTrue { path.isNotEmpty() }
    assertEquals(GridNode(0,0), path.first()) // start
    assertEquals(GridNode(9,9), path.last()) // goal
    assertFalse { path.contains(GridNode(1,1)) } // wall
    //println(path)
  }
}