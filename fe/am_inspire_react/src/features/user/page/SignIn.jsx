import React, {useState} from "react";
import styled from "styled-components";
import api from "../../../api/axios";
import {useNavigate, Link} from "react-router-dom";

// Container
const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
`;

// Form Box
const FormWrapper = styled.div`
  background-color: #fff;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.1);
  width: 350px;
`;

// Title
const Title = styled.h2`
  text-align: center;
  margin-bottom: 25px;
  color: #333;
`;

// Input
const Input = styled.input`
  width: 100%;
  padding: 12px;
  margin-bottom: 15px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 16px;

  &:focus {
    outline: none;
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0,123,255,0.3);
  }
`;

// Button
const Button = styled.button`
  width: 100%;
  padding: 12px;
  background-color: #007bff;
  color: white;
  border: none;
  font-size: 16px;
  border-radius: 6px;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: #0056b3;
  }

  &:disabled {
    background-color: #aaa;
    cursor: not-allowed;
  }
`;

// Link
const TextLink = styled(Link)`
  display: block;
  text-align: center;
  margin-top: 15px;
  font-size: 14px;
  color: #007bff;
  text-decoration: none;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
`;

const SignIn = () => {

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handlerChange = (e) => {
        const {name, value} = e.target;
        setForm({...form, [name]: value});
    }

    const moveUrl = useNavigate();

    const handlerSubmit = async (e) => {
        
        e.preventDefault();

        try {
            const response = await api.get("/users" , {
                params: {
                    email: form.email,
                    password: form.password
                }
            });

            console.log(">>> axios success: ", response);

            if (response.status === 200) {
              // 인증된 사용자 정보(token)를 공유하기 위하여 localStorage에 데이터를 추가한다.
              const email = response.data[0].email;
              console.log(">>> token:", email);
              localStorage.setItem("token", email);
            }

            moveUrl("/blog/index");

        } catch( err ) {
            console.log(">>> axios err: " , err);
        }
    }

    return (
        <Container>
            <FormWrapper>
                <Title>로그인</Title>
                <form onSubmit={handlerSubmit}>
                    <Input
                        type="email"
                        name="email"
                        value={form.email}
                        onChange={handlerChange}
                        placeholder="email"
                    />
                    <Input
                        type="password"
                        name="password"
                        value={form.password}
                        onChange={handlerChange}
                        placeholder="password"
                    />
                    <Button type="submit">로그인</Button>
                </form>
                <TextLink>비밀번호를 잊으셨나요?</TextLink>
                <TextLink to="/">회원가입</TextLink>
            </FormWrapper>
        </Container>
    );
}

export default SignIn;