import styled from 'styled-components';
import { Link } from 'react-router-dom';

function DetailTitle({ data }) {
  return (
    <Detailtitle>
      <header className="question-header">
        <h2>{data.questionTitle}</h2>
        <Address>
          <span>
            Asked <strong>today</strong>
          </span>
          <span>
            Modified <strong>today</strong>
          </span>
          <span>
            Viewed <strong>today</strong>
          </span>
        </Address>
      </header>
      <div className="question-btn">
        <Link to={'/question/ask'}>
          <button className="askquestion_Btn">Ask Question</button>
        </Link>
      </div>
    </Detailtitle>
  );
}

export default DetailTitle;

const Detailtitle = styled.article`
  display: flex;
  justify-content: space-between;
  width: 100%;
  border-bottom: 1px solid #ddd;
  padding-bottom: 30px;
  h2 {
    font-size: 1.8em;
    font-weight: 500;
    margin-bottom: 10px;
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
    font-weight: 500;
  }
  a {
    color: #fff;
    text-decoration: none;
  }
  .askquestion_Btn:hover {
    background: hsl(206, 100%, 40%);
  }
`;

const Address = styled.div`
  font-size: 12px;
  span {
    margin-right: 10px;
  }
`;
