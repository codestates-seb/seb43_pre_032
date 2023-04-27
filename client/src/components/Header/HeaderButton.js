import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faInbox,
  faCircleQuestion,
  faTrophy,
} from '@fortawesome/free-solid-svg-icons';
import { faStackExchange } from '@fortawesome/free-brands-svg-icons';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';

const OtherButtons = () => {
  // 로그인시 get요청으로 유저이름 가져오기
  // 프로필 클릭시 마이페이지 링크 연결

  const [name, setName] = useState(''); // 사용자 이름
  const [reputation, setReputation] = useState('');

  const getToken = localStorage.getItem('token'); // 로컬 저장된 토큰 가져오기
  const getMemberid = localStorage.getItem('memberid'); // 로컬 저장된 멤버 아이디 가져오기

  const membersIdAxios = () => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/mypage/${getMemberid}`, //url
        { headers: { Authorization: getToken } } // 인증토큰 헤더에 담기
      )
      .then((res) => {
        const name = res.data.name;

        if (name) {
          // 이름의 크기에 따른 프로필 글자 수 제한
          if (name.length === 3) {
            setName(name.slice(1, 3));
          } else if (name.length === 2) {
            setName(name);
          } else if (name.length === 4) {
            setName(name.slice(2, 4));
          } else {
            setName(name.slice(3, 5));
          }
        }
        const reputation = res.data.requtation;
        setReputation(reputation);
      })
      .catch((err) => console.log(err));
  };
  useEffect(() => {
    membersIdAxios(); // 화면 첫 렌더링 시 한번만 axios 데이터 받아오기
  }, []);

  return (
    <OtherContainer className="flex-center">
      <Menu className="section-size">
        <Link to={`/users/${getMemberid}`}>
          <Profile>{name}</Profile>
          {reputation}
        </Link>
      </Menu>
      <Menu className="menu">
        <FontAwesomeIcon icon={faInbox} />
      </Menu>
      <Menu className="menu">
        <FontAwesomeIcon icon={faTrophy} />
      </Menu>
      <Menu className="menu">
        <FontAwesomeIcon icon={faCircleQuestion} />
      </Menu>
      <Menu className="menu">
        <Link to={'/logout'}>
          <FontAwesomeIcon icon={faStackExchange} />
        </Link>
      </Menu>
    </OtherContainer>
  );
};

export const LoginBtn = styled.div`
  height: 30px;
  :hover {
    background-color: var(--login-btn-after);
  }
`;

export const LogOutBtn = styled.div`
  height: 30px;
  :hover {
    background-color: var(--login-btn-after);
  }
`;

export const SignInBtn = styled.div`
  height: 30px;
  font-weight: bold;
  width: 60px;
  :hover {
    background-color: var(--signup-btn-after);
  }
`;

const OtherContainer = styled.div`
  width: 230px;
  height: 100%;
  margin-left: 20px;

  a {
    text-decoration: none; /* 밑줄 제거 */
    color: inherit; /* 상속받은 색상 사용 */
    font-size: inherit;
  }

  @media (max-width: 640px) {
    width: 15%;
    .menu {
      display: none;
    }
  }
`;
const Menu = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 100%;
  padding: 0px 10px;
  color: hsl(210, 8%, 35%);
  :hover {
    background-color: var(--menu-hover-background);
  }
  .section-size {
    padding: 0px 20px;
  }
`;
const Profile = styled.div`
  width: 25px;
  height: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: brown;
  /* padding-top: 7px;
  padding-bottom: 7px;
  padding-left: 4px;
  padding-right: 4px; */
  margin-right: 2px;
  border-radius: 4px;
  color: white;
  font-size: 10px;
`;

export { OtherButtons };
