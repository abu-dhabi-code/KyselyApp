import { useState, useEffect } from "react";
import { Chart } from "react-google-charts";

/**
 * 
 * @param {{answerObjects: import("../utils/api").Answer[]}} answerObjects - list of answers
 * @returns {JSX.Element}
 */
function MultiselectResult({ answerObjects }){

    const [data, setData] = useState();

    const options = {
        backgroundColor: "#482C3D",
        
        width: "100%",
        height: 300,
        legend: "none"
    };

    useEffect(() => {
        let temp = [["answer", "Times answered"]]
        answerObjects.forEach( answer => {
            temp = [...temp, [answer.answer, answer.count]]
        })
        setData(temp);
    }, []);

    return(
        <div className="flex flex-col gap-4 pt-2 pl-6 justify-center items-start w-full">
            <Chart
            chartType="BarChart"
            data={data}
            options={options}
            />
        </div>
    )
}

export default MultiselectResult;