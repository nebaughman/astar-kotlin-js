<template>
  <div class="d-flex flex-column align-stretch">
    <div class="d-flex flex-row justify-space-around">
      <legend-label color="#00f">Start</legend-label>
      <legend-label color="#0f0">Goal</legend-label>
      <legend-label color="#000">Wall</legend-label>
    </div>
    <span class="howto text-center">Click/drag to draw walls</span>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop } from "vue-property-decorator"
  import LegendLabel from "./LegendLabel.vue"

  @Component({
    components: { LegendLabel }
  })
  export default class Legend extends Vue {
  }
</script>

<style scoped>
  .howto {
    color: #ccc;
  }
</style>