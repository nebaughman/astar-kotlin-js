<template>
  <div class="version d-flex flex-column align-center">
    <span>Learn more at GitHub</span>
    <a href="https://github.com/nebaughman/astar-kotlin-js" target="_blank">
      astar-kotlin-js {{ version }}
    </a>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component } from "vue-property-decorator"

  @Component
  export default class Version extends Vue {
    get version() {
      return "v0.0.0" // TODO: from astar library (or at least local package.json)
    }
  }
</script>

<style scoped>
  .version {
    color: #666;
  }
</style>