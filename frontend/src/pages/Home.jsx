import { Link } from "react-router-dom";

function Home() {

    const surveyId = Math.floor(Math.random() * 500);


    return (
        <div className="flex flex-col items-center h-full">
            <h1>Home</h1>
            <Link className="btn-primary" to={`/survey/${surveyId}`}>
                To survey {surveyId}
            </Link>
        </div>
    )
}

export default Home;