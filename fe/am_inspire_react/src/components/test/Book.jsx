

const Book = (props) => {
    return (
        <div>
            <div>책 이름: {props.name}, 책 가격: {props.price}</div>
        </div>
    )
}

/* 구조 분해를 사용해 props 대신 {name, price}를 사용할 수도 있다. */
// const Book = ({name, price}) => {
//     return (
//         <div>
//             <div>책 이름: {name}, 책 가격: {price}</div>
//         </div>
//     )
// }

/* 객체의 형태로 전달받은 경우 */
// const Book = (props) => {
//     return (
//         <div>
//             <div>책 이름: {props.data.name}, 책 가격: {props.data.price}</div>
//         </div>
//     )
// }


export default Book;