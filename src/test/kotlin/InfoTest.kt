import astar.Info
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InfoTest {
  @Test
  fun testInfo() {
    println(Info.fullVersion)
    assertEquals("${Info.name} v${Info.version}", Info.fullVersion)
    assertTrue(Info.name.isNotBlank())
    assertTrue(Info.version.isNotBlank())
  }
}