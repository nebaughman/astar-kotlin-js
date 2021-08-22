<template>
  <div class="cell pa-1" :class="cellClass" @click="handleClick">
    {{ x }},{{ y }}
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Inject, Emit } from "vue-property-decorator"
  import { Logic } from "./Logic"
  import kotlin from "astar-kotlin-js"

  @Component
  export default class Cell extends Vue {
    @Inject() readonly logic!: Logic

    @Prop(Number) readonly x!: number // TODO: take GridNode rather than x,y
    @Prop(Number) readonly y!: number

    private get gridNode() {
      return new kotlin.astar.GridNode(this.x, this.y)
    }

    //@Emit("cell-click")
    private handleClick() {
      if (this.isStart || this.isGoal) return
      this.$emit("cell-click", this.gridNode)
    }

    get isWall() {
      return this.logic.isWall(this.gridNode)
    }

    get isStart() {
      return this.logic.isStart(this.gridNode)
    }

    get isGoal() {
      return this.logic.isGoal(this.gridNode)
    }

    get onPath() {
      return this.logic.onPath(this.gridNode)
    }

    get cellClass() {
      if (this.isStart) return "start"
      if (this.isGoal) return "goal"
      if (this.isWall) return "wall"
      if (this.onPath) return "path"
      return false
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
    background: #666;
  }

  .start {
    background: #00f;
  }

  .goal {
    background: #0f0;
  }

  .start:hover, .goal:hover {
    cursor: default;
  }

  .path {
    background: aqua;
  }
</style>