import { useState } from "react";
import Results from "../pages/Results";


function Result({ result }){

    const [showResults,  setShowResults] = useState(false);

    const question = result.question;

    const count = Math.floor(Math.random() * 10);

    

    return (
        <div
            className="bg-sweet-brown 
                m-3 w-full
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
                    <div className="flex flex-col gap-4 pt-2 pl-6 justify-center items-start w-full">
                        {result.answers.length > 0 && 
                            result.answers.map(a => (
                            <div className="flex justify-start w-full">
                                <p key={a.id+a.answer}>{a.answer}</p>
                                <p 
                                  className="flex justify-center items-center
                                  mr-6 ml-auto 
                                  bg-tea-green text-dk-byzantium
                                  rounded-full 
                                  w-8 h-8
                                  select-none"
                                  title="Amount of times answer was given"
                                >
                                  {a.count}
                                </p>
                            </div>
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