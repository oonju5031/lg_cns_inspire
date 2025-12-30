import styled from "styled-components";
import Button from "../../../ui/Button";

const Wrapper = styled.div`
    width: calc(100% - 32px);
    padding: 8px 16px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    border: 1px solid grey;
    border-radius: 8px;
    cursor: pointer;
    background: white;
    :hover {
        background: lightgrey;
    }
`;

const ContentText = styled.p`
    font-size: 16px;
    white-spzce: pre-wrap;
`


const BlogCommentItem = ({comment, onDelete}) => {
    
    return (
        <Wrapper>
            <ContentText>{comment.content}</ContentText>
            <Button title="삭제"
                    onClick={ () => {onDelete(comment.id)}}
            />
        </Wrapper>
    )
}

export default BlogCommentItem;