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
  background-color: #f2f2f2;
`;

// Form Box
const FormWrapper = styled.div`
  background-color: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0px 8px 16px rgba(0,0,0,0.1);
  width: 400px;
`;

// Title
const Title = styled.h2`
  text-align: center;
  margin-bottom: 20px;
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

const SignUp = () => {

    const [form, setForm] = useState({
        name: "",
        email: "",
        password: ""
    });

    const handlerChange = (e) => {

        // 구조 분할을 통해 e.target.name과 e.target.value을 받음: 주석의 코드와 동일
        // const name = e.target.name;
        // const value = e.target.value;
        const {name, value} = e.target;

        setForm({...form, [name]: value});
    }

    // Reloading 대신 Router를 이용한 component transition을 사용
    const moveUrl = useNavigate();

    /**
     * 해당 이벤트 함수 호출 시 서버에 데이터(이 경우 name, email, password)를 json 형태로 전달
     * 유효성 검사가 필수
     *  -> 서버에서도 한 차례 유효성 검사를 하지만, 프론트단에서 검사하는 경우 유효성이 충족되지 않을 경우의 불필요한 네트워크 트래픽을 방지할 수 있음
     */
    const handlerSubmit = async (e) => {

        e.preventDefault();  // submit 시의 event bubbling(새로고침)을 방지

        console.log(">>> signup submit call");

        try {
            const response = await api.post("/users", {
                name: form.name,
                email: form.email,
                password: form.password
            });

            console.log(">>> axios success: ", response);
            moveUrl("/login");

        } catch (err) {
            console.log(">>> axios err: ", err);
        }
    }

    return (
        <Container>
            <FormWrapper>
                <Title>회원가입</Title>
                <form onSubmit={handlerSubmit}>
                    <Input
                        type="text"
                        name="name"
                        value={form.name}
                        onChange={handlerChange}
                        placeholder="name"
                    />
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
                    <Button type="submit">가입하기</Button>
                </form>
                <TextLink to="/login">로그인</TextLink>
            </FormWrapper>
        </Container>
    );
}

export default SignUp;