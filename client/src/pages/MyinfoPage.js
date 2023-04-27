import MyPageTop from '../components/MyinfoCom/SectionTop';
import styled from 'styled-components';
import NavigateBar from '../components/MyinfoCom/NavigateBar';
import Myinfo_SideBar from '../components/MyinfoCom/Main/Myinfo_SideBar';
import Myinfo_Main from '../components/MyinfoCom/Main/Myinfo_Main';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const MyPageContainer = styled.main`
  font-size: 14px;
  width: calc(100%-200px);
  height: 100%;
`;
const MyPageBarSection = styled.section`
  padding-left: 20px;
`;
const MyPageMainSection = styled.section`
  padding-top: 15px;
  padding-left: 20px;
`;

const MyPage = () => {
  //let id = localStorage.getItem('memberId') // 이렇게 로컬에서 가져와야 할 듯
  // const [myInfo, setMyInfo] = useState({});
  const memberId = useParams();
  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/mypage/${memberId.qsId}`
      )
      .then((res) => {
        console.log(res.data);
        let topData = {
          name: res.data.name,
          createAt: res.data.createAt,
          memberId: res.data.memberId,
          myPageTitle: res.data.myPageTitle,
          modifiedAt: res.data.modifiedAt,
        };
        let mainData = {
          questions: res.data.questions,
          questionCount: res.data.questionCount,
          reputation: res.data.reputation,
          answerCount: res.data.answerCount,
          answers: res.data.answers,
          aboutMe: res.data.aboutMe,
        };
        console.log(res.data.question);
        setTopData(topData);
        setMainData(mainData);
      });
  }, []);
  const [topData, setTopData] = useState({});
  const [mainData, setMainData] = useState({});
  return (
    <MyPageContainer>
      <MyPageTop topData={topData} />
      <MyPageBarSection>
        <NavigateBar />
      </MyPageBarSection>
      <MyPageMainSection className="flex-row">
        <Myinfo_SideBar></Myinfo_SideBar>
        <Myinfo_Main mainData={mainData}></Myinfo_Main>
      </MyPageMainSection>
    </MyPageContainer>
  );
};
export default MyPage;
