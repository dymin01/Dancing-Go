module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    disableHostCheck: true,
    https: true,
    open: true,
    historyApiFallback: true,
    hot: true,
  },
}
