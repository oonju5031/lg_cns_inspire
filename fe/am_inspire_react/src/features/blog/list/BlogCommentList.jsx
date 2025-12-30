import styled from "styled-components";

import BlogCommentItem from "../item/BlogCommentItem";

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


const BlogCommentList = ({comments, commentHandler}) => {

    return (
        <Wrapper>
            {
                comments.map( (comment, idx) => {
                    return (
                        <BlogCommentItem    key={comment.id}
                                            comment={comment}
                                            onDelete={commentHandler}
                        />
                    )
                })
            }
        </Wrapper>
    );

}

export default BlogCommentList;