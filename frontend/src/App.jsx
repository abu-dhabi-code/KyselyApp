import { Route, Routes, Link } from "react-router-dom"
import Home from "./pages/Home"
import NotFound from "./pages/NotFound"
import Survey from "./pages/Survey"
import Results from "./pages/Results"

function App() {

  return (
    <div className="flex flex-col items-center h-full">
      <div className="bg-teal-blue w-full text-center flex flex-row items-center justify-center">
        <span className="invisible ml-4 mr-auto w-28"></span>
        <Link
          className="text-3xl mt-2 mb-2"
          to={"/"}>
            KyselyApp
        </Link>
        <a href="/addsurvey" className="btn-primary ml-auto mr-4">Create survey</a>
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
