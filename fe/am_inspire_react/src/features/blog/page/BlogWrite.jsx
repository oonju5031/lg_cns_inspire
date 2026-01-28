import styled from "styled-components";
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import api from "../../../api/axios";

import TextInput from "../../../ui/TextInput";
import Button from "../../../ui/Button";

/*
요구사항
- title, content hood을 이용해서 상태 관리를 하고자 함
- 이벤트 발생 시 axios를 이용하여 데이터를 전달하고 json-server (db.json)에 저장
    - 이 때 작성글의 식별자(id)는 시간 객체를 이용함
- 라우터를 이용하여 BlogIndex로 이동
*/

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const Container = styled.div`
    width: 100%;
    max-width: 720px;

    & > * {
        :not(:last-child) {
            margin-bottom: 16px;
        }
    }
`;

const WelcomeMessage = styled.div`
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 16px;
    color: #333;
`;

const BlogWrite = () => {

    const moveUrl = useNavigate();

    // input의 값을 Reactive state로 관리하여야 하나,
    // form으로 감싸지 않았기 때문에 각 input값을 단일로 설정하여야 한다
    const [title,   setTitle]   = useState("");
    const [content, setContent] = useState("");

    // token 정보 가져오기
    const email = localStorage.getItem("token");
    console.log(">>> BlogIndex token email:", email);
    const at    = localStorage.getItem("access_token");
    console.log(">>> BlogIndex token access:", at);

    const saveHandler = async (title, content) => {
    
        console.log(">>> save btn click params:", title, content);

        const id = Date.now();

        await api.post("/blogs/write", {
            email: email,
            title: title,
            content: content
        }, {
            headers: {Authorization: at ? at : ""}
        })
        .then( (response) => {
            console.log(">>> then:", response.status);
            moveUrl("/blog/index");
        })
        .catch( (error) => {
            console.log(">>> catch:", error);
        });
    }

    return (
        <Wrapper>
            <Container>

                {email && <WelcomeMessage>{email}님 환영합니다.</WelcomeMessage>}

                <TextInput  height={20}
                            value={title}
                            changeHandler={ (e) => {setTitle(e.target.value);} }
                />
                <TextInput
                            height={480}
                            value={content}
                            changeHandler={ (e) => {setContent(e.target.value);} }
                />

                <Button title="저장"
                        onClick={ () => {saveHandler(title, content);} }
                />
                <Button title="이전" 
                        onClick={ () => {moveUrl("/blog/index");} }
                />
            </Container>
        </Wrapper>
    )
}

export default BlogWrite;