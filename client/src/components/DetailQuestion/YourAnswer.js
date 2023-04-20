import { useState } from 'react';
import styled from 'styled-components';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function YourAnswer() {
  const [openTip, setOpenTip] = useState(false);
  return (
    <>
      <YourAnswerTitle>
        <h2>YourAnswer</h2>
      </YourAnswerTitle>
      <YourAnswerInput>
        <textarea
          placeholder="답변 내용을 입력해주세요"
          onClick={() => {
            setOpenTip(true);
          }}
        ></textarea>
      </YourAnswerInput>
      {openTip ? (
        <YourAnswerTips>
          Thanks for contributing an answer to Stack Overflow!
          <ul>
            <li>
              Please be sure toanswer the question. Provide details and share
              your research!
            </li>
          </ul>
          But avoid…
          <ul>
            <li>
              Asking for help, clarification, or responding to other answers.
            </li>
            <li>
              Making statements based on opinion; back them up with references
              or personal experience.
            </li>
            <a href="https://stackoverflow.com/help/how-to-answer">
              To learn more, see our tips on writing great answers.
            </a>
          </ul>
          <button
            onClick={() => {
              setOpenTip(!openTip);
            }}
            className="openTip-btn"
          >
            <FontAwesomeIcon icon={faXmark} />
          </button>
        </YourAnswerTips>
      ) : null}
      <YourAnswerBtn>
        <button className="askquestion_Btn">Post Your Answer</button>
      </YourAnswerBtn>
    </>
  );
}

export default YourAnswer;

const YourAnswerTitle = styled.div`
  margin: 20px 0px;
`;

const YourAnswerInput = styled.div`
  display: flex;
  margin: 10px 0px;
  background-color: #f7f7f7;
  border-radius: 3px;
  border: 1px solid #ddd;
  padding: 20px;
  textarea {
    width: 100%;
    height: 100px;
    border: 1px solid #dfdfdf;
    padding: 20px;
  }
`;

const YourAnswerTips = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  margin: 10px 0px;
  background-color: #fdf7e2;
  border-radius: 3px;
  line-height: 180%;
  font-size: 14px;
  border: 1px solid hsl(47, 69%, 69%);
  padding: 20px;
  margin-bottom: 20px;
  li {
    list-style: inside;
    margin-left: 20px;
  }
  a {
    text-decoration: none;
    color: hsl(206, 100%, 32%);
  }
  a:hover {
    color: hsl(206, 100%, 52%);
  }
  .openTip-btn {
    position: absolute;
    top: 20px;
    right: 20px;
    width: 20px;
    height: 20px;
    border: none;
    background: none;
  }
`;

const YourAnswerBtn = styled.div`
  display: flex;
  .askquestion_Btn {
    background: #0a95ff;
    display: flex;
    justify-content: center;
    font-size: 13px;
    align-items: center;
    color: #fff;
    width: 125px;
    height: 40px;
    border-radius: 3px;
    border: #1681d2;
    font-weight: 500;
  }
  .askquestion_Btn:hover {
    background: hsl(206, 100%, 40%);
  }
`;
