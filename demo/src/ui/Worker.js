import { AStarAdaptor } from "./AStar"
import kotlin from "astar-kotlin-js"

/*
 * Web Worker to compute pathfinding separate from the main JS thread.
 */

// TODO: TypeScript

/**
 * Message receiver for this worker
 */
onmessage = (event) => {
  //console.log("Worker received", event.data) // TODO: loglevel
  compute(event.data) // payload from host
}

function compute(data) {
  const result = new AStarAdaptor(
    data.retainPaths,
    kotlin.astar.GridParser.parseGridMap(data.gridMap),
    kotlin.astar.GridParser.parseGridNode(data.start),
    kotlin.astar.GridParser.parseGridNode(data.goal),
  ).findPath()
  postMessage(result)
  // TODO: consider processing result into intermediate string format;
  //  It won't be the right type (ProgressMonitor) on the other side anyway,
  //  so might as well return terse data that can be easily used by logic;
  //  Maybe use kotlin serialization to/from json to preserve typing on each side.
}
