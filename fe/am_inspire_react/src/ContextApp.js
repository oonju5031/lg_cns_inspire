import {useState} from "react";
import ContextPage from "./pages/context/ContextPage";
import context from "./components/context/util/context";

const ContextApp = () => {

    const [isMode, setIsMode] = useState(false);

    return (
        <div>
            <context.Provider value={{isMode, setIsMode}}>
                
                <ContextPage />

            </context.Provider>
        </div>
    )
}

export default ContextApp;