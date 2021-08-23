<template>
  <div class="version d-flex flex-column align-center">
    <span>Learn more at GitHub</span>
    <a href="https://github.com/nebaughman/astar-kotlin-js" target="_blank">
      {{ library }}
    </a>
  </div>
</template>

<script lang="ts">  
  import { Vue, Component } from "vue-property-decorator"
  import kotlin from "astar-kotlin-js"

  @Component
  export default class Version extends Vue {
    get library() { return kotlin.astar.Info.fullVersion }
  }
</script>

<style scoped>
  .version {
    color: #666;
  }
</style>