import MyPageTop from '../components/MyinfoCom/SectionTop';
import styled from 'styled-components';
import NavigateBar from '../components/MyinfoCom/NavigateBar';
import MyInfoEdit_SideBar from '../components/MyInfoEdit/MyInfoEdit_SideBar';
import MyInfoEdit_Main from '../components/MyInfoEdit/MyInfoEdit_Main';

const MyPageContainer = styled.main`
  font-size: 14px;
  width: calc(100%-200px);
  height: 100%;
  .flex-row {
    display: flex;
  }
`;
const MyPageBarSection = styled.section`
  padding-left: 20px;
`;
const MyPageMainSection = styled.section`
  padding-top: 15px;
  padding-left: 20px;
`;

const MyInfoEdit = () => {
  return (
    <MyPageContainer>
      <MyPageTop />
      <MyPageBarSection>
        <NavigateBar />
      </MyPageBarSection>
      <MyPageMainSection className="flex-row">
        <MyInfoEdit_SideBar />
        <MyInfoEdit_Main></MyInfoEdit_Main>
      </MyPageMainSection>
    </MyPageContainer>
  );
};
export default MyInfoEdit;
