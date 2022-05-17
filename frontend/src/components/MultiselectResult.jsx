import { useState, useEffect } from "react";
import { Chart } from "react-google-charts";
import { colours } from "../utils/chartCommons";

/**
 * 
 * @param {{answerObjects: import("../utils/api").Answer[]}} answerObjects - list of answers
 * @returns {JSX.Element}
 */
function MultiselectResult({ answerObjects }) {

	const [data, setData] = useState([]);


	/**
   * @type import("react-google-charts").GoogleChartOptions
   */
	const options = {
		backgroundColor: "#482C3D",
		width: "100%",
		height: 60 * data.length,
		/* bar: { groupWidth: "95%" }, */
		hAxis: {
			textStyle: { color: 'white' },
			gridlines: { color: '#787878' },
			minorGridlines: { count: 0 },
			/* titleTextStyle: {color: 'white'}, */
		},
		vAxis: {
			textStyle: { color: 'white' },
		},
		chartArea: {
			top: 20,
			/* left: '30%',
			width: '70%', */
			bottom: 50
		},
		legend: "none",
	};

	useEffect(() => {
		const temp = [["answer", "Times answered", { role: 'style' }]]
		
		answerObjects.forEach((answer, i) => {
			const colour = colours[i % colours.length];
			temp.push([answer.answer, answer.count, colour]);
		})
		setData(temp);
	}, [answerObjects]);

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