import {Button} from "react-bootstrap";
import "./css/weather.css";

const WeatherButton = ({cities, handler, city}) => {
    return (
        <div className="weather-btn">

            <Button className="btn"
                    variant={`${ city === "" ? "outline-warning" : "warning"}`}
                    onClick={() => {
                        handler("");
                    }}
            >
                Current Location
            </Button>
        
            {
                cities.map((item, idx) => {
                    return (
                        <Button className="btn"
                                key={idx}
                                variant={`${ city === "" ? "outline-warning" : "warning"}`}
                                onClick={() => {
                                    handler(item);
                                }}
                        >
                            {item}
                        </Button>
                    );
                })
            }
        </div>
    );
}

export default WeatherButton;