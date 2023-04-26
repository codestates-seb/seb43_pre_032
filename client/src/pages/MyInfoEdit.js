import MyPageTop from '../components/MyinfoCom/SectionTop';
import styled from 'styled-components';
import NavigateBar from '../components/MyinfoCom/NavigateBar';
import MyInfoEdit_SideBar from '../components/MyInfoEdit/MyInfoEdit_SideBar';
import MyInfoEdit_Main from '../components/MyInfoEdit/MyInfoEdit_Main';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

const MyPageContainer = styled.main`
  font-size: 14px;
  /* width: calc(100%-200px); */
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
  const memberId = useParams();
  console.log(memberId);
  const [topData, setTopData] = useState({});
  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/mypage/${memberId.userId}`
      )
      .then((res) => {
        console.log(res.data);
        let topData = {
          name: res.data.name,
          createAt: res.data.createAt,
          memberId: res.data.memberId,
          myPageTitle: res.data.myPageTitle,
        };
        console.log(res.data.question);
        setTopData(topData);
      });
  }, []);
  return (
    <MyPageContainer>
      <MyPageTop topData={topData} />
      <MyPageBarSection>
        <NavigateBar />
      </MyPageBarSection>
      <MyPageMainSection className="flex-row">
        <MyInfoEdit_SideBar />
        <MyInfoEdit_Main
          membername={topData.name}
          memberId={memberId.userId}
        ></MyInfoEdit_Main>
      </MyPageMainSection>
    </MyPageContainer>
  );
};
export default MyInfoEdit;
