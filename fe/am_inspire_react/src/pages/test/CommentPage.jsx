import Comment from "../../components/test/Comment";
import "../../styles/comment.css";  // 외부 스타일시트: script에서 script를 호출하는 것이 아닌 css를 호출하므로 확장자를 명시하여야 한다.

// 내부 스타일시트: 스크립트의 변수 형태로 사용한다. 문법이 일반 css와 다름에 주의
const styles = {
	content: {
		display: "flex",
		flexDirection: "column",
		justifyContent: "center",
		marginLeft: "8px"
	},
    image: {
        width: "50px",
        height: "50px",
        borderRadius: "25px"
    }
};

const CommentPage = () => {

    const comments = [
        {
            name: "이준영",
            comment: "안녕하세요."
        },
        {
            name: "김민재",
            comment: "저는 수학과입니다."
        },
        {
            name: "정광희",
            comment: "저도 수학과입니다."
        }
    ]

    return (
        // <div className="wrapper">
        //     <div>
        //         <img src='../../img/image.webp' style={styles.image} />
        //     </div>
        //     <div style={styles.content}>  {/* 내부 스타일시트의 사용: inline style 형태로 사용한다. */}
        //         <span>이준영</span>
        //         <span>안녕하세요.</span>
        //     </div>
        // </div>
        <div>
            {
                comments.map(comment => {
                    return (
                        <Comment data={comment} />
                    );
                })
            }
        </div>
    );
}

export default CommentPage;