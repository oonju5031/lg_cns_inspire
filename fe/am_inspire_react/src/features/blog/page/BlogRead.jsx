import styled, {keyframes} from "styled-components";
import {useState, useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import api from "../../../api/axios";

import Button from "../../../ui/Button";
import TextInput from "../../../ui/TextInput";
import BlogCommentList from "../list/BlogCommentList";

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

const PostContainer = styled.div`
    padding: 8px 16px;
    border: 1px solid grey;
    border-radius: 8px;
`;

const TitleText = styled.p`
    font-size: 28px;
    font-weight: 500;
`;

const ContentText = styled.p`
    font-size: 20px;
    line-height: 32px;
    white-space: pre-wrap;
`;

const CommentLabel = styled.p`
    font-size: 16px;
    font-weight: 500;
`;

const spin = keyframes`
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
`;

const Spinner = styled.div`
  border: 6px solid #f3f3f3;
  border-top: 6px solid #3498db;
  border-radius: 50%;
  width: 48px;
  height: 48px;
  animation: ${spin} 1s linear infinite;
  margin: 100px auto;
`;

const WelcomeMessage = styled.div`
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 16px;
    color: #333;
`;

const BlogRead = () => {

    // useParams: URL에서 전달되는 파라미터를 전달받을 수 있는 hook
    const {id} = useParams();
    console.log(">>> request parameter:", id);

    const [blog,     setBlog]     = useState({});  // 빈 객체로 초기화
    const [comments, setComments] = useState([]);  // 빈 배열로 초기화
    const [comment,  setComment]  = useState("");

    const getBlog = async () => {
        await api.get(`/blogs/${id}?_embed=comments`)  // Embed를 이용하여 특정 게시글의 댓글을 함께 가져올 수 있다.
                .then( (response) => {

                    console.log(response);

                    // setBlog(response.data);  // useState의 초깃값인 빈 객체 {}에 자동으로 binding

                    // 댓글이 있는 경우 setBlog()를 다음과 같이 변경하여야 한다.
                    setBlog({
                        id: response.data.id,
                        title: response.data.title,
                        content: response.data.content
                    });

                    setComments(response.data.comments || []);  // comment가 없는 blog를 고려하여 []를 OR로 넣는다.
                })
                .catch( (error) => {
                    console.log(">>> BlogRead event getBlog error:", error);
                });
    };

    useEffect(() => {
        getBlog();
    }, []);

    const moveUrl = useNavigate();

     /**
      * 댓글 입력 핸들러
      * - 댓글 내용을 추가 후
      * - 댓글 영역을 다시 렌더링
      * @param {*} blogId 게시글 식별값
      * @param {*} content 댓글 내용
      */
    const commentCreateHandler = async (blogId, content) => {
        console.log(">>> commentHandler: ", blogId, content);

        await api.post("/comments", {
            id: Date.now(),
            blogId: blogId,
            content: content
        })
        .then( (response) => {
            console.log(">>> commentHandler success:", response.data);

            // 부분 리렌더링을 위한 기본적인 패턴(배열인 경우)
            // if (response.status === 201) {
            //     const newComment = response.data[response.data.length - 1];  // 가장 마지막 댓글만 가져옴
            //     setComments( (ary) => {
            //         return [...ary, newComment];
            //     });
            //     setComments("");
            // }

            if (response.status === 201) {
                setComments( (ary) => [...ary, response.data] );
                setComment("");
            }
            
        })
        .catch( (error) => {
            console.log(">>> commentHandler error:", error);
        });

    }

    /**
     * 댓글 삭제 핸들러
     * - 전달받은 식별값을 이용하여 해당 댓글을 삭제한 후
     * - 댓글 영역을 재렌더링
     * @param {*} id 댓글 식별값
     */
    const commentDeleteHandler = async (id) => {

        await api.delete(`/comments/${id}`)
        .then( (response) => {

            if (response.status === 200 || response.status === 204) {
                // setComments(comments.filter(comment => comment.id !== id));  // 일반 업데이트 -> comment는 렌더링 시점의 값이므로 여러 사용자가 이용하는 경우 동기화 문제 발생 가능
                setComments( (ary) => ary.filter( comment => comment.id !== id) );  // 따라서 함수형 업데이트를 사용하여 최신 값을 사용하여야 함
            }
            
        })
        .catch( (error) => {
            console.log("commentDeleteHandler error:", error);
        });

    }

    return (
        <Wrapper>
            <Container>
                <Button title="메인 페이지"
                        onClick={ () => {
                            moveUrl("/blog/index");
                        }}
                />

            <PostContainer>
                <TitleText>{blog.title}</TitleText>
                <ContentText>{blog.content}</ContentText>
            </PostContainer>

            <CommentLabel>작성된 댓글</CommentLabel>

            <BlogCommentList    comments={comments || [] }  // undefined 처리
                                commentHandler={commentDeleteHandler}  // () => {commentHandler()}와 동일
            />

            <TextInput  height={14}
                        value={comment}
                        changeHandler={ (e) => {
                            setComment(e.target.value);
                        }}
            />

            <Button title="댓글 작성"
                    onClick={ () => commentCreateHandler(blog.id, comment) }
            />

            </Container>
        </Wrapper>
    );
}

export default BlogRead;