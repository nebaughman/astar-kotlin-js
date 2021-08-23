module.exports = {
  transpileDependencies: [
    'vuetify'
  ],

  // relative paths in production (eg, GitHub Pages deployment)
  // https://cli.vuejs.org/config/#publicpath
  publicPath: process.env.NODE_ENV === "production" ? "./" : "/",
}
