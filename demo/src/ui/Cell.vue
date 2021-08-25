<template>
  <div class="cell pa-1" :class="cellClass" @mousedown="handleClick" @mouseenter="handleDrag">
    <span v-if="showCoords">{{ x }},{{ y }}</span>
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

    private get isRunning() {
      return this.logic.running
    }

    private get gridNode() {
      return new kotlin.astar.GridNode(this.x, this.y)
    }

    private get showCoords() {
      return this.logic.size <= 16
    }

    //@Emit("toggle-wall")
    private handleClick() {
      if (this.isRunning || this.isStart || this.isGoal) return
      this.$emit("toggle-wall", this.gridNode)
    }

    private handleDrag(event) {
      if (this.isRunning) return
      if (event.buttons) this.$emit("add-wall", this.gridNode)
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
      return [
        this.cellType,
        this.isRunning ? "running" : false,
      ]
    }

    get cellType() {
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
    border-radius: 0px;
    background: #fff;
    font-size: 12px;
    color: #ccc;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  .cell:hover {
    background: #ff0;
    cursor: pointer;
  }

  .cell.running:hover {
    cursor: default;
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
    color: #ccc;
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