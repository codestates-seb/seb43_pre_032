import React, { useState } from 'react';
import styled from 'styled-components';

// eslint-disable-next-line react/display-name
const InputItem = React.memo(({ handleData, title, help }) => {
  let [text, setText] = useState('');

  const textHandler = (e) => {
    setText([title[1], e.target.value]);
    handleData(text);
  };

  return (
    <InputContainer>
      <div className="title">{title[0]}</div>
      <div className="help">{help}</div>
      <input onChange={textHandler} className="modify-input-content"></input>
    </InputContainer>
  );
});

// eslint-disable-next-line react/display-name
const TextareaItem = React.memo(({ handleData, title, help }) => {
  let [text, setText] = useState('');
  const textHandler = (e) => {
    setText([title[1], e.target.value]);
    handleData(text);
  };

  return (
    <TextareaContainer>
      <div className="title">{title[0]}</div>
      <div className="help">{help}</div>
      <input onChange={textHandler} className="modify-textarea-content"></input>
    </TextareaContainer>
  );
});
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
export { InputItem, TextareaItem };
