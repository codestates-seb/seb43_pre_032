import styled from 'styled-components';

function Questions() {
  return (
    <>
      <Questionscomponent>
        <QuestionFilter>
          <div className="headContents">
            <h2>All Questions</h2>
            <buttom className="askquestion_Btn">Ask Question</buttom>
          </div>
          <div className="headContents flex-column">
            <span>23,652,799 questions</span>
            <aside className="subFilterBtn">
              <button>Newset</button>
              <button>Active</button>
              <button>
                Bountied<span className="length_tag">234</span>
              </button>
              <button>Unanswered</button>
            </aside>
          </div>
        </QuestionFilter>
        <QuestionList>
          <li>
            <IntData>
              <span>
                <strong>0</strong> votes
              </span>
              <span>
                <strong>0</strong> answers
              </span>
              <span>
                <strong>2</strong> views
              </span>
            </IntData>
            <ContentsData>
              <h3>
                PHP dropdown populated from MySQL database wont POST selection
                to PHP query
              </h3>
              <span>
                Scenario: I am populating a dropdown menu with data from MySQL
                database. Upon clicking submit button, script should take the
                user to the results page and show data based on their selection.
              </span>
              <div className="tagData">
                <p>php</p>
                <p>php</p>
                <p>php</p>
                <p>php</p>
              </div>
            </ContentsData>
            <UserData>
              <img src="https://i.imgur.com/nXnTowV.jpg" alt="profile icon" />
              <span className="username_color">zth_codes</span>
              <span>1 asked 43 secs ago</span>
            </UserData>
          </li>
        </QuestionList>
      </Questionscomponent>
    </>
  );
}

export default Questions;

const Questionscomponent = styled.section`
  width: 100%;
  height: auto;
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
  align-items: center;
  flex-direction: column;
  width: 200px;
  height: 100%;
  margin-right: 20px;
  span {
    font-size: 13px;
    margin-top: 10px;
    width: 100%;
    text-align: right;
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
  > * {
    margin: 10px 0px;
  }
  h3 {
    font-weight: 500;
    color: #0074cc;
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
