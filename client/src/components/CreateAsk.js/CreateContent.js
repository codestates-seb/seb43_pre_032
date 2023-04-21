import { useState } from 'react';
import styled from 'styled-components';

const BtnContainer = styled.div`
  margin-top: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 35px;
  width: 75px;
  font-size: 13px;
  font-weight: 600;
  color: white;
  background-color: var(--signup-btn-before);
  :hover {
    background-color: var(--signup-btn-after);
  }
`;
const InputContainer = styled.section`
  display: grid;
  grid-row-gap: 3px;
  padding: 20px;
  .title {
    font-weight: 600;
  }
  .help {
    width: 100%;
    font-size: 12px;
  }
  border: 1px solid lightgray;
  border-radius: 3px;
`;
const TextareaContainer = styled.section`
  display: grid;
  grid-row-gap: 3px;
  padding: 20px;
  .title {
    font-weight: 600;
  }
  .help {
    width: 100%;
    font-size: 12px;
  }
  .modify-textarea-content {
    width: 98%;
  }
  border: 1px solid lightgray;
  border-radius: 3px;
`;
const InputItem = ({ handleData, title, help }) => {
  let [text, setText] = useState('');
  const textHandler = (e) => {
    setText(e.target.value);
  };
  console.log(handleData, text);
  handleData(text);
  return (
    <InputContainer>
      <div className="title">{title}</div>
      <div className="help">{help}</div>
      <input onChange={textHandler} className="modify-input-content"></input>
    </InputContainer>
  );
};
const TextareaItem = ({ title, help }) => {
  let [text, setText] = useState('');
  text.length;
  const textHandler = (e) => {
    setText(e.target.value);
  };
  return (
    <TextareaContainer>
      <div className="title">{title}</div>
      <div className="help">{help}</div>
      <input onChange={textHandler} className="modify-textarea-content"></input>
    </TextareaContainer>
  );
};
export { InputItem, TextareaItem, BtnContainer };
