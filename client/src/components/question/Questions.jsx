import styled from 'styled-components';
// import Pagination from './pagenation';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function Questions() {
  const [qsData, setQsData] = useState([]);
  const [index, setIndex] = useState(0); // 탭메뉴 인덱스 상태
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
  const [totalPages, setTotalPages] = useState(3); // 전체 페이지 수
  const [totalcontetns, setTotalcontetns] = useState(0); // 전체 페이지 수

  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions?page=${currentPage}&size=10&sortBy=questionId`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (response) {
        setQsData(response.data.data); //현재 페이지의 데이터
        setTotalPages(Math.ceil(response.data.pageInfo.totalElements / 10)); //전체페이지 계산
        setTotalcontetns(response.data.pageInfo.totalElements);
        window.scrollTo(0, 0);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [currentPage]);

  //이전페이지
  const handlePreviousPageClick = () => {
    setCurrentPage((prev) => prev - 1);
  };

  //다음페이지
  const handleNextPageClick = () => {
    setCurrentPage((prev) => prev + 1);
  };

  //전체 페이지 수 만큼 버튼 생성
  const buttonArray = [];
  for (let i = 1; i <= totalPages; i++) {
    buttonArray.push(
      <button
        key={i}
        className={i === currentPage ? 'page-focused' : null}
        onClick={() => {
          setCurrentPage(i);
        }}
      >
        {i}
      </button>
    );
  }

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

  //필터버튼 리스트
  const buttomArr = [
    { bottomName: 'Newset' },
    { bottomName: 'Active' },
    { bottomName: 'Bountied' },
    { bottomName: 'Unanswered' },
  ];

  //필터버튼 인덱스 저장
  const selectMenu = (index) => {
    setIndex(index);
  };

  return (
    <>
      <Questionscomponent>
        <QuestionFilter>
          <div className="headContents">
            <h2>All Questions</h2>
            <buttom className="askquestion_Btn">Ask Question</buttom>
          </div>
          <div className="headContents flex-column">
            <span>{totalcontetns} questions</span>
            <aside className="subFilterBtn">
              {buttomArr.map((el, i) => (
                <button
                  key={i}
                  className={i === index ? 'focused' : null}
                  onClick={() => {
                    selectMenu(i);
                  }}
                >
                  {el.bottomName}
                </button>
              ))}
            </aside>
          </div>
        </QuestionFilter>

        {qsData.map((el) => (
          <QuestionList key={el.questionId}>
            <li>
              <IntData>
                <span>
                  <strong>{el.questionVoteSum}</strong> votes
                </span>
                <span>
                  <p className={el.answerCount > 0 ? 'answer_count' : null}>
                    <strong>{el.answerCount}</strong> answers
                  </p>
                </span>
                <span>
                  <strong>{el.viewCount}</strong> views
                </span>
              </IntData>
              <ContentsData>
                <Link to={'/question/' + el.questionId}>
                  <h3>{el.questionTitle}</h3>
                </Link>
                <span>{el.questionContent}</span>
                <div className="tagData">
                  {el.tagName.map((tag) => (
                    <p key={tag.tagId}>{tag.tagName}</p>
                  ))}
                </div>
              </ContentsData>
              <UserData>
                <img src="https://i.imgur.com/nXnTowV.jpg" alt="profile icon" />
                <span className="username_color">{el.memberName}</span>
                <span>
                  {el.memberReputation} asked{' '}
                  {displayedAt(new Date(el.createdAt))}
                </span>
              </UserData>
            </li>
          </QuestionList>
        ))}
        <Pagination>
          <div className="margin-left">
            <button
              onClick={handlePreviousPageClick}
              disabled={currentPage === 1}
            >
              Prev
            </button>
            {buttonArray}
            <button
              onClick={handleNextPageClick}
              disabled={currentPage === totalPages}
            >
              Next
            </button>
          </div>
          <div>{currentPage} page</div>
        </Pagination>
      </Questionscomponent>
    </>
  );
}

export default Questions;

const Pagination = styled.div`
  width: 100%;
  height: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  button {
    padding: 5px 10px;
    border: 1px solid #ccc;
    background: #fff;
    margin: 3px;
    border-radius: 3px;
    font-weight: 500;
  }
  button:disabled {
    background-color: #f7f7f7;
  }
  .margin-left {
    margin-left: 30px;
  }
  .page-focused {
    border-color: #f48225;
    background: #f48225;
    color: #fff;
  }
`;

const Questionscomponent = styled.section`
  width: 100%;
  height: auto;
  .bottom-more {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
    height: 80px;
    color: #999;
  }
  .bottom-more > * {
    margin: 5px;
  }
`;

const QuestionFilter = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  border-bottom: 1px solid #ddd;
  .headContents {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    margin-top: 10px;
  }
  h2 {
    font-size: 1.7em;
    font-weight: 500;
  }
  .askquestion_Btn {
    background: #0a95ff;
    display: flex;
    justify-content: center;
    font-size: 13px;
    align-items: center;
    color: #fff;
    width: 100px;
    height: 40px;
    border-radius: 3px;
    border: #1681d2;
  }
  .askquestion_Btn:hover {
    background: hsl(206, 100%, 40%);
  }
  .subFilterBtn {
    display: flex;
    overflow: hidden;
    height: 35px;
    border: 1px solid hsl(210, 8%, 65%);
    border-radius: 3px;
    button {
      background: rgb(255, 255, 255);
      border: none;
      color: #6b6f73;
      border-left: 1px solid hsl(210, 8%, 65%);
      padding: 0px 10px;
    }
    button:nth-child(1) {
      border: none;
    }
    button:hover {
      background: #f5f5f5;
    }
    button > span {
      padding: 0px 5px;
      margin-left: 3px;
      border-radius: 5px;
      color: #fff;
      background: hsl(206, 100%, 40%);
    }
    .focused {
      background-color: #e3e6e8;
      color: #000;
    }
    .focused:hover {
      background-color: #e3e6e8;
    }
  }
  @media (max-width: 800px) {
    .flex-column {
      display: flex;
      flex-direction: column;
      justify-content: left;
      align-items: flex-start;
    }
    .subFilterBtn {
      margin-top: 10px;
    }
  }
`;
const QuestionList = styled.ul`
  width: 100%;
  position: relative;
  border-bottom: 1px solid #ddd;
  li {
    display: flex;
    justify-content: center;
    padding: 20px;
  }

  @media (max-width: 800px) {
    li {
      display: flex;
      flex-direction: column;
      justify-content: center;
      padding: 20px;
    }
  }
`;

const IntData = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-end;
  flex-direction: column;
  width: 150px;
  height: 100%;
  margin-right: 20px;
  span {
    font-size: 13px;
    margin-top: 10px;
    text-align: right;
  }
  .answer_count {
    border-radius: 5px;
    border: 1px solid #2f6f44;
    color: #2f6f44;
    padding: 3px 10px;
  }
  @media (max-width: 800px) {
    flex-direction: row;
    span {
      text-align: left;
      width: auto;
      margin-right: 10px;
    }
  }
`;
const ContentsData = styled.div`
  width: 100%;
  > * {
    margin: 10px 0px;
  }
  h3 {
    font-weight: 500;
    color: #0074cc;
  }
  a {
    text-decoration: none;
  }
  span {
    font-size: 14px;
  }
  p {
    background-color: hsl(205, 46%, 92%);
    color: hsl(205, 47%, 42%);
    border-radius: 3px;
    border: 1px solid hsl(205, 47%, 42%);
    font-size: 12px;
    padding: 5px 10px;
    margin: 3px;
    transition: 0.2s ease-in-out;
  }
  p:hover {
    background-color: #afccdf;
    cursor: pointer;
  }
  .tagData {
    display: flex;
    justify-content: left;
    align-items: center;
    width: 100%;
  }
  @media (max-width: 800px) {
    h3 {
      font-size: 16px;
    }
  }
`;
const UserData = styled.div`
  display: flex;
  align-items: center;
  position: absolute;
  bottom: 20px;
  right: 20px;
  font-size: 12px;
  img {
    width: 15px;
    border-radius: 3px;
  }
  > * {
    margin-right: 3px;
  }
  .username_color {
    color: #0074cc;
  }
`;
