import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Question from "../components/Question";
import Success from "../components/Success";
import { getSurvey, sendAnswers } from "../utils/api";

function Survey() {
    const id = parseInt(useParams().id);
    const [survey, setSurvey] = useState();
    const [answers, setAnswers] = useState([]);
    const [showSuccess, setShowSuccess] = useState(false);

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
        setShowSuccess(true);
    }

    return (
        <div className="flex flex-col items-center h-full">
            <h1 className="text-2xl my-2">{survey?.surveyName}</h1>
            <p>{survey?.description}</p>
            <div className="mt-2">
                {!showSuccess &&
                (survey
                    ?
                    (survey.questions.map(question => (
                        <><span key={question.id} >
                            <Question
                                question={question}
                                answerState={[answers, setAnswers]}
                            />
                        </span>
                        <button className="btn-primary mt-auto mb-4" onClick={submitAnswers}>Submit answers</button>
                        </>
                    )))
                    :
                    (
                        <h1>Loading</h1>
                    )
                    
                )
                }
                {showSuccess &&
                    <Success />

                }
            </div>
            
            <Link className="btn-primary mt-auto mb-4" to="/">Go back home</Link>
        </div>
    )
}

export default Survey;