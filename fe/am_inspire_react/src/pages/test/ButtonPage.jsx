import Button from "../../ui/Button"

const ButtonPage = () => {
    const saveHandler = () => {
        console.log(">>> saveHandler call");
    }
    const listHandler = () => {
        console.log(">>> listHandler call");
    }

    return (
        <div>
            <Button
                title="글 작성하기"
                onClick={(e) => saveHandler() } />
            <Button
                title="목록 보기"
                onClick={(e) => listHandler()} />
        </div>
    )
}

export default ButtonPage;