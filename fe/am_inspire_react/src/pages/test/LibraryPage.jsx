import Book from '../../components/test/Book';

const LibraryPage = (props) => {  // 매개변수 = 속성(props; properties)

    const msg = "추가 텍스트"

    const books = [
        {name: "A", price: "10,000"},
        {name: "B", price: "11,000"},
        {name: "C", price: "21,000"},
        {name: "D", price: "24,000"}
    ];

    return (
        <div>  {/* 하나의 큰 div로 wrapping해야 한다. */}
            <div align="center">
                <font color="red">Hello World! {msg}</font>  {/* {}를 통해 출력 가능, {{}}를 통해 객체 출력 가능 */}
            </div>

            <hr/>

            {/* props 이름을 지정하여 보내기 */}
            <Book name="책 이름" price="12,000" />
            {
                books.map(book => {
                    return (  // return은 element가 추출되었음을 의미한다.
                        <Book name={book.name} price={book.price} />
                    )
                })
            }

            {/* // 객체의 형태로 보내기 -> book.jsx에서 {data.name}, {data.price}의 형태로 사용하여야 함 */}
            {/* { 
                books.map(book => {
                    return (
                        <Book data={book} />
                    )
                })
            } */}

        </div>    
    )

}

export default LibraryPage;  // 다른 컴포넌트에서 사용할 수 있도록 모듈화