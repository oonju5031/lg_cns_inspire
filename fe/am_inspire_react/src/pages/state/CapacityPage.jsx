/*
- 현재 인원과 최대 인원이 있고
- 증가 버튼의 이벤트를 통해서 버튼이 클릭될 때마다 인원을 증가시킴
- 최대 인원에 도달하면 입장 버튼 비활성화
- 감소 버튼의 이벤트를 통해서 버튼이 클릭될 때마다 인원을 감소시킴
- 최대 인원에 도달하지 않으면 입장 버튼 활성화
*/

import {useState, useEffect} from 'react';  // (상태 관리) 여러 개를 export -> export default 사용 불가 -> {}를 써서 import
import Button from "../../ui/Button";

const CapacityPage = () => {

    // let cnt = 0;
    const [cnt, setCnt] = useState(0);  // (상태 관리) const [변수명, set 함수명] = useState(초기값)
    const [full, setFull] = useState(false);
    const [empty, setEmpty] = useState(true);
    const capacity = 10;

    const upCntHandler = () => {
        // cnt++;  // (상태 관리) script에선 증가하나 document에선 증가하지 않음 -> useState를 사용한 상태 관리 필요
        setCnt(cnt => cnt + 1);
        // console.log(">>> upCntHandler called: ", cnt)  // (사이드 이펙트) useState 사용 시: document에선 증가하나 script에선 증가하지 않음 -> useEffect를 사용한 side effect 관리 필요
    }

    const downCntHandler = () => {
        // cnt--;
        setCnt(cnt => cnt - 1);
        console.log();
        // console.log(">>> downCntHandler called: ", cnt)
    }

    // (사이드 이펙트)
    // 현재 useEffect(이펙트 함수) 형태 -> update 시마다 호출됨
    // 만일 useEffect(이펙트 함수, []) 형태라면 mount, unmount 시에 각 1회만 호출됨
    // useEffect(() => {
    //     console.log(">>> useEffect called");
    //     console.log(">>> cnt", cnt);
    // });

    // setFull을 적용하는 경우
    useEffect(() => {
        console.log(">>> useEffect called");
        console.log(">>> cnt", cnt);
        setFull(cnt >= capacity);
        setEmpty(cnt == 0);
    }, [cnt]);

    return (
        <div>
            <p>입장 인원: {cnt}</p>
            <Button title="증가" onClick={() => upCntHandler()}   disabled={full}  />
            <Button title="감소" onClick={() => downCntHandler()} disabled={empty} />
            {
                full && <p style={{color: "red"}}>정원이 가득 찼습니다.</p>
            }
        </div>
    )

}

export default CapacityPage;