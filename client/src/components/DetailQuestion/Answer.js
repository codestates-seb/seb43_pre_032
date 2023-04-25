import styled from 'styled-components';
import AnsComment from './AnsComment';
import VoteGroup from './VoteGroup';
import { DetailContents, TextContents, SideContents } from './DetailContent';

function Answer({ answerData }) {
  // console.log(answerData);

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
                  <p>Share</p>
                  <p>Edit</p>
                  <p>Delete</p>
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
