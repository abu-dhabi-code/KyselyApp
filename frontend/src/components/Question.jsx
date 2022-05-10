

function Question({ question, key, answerState }) {

    const [answers, setAnswers] = answerState;

    function getAnswerByQuestionId(id) {
        const results = answers.filter(a => a.id === id);

        return results.length > 0 ? results[0].text : '';
    }

    function updateAnswerByQuestionId(newText, id) {
        setAnswers(answers.map(a => {
            // console.log(a)
            if (a.id === id)
                a.text = newText;
            return a;
        }));

    }

    function getAnswerComponent(question) {
        console.log(question.type)
        switch(question.type) {
            case "LongText":
                return(
                    <textarea
                        placeholder="Answer"
                        className="bg-white text-black px-2 mx-5 rounded textbox-width"
                        value={getAnswerByQuestionId(question.id)}
                        onChange={e => updateAnswerByQuestionId(e.currentTarget.value, question.id)}
                    />  
                )
                case "Radio":
                    return (
                        <div className="flex flex-col gap-4 w-full">
                        {question.options.map(o => (
                        <label>
                            {o.option}
                            <input 
                                name={question.id + question.question}
                                key={o.id + o.option}
                                type="radio"
                                className="bg-white text-black px-2 mx-5 rounded"
                                
                                onChange={e => updateAnswerByQuestionId(o.option, question.id)}
                            /> 
                        </label>
                    ))}
                    </div>
                    )
                    case "Multiselect":
                        return (
                            <div className="flex flex-col gap-4 w-full">
                            {question.options.map(o => (
                            <label>
                                {o.option}
                                <input 
                                    name={question.id + question.question}
                                    key={o.id + o.option}
                                    type="checkbox"
                                    className="bg-white text-black px-2 mx-5 rounded"
                                    
                                    onChange={e => console.log(e)}
                                /> 
                            </label>
                        ))}
                        </div>
                        )
            default: 
                return(
                    <input
                        type="text"
                        placeholder="Answer"
                        className="bg-white text-black px-2 mx-5 rounded textbox-width"
                        value={getAnswerByQuestionId(question.id)}
                        onChange={e => updateAnswerByQuestionId(e.currentTarget.value, question.id)}
                    />
                    )
        }
    }

    return (
        <div
            key={key}
            className="bg-sweet-brown 
                m-3
                px-1 py-1 rounded shadow-sm
                flex flex-col items-center"
        >
            <div
                className="w-full px-5 py-3 
                mt-0 mx-0 bg-dk-byzantium
                rounded-t"
            >
                <h1 className="">
                    {question.name}
                </h1>
            </div>
            <div className="bg-dk-byzantium text-center brightness-95 rounded-b w-full pb-2">
                {
                    getAnswerComponent(question)
                }
            </div>
        </div>
    )
}

export default Question;