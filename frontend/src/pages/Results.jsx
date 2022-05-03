import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getAnswers } from "../utils/api";
import Result from "../components/Result";

function Results() {
    const id = parseInt(useParams().id);
    const [survey, setSurvey] = useState({});
    const [results, setResults] = useState([]);

    useEffect(() => {
      getAnswers(id)
        .then(data => setSurvey(data));
    }, []);

    useEffect(() => {
      if (!survey || !survey.questions){
        setResults([]);
        return;
      }
      const questions = survey.questions;
      const resultsObjects = questions.map(question => {
        return{
          id: question.id,
          question: question.name,
          answers: question.answers,
        }
      });
      setResults(resultsObjects);

    }, [survey])

    return(
      <div className="flex flex-col items-center h-full">
            <h1 className="text-3xl mt-3 mb-6">Survey Results</h1>
            <div className="flex flex-col items-stretch gap-4 justify-evenly">
              <h2>{survey?.surveyName}</h2>
              <p>{survey?.description}</p>
              <div className="flex flex-col items-center">
              {results.map(r => (
                <div key={r.id+r.question} className="flex gap-4 justify-center w-full">
                  <Result
                    result={r}
                  />
                </div>  
                
              ))}
              </div>
            </div>
            
        </div>
    );
}

export default Results;