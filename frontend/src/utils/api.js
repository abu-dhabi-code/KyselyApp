
const API_URL = process.env.NODE_ENV === 'production'
  ? '/api/'
  : 'http://localhost:8080/api/'


/**
 * @typedef QuestionType
 * @type {('Text'|'LongText'|'Radio'|'Multiselect')}
 */

/**
 * @typedef Option
 * @type {object}
 * @property {number} id - the option ID
 * @property {string} option - the name of the option
 * @property {Question} question - the question this option is attached to
 */

/**
 * @typedef Answer
 * @type {object}
 * @property {number} id - the answer ID
 * @property {string} answer - the submitted answer
 * @property {Question} question - the question this is an answer to
 * 
 */

/**
 * @typedef Question
 * @type {object}
 * @property {number} id - the question ID
 * @property {string} name - the question
 * @property {Survey} survey - the survey this question is attached to
 * @property {(Answer[]|FormattedAnswers[])?} answers - a list of answers to this question
 * @property {Option[]?} options - a list of options on this question
 * @property {QuestionType} type - Enum for the question's type
 */

/**
 * @typedef Survey
 * @type {object}
 * @property {number} id - the survey ID
 * @property {string} surveyName - name of the survey
 * @property {string} description - survey description
 * @property {Question[]?} questions - questions included in the survey
 */

/**
 * Used for fetching a survey by id.
 * @param {number} id - Survey's id in the database.
 * @returns {Survey} A promise to a survey object.
 */
export async function getSurvey(id) {
  /**
   * @type Survey
   */
  const data = await (await fetch(`${API_URL}v1/surveys/${id}`)).json();
  return data;
}

/**
 * Used for fetching all available surveys
 * @returns {Survey[]} A promise to an array of surveys
 */
export async function getSurveys() {
  /**
   * @type Survey[]
   */
  const data = await (await fetch(`${API_URL}v1/surveys`)).json();
  return data;
}

export async function sendAnswers(answers) {
  const data = await (await fetch(`${API_URL}v1/answers`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/JSON'
    },
    body: JSON.stringify(answers)
  })).json();
  return data;
}

/**
 * @typedef FormattedAnswers
 * @type {object}
 * @property {string} answerText - the answer text
 * @property {number} count - amount of times this answer was submitted
 */

/**
 * @typedef SurveyWithAnswers
 * @type {object}
 * @property {number} id - the survey ID
 * @property {string} surveyName - the name of the survey
 * @property {string} description - the survey's description
 * @property {Question[]} questions - questions in this survey
 */

/**
 * 
 * @param {string} id of the survey
 * @returns 
 */
export async function getAnswers(id) {
  //fetch data from db
  /**
   * @type {SurveyWithAnswers}
   */
  const data = await (await fetch(`${API_URL}v1/surveys/${id}/answers`)).json();

  //loop through each question to format answers
  for (const question of data.questions) {
    /**
     * @type {FormattedAnswers}
     */
    const formattedAnswers = [];

    for (const answer of question.answers) {
      const answerText = answer.answer;
      if (formattedAnswers.filter(a => a.answer.toLowerCase() === answerText.toLowerCase()).length > 0) {
        formattedAnswers.find(a => a.answer.toLowerCase() === answerText.toLowerCase()).count++;
      } else {
        formattedAnswers.push({ answer: answerText, count: 1 });
      }
    }
    //sort the array by count in reverse
    formattedAnswers.sort((a, b) => b.count - a.count);
    question.answers = formattedAnswers;
  }
  console.log(data);
  return data;
}