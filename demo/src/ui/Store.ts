import VueStore from "vue-class-store"
import kotlin from "astar-kotlin-js"

@VueStore
export class Store {
  constructor(
    readonly size: number,
  ) {}

  // TODO: adjustable size; gridMap as computed getter (but preserve its state?)
  readonly gridMap = new kotlin.astar.GridMap(this.size, this.size)
}