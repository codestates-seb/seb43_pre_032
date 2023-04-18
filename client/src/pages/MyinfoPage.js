import MyPageTop from '../components/MyinfoCom/SectionTop';
import styled from 'styled-components';
import NavigateBar from '../components/MyinfoCom/NavigateBar';
import Myinfo_SideBar from '../components/MyinfoCom/Main/Myinfo_SideBar';
import Myinfo_Main from '../components/MyinfoCom/Main/Myinfo_Main';
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
  return (
    <MyPageContainer>
      <MyPageTop />
      <MyPageBarSection>
        <NavigateBar />
      </MyPageBarSection>
      <MyPageMainSection className="flex-row">
        <Myinfo_SideBar></Myinfo_SideBar>
        <Myinfo_Main></Myinfo_Main>
      </MyPageMainSection>
    </MyPageContainer>
  );
};
export default MyPage;
