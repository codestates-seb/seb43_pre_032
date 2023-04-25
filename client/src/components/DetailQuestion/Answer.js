import styled from 'styled-components';
import AnsComment from './AnsComment';
import VoteGroup from './VoteGroup';
import { DetailContents, TextContents, SideContents } from './DetailContent';
import axios from 'axios';

function Answer({ answerData }) {
  console.log(answerData);

  //작성시간계산 : ~~시간전 으로 표기
  function displayedAt(createdAt) {
    const milliSeconds = new Date() - createdAt;
    const seconds = milliSeconds / 1000;
    if (seconds < 60) return `${Math.floor(seconds)} secs ago`;
    const minutes = seconds / 60;
    if (minutes < 60) return `${Math.floor(minutes)} min ago`;
    const hours = minutes / 60;
    if (hours < 24) return `${Math.floor(hours)} hour ago`;
    const days = hours / 24;
    if (days < 7) return `${Math.floor(days)} days ago`;
    const weeks = days / 7;
    if (weeks < 5) return `${Math.floor(weeks)} weeks ago`;
    const months = days / 30;
    if (months < 12) return `${Math.floor(months)} months ago`;
    const years = days / 365;
    return `${Math.floor(years)}years ago`;
  }

  const token = localStorage.getItem('token'); //로컬스토리지 토큰
  // console.log(token);

  //답변 삭제 핸들러
  const deleteAnswer = (answerId) => {
    console.log(`답변 아이디 번호 ${answerId}`);

    axios
      .delete(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/answers/${answerId}`,
        {
          headers: {
            Authorization: token,
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (res) {
        // 성공한 경우 실행
        console.log(res);
        window.location.reload();
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  };

  return (
    <>
      <AnswerTitle>
        <h2>{answerData.length} Answer</h2>
        <select>
          <option>Highest score (default)</option>
          <option>Trending (recent votes count more)</option>
          <option>Date modified (newest first)</option>
          <option>Date created (oldest first)</option>
        </select>
      </AnswerTitle>
      {answerData.map((answer) => (
        <DetailContents key={answer.answerId}>
          <div>
            <VoteGroup />
            <TextContents>
              <span>{answer.answerContent}</span>
              <SideContents>
                <div className="subMenus">
                  <button>Share</button>
                  <button>Edit</button>
                  <button
                    onClick={() => {
                      deleteAnswer(answer.answerId);
                    }}
                  >
                    delete
                  </button>
                </div>
                <div>
                  <div className="user-info">
                    <img
                      src="https://i.imgur.com/FZ7PeOO.png"
                      alt="profileIcon"
                    />
                    <span>
                      <p>{answer.memberName}</p>
                      <p>asked {displayedAt(new Date(answer.createdAt))}</p>
                    </span>
                  </div>
                </div>
              </SideContents>
              <AnsComment />
            </TextContents>
          </div>
        </DetailContents>
      ))}
    </>
  );
}

export default Answer;

const AnswerTitle = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 20px 0px;
`;
