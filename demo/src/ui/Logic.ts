import VueStore from "vue-class-store"
import kotlin from "astar-kotlin-js"

@VueStore
export class Logic {
  constructor(
    readonly size: number,
  ) {}

  // TODO: adjustable size; gridMap as computed getter (but preserve its state?)
  readonly gridMap = new kotlin.astar.GridMap(this.size, this.size)

  start = new kotlin.astar.GridNode(0,0)
  goal = new kotlin.astar.GridNode(this.size - 1, this.size - 1)

  isStart(node: kotlin.astar.GridNode) {
    return this.start.equals(node)
  }

  isGoal(node: kotlin.astar.GridNode) {
    return this.goal.equals(node)
  }

  isWall(node: kotlin.astar.GridNode) {
    return this.gridMap.isWall(node)
  }

  private p_running = false
  get running(): boolean { return this.p_running }

  private p_lastPath: kotlin.astar.GridNode[] = []
  get lastPath() { return this.p_lastPath }
  onPath(node: kotlin.astar.GridNode) { return this.lastPath.some(p => p.equals(node)) }

  private p_noPath = false
  get noPath() { return this.p_noPath }

  async findPath() {
    this.p_running = true
    this.p_lastPath = []
    await new Promise(resolve => setTimeout(resolve, 100)) // give ui bit of time
    this.p_lastPath = await this.runAStar()
    this.p_noPath = this.p_lastPath.length == 0
    this.p_running = false
  }

  private async runAStar() {
    const astar = new kotlin.astar.AStar(this.gridMap, this.start, this.goal)
    const path = astar.findPath()
    return path.isEmpty() ? [] : path.toArray()
  }
}