<template>
  <div class="controls d-flex flex-column">
    <v-btn :disabled="running" :loading="running" @click="handleGo">Go</v-btn>
    <div v-if="hasPath">
      Length {{ pathLength }}
    </div>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop, Inject } from "vue-property-decorator"
  import { Logic } from "./Logic"
  import kotlin from "astar-kotlin-js"

  @Component({
    components: { }
  })
  export default class Controls extends Vue {
    @Inject() readonly logic!: Logic
    private get running() { return this.logic.running }
    private get lastPath() { return this.logic.lastPath }
    private get pathLength() { return this.lastPath?.length || 0 }
    private get hasPath() { return !!this.pathLength }
    private handleGo() { this.logic.runAStar() }
  }
</script>

<style scoped>
  .controls {
    gap: 16px;
  }
</style>