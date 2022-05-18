import { Route, Routes, Link } from "react-router-dom"
import Home from "./pages/Home"
import NotFound from "./pages/NotFound"
import Survey from "./pages/Survey"
import Results from "./pages/Results"
import Logo from "./favicon.svg";

function App() {

  return (
    <div className="flex flex-col items-center h-full">
      <div 
        className="bg-teal-blue w-full text-center 
          flex flex-row items-center justify-center
          shadow-lg"
        >
        <span className="flex justify-center items-center gap-4 shadow-sm">
          <img src={Logo} alt="KyselyApp logo" className="h-8" />
          <Link
            className="text-3xl mt-2 mb-2"
            to={"/"}>
            KyselyApp
          </Link>
          <img src={Logo} alt="KyselyApp logo" className="h-8" />
        </span>
      </div>
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
