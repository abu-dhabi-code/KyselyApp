
import { useState, useEffect } from "react";
import { Chart } from "react-google-charts";

function RadioResult({ answerObjects }) {

  const [data, setData] = useState();

  const options = {
    backgroundColor: "#482C3D",
    width: "100%",
    height: 300,
    legend: { textStyle: { color: 'white' } }
  };

  useEffect(() => {
    let temp = [["answer", "count"]]
    answerObjects.forEach(answer => {
      temp = [...temp, [answer.answer, answer.count]]
    })
    setData(temp);
  }, []);

  return (
    <div className="flex flex-col gap-4 pt-2 pl-6 justify-center items-start w-full">
      <Chart
        chartType="PieChart"
        data={data}
        options={options}
      />
    </div>
  )
}

export default RadioResult;