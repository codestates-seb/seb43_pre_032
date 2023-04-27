import styled from 'styled-components';
import '../../assets/logo.png';
import { LoginBtn, SignInBtn, LogOutBtn, OtherButtons } from './HeaderButton';
import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faMagnifyingGlass,
  faBars,
  faXmark,
} from '@fortawesome/free-solid-svg-icons';
import Nav from '../Nav';

function Header() {
  //토큰 여부로 로그인 여부 확인하여 헤더 표시
  const tokenValid = localStorage.getItem('token');
  let isLogin = false;
  if (tokenValid) {
    isLogin = true;
  } else {
    isLogin = false;
  }

  let [menuOpen, setMenuOpen] = useState(false);

  let navigate = useNavigate();

  const clickLogin = () => {
    navigate('/auth/login');
  };
  const clickSignup = () => {
    navigate('/members');
  };

  return (
    <HeaderContainer>
      <ContentsContainer className="flex-space-between">
        {menuOpen ? (
          <NavBox>
            <Nav />
          </NavBox>
        ) : null}
        <SidemenuGroup>
          <button
            onClick={() => {
              setMenuOpen(!menuOpen);
            }}
          >
            {menuOpen ? (
              <FontAwesomeIcon icon={faXmark} size="lg" />
            ) : (
              <FontAwesomeIcon icon={faBars} />
            )}
          </button>
        </SidemenuGroup>
        <Logogroup>
          <Link to={'/question'}>
            <div className="logo"></div>
          </Link>
          <MenuGroup>
            <Menu
              className={`flex-center display-none ${
                isLogin ? 'moblie-none' : null
              }`}
            >
              <a href="https://stackoverflow.co/">About</a>
            </Menu>
            <Menu className="flex-center">Products</Menu>
            <Menu
              className={`flex-center display-none ${
                isLogin ? 'moblie-none' : null
              }`}
            >
              <a href="https://stackoverflow.co/teams/">For Teams</a>
            </Menu>
          </MenuGroup>
        </Logogroup>
        <SerachGroup>
          <FontAwesomeIcon icon={faMagnifyingGlass} className="serch-icon" />
          <SerachBar placeholder="Search..."></SerachBar>
        </SerachGroup>
        {isLogin ? (
          <>
            <OtherButtons />
            <div className="flex-center log-sign margin-10">
              <Link to={'/logout'}>
                <LogOutBtn
                  className="flex-center btn-skyblue-style temp-width"
                  onClick={clickLogin}
                >
                  Log out
                </LogOutBtn>
              </Link>
            </div>
          </>
        ) : (
          <div className="flex-center log-sign margin-10">
            <LoginBtn
              className="flex-center btn-skyblue-style temp-width"
              onClick={clickLogin}
            >
              Log in
            </LoginBtn>
            <SignInBtn
              className="flex-center btn-blue-style"
              onClick={clickSignup}
            >
              Sign up
            </SignInBtn>
          </div>
        )}
      </ContentsContainer>
    </HeaderContainer>
  );
}
const HeaderContainer = styled.header`
  width: 100vw;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  -webkit-box-shadow: 0px 1px 1px -1px rgba(0, 0, 0, 0.01);
  box-shadow: 0px 1px 1px -1px rgba(0, 0, 0, 0.03);
  border-top: 1spx solid #f48225;
  background-color: #f8f9f9;
`;

const ContentsContainer = styled.div`
  position: relative;
  width: 1250px;
  display: flex;
  flex-direction: row;
  height: 100%;
  padding: 0px 10px;
  & > :hover.hover {
    background-color: var(--menu-hover-background);
  }

  a {
    text-decoration: none; /* 밑줄 제거 */
    color: inherit; /* 상속받은 색상 사용 */
    font-size: inherit;
  }

  .logo {
    width: 150px;
    height: 30px;
    background: url('/assets/logo.png');
    background-size: cover;
  }
  .display-login {
    display: none;
  }
  .margin-10 {
    margin: 0px 10px;
  }
  .temp-width {
    width: 50px;
  }
  @media (max-width: 800px) {
    .display-none {
      display: none;
    }
    .logo {
      width: 30px;
      height: 30px;
      background: url('/assets/logo-stackoverflow-icon.png');
      background-size: cover;
    }
  }
`;

const NavBox = styled.div`
  position: absolute;
  top: 45px;
  width: 250px;
  background-color: #fff;
  box-shadow: 0px 0px 6px rgb(0, 0, 0, 0.1);
  padding-bottom: 20px;
  @media (min-width: 640px) {
    display: none;
  }
`;

const SidemenuGroup = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 100%;
  margin: 0px 20px;
  > button {
    background: none;
    border-style: none;
  }
  @media (min-width: 640px) {
    display: none;
    background-color: red;
  }
`;

const Logogroup = styled.div`
  display: flex;
`;

const MenuGroup = styled.div`
  display: flex;
  margin: 0px 10px;
`;
const SerachGroup = styled.div`
  display: flex;
  position: relative;
  width: 100%;
  .serch-icon {
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
    color: #999;
  }
  @media (max-width: 640px) {
    justify-content: right;
    align-items: center;
    height: 100%;
    .serch-icon {
      position: relative;
      transform: none;
      top: 0;
      left: 0;
    }
  }
`;

const Menu = styled.div`
  font-size: 13px;
  padding: 0px 15px;
  border-radius: 20px;
  height: 30px;
  a {
    color: black;
    text-decoration: none;
  }
  :hover {
    background-color: var(--menu-hover-background);
  }
  :nth-child(3) {
    width: 70px;
  }
  .moblie-none {
    display: none;
  }
`;

const SerachBar = styled.input`
  width: 100%;
  height: 20px;
  border: 1px solid lightgray;
  border-radius: 2px;
  font-size: 13px;
  padding: 5px 30px;
  @media (max-width: 640px) {
    display: none;
  }
`;

export default Header;
