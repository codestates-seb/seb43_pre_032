import styled from 'styled-components';
import AnsComment from './AnsComment';
import VoteGroup from './VoteGroup';
import { DetailContents, TextContents, SideContents } from './DetailContent';
import axios from 'axios';
import { useEffect, useState } from 'react';

function Answer({ qsId }) {
  const [answerTap, setAnswerTap] = useState('score');
  const [answerlist, setAnswerlist] = useState([]);

  const token = localStorage.getItem('token'); //로컬스토리지 토큰

  //답변 리스트 불러오기
  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/${qsId.qsId}?page=1&answertab=${answerTap}`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (res) {
        console.log(res.data.data.answer);
        setAnswerlist(res.data.data.answer);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [answerTap]);

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

  //답변 filter tap
  const answerFilter = [
    {
      id: 1,
      Name: 'Highest score (default)',
      value: 'score',
    },
    {
      id: 2,
      Name: 'Date modified (newest first)',
      value: 'newest',
    },
    {
      id: 3,
      Name: 'Date created (oldest first)',
      value: 'oldest',
    },
  ];

  //answer filter 핸들러
  const answerFilterhandler = (e) => {
    setAnswerTap(e.target.value);
  };

  return (
    <>
      <AnswerTitle>
        <h2>{answerlist.length} Answer</h2>
        <select onChange={answerFilterhandler}>
          {answerFilter.map((el) => (
            <option key={el.id} value={el.value}>
              {el.Name}
            </option>
          ))}
        </select>
      </AnswerTitle>
      {answerlist.map((answer) => (
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
              <AnsComment
                answerComment={answer.answerAnswers}
                answerId={answer.answerId}
              />
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
