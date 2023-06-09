import styled from 'styled-components';
import Answer from './Answer';
import YourAnswer from './YourAnswer';
import Comment from './Comment';
import VoteGroup from './VoteGroup';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';

function DetailContent({ data, tagData, answerData, qsId }) {
  // console.log(qsId);

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

  const navigate = useNavigate(); //네비게이트
  const token = localStorage.getItem('token'); //로컬스토리지 토큰 가져오기
  const memberid = localStorage.getItem('memberid');

  const deleteHandler = () => {
    axios
      .delete(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/${qsId.qsId}`,
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
        navigate('/question');
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  };

  return (
    <DetailContents>
      <div>
        <VoteGroup qsId={qsId} voteSum={data.questionVoteSum} />
        <TextContents>
          <span>{data.questionContent}</span>
          <div className="tagData">
            {tagData.map((tag) => (
              <p key={tag.tagId}>{tag.tagName}</p>
            ))}
          </div>
          <SideContents>
            {Number(data.memberId) === Number(memberid) ? (
              <div className="subMenus">
                <button>Share</button>
                <button>
                  <Link to={`/modify/${qsId.qsId}`}>Edit</Link>
                </button>
                <button onClick={deleteHandler}>delete</button>
              </div>
            ) : (
              <div className="subMenus">
                <button>Share</button>
                <button>Follow</button>
              </div>
            )}
            <div>
              <div className="user-info">
                <img src="https://i.imgur.com/4b1ExzY.png" alt="profileIcon" />
                <span>
                  <p>{data.memberName}</p>
                  <p>asked {displayedAt(new Date(data.createdAt))}</p>
                </span>
              </div>
            </div>
          </SideContents>
          <Comment />
        </TextContents>
      </div>
      <div>
        <Answer answerData={answerData} qsId={qsId} />
      </div>
      <div>
        <YourAnswer qsId={qsId} />
      </div>
    </DetailContents>
  );
}

export default DetailContent;

export const DetailContents = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 30px;
  max-width: 700px;
  div:nth-child(1) {
    display: flex;
  }
`;

export const TextContents = styled.div`
  width: 100%;
  padding: 0px 20px;
  line-height: 150%;
  word-break: break-all;
  .tagData {
    display: flex;
    justify-content: left;
    align-items: center;
    width: 100%;
    flex-wrap: wrap;
    margin-top: 20px;
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
  padding: 20px 0px;
  a {
    color: #999;
    text-decoration: none;
  }
  a:hover {
    color: #666;
  }
  button {
    background: none;
    border-style: none;
    color: #999;
    font-size: 14px;
    margin-right: 10px;
    cursor: pointer;
  }

  .subMenus button:hover {
    color: #666;
  }
  .user-info {
    display: flex;
    align-items: center;
    font-size: 12px;
    padding: 10px 20px;
    max-width: 150px;
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
  @media (max-width: 660px) {
    flex-direction: column;
    > div:nth-child(2) {
      display: flex;
      width: 100%;
      justify-content: right;
    }
  }
`;
