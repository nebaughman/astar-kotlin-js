<template>
  <v-app>
    <v-main>
      <Main/>
    </v-main>
  </v-app>
</template>

<script lang="ts">
  import { Vue, Component, Prop } from 'vue-property-decorator'
  import Main from '@/ui/Main.vue'

  @Component({
    components: { Main },
  })
  export default class App extends Vue {
  }
</script>

<style>
  /*
   * https://vuetifyjs.com/en/getting-started/frequently-asked-questions/#scrollbar-overflow
   * https://stackoverflow.com/a/66208124
   */
  html {
    overflow-y: auto !important;
  }
</style>
