import styled from 'styled-components';
import AnsComment from './AnsComment';
import {
  DetailContents,
  VoteIcon,
  TextContents,
  SideContents,
} from './DetailContent';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faPlay,
  faClockRotateLeft,
  faBookmark as faSolidBookmark,
} from '@fortawesome/free-solid-svg-icons';
import { faBookmark as faRegularBookmark } from '@fortawesome/free-regular-svg-icons';
import { useState } from 'react';

function Answer() {
  const [bookmark, setBookmark] = useState(false);
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
