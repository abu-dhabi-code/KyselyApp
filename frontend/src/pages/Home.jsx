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
            <div className="flex flex-col items-center gap-4">
              { surveys &&
                (surveys.map(s => (
                  <Link key={s.id} className="btn-primary" to={`/survey/${s.id}`}>
                  To survey #{s.id} - {s.surveyName}
                  </Link>
                )))
              }
            </div>
        </div>
    )
}

export default Home;