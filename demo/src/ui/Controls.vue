<template>
  <div class="controls d-flex flex-column">
    <Legend/>
    <v-divider/>
    <v-btn :disabled="running" :loading="running" @click="handleGo">Go</v-btn>
    <path-status/>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop, Inject } from "vue-property-decorator"
  import kotlin from "astar-kotlin-js"
  import { Logic } from "./Logic"
  import Legend from "./Legend.vue"
  import PathStatus from "./PathStatus.vue"

  @Component({
    components: { Legend, PathStatus }
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