import { useState } from "react";
import Results from "../pages/Results";
import RadioResult from "./RadioResult";
import TextResult from "./TextResult";


function Result({ result }){

    const [showResults,  setShowResults] = useState(false);

    const renderByType = () => {
        switch(result.type) {
            case "Text":
              return <TextResult result={result} />;
            case "Radio":
                return <RadioResult result={result} />;
            default:
              return 'foo';
          }
    }

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
                    {result.name}
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
                    <span>
                        {result.answers.length > 0
                        ?
                        renderByType()
                        /* result.type == "Text"
                        ?
                            <TextResult result={result} />
                        : ":(" */
                        :
                        <p className="my-4 text-red-500">No answers</p>
                        }
                    </span>
                )}
            </div>
        </div>
    )
}

export default Result;