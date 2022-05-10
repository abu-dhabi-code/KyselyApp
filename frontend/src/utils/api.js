
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

export async function getAnswers(id) {
    //fetch data from db
    const data = await (await fetch(`${API_URL}v1/surveys/${id}/answers`)).json();

    //loop through each question to format answers
    for (const question of data.questions) {
        const formattedAnswers = [];

        for (const answer of question.answers) {
            const answerText = answer.answer;
            if (formattedAnswers.filter(a => a.answer.toLowerCase() === answerText.toLowerCase()).length > 0) {
                formattedAnswers.find(a => a.answer.toLowerCase() === answerText.toLowerCase()).count++;
            } else {
                formattedAnswers.push({answer: answerText, count: 1});
            }
        }
        //sort the array by count in reverse
        formattedAnswers.sort((a, b) => b.count - a.count );
        question.answers = formattedAnswers;
    }
    console.log(data);
    return data;
}