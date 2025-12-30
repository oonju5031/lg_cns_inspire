import styled from "styled-components";
import api from "../../../api/axios";
import { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";

import Button from "../../../ui/Button";
import BlogList from "../list/BlogList";

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
        :first-child {
            margin-top: 16px;
        }
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

const LogoutButton = styled(Button)`
    background-color: #f44336;
    color: white;

    &:hover {
        background-color: #d32f2f;
    }
`;


const BlogIndex = () => {

    const moveUrl = useNavigate();

    const [ary, setAry] = useState([]);

    // token 정보 가져오기
    const email = localStorage.getItem("token");

    const loadData = async () => {
        try {
            const response = await api.get("/blogs");

            console.log(">>> BlogIndex blogs data: ", response.data);
            setAry(response.data);
            
        } catch (err) {
            console.log(">>> BlogIndex event loadData error: ", err);
        }
    }

    useEffect(() => {
        loadData();
    }, []);

    const logoutHandler = () => {
        localStorage.removeItem("token");
        moveUrl("/");
    }

    return (
        <Wrapper>
            <Container>
                
                {email && <WelcomeMessage>{email}님 환영합니다.</WelcomeMessage>}

                <Button title="글 작성하기"
                        onClick={ () => {
                            moveUrl("/blog/write")
                        }}
                />

                <Button title="로그아웃"
                        onClick={logoutHandler}
                />

                <BlogList blogs={ary} />

            </Container>
        </Wrapper>
    );
}

export default BlogIndex;