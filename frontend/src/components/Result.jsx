import { useState } from "react";
import Results from "../pages/Results";


function Result({ result }){

    const [showResults,  setShowResults] = useState(false);

    const question = result.question;

    

    

    return (
        <div
            className="bg-sweet-brown 
                m-3
                px-1 py-1 rounded shadow-sm
                flex flex-col items-center"
        >
            <div
                className="w-full px-5 py-3 
                mt-0 mx-0 bg-teal-blue
                rounded-t"
            >
                <h1 className="">
                    {question}
                </h1>
            </div>
            <div className="bg-dk-byzantium text-center brightness-95 rounded-b w-full pb-2">
                <input
                    type="button"
                    className="btn-primary mb-2 mt-6"
                    value={showResults ? "Hide Results" : "Show Results"}
                    onClick={() => setShowResults(!showResults)}
                />
                {showResults && (
                    <div className="flex flex-col gap-4 mt-2 justify-center w-full">
                        {result.answers.length > 0 && 
                            result.answers.map(a => (
                            <p key={a.id+a.answer}>{a.answer}</p>
                            ))
                        }
                        {result.answers.length === 0 &&
                            <p className="my-4 text-red-500">No answers</p>
                        }
                    </div>
                )}
            </div>
        </div>
    )
}

export default Result;