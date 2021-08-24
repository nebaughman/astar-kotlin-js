<template>
  <div class="text-center">
    <div v-if="running">
      Computing...
    </div>
    <div v-else-if="hasPath">
      Shortest Path: {{ pathLength }} {{ pluralize(pathLength, "node", "nodes") }}
    </div>
    <div v-else-if="noPath">
      No path to destination
    </div>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop, Inject } from "vue-property-decorator"
  import { Logic } from "./Logic"

  @Component
  export default class PathStatus extends Vue {
    @Inject() readonly logic!: Logic

    private get running() { return this.logic.running }
    private get lastPath() { return this.logic.lastPath }
    private get pathLength() { return this.lastPath?.length || 0 }
    private get hasPath() { return !!this.pathLength }
    private get noPath() { return this.logic.noPath }

    private pluralize(value: number, singular: string, plural: string) {
      return value == 1 ? singular : plural
    }
  }
</script>

<style scoped>
</style>