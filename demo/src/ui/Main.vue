<template>
  <div class="main">
    <div class="grid pa-4">
      <grid :w="size" :h="size"/>
    </div>
    <div class="side pa-4">
      <controls/>
    </div>
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Provide } from "vue-property-decorator"
  import { Logic } from "./Logic"
  import Grid from "./Grid.vue"
  import Controls from "./Controls.vue"

  @Component({
    components: { Grid, Controls }
  })
  export default class Main extends Vue {
    @Provide() readonly logic = new Logic(16)
    get size() { return this.logic.size }
  }
</script>

<style scoped>
  .main {
    display: grid;
    grid-template-columns: 1fr auto;
    grid-template-rows: 100vh;
  }

  .grid {
    justify-self: center;
    align-self: center;
  }

  .side {
    border-left: 1px solid #ccc;
    min-width: 20vw;
  }
</style>