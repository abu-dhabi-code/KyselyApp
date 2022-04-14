
const API_URL = process.env.NODE_ENV === 'production'
    ? '/api/'
    : 'http://localhost:8080/api/'

/**
 * Used for fetching a survey by id.
 * @param {number} id - Survey's id in the database.
 * @returns - Promise to a survey object.
 */
export async function getSurvey(id) {
    const data = await (await fetch(`${API_URL}v1/surveys/${id}`)).json();

    return data;

    // For now returns dummy data instead of fetching the Spring server

    const question_count = Math.floor(Math.random() * 10) + 1;
    const questions = [];
    for (let i = 1; i < question_count; i++) {
        questions.push({
            id: i,
            survey: id,
            name: `Question ${i} ${id}`,
        });
    }

    return new Promise((res, rej) => {
        res({
            id,
            name: `Survey-${id}`,
            questions,
        });
    });

}

export async function getSurveys() {
  const data = await (await fetch(`${API_URL}v1/surveys`)).json();
  return data;
}
