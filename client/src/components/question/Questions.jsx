import styled from 'styled-components';

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
    padding: 0px 20px 10px 20px;
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
    border-radius: 5px;
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
`;
const QuestionList = styled.ul`
  width: 100%;
`;

const IntData = styled.div``;
const ContentsData = styled.div``;
const UserData = styled.div``;

function Questions() {
  return (
    <>
      <Questionscomponent>
        <QuestionFilter>
          <div className="headContents">
            <h2>All Questions</h2>
            <buttom className="askquestion_Btn">Ask Question</buttom>
          </div>
          <div className="headContents">
            <span>23,652,799 questions</span>
            <aside className="subFilterBtn">
              <button>Newset</button>
              <button>Active</button>
              <button>
                Bountied<span className="length_tag">234</span>
              </button>
              <button>Unanswered</button>
              <button>More</button>
            </aside>
          </div>
        </QuestionFilter>
        <QuestionList>
          <IntData>
            <span>0 votes</span>
            <span>0 answers</span>
            <span>2 views</span>
          </IntData>
          <ContentsData>
            <h3>
              PHP dropdown populated from MySQL database wont POST selection to
              PHP query
            </h3>
            <span>
              Scenario: I am populating a dropdown menu with data from MySQL
              database. Upon clicking submit button, script should take the user
              to the results page and show data based on their selection.
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
            <span>zth_codes</span>
            <span>1 asked 43 secs ago</span>
          </UserData>
        </QuestionList>
      </Questionscomponent>
    </>
  );
}

export default Questions;
