import styled from 'styled-components';
import logo from '../../assets/logo.png';
import { LoginBtn, SignInBtn, OtherButtons } from './HeaderButton';
import { useState } from 'react';

function Header() {
  let [isLogin, setIsLogin] = useState(false);
  const clickLogin = () => {
    setIsLogin((pre) => !pre);
  };
  return (
    <HeaderContainer>
      <ContentsContainer className="flex-space-between">
        {isLogin ? (
          <>
            <img className="logo" src={logo} alt="로고" />
            <Menu className="hover">Products</Menu>
          </>
        ) : (
          <>
            <img className="logo" src={logo} alt="로고" />
            <Menu className="hover moblie-none flex-center">About</Menu>
            <Menu className="hover flex-center">Products</Menu>
            <Menu className="hover moblie-none flex-center">For Teams</Menu>
          </>
        )}
        <SerachBar placeholder=" 🔍 Search..."></SerachBar>
        {isLogin ? (
          <OtherButtons />
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
const HeaderContainer = styled.header`
  width: 100vw;
  height: 40px;

  display: flex;
  align-items: center;
  justify-content: center;
  padding-top: 3px;
  padding-bottom: 3px;

  -webkit-box-shadow: 0px 5px 12px -2px rgba(0, 0, 0, 0.27);
  box-shadow: 0px 5px 12px -2px rgba(0, 0, 0, 0.27);
  border-top: 3px solid #f48225;
  background-color: #f8f9f9;
`;

const ContentsContainer = styled.div`
  width: 1250px;
  margin-top: 3px;
  display: flex;
  flex-direction: row;
  height: 80%;
  & > :hover.hover {
    background-color: var(--menu-hover-background);
  }
`;
const Menu = styled.div`
  color: black;
  font-size: 13px;
  padding: 5px;
  border-radius: 13px;
  width: 70px;
`;
const SerachBar = styled.input`
  width: 680px;
  height: 25px;
  border: 1px solid lightgray;
  border-radius: 2px;
  font-size: 13px;
  @media (max-width: 1250px) {
    width: 45%;
  }
`;
export default Header;
