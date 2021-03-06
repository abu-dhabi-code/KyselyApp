import { useState } from "react";
import RadioResult from "./RadioResult";
import MultiselectResult from "./MultiselectResult";
import TextResult from "./TextResult";

/**
 * 
 * @param {{result: import("../utils/api").Question}} result - the question to display the results of
 * @returns {JSX.Element}
 */
function Result({ result }) {

  const [showResults, setShowResults] = useState(false);

  const renderByType = () => {
    switch (result.type) {
      case "Radio":
        return <RadioResult answerObjects={result.answers} />;
      case "Multiselect":
          return <MultiselectResult answerObjects={result.answers} />;
      default:
        return <TextResult result={result} />;
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