import styled from "styled-components";

const StyledTextInput = styled.textarea`
    width: calc(100% - 32px);
    ${(props) =>
        props.height && `height: ${props.height}px;`
    }
    padding: 16px;
    font-size: 16px;
    line-height: 20px;
    margin-bottom: 16px;
`;

const TextInput = ({height, value, changeHandler}) => {
    return (
        <StyledTextInput    height={height}
                            value={value}
                            onChange={changeHandler}
        />
    )
}

export default TextInput;