import styled from 'styled-components';
import Answer from './Answer';
import YourAnswer from './YourAnswer';
import Comment from './Comment';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faPlay,
  faClockRotateLeft,
  faBookmark as faSolidBookmark,
} from '@fortawesome/free-solid-svg-icons';
import { faBookmark as faRegularBookmark } from '@fortawesome/free-regular-svg-icons';
import { useState } from 'react';

function DetailContent() {
  const [bookmark, setBookmark] = useState(false);
  return (
    <DetailContents>
      <div>
        <VoteIcon>
          <span className="side-icon-color">
            <FontAwesomeIcon icon={faPlay} rotation={270} />
          </span>
          <span>0</span>
          <span className="side-icon-color">
            <FontAwesomeIcon icon={faPlay} rotation={90} />
          </span>
          <button
            className="side-icon-size side-icon-color"
            onClick={() => {
              setBookmark(!bookmark);
            }}
          >
            {bookmark ? (
              <FontAwesomeIcon
                icon={faSolidBookmark}
                className="color-orange"
              />
            ) : (
              <FontAwesomeIcon icon={faRegularBookmark} />
            )}
          </button>
          <span className="side-icon-size side-icon-color">
            <FontAwesomeIcon icon={faClockRotateLeft} />
          </span>
        </VoteIcon>
        <TextContents>
          <span>
            I am a react native beginner struggling with concurrency. I would
            like to enforce within the handlePlay function that no new sound is
            played while the current sound is playing. So if sound1.mp3 is
            playing now and the handlePlay function is called, nothing will
            happen. Once sound1.mp3 is finished playing and the handlePlay
            function is called by useEffect being triggered again, sound2.mp3
            should be played (should be sound2.mp3 because currentStepInc will
            have been run to trigger the useEffect). However quickly the button
            is pressed there should be no time at which two sounds are being
            played at once.
          </span>
          <div className="tagData">
            <p>나는 왜 이시간까지 잠을안잤는가</p>
            <p>javascript</p>
            <p>styled-components</p>
          </div>
          <SideContents>
            <div className="subMenus">
              <p>Share</p>
              <p>Edit</p>
              <p>Follow</p>
            </div>
            <div className="user-info">
              <img src="https://i.imgur.com/4b1ExzY.png" alt="profileIcon" />
              <span>
                <p>scotjam1981</p>
                <p>asked 3 mins ago</p>
              </span>
            </div>
          </SideContents>
          <Comment />
        </TextContents>
      </div>
      <div>
        <Answer />
      </div>
      <div>
        <YourAnswer />
      </div>
    </DetailContents>
  );
}

export default DetailContent;

export const DetailContents = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 30px;
  div:nth-child(1) {
    display: flex;
  }
`;

export const VoteIcon = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  font-size: 30px;
  min-width: 50px;
  > * {
    margin-bottom: 10px;
  }
  .side-icon-size {
    font-size: 16px;
  }
  .side-icon-color {
    color: #ccc;
  }
  .side-icon-color:hover {
    color: hsl(206, 100%, 60%);
  }
  > button {
    background: none;
    border-style: none;
    cursor: pointer;
  }
  .color-orange {
    color: hsl(27, 90%, 55%);
  }
`;

export const TextContents = styled.div`
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

export const SideContents = styled.div`
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
