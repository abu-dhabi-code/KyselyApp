module.exports = {
  mode: 'jit',
  content: [
    "./src/**/*.{html,js,jsx}",
    "./index.html",
    "../KyselyApp/src/main/resources/templates/**/*.html",
    "./templates/**/*.html"
  ],
  theme: {
    extend: {
      colors: {
        'dk-byzantium': '#482C3D',
        'sweet-brown': '#AC3931',
        'tea-green': '#93d08b',
        'lt-yellow': '#F8FFE5',
        'teal-blue': '#537D8D',
        'error': '#F02850',
        'warn': '#DF5018',
        'success': '#74D234'
      }
    },
  },
  plugins: [],
}
