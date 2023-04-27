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
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/mypage/${memberId.qsId}` // 유저 정보 가져오기
      )
      .then((res) => {
        let topData = {
          name: res.data.name,
          createAt: res.data.createAt,
          memberId: res.data.memberId,
          myPageTitle: res.data.myPageTitle,
          modifiedAt: res.data.modifiedAt,
          memberJpegUrl: res.data.memberJpegUrl,
        };
        let mainData = {
          questions: res.data.questions,
          questionCount: res.data.questionCount,
          reputation: res.data.reputation,
          answerCount: res.data.answerCount,
          answers: res.data.answers,
          aboutMe: res.data.aboutMe,
        };
        setTopData(topData); // 상단 데이터 상태 응답 데이터로 변경
        setMainData(mainData); // 중심 데이터 상태 응답 데이터 변경
      });
  }, []);
  const [topData, setTopData] = useState({}); // 상단 부분 data 상태
  const [mainData, setMainData] = useState({}); // 중심 부분 data 상태
  // 이후 각 컴포넌트에 필요한 데이터 내려주기
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
