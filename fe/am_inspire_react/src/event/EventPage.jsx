import {useState, useEffect} from "react";
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";

const EventPage = () => {

    const handler = (id, pwd) => {
        console.log(">>> handler called: ", id, " ", pwd);
    }

    const idHandler = (e) => {
        setId(e.target.value);
    }
    const pwdHandler = (e) => {
        setPwd(e.target.value);
    }

    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");

    return (
        <div>
            <div>
                <label>아이디</label>
                <input
                    type="text"
                    value={id}
                    placeholder="아이디를 입력하세요."
                    onChange={ (e) => idHandler(e) }
                />
            </div>
            <div>
                <label>패스워드</label>
                <input
                    type="password"
                    value={pwd}
                    placeholder="비밀번호를 입력하세요."
                    onChange={ (e) => pwdHandler(e) }
                />
            </div>
            <Button variant="primary" onClick={ () => handler(id, pwd) }>login</Button>
        </div>
    );
}

export default EventPage;