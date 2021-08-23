# A* Pathfinding Demo with Kotlin/JS

[**&rarrtl; Try it out &rarrtl;**](https://nebaughman.github.io/astar-kotlin-js)

This is an experiment/demonstration of implementing a library in Kotlin targeting JavaScript/TypeScript.

- Configured IntelliJ IDEA for a [Kotlin/JS](https://kotlinlang.org/docs/js-project-setup.html) project
- Implemented [A* pathfinding algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) in Kotlin
- Produced `astar-kotlin-js.js` via `browserProductionWebpack` gradle target
- Built a `demo` [Vue-Cli](https://cli.vuejs.org/)+[Vuetify](https://vuetifyjs.com)+[TypeScript](https://www.typescriptlang.org) project that uses the library to interact with the pathfinding

> Note that the A* implementation is for demonstration purposes. I just wanted something a bit interesting to visualize. It's not optimized for production use.

## Thoughts

I learned many things along the way, which I should write down...

The build and distribution process is very manual right now. I should document how to do that...

## License

[MIT License](LICENSE.txt) &copy; Nathaniel Baughman

_Share and enjoy :)_
