# A* Pathfinding Demo with Kotlin/JS

[**Try it out &rarrtl;**](https://nebaughman.github.io/astar-kotlin-js)

This is an experiment/demonstration of implementing a library in Kotlin targeting JavaScript/TypeScript.

- IntelliJ IDEA Kotlin/JS Gradle project
- [A* pathfinding algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) implemented in Kotlin
- Gradle `browserProductionWebpack` target produces `astar-kotlin-js.js` library
- `demo` Vue project uses `astar-kotlin-js.js` library to visualize pathfinding

> Note that the A* implementation is for demonstration purposes. I just wanted something a bit interesting to visualize. It's not optimized for production use.

## Ecosystem

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Kotlin/JS](https://kotlinlang.org/docs/js-project-setup.html)
- [Gradle](https://gradle.org/)
- [Kotlin](https://kotlinlang.org/)
- [Vue](https://vuejs.org/)
- [Vue-Cli](https://cli.vuejs.org)
- [Vuetify](https://vuetifyjs.com)
- [Vue Class Component](https://github.com/vuejs/vue-class-component)
- [Vue Property Decorator](https://github.com/kaorun343/vue-property-decorator)
- [Vue Class Store](https://github.com/davestewart/vue-class-store)
- [TypeScript](https://www.typescriptlang.org)
- [GitHub](https://github.com/) [Pages](https://pages.github.com/)
- [Coffee](https://www.buymeacoffee.com/nebaughman)

## Thoughts

I learned many things along the way, which I should write down... _more to follow..._

## Build Process

> This is very clunky and manual. Needs to be improved. 
> Build products (`/demo/lib`) should not be in repo (except for `/docs` for GitHub Pages).
> Try using `yarn link` rather than `yarn add` and point to proper astar module build path.

- Run gradle target `browserProductionWebpack`
- Copy `distributions/astar-kotlin-js.js` (and `.map`) to `demo/lib/astar`
- From `/demo` project...
- Run `yarn upgrade astar-kotlin-js` (if already added)
- Run `yarn build` (or `yarn serve` for development)
- Move `dist` `/docs` (for GitHub Pages hosting)

## Release Process

- `git checkout develop`
- Update `build.gradle` with version `X.Y.Z`
- `git commit -am "vX.Y.Z`"
- `git checkout master && git merge develop && git tag X.Y.Z` // no `v`
- `git push --all && git push --tags`
- `git checkout develop` // back to work

> GitHub Pages is hosting `/docs` off `master` branch.

## License

[MIT License](LICENSE.txt) &copy; Nathaniel Baughman

_Share and enjoy :)_
