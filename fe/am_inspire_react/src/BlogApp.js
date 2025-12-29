import {BrowserRouter, Routes, Route} from "react-router-dom" ;
import styled from "styled-components";
import SingUp from "./features/user/page/SignUp";
import SingIn from "./features/user/page/SignIn";

const TitleDiv = styled.p`
    font-size: 24px;
    font-weight: bold;
    text-align: center;
`
const BlogApp = () => {

    return(
        <BrowserRouter>
            <TitleDiv>
                AM Inspire Camp 4th Lgcns
            </TitleDiv>
            <Routes>
                <Route path="/"         element={<SingUp />} />
                <Route path="/login"    element={<SingIn />} />
            </Routes>
        </BrowserRouter>
    );
}

export default BlogApp;