<template>
  <div class="grid" :style="gridStyle">
    <cell
      v-for="n in size"
      :key="n-1"
      :x="(n-1) % w"
      :y="Math.floor((n-1) / w)"
      @cell-click="cellClick"
    />
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Inject } from "vue-property-decorator"
  import kotlin from "astar-kotlin-js"
  import { Store } from "./Store"
  import Cell from "./Cell.vue"

  @Component({
    components: { Cell }
  })
  export default class Grid extends Vue {
    @Inject() readonly store!: Store

    @Prop(Number) readonly w!: number
    @Prop(Number) readonly h!: number

    get size() { return (this.w && this.h) ? this.w * this.h : 0 }

    get gridStyle() {
      if (!this.size) return false
      return {
        "grid-template-columns": `repeat(${this.w},1fr)`
      }
    }

    cellClick(node: kotlin.astar.GridNode) {
      this.store.gridMap.addWall(node)
    }
  }
</script>

<style scoped>
  .grid {
    width: 80vh;
    height: 80vh;
    background: #eee;
    border: 1px solid #ccc;
    border-radius: 4px;
    display: grid;
  }
</style>