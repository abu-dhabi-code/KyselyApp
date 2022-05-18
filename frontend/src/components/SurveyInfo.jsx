
/**
 * @param {{survey: import("../utils/api").SurveyWithAnswers}} props - props
 * @returns {JSX.Element}
 */
function SurveyInfo({survey}) {

  return (
    <div className="flex flex-col items-center group textbox-width">
      <div
        className="bg-sweet-brown 
                  m-3 w-full
                  px-1 py-1 rounded shadow-sm
                  flex flex-col items-center
                  border-b-teal-blue
                  group-hover:border-b-white
                  border-b-2
                  transition-all"
      >
        <span>Title</span>
        <h2 className="text-2xl mb-3">{survey?.surveyName}</h2>
        <span className="w-full h-1 bg-inherit filter brightness-75" />
        <div className="flex flex-col items-center w-full bg-black/25 rounded pb-2">
          <span>Description</span>
          <p className="text-xl">{survey?.description}</p>
        </div>
      </div>
    </div>
  )
}

export default SurveyInfo;