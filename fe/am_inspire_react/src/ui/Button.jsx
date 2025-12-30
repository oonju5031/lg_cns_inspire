import React from "react";
import styled from "styled-components";

// Backtick을 사용해 const에 key-value 대신 일반적인 css 구문을 입력 가능(styled-component의 기능)
const StyledButton = styled.button`
    padding: 8px 16px;
    font-size: 16px;
    border-width: 1px;
    border-radius: 8px;
    cursor: pointer;

    & + & {
        margin-left: 15px;
    }
`

const Button = (props) => {
    return (
        <StyledButton   onClick={props.onClick}
                        disabled={props.disabled}>
            {props.title}
        </StyledButton>
    )
}

export default Button;