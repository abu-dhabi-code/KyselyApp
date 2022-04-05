

function Question({question, key}) {

    return (
        <div 
            key={key} 
            className="bg-sweet-brown 
                m-3
                px-1 py-1 rounded shadow-sm
                flex flex-col items-center"
        >
            <div 
                className="w-full px-5 py-3 
                mt-0 mx-0 bg-dk-byzantium
                rounded-t"
            >
                <h1 className="">
                    {question.name}
                </h1>
            </div>
            <div className="bg-dk-byzantium brightness-95 rounded-b pb-2">
                <input 
                    type="text" 
                    placeholder="Answer"
                    className="bg-white text-black px-2 mx-5 rounded"
                />
            </div>
        </div>
    )
}

export default Question;