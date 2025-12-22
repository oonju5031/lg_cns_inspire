import "../../styles/comment.css";

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

const Comment = (props) => {
    return (
        <div className="wrapper">
            <div>
                <img src='../../img/image.webp' style={styles.image} />
            </div>
            <div style={styles.content}>
                <span>{props.data.name}</span>
                <span>{props.data.comment}</span>
            </div>
        </div>
        
    );
}

export default Comment;