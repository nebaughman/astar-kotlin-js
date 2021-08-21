<template>
  <div class="cell pa-1" :class="cellClass" @click="handleClick">
    {{ x }},{{ y }}
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Inject, Emit } from "vue-property-decorator"
  import kotlin from "astar-kotlin-js"
  import { Store } from "./Store"

  @Component
  export default class Cell extends Vue {
    @Inject() readonly store!: Store

    @Prop(Number) readonly x!: number // TODO: take GridNode rather than x,y
    @Prop(Number) readonly y!: number

    private get gridNode() {
      return new kotlin.astar.GridNode(this.x, this.y)
    }

    @Emit("cell-click")
    handleClick() {
      return this.gridNode
      //this.store.gridMap.addWall(this.gridNode) // TODO: parent should manage state
      //return { x: this.x, y: this.y } // TODO: return GridNode? (probably should)
    }

    get isWall() {
      return this.store.gridMap.isWall(this.gridNode)
    }

    get cellClass() {
      return this.isWall ? "wall" : false
    }
  }
</script>

<style scoped>
  .cell {
    border: thin solid #ccc;
    border-radius: 2px;
    background: #fff;
    font-size: 12px;
    color: #666;
  }

  .cell:hover {
    background: #ff0;
    cursor: pointer;
  }

  .wall {
    color: #ccc;
    background: #000;
  }

  .wall:hover {
    background: #666
  }
</style>