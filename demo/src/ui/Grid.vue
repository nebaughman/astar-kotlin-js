<template>
  <div class="grid pa-4">
    Grid goes here
  </div>
</template>

<script lang="ts">
  import Vue from 'vue'
  import Component from 'vue-class-component'

  @Component({
    components: { }
  })
  export default class Grid extends Vue {
  }
</script>

<style scoped>
  .grid {
    width: 80vh;
    height: 80vh;
    background: #eee;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
</style>