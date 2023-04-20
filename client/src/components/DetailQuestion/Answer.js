import styled from 'styled-components';
import AnsComment from './AnsComment';

function Answer() {
  return (
    <>
      <AnswerTitle>
        <h2>1 Answer</h2>
        <select>
          <option>Highest score (default)</option>
          <option>Trending (recent votes count more)</option>
          <option>Date modified (newest first)</option>
          <option>Date created (oldest first)</option>
        </select>
      </AnswerTitle>
      <DetailContents>
        <div>
          <VoteIcon></VoteIcon>
          <TextContents>
            <span>
              Open a file and use dir() on the open file object. Remove the
              entries starting with _ as they are typically implementation
              details. If you only want callable methods use getattr to look up
              the attribute in the object and callable to see if it is a method.
              There will be slightly different lists depending on the mode the
              file is opened with.
            </span>
            <SideContents>
              <div className="subMenus">
                <p>Share</p>
                <p>Edit</p>
                <p>Follow</p>
              </div>
              <div className="user-info">
                <img src="https://i.imgur.com/DjXkSGM.png" alt="profileIcon" />
                <span>
                  <p>JuBee</p>
                  <p>asked 40 mins ago</p>
                </span>
              </div>
            </SideContents>
            <AnsComment />
          </TextContents>
        </div>
      </DetailContents>
    </>
  );
}

export default Answer;

const AnswerTitle = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 20px 0px;
`;

const DetailContents = styled.div`
  display: flex;
  flex-direction: column;
  div:nth-child(1) {
    display: flex;
  }
`;

const VoteIcon = styled.div`
  min-width: 50px;
  border: 1px solid red;
`;

const TextContents = styled.div`
  width: 100%;
  padding: 0px 20px;
  line-height: 150%;
  .tagData {
    display: flex;
    justify-content: left;
    align-items: center;
    width: 100%;
    flex-wrap: wrap;
    margin: 20px 0px;
  }
  .tagData > p {
    background-color: hsl(205, 46%, 92%);
    color: hsl(205, 47%, 42%);
    border-radius: 3px;
    border: 1px solid hsl(205, 47%, 42%);
    font-size: 12px;
    padding: 0px 10px;
    margin: 3px;
    transition: 0.2s ease-in-out;
  }
  .tagData > p:hover {
    background-color: #afccdf;
    cursor: pointer;
  }
`;

const SideContents = styled.div`
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  .subMenus {
    display: flex;
    justify-content: left;
    align-items: center;
  }
  .subMenus > * {
    margin-right: 10px;
    color: #999;
  }
  .subMenus > *:hover {
    color: #666;
  }
  .user-info {
    display: flex;
    align-items: center;
    font-size: 12px;
    padding: 10px 20px;
    background-color: #f7f7f7;
    color: #5c666e;
    border-radius: 3px;
    border: 1px solid #9da1a3;
  }

  .user-info > img {
    border-radius: 5px;
    width: 30px;
    height: 30px;
    margin-right: 10px;
  }
`;
