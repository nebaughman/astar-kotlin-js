<template>
  <div class="controls d-flex flex-column">
    <Legend/>
    <v-divider/>
    <grid-size/>
    <v-divider/>
    <div class="d-flex flex-row justify-space-between">
      <v-btn :disabled="running" :loading="running" @click="handleGo" class="flex-grow-1">Go</v-btn>
      <v-switch label="Slow" v-model="logic.slowPlayback" class="align-end mt-0 ml-3"/>
    </div>
    <path-status/>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop, Inject } from "vue-property-decorator"
  import kotlin from "astar-kotlin-js"
  import { Logic } from "./Logic"
  import Legend from "./Legend.vue"
  import PathStatus from "./PathStatus.vue"
  import GridSize from "./GridSize.vue"

  @Component({
    components: { Legend, PathStatus, GridSize }
  })
  export default class Controls extends Vue {
    @Inject() readonly logic!: Logic

    private get running() { return this.logic.running }
    private handleGo() { this.logic.findPath() }
  }
</script>

<style scoped>
  .controls {
    gap: 16px;
  }
</style>