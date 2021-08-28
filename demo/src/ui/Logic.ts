import VueStore from "vue-class-store"
import kotlin from "astar-kotlin-js"
import { AStarAdaptor, ProgressMonitor } from "./AStar"

//@ts-ignore
import Worker from "worker-loader!./Worker.js"

@VueStore
export class Logic {
  constructor(
    private initialSize: number,
  ) {}

  // TODO: adjustable size; gridMap as computed getter (but preserve its state?)
  private p_gridMap = new kotlin.astar.GridMap(this.initialSize, this.initialSize)
  get gridMap() { return this.p_gridMap }

  get size() { return this.p_gridMap.lonW } // assuming symmetric
  get lonW() { return this.p_gridMap.lonW }
  get latH() { return this.p_gridMap.latH }

  setGridSize(size: number) {
    this.p_gridMap = new kotlin.astar.GridMap(size, size)
    this.goal = new kotlin.astar.GridNode(size - 1, size - 1)
    this.p_lastPath = []
  }

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

  onPath(node: kotlin.astar.GridNode) {
    return this.lastPath.some(p => p.equals(node))
  }

  private p_running = false
  get running(): boolean { return this.p_running }

  private p_lastPath: kotlin.astar.GridNode[] = []
  get lastPath() { return this.p_lastPath }

  // number of paths evaluated
  private p_pathCount = 0
  get pathCount() { return this.p_pathCount }

  private p_noPath = false
  get noPath() { return this.p_noPath }

  slowPlayback = false

  findPath() {
    this.p_running = true
    this.p_lastPath = []
    this.p_pathCount = 0
    this.postAStar()
  }

  /**
   * Process pathfinding on Worker instance
   */
  private postAStar() {
    const worker = new Worker()
    worker.onmessage = (event:any) => {
      this.showResult(event.data)
      worker.terminate() // not clear if this is important
    }
    worker.postMessage({
      retainPaths: this.slowPlayback, // retain paths if playing back slow
      gridMap: kotlin.astar.GridParser.exportGridMap(this.gridMap),
      start: kotlin.astar.GridParser.exportGridNode(this.start),
      goal: kotlin.astar.GridParser.exportGridNode(this.goal),
    })
  }

  private async showResult(result: ProgressMonitor) {
    if (this.slowPlayback) {
      for (var i = 0; i < result.pathCount; i++) {
        this.p_lastPath = result.paths[i].map(n => new kotlin.astar.GridNode(n.x, n.y))
        await this.stall(10)
        if (!this.slowPlayback) break
      }
    }
    this.p_lastPath = result.lastPath.map(n => new kotlin.astar.GridNode(n.x, n.y))
    this.p_pathCount = result.pathCount
    this.p_noPath = this.p_lastPath.length == 0
    this.p_running = false
  }

  private async stall(ms: number) {
    await new Promise(resolve => setTimeout(resolve, ms))
  }
}