<template>
  <div class="main">
    <div class="grid pa-4">
      <grid/>
    </div>
    <div class="side pa-4">
      Controls go here
    </div>
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop } from 'vue-property-decorator'
  import Grid from "./Grid.vue"

  @Component({
    components: { Grid }
  })
  export default class Main extends Vue {
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