<template>
  <div class="grid" :style="gridStyle">
    <cell
      v-for="n in size"
      :key="n-1"
      :x="(n-1) % w"
      :y="Math.floor((n-1) / w)"
      @toggle-wall="toggleWall"
      @add-wall="addWall"
    />
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Inject } from "vue-property-decorator"
  import kotlin from "astar-kotlin-js"
  import { Logic } from "./Logic"
  import Cell from "./Cell.vue"

  @Component({
    components: { Cell }
  })
  export default class Grid extends Vue {
    @Inject() readonly logic!: Logic

    @Prop(Number) readonly w!: number
    @Prop(Number) readonly h!: number

    get size() { return (this.w && this.h) ? this.w * this.h : 0 }

    get gridStyle() {
      if (!this.size) return false
      return {
        "grid-template-columns": `repeat(${this.w},1fr)`
      }
    }

    toggleWall(node: kotlin.astar.GridNode) {
      this.logic.gridMap.toggleWall(node)
    }

    addWall(node: kotlin.astar.GridNode) {
      this.logic.gridMap.addWall(node)
    }
  }
</script>

<style scoped>
  .grid {
    width: 80vh;
    height: 80vh;
    background: #eee;
    /*border: 1px solid #ccc;*/
    border-radius: 0px;
    display: grid;
    user-select: none;
  }
</style>