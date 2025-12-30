import {BrowserRouter, Routes, Route} from "react-router-dom" ;
import styled from "styled-components";
import SingUp from "./features/user/page/SignUp";
import SingIn from "./features/user/page/SignIn";

import BlogIndex from "./features/blog/page/BlogIndex";
import BlogWrite from "./features/blog/page/BlogWrite";
import BlogRead from "./features/blog/page/BlogRead";

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

                <Route path="/blog/index"       element={<BlogIndex />} />
                <Route path="/blog/write"       element={<BlogWrite />} />
                <Route path="/blog/read/:id"    element={<BlogRead  />} />
            </Routes>
        </BrowserRouter>
    );
}

export default BlogApp;