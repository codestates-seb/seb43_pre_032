import Article from './Article';
import styled from 'styled-components';
import CardHeader from './CardHeader';
import { useDispatch } from 'react-redux';
import { useEffect } from 'react';
import { selectFooter, selectNav } from '../../../store/store';
import MemberDelete from '../../MemberDelete';

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
  padding: 10px;
  font-size: 20px;
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
  // main에 사용할 데이터 전달 받음
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(selectNav(true)); // nav 가져옴
    dispatch(selectFooter(true)); // footer 가져옴
  }, []);

  const option1 = ['score', 'Activity', 'Newest']; // 옵션 타입1
  const option2 = ['score', 'Activity', 'Newest', 'Views']; // 옵션 타입2

  const questions = mainData.questions; //questions 배열
  const answers = mainData.answers; // answers 배열

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
          <Box1 className="mypage-col-style">
            {mainData.aboutMe
              ? mainData.aboutMe
              : 'Edit profile을 통해 자신을 소개해 주세요!'}
          </Box1>
        </SummaryContentsContainer>
      </div>
      <ShortContainer>
        <Article
          title="Answers"
          options={option1}
          data={answers}
          select="answerContent"
        />
        <Article
          title="Questions"
          data={questions}
          options={option2}
          select="questionsTitle"
        />
      </ShortContainer>
      <MemberDelete />
    </MainContainer>
  );
};

export default Myinfo_Main;
