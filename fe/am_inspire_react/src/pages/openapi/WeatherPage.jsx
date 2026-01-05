import {useEffect, useState} from "react";
import WeatherBox from "../../components/openapi/WeatherBox";
import WeatherButton from "../../components/openapi/WeatherButton";
import "./css/index.css";

const WeatherPage = () => {

    const apiKey = process.env.REACT_APP_OPENAPI_KEY;

    const cities = ["Seoul", "Busan", "Paris", "New York"];
    const [city, setCity] = useState("");
    const [weather, setWeather] = useState(null);

    // 현재 위치(위도, 경도) 정보를 바탕으로 날씨 정보를 얻어옴
    const getCurrLoc = () => {
        navigator.geolocation.getCurrentPosition((position) => {
            let lat = position.coords.latitude;
            let lon = position.coords.longitude;
            console.log(">>> lat, lon:", lat, lon);
            getCurWeather(lat, lon);
        });
    }

    const getCurWeather = async (lat, lon) => {
        let url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric`;
        const response = await fetch(url);
        let data = await response.json();
        console.log(">>> data:", data);
        setWeather(data);
    }

    const getCityWeather = async () => {
        let url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;
        const response = await fetch(url);
        let data = await response.json();
        console.log(">>> data:", data);
        setWeather(data);
    }

    const cityHandler = (city) => {
        console.log(">>> cityHandler city click:", city);
        setCity(city);
    }

    useEffect(() => {
        console.log(">>> WeatherPage city:", city);

        if (city === "") {
            getCurrLoc();
        } else {
            getCityWeather();
        }

    }, [city]);

    return (
        <div className="container">
            <WeatherBox     weather={weather} />
            <WeatherButton  cities={cities}
                            handler={cityHandler}
                            city={city}
            />
        </div>
    );

}

export default WeatherPage;