module.exports = {
  mode: 'jit',
  content: [
    "./src/**/*.{html,js,jsx}",
    "./index.html",
    "../KyselyApp/src/main/resources/templates/**/*.html"
  ],
  theme: {
    extend: {
      colors: {
        'dk-byzantium': '#482C3D',
        'sweet-brown': '#AC3931',
        'tea-green': '#C4F1BE',
        'lt-yellow': '#F8FFE5',
        'teal-blue': '#537D8D',
      }
    },
  },
  plugins: [],
}
