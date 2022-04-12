import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { getSurveys } from "../utils/api";

function Home() {

    const [surveys, setSurveys] = useState([]);
    
    useEffect(() => {
      getSurveys()
        .then(data => setSurveys(data));
    }, []);

    return (
        <div className="flex flex-col items-center h-full">
            <h1 className="text-3xl mt-3 mb-6">Home</h1>
            <div className="flex flex-col items-stretch gap-4 justify-evenly">
              { surveys &&
                (surveys.map(s => (
                  <div className="flex gap-4 justify-start w-full">
                  <Link
                    title={s.description}
                    key={s.id}
                    className="btn-primary min-w-full text-center"
                    to={`/survey/${s.id}`}>
                      To survey #{s.id} - {s.surveyName}
                  </Link>
                  <a className="btn-secondary ml-auto mr-0" href={`/editsurvey/${s.id}`}>Edit</a>
                  </div>
                )))
              }
            </div>
        </div>
    )
}

export default Home;