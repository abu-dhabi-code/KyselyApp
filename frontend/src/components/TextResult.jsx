import { useState } from "react";

/**
 * 
 * @param {{result: import("../utils/api").Question}} result - the question to display the results of
 * @returns {JSX.Element}
 */
function TextResult({ result }) {


  return (
    <div className="flex flex-col gap-4 pt-2 pl-6 justify-center items-start w-full">
      {result.answers.map(a => (
        <div className="flex justify-start w-full">
          <p key={a.id + a.answer}>{a.answer}</p>
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
      ))}
    </div>
  )
}

export default TextResult;