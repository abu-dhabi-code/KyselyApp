import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { getAnswers } from "../utils/api";
import Result from "../components/Result";

function Results() {
  const id = parseInt(useParams().id);
  /**
   * @type {[
   *  import("../utils/api").SurveyWithAnswers, 
   *  React.Dispatch<React.SetStateAction<import("../utils/api").SurveyWithAnswers>>
   * ]}
   */
  const [survey, setSurvey] = useState({});
  /**
   * @type {[
   *  import("../utils/api").Question[], 
   *  React.Dispatch<React.SetStateAction<import("../utils/api").Question[]>>
   * ]}
   */
  const [results, setResults] = useState([]);

  useEffect(() => {
    getAnswers(id)
      .then(data => setSurvey(data));
  }, []);

  useEffect(() => {
    if (!survey || !survey.questions) {
      setResults([]);
      return;
    }
    setResults(survey.questions);

  }, [survey])

  return (
    <div className="flex flex-col items-center">
      <h1 className="text-3xl mt-3 mb-6">Survey Results</h1>
      <div className="flex flex-col items-stretch gap-4 justify-evenly">
        <div className="flex flex-col items-center group">
          <div
            className="bg-sweet-brown 
                  m-3 w-full
                  px-1 py-1 rounded shadow-sm
                  flex flex-col items-center
                  border-b-teal-blue
                  group-hover:border-b-white
                  border-b-2
                  transition-all"
          >
            <span>Title</span>
            <h2 className="text-2xl mb-3">{survey?.surveyName}</h2>
            <span className="w-full h-1 bg-inherit filter brightness-75" />
            <div className="flex flex-col items-center w-full bg-black/25 rounded pb-2">
              <span>Description</span>
              <p className="text-xl">{survey?.description}</p>
            </div>
          </div>
        </div>
        <div className="flex flex-col items-center pb-8">
          {results.map(r => (
            <div key={r.id + r.name} className="flex gap-4 justify-center w-full">
              <Result
                result={r}
              />
            </div>

          ))}
        </div>
      </div>
      <Link className="btn-primary mt-auto mb-12" to="/">Go back home</Link>
    </div>
  );
}

export default Results;