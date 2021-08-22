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
    val grid = GridMap(8, 8).also {
      it.addWall(GridNode(1,1))
      assertTrue(it.isWall(GridNode(1,1)))
    }
    val astar = AStar(grid, GridNode(0,0), GridNode(7,7))
    val path = astar.findPath()
    assertTrue { path.isNotEmpty() }
    assertEquals(GridNode(0,0), path.first()) // start
    assertEquals(GridNode(7,7), path.last()) // goal
    assertFalse { path.contains(GridNode(1,1)) } // wall
    //println(path)
  }

  /**
   * This pattern caused an infinite loop in practice (due to faulty astar impl)
   */
  @Test
  fun testPathfinding() {
    val grid = GridMap(16,16)
      .addWall(7,5)
      .addWall(7,6)
      .addWall(7,7)
      .addWall(7,8)
      .addWall(3,8)
      .addWall(4,8)
      .addWall(5,8)
      .addWall(6,8)
    val astar = AStar(grid, GridNode(0,0), GridNode(15,15))
    val path = astar.findPath()
    assertTrue { path.isNotEmpty() }
    assertEquals(GridNode(0,0), path.first()) // start
    assertEquals(GridNode(15,15), path.last()) // goal
  }
}