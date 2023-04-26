import Article from './Article';
import styled from 'styled-components';
import CardHeader from './CardHeader';
import { useDispatch } from 'react-redux';
import { useEffect } from 'react';
import { selectFooter, selectNav } from '../../../store/store';
// import { useState } from 'react';
const MainContainer = styled.div`
  width: 100%;
  @media (max-width: 1300px) {
    padding-left: 30px;
    margin-right: 40px;
  }
`;
const Box = styled.div`
  height: 300px;
  width: 100%;
  border: 1px solid var(--menu-hover-background);
  border-radius: 5px;
`;
const Box1 = styled(Box)`
  height: 300px;
  @media (max-width: 980px) {
    min-width: 610px;
  }
`;

const SummaryContentsContainer = styled.div`
  display: grid;
  grid-column-gap: 20px;
  @media only screen and (max-width: 980px) {
    grid-template-columns: 1fr;
    /* grid-row-gap: 20px; */
    grid-column-gap: 0px;
  }
  width: 100%;
`;
const ShortContainer = styled.div`
  display: grid;
  grid-column-gap: 30px;
  grid-template-columns: 1fr;

  width: 100%;
`;
// const LongContainer = styled.div`
//   display: grid;
//   grid-template-columns: 1fr;
//   width: 100%;
// `;
const StatusContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-column-gap: 10%;
  min-height: 80px;
  width: 100%;
  border: 1px solid lightgray;
  border-radius: 4px;
`;
const Status = styled.div`
  width: 100%;
`;

const Myinfo_Main = ({ mainData }) => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(selectNav(true));
    dispatch(selectFooter(true));
  }, []);

  const option1 = ['score', 'Activity', 'Newest'];
  const option2 = ['score', 'Activity', 'Newest', 'Views'];
  // const option3 = ['score', 'Activity', 'Newest', 'Added'];
  // const option4 = ['Active', 'Offered', 'Earned'];
  // const option5 = ['score', 'Activity', 'Newest', 'Views'];

  // let [titles, setTitles] = useState([]);
  // let [reputation, setReputation] = useState(0);
  // let [questionCnt, setQuestionCnt] = useState(0);
  // let [answerCnt, setAnswerCnt] = useState(0);
  const questions = mainData.questions;
  const answers = mainData.answers;
  console.log(mainData);
  return (
    <MainContainer>
      <div className="flex-col">
        <StatusContainer>
          <Status className="flex-space-around">
            <div>reputation</div>
            <div>{mainData.reputation}</div>
          </Status>
          <Status className="flex-space-around">
            <div>answers</div>
            <div>{mainData.answerCount}</div>
          </Status>
          <Status className="flex-space-around">
            <div>questions</div>
            <div>{mainData.questionCount}</div>
          </Status>
        </StatusContainer>
        <CardHeader title="About me" />
        <SummaryContentsContainer>
          <Box1 className="mypage-col-style"></Box1>
        </SummaryContentsContainer>
      </div>
      <ShortContainer>
        <Article title="Answers" options={option1} data={answers} />
        <Article title="Questions" data={questions} options={option2} />
        <Article title="Tags" isFilter={false} />
        <Article title="Requtation" isFilter={false} />
      </ShortContainer>
      {/* <LongContainer>
        <Article title="Budges" options={option3} />
        <Article title="Followed posts" options={option4} />
        <Article title="Active bounties" isFilter={false} />
        <Article title="Articles" options={option5} />
        <Article title="Votes cast" isFilter={false} />
      </LongContainer> */}
    </MainContainer>
  );
};

export default Myinfo_Main;
