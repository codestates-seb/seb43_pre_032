import styled from 'styled-components';
import logo from '../assets/logo.png';
import MenuIcon from './Menu-Icon';
import { LoginBtn, SignInBtn, OtherButtons } from './NavButton';
import { useState } from 'react';

const HeaderContainer = styled.header`
  width: 100vw;
  height: 3.5rem;

  display: flex;
  align-items: center;
  justify-content: center;
  padding-top: 0.2rem;
  padding-bottom: 0.2rem;

  -webkit-box-shadow: 0px 5px 12px -2px rgba(0, 0, 0, 0.27);
  box-shadow: 0px 5px 12px -2px rgba(0, 0, 0, 0.27);
  border-top: 3px solid #f48225;
  background-color: #f8f9f9;
`;

const ContentsContainer = styled.div`
  margin-top: 3px;
  display: flex;
  flex-direction: row;
  height: 80%;
  & > :hover.menu {
    background-color: var(--menu-hover-background);
  }
`;
// const SubContainer = styled.div`
//   width: 38rem;
//   height: 100%;
//   overflow: hidden;
//   margin: 1.5rem;
// `;
const Menu = styled.div`
  color: black;
  font-size: 1rem;
  padding: 0.3rem;
  border-radius: 1rem;
  margin: 1rem;
`;
const SerachBar = styled.input`
  width: 55rem;
  height: 2.3rem;
  border: 1px solid lightgray;
  border-radius: 0.2rem;
  font-size: 1.1rem;
`;

function Header() {
  let [isLogin, setIsLogin] = useState(false);
  const clickLogin = () => {
    setIsLogin((pre) => !pre);
  };
  return (
    <HeaderContainer className="header-pos">
      <ContentsContainer className="flex-space-between">
        {isLogin ? (
          <>
            <img className="logo menu" src={logo} alt="로고" />
            <Menu className="menu">Products</Menu>
          </>
        ) : (
          <>
            <div className="btn1 flex-center menu">
              <MenuIcon></MenuIcon>
            </div>
            <img className="logo menu" src={logo} alt="로고" />
            <Menu className="menu">About</Menu>
            <Menu className="menu">Products</Menu>
            <Menu className="menu">For Teams</Menu>
          </>
        )}
        <SerachBar placeholder=" 🔍 Search.."></SerachBar>
        {isLogin ? (
          <OtherButtons></OtherButtons>
        ) : (
          <div className="flex-center log-sign">
            <LoginBtn className="flex-center" onClick={clickLogin}>
              Log in
            </LoginBtn>
            <SignInBtn className="flex-center">Sign up</SignInBtn>
          </div>
        )}
      </ContentsContainer>
    </HeaderContainer>
  );
}
export default Header;
