import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Question from "../components/Question";
import Success from "../components/Success";
import Failure from "../components/Failure";
import { getSurvey, sendAnswers } from "../utils/api";
import SurveyInfo from "../components/SurveyInfo";

function Survey() {
  const id = parseInt(useParams().id);
  /**
   * @type {[
   *  import("../utils/api").Survey, 
   *  React.Dispatch<React.SetStateAction<import("../utils/api").Survey>>
   * ]}
   */
  const [survey, setSurvey] = useState();
  /**
   * @typedef InProgressAnswer
   * @type {object}
   * @property {number} id - of the question
   * @property {import("../utils/api").QuestionType} type - of question
   * @property {(string | string[])} value - answers to the question
   */
  /**
   * @type {[
   *  InProgressAnswer[], 
   *  React.Dispatch<React.SetStateAction<InProgressAnswer[]>>
   * ]}
   */
  const [answers, setAnswers] = useState([]);
  const [showSuccess, setShowSuccess] = useState(false);
  const [showError, setShowError] = useState(false);
  const [errorInfo, setErrorInfo] = useState({});

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
          type: q.type,
          value: ''
        }
      }));
  }, [survey]);

  /**
   * @typedef AnswerObject
   * @type {object}
   * @property {import("../utils/api").Question} - question the answer is linked to. Only the ID is required
   * @property {string} answer - answer text
   */

  function submitAnswers() {
    console.log(answers);
    /**
     * @type {AnswerObject[]}
     */
    const answerObjects = answers
      .filter(e => e.type !== "Multiselect")
      .map(e => {
        return {
          answer: e.value,
          question: {
            id: e.id
          }
        }
      })

    /**
     * @type {InProgressAnswer[]}
     */
    const multiAnswers = answers.filter(e => e.type === "Multiselect");
    for (const answer of multiAnswers)
      for (const option of answer.value)
        answerObjects.push({
          answer: option,
          question: {
            id: answer.id
          }
        });

    sendAnswers(answerObjects)
      .then(res => {
        console.log(res)
        setShowSuccess(true);
        setShowError(false);
      })
      .catch(e => {
        console.log(e)
        setShowSuccess(false);
        setShowError(true);
        setErrorInfo(e);
      })
  }

  return (
    <div className="flex flex-col items-center h-full">
      <span className="mt-4">
        <SurveyInfo survey={survey} />
      </span >
      <div className="mt-2">
        {!showSuccess && !showError &&
          (survey
            ?
            <div className="flex flex-col items-center">
              {(survey.questions.map(question => (
                <span key={question.id} >
                  <Question
                    question={question}
                    answerState={[answers, setAnswers]}
                  />
                </span>
              )))}
              <button className="btn-primary mt-auto mb-4" onClick={submitAnswers}>Submit answers</button>
            </div>
            :
            (
              <h1>Loading</h1>
            )

          )
        }
        {showSuccess &&
          <Success />

        }

        {showError &&
          <Failure />
        }
      </div>

      <Link className="btn-primary mt-auto mb-4" to="/">Go back home</Link>
    </div>
  )
}

export default Survey;