import styled from 'styled-components';
import AnsComment from './AnsComment';
import VoteGroup from './VoteIcon';
import { DetailContents, TextContents, SideContents } from './DetailContent';

function Answer({ answerData }) {
  console.log(answerData);
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
                <div className="user-info">
                  <img
                    src="https://i.imgur.com/DjXkSGM.png"
                    alt="profileIcon"
                  />
                  <span>
                    <p>{answer.memberName}</p>
                    <p>asked 40 mins ago</p>
                  </span>
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
