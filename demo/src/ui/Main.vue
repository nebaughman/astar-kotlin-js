<template>
  <div class="main">
    <div class="content">
      <h1 class="pa-4">A* Pathfinding Implemented in Kotlin/JS</h1>
      <div class="grid-area pa-4">
        <grid :w="size" :h="size"/>
      </div>
    </div>
    <div class="side pa-4">
      <controls/>
      <version class="mt-auto"/>
    </div>
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Provide } from "vue-property-decorator"
  import { Logic } from "./Logic"
  import Grid from "./Grid.vue"
  import Controls from "./Controls.vue"
  import Version from "./Version.vue"

  @Component({
    components: { Grid, Controls, Version }
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

  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: stretch;
  }

  .grid-area {
    justify-self: center;
    align-self: center;
  }

  .side {
    border-left: 1px solid #ccc;
    min-width: 20vw;
    display: flex;
    flex-direction: column;
    align-items: stretch;
  }
</style>