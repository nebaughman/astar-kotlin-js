<template>
  <div class="d-flex flex-row align-center">
    <div class="icon mr-1" :style="iconStyle"></div>
    <span><slot/></span>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component, Prop } from "vue-property-decorator"

  @Component
  export default class LegendLabel extends Vue {
    @Prop(String) color!: string

    get iconStyle() {
      return {
        "background-color": this.color
      }
    }
  }
</script>

<style scoped>
  .icon {
    width: 8px;
    height: 8px;
  }
</style>