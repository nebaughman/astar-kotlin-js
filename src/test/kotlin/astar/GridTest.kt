package astar

import kotlin.test.Test
import kotlin.test.assertEquals

class GridTest {
  @Test
  fun testNeighbors() {
    val grid = GridMap(8, 8)

    grid.neighbors(GridNode(0,0)).let {
      assertEquals(3, it.size)
      assertEquals(setOf(GridNode(1,0), GridNode(1,1), GridNode(0,1)), it.toSet()) // any order
    }

    grid.neighbors(GridNode(7,0)).let {
      assertEquals(3, it.size)
      assertEquals(setOf(GridNode(6,0), GridNode(6,1), GridNode(7,1)), it.toSet()) // any order
    }

    grid.neighbors(GridNode(7,7)).let {
      assertEquals(3, it.size)
      assertEquals(setOf(GridNode(6,7), GridNode(6,6), GridNode(7,6)), it.toSet()) // any order
    }

    grid.neighbors(GridNode(0,7)).let {
      assertEquals(3, it.size)
      assertEquals(setOf(GridNode(0,6), GridNode(1,6), GridNode(1,7)), it.toSet()) // any order
    }

    grid.neighbors(GridNode(1, 1)).let {
      assertEquals(8, it.size)
      assertEquals(setOf(
        GridNode(0,0), GridNode(1,0), GridNode(2,0),
        GridNode(0, 1), GridNode(2, 1),
        GridNode(0, 2), GridNode(1, 2), GridNode(2, 2),
      ), it.toSet()) // any order
    }

    grid.addWall(GridNode(2,2))

    grid.neighbors(GridNode(1, 1)).let {
      assertEquals(7, it.size)
      assertEquals(setOf(
        GridNode(0,0), GridNode(1,0), GridNode(2,0),
        GridNode(0, 1), GridNode(2, 1),
        GridNode(0, 2), GridNode(1, 2), // GridNode(2, 2), // no walls
      ), it.toSet()) // any order
    }
  }

  @Test
  fun testHeuristic() {
    val grid = GridMap(4, 4)
    assertEquals(3, grid.heuristic(GridNode(0,0), GridNode(3, 3)))
  }
}