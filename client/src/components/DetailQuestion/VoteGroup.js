import styled from 'styled-components';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faPlay,
  faClockRotateLeft,
  faBookmark as faSolidBookmark,
} from '@fortawesome/free-solid-svg-icons';
import { faBookmark as faRegularBookmark } from '@fortawesome/free-regular-svg-icons';
import { useState } from 'react';

function VoteGroup({ qsId, voteSum }) {
  const [bookmark, setBookmark] = useState(false);

  const memberId = localStorage.getItem('memberid'); //로컬스토리지 멤버아이디 가져오기
  const token = localStorage.getItem('token'); //로컬스토리지 토큰 가져오기

  const voteHandler = (votee) => {
    axios
      .post(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questionvote`,
        { questionId: qsId.qsId, memberId: memberId, vote: votee },
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
    <VoteIcon>
      <span className="side-icon-color">
        <FontAwesomeIcon
          icon={faPlay}
          rotation={270}
          onClick={() => {
            voteHandler(true);
          }}
        />
      </span>
      <span>{voteSum}</span>
      <span className="side-icon-color">
        <FontAwesomeIcon
          icon={faPlay}
          rotation={90}
          onClick={() => {
            voteHandler(false);
          }}
        />
      </span>
      <button
        className="side-icon-size side-icon-color"
        onClick={() => {
          setBookmark(!bookmark);
        }}
      >
        {bookmark ? (
          <FontAwesomeIcon icon={faSolidBookmark} className="color-orange" />
        ) : (
          <FontAwesomeIcon icon={faRegularBookmark} />
        )}
      </button>
      <span className="side-icon-size side-icon-color">
        <FontAwesomeIcon icon={faClockRotateLeft} />
      </span>
    </VoteIcon>
  );
}

export default VoteGroup;

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
