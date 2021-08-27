<template>
  <div>
    <v-slider
      min="16"
      max="32"
      step="8"
      ticks="always"
      tick-size="4"
      :tick-labels="labels"
      thumb-size="24"
      :thumb-label="false"
      label="Grid"
      dense
      :disabled="running"
      v-model="size"
      class="custom-slider"
    />
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop, Inject, Watch } from "vue-property-decorator"
  import { Logic } from "./Logic"

  @Component
  export default class GridSize extends Vue {
    @Inject() readonly logic!: Logic

    private size = 16
    private labels = ["16","24","32"]

    private get running() { return this.logic.running }

    @Watch("size")
    private onSize(size: number) {
      setTimeout(() => this.logic.setGridSize(size), 500) // give ui moment to update
    }
  }
</script>

<style scoped>
  >>> .custom-slider .v-slider__tick--filled {
    background-color: #00000080 !important; /* override lighter dots */
  }
</style>