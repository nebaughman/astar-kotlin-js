import kotlin from "astar-kotlin-js"

// TODO: this has become rather awkward; instead, define (in kotlin) PathSpec and PathResult
export class ProgressMonitor implements kotlin.astar.Progress {
  constructor(readonly retainPaths: boolean = true) {}
  readonly paths: kotlin.astar.GridNode[][] = []
  lastPath: kotlin.astar.GridNode[] = []
  pathCount = 0
  currentPath(path: kotlin.collections.List) {
    const array = path.toArray()
    this.paths.push(array)
    this.lastPath = array // might be goal
    this.pathCount++ // redundant if retaining paths
  }
}

/**
 * Helper class to perform astar and report results
 */
export class AStarAdaptor {

  constructor(
    private readonly retainPaths: boolean,
    private readonly gridMap: kotlin.astar.GridMap,
    private readonly start: kotlin.astar.GridNode,
    private readonly goal: kotlin.astar.GridNode,
  ) {}

  findPath(): ProgressMonitor {
    const progress = new ProgressMonitor(this.retainPaths)
    const astar = new kotlin.astar.AStar(this.gridMap, this.start, this.goal)
    const path = astar.findPath(progress)
    return progress // holds last path (goal)
  }
}