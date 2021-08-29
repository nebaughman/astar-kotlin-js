# A* Pathfinding Demo with Kotlin/JS

[**Try it out &rarrtl;**](https://nebaughman.github.io/astar-kotlin-js)

This is an experiment/demonstration of implementing a library in Kotlin targeting JavaScript/TypeScript.

- IntelliJ IDEA Kotlin/JS Gradle project
- [A* pathfinding algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) implemented in Kotlin
- Gradle `browserProductionWebpack` target produces `astar-kotlin-js.js` library
- `demo` Vue project uses `astar-kotlin-js.js` library to visualize pathfinding

> Note that the A* implementation is for demonstration purposes. I just wanted something a bit interesting to visualize. It's not optimized for production use, and might not even be correct.

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

## Development

Initial setup:

- In `build/js/packages/astar-kotlin-js` run [`yarn link`](https://classic.yarnpkg.com/en/docs/cli/link/)
- In `demo` run `yarn link astar-kotlin-js`

During development:

- In `demo` run `yarn serve` for hot-reloading development server
- Run `browserDevelopmentWebpack` gradle task after making any kotlin code changes

Active `yarn serve` process will pick up any changes.

## Release Process

Prefer not to make code changes with the release commit, just update version and build docs. Always start (and develop) in `develop` branch. Never make changes directly to `master`. Merging to `master` defines a release, must be tagged, and will trigger CI/CD (just GitHub Pages hosting at this point).

- `git checkout develop`
- Update `build.gradle` with version `X.Y.Z`
- For GitHub Pages hosting:
  - Run `browserProductionWebpack` gradle task
  - In `demo` run `yarn build`
  - Remove old `docs` & move `demo/dist` to `docs`
  - Remove `docs/js/*.map` sourcemaps (unneeded in repo)
  - In `docs` run `http-server` to see if distribution works
- `git commit -am "vX.Y.Z"`
- `git checkout master && git merge develop && git tag X.Y.Z` # no `v`
- `git push --all && git push --tags`
- `git checkout develop` # back to work

> GitHub Pages is hosting `/docs` off `master` branch.

> TODO: These steps could be more automated (especially the `docs` build).

## License

[MIT License](LICENSE.txt) &copy; Nathaniel Baughman

_Share and enjoy :)_
