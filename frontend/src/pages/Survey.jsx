import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Question from "../components/Question";
import { getSurvey, sendAnswers } from "../utils/api";

function Survey() {
    const id = parseInt(useParams().id);
    const [survey, setSurvey] = useState();
    const [answers, setAnswers] = useState([]);

    console.log(id);

    useEffect(() => {
        getSurvey(id)
            .then(v => {
                setSurvey(v);
            });
    }, []);

    useEffect(() => {
        if (survey)
            setAnswers(survey.questions.map(q => {
                return {
                    id: q.id,
                    text: ''
                }
            }));
    }, [survey]);

    function submitAnswers() {
        console.log(answers);
        const answerObject = answers.map(e => {
            return {
                answer: e.text,
                question: {
                    id: e.id
                }
            }
        });
        sendAnswers(answerObject)
            .then(res => console.log(res))
    }

    return (
        <div className="flex flex-col items-center h-full">
            <h1 className="text-2xl my-2">{survey?.surveyName}</h1>
            <p>{survey?.description}</p>
            <div className="mt-2">
                {survey
                    ?
                    (survey.questions.map(question => (
                        <span key={question.id} >
                            <Question
                                question={question}
                                answerState={[answers, setAnswers]}
                            />
                        </span>
                    )))
                    :
                    (
                        <h1>Loading</h1>
                    )
                }

            </div>
            <button className="btn-primary mt-auto mb-4" onClick={submitAnswers}>Submit answers</button>
            <Link className="btn-primary mt-auto mb-4" to="/">Go back home</Link>
        </div>
    )
}

export default Survey;