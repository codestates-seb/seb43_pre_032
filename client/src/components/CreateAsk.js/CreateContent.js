import styled from 'styled-components';

const InputItem = ({ setTitle, title, help }) => {
  //제목, 제목변경함수, Help 배너 내용 내려받기
  const textHandler = (e) => {
    // input 입력 추적
    setTitle(e.target.value);
  };

  return (
    <InputContainer>
      <div className="title">{title[0]}</div>
      <div className="help">{help}</div>
      <input onChange={textHandler} className="modify-input-content"></input>
    </InputContainer>
  );
};

// eslint-disable-next-line react/display-name
const TextareaItem = ({ setBody, title, help }) => {
  //내용, 내용변경함수, Help 배너 내용 내려받기
  const textHandler = (e) => {
    // Textarea 입력 추적
    setBody(e.target.value);
  };

  return (
    <TextareaContainer>
      <div className="title">{title[0]}</div>
      <div className="help">{help}</div>
      <input onChange={textHandler} className="modify-textarea-content"></input>
    </TextareaContainer>
  );
};
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
