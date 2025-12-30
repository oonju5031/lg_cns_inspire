import { useContext } from "react";
import ctx from "./util/context";

const ContextHeader = () => {

    const {isMode} = useContext(ctx);

    return (
        <div>
            <header style={{
                backgroundColor: isMode ? "black" : "white",
                color: isMode? "white" : "black"
            }}>
                <h1>Farewell 2025</h1>
            </header>
        </div>
    );

}

export default ContextHeader;