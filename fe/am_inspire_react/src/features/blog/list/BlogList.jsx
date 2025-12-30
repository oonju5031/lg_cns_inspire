import styled from "styled-components";

import BlogItem from "../item/BlogItem";

const Wrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;

    & :hover {
        background: #efefef;
    }

    & > * {
        :not(:last-child) {
            margin-bottom: 16px;
        }
    }
`;

const BlogList = ({blogs}) => {

    return (
        <Wrapper>
            {
                blogs.map( (blog, idx) => {
                    return (
                        <BlogItem key={blog.idx} blog={blog} />
                    );
                    
                })
            }
        </Wrapper>
    );
}

export default BlogList;