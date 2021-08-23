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
  fun testWalls() {
    testPathfinding(16,
      GridNode(7,5),
      GridNode(7,6),
      GridNode(7,7),
      GridNode(7,8),
      GridNode(3,8),
      GridNode(4,8),
      GridNode(5,8),
      GridNode(6,8),
    )
  }

  @Test
  fun testNoRoute() {
    testPathfinding(8,
      GridNode(3,0),
      GridNode(3,1),
      GridNode(3,2),
      GridNode(3,3),
      GridNode(3,4),
      GridNode(3,5),
      GridNode(3,6),
      GridNode(3,7),
      expectPath = false, // walls block goal
    )
  }

  private fun testPathfinding(size: Int, vararg walls: GridNode, expectPath: Boolean = true) {
    val grid = GridMap(size,size)
    walls.forEach { grid.addWall(it) }
    val start = GridNode(0,0)
    val goal = GridNode(size - 1,size - 1)
    val astar = AStar(grid, start, goal)
    val path = astar.findPath()
    if (expectPath) {
      assertTrue { path.isNotEmpty() }
      assertEquals(start, path.first()) // start
      assertEquals(goal, path.last()) // goal
    } else {
      assertTrue { path.isEmpty() } // no path
    }
  }
}