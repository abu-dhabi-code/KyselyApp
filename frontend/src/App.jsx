import { Route, Routes, Link } from "react-router-dom"
import Home from "./pages/Home"
import NotFound from "./pages/NotFound"
import Survey from "./pages/Survey"
import Results from "./pages/Results"
import Logo from "./favicon.svg";
import textFiller from "./utils/textFiller"

function App() {

  return (
    <div className="flex flex-col items-center min-h-screen">
      <div 
        className="bg-teal-blue w-full text-center 
          flex flex-row items-center justify-center
          shadow-lg z-0 relative"
        >
        <p 
          className="font-metro 
          text-5xl m-0 p-0 
          text-error 
          absolute top-0 left-0
          text-left h-full w-full
          -z-10 overflow-hidden"
        > 
          { textFiller(3) }
        </p>
        <span className="flex bg-black bg-opacity-40 w-full px-5 justify-center items-center gap-4 shadow-sm">
          <img src={Logo} alt="KyselyApp logo" className="h-8" />
          <Link
            className="text-3xl mt-2 mb-2 hover:text-black transition-colors"
            to={"/"}>
            KyselyApp
          </Link>
          <img src={Logo} alt="KyselyApp logo" className="h-8" />
        </span>
      </div>
      {/*<p 
          className="font-box 
          text-5xl m-0 p-0 
          text-dk-byzantium
          filter brightness-95 
          absolute top-0 left-0
          text-left h-full w-full
          -z-10 overflow-hidden"
        > 
         { textFiller(5) }
  </p>*/}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/survey/:id" element={<Survey />} />
        <Route path="/survey/:id/answers" element={<Results />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  )
}

export default App
