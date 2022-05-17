import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { getSurveys } from "../utils/api";

function Home() {

  /**
  * @type {[
  *  import("../utils/api").Survey[], 
  *  React.Dispatch<React.SetStateAction<import("../utils/api").Survey[]>>
  * ]}
  */
  const [surveys, setSurveys] = useState([]);

  useEffect(() => {
    getSurveys()
      .then(data => setSurveys(data));
  }, []);

  return (
    <div className="flex flex-col items-center h-full">
      <h1 className="text-3xl mt-3 mb-6">Home</h1>
      <div className="flex flex-col items-stretch gap-4 justify-evenly">
        {surveys &&
          (surveys.map(s => (
            <div 
              className="flex gap-4 justify-start w-full 
                flex-col sm:flex-row
                p-2 rounded
                bg-slate-800" 
              key={s.id}
            >
              <Link
                title={s.description}
                className="btn-primary min-w-full text-center"
                to={`/survey/${s.id}`}>
                To survey #{s.id} - {s.surveyName}
              </Link>
              <div className="flex gap-4 justify-evenly">
                <a className="btn-secondary" href={`/editsurvey/${s.id}`}>Edit</a>
                <Link className="btn-tertiary" to={`/survey/${s.id}/answers`}>Results</Link>
              </div>
            </div>
          )))
        }
      </div>
    </div>
  )
}

export default Home;