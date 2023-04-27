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
  // axios요청 응답값으로 받아온 name과 reputation을 저장하는 상태
  const [name, setName] = useState('');
  const [reputation, setReputation] = useState('');

  // 로컬에 저장한 토큰과 멤버id 값을 변수에 할당
  const getToken = localStorage.getItem('token');
  const getMemberid = localStorage.getItem('memberid');

  // axios.get 멤버 id와 토큰을 사용해 이름을 읽어오는 함수
  const membersIdAxios = () => {
    //헤더에 토큰값을 주어서 유저정보를 읽어온다
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/mypage/${getMemberid}`,
        { headers: { Authorization: getToken } }
      )
      .then((res) => {
        //받아온 이름을 변수에 할당후
        const name = res.data.name;
        // 조건문을 통해 이름이 있다면 뒤에서 2글자만 저장(ex. 박지성 => 지성)
        if (name) {
          setName(name.slice(name.length - 2));
        }
        const reputation = res.data.requtation;
        setReputation(reputation);
      })
      .catch((err) => console.log(err));
  };

  //유즈이펙트로 처음 렌더링때 axios함수를 실행
  useEffect(() => {
    membersIdAxios();
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

// 스타일드 컴포넌트
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
