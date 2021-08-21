<template>
  <div class="main">
    <div class="grid pa-4">
      <grid :w="size" :h="size"/>
    </div>
    <div class="side pa-4">
      Controls go here
    </div>
  </div>
</template>

<script lang="ts">
  import { Vue, Component, Prop, Provide } from 'vue-property-decorator'
  import Grid from "./Grid.vue"
  import { Store } from "./Store"

  @Component({
    components: { Grid }
  })
  export default class Main extends Vue {
    @Provide() readonly store = new Store(16)
    get size() { return this.store.size }
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