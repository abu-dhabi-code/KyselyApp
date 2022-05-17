import { useState, useEffect } from "react";
import { Chart } from "react-google-charts";

function MultiselectResult({ answerObjects }) {

  const [data, setData] = useState([]);

  const options = {
    backgroundColor: "#482C3D",
    width: "100%",
    height: 60 * data.length,
    /* bar: { groupWidth: "95%" }, */
    hAxis: {
      textStyle: {color: 'white'},
      gridlines: {color: '#787878'},
      minorGridlines: {count:0},
      /* titleTextStyle: {color: 'white'}, */
    },
    vAxis: {
      textStyle: {color: 'white'},
    },
    chartArea: {
      top: 20,
      /* left: '30%',
      width: '70%', */
      bottom: 50
    },
    legend: "none"
  };

  useEffect(() => {
    let temp = [["answer", "Times answered"]]
    answerObjects.forEach(answer => {
      temp = [...temp, [answer.answer, answer.count]]
    })
    setData(temp);
  }, []);

  return (
    <div className="flex flex-col gap-4 pl-6 justify-center items-start w-full">
      <Chart
        chartType="BarChart"
        data={data}
        options={options}
      />
    </div>
  )
}

export default MultiselectResult;