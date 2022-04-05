import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Question from "../components/Question";
import { getSurvey } from "../utils/api";

function Survey() {
    const id = parseInt(useParams().id);
    const [survey, setSurvey] = useState();

    console.log(id);

    useEffect(() => {
        getSurvey(id)
            .then(v => setSurvey(v));
    }, []);

    return (
        <div className="flex flex-col items-center h-full">
            <h1 className="text-2xl mt-2">Survey #{id}</h1>
            <div className="mt-2">
                { survey &&
                    (survey.questions.map(question => (
                        <Question 
                            question={question} 
                            key={question.id} 
                        />
                    )))
                }
            </div>
            <Link className="btn-primary mt-auto mb-4" to="/">Go back home</Link>
        </div>
    )
}

export default Survey;