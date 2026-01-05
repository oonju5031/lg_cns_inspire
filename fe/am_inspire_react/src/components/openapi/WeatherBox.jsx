
/*
현재 위치, 섭씨, 화씨, 날씨 정보 표시
*/
const WeatherBox = ({weather}) => {
    return (
        <div className="weather-box">
            {/* Optional 문법을 이용하여 API 통신 실패에 대비한 undefined 처리 */}
            <div>{weather?.sys?.country}</div>
            <div>{weather?.name}</div>
            <div>{weather?.main?.temp}</div>
        </div>
    );
}

export default WeatherBox;