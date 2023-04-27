import { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';

import styled from 'styled-components';
import { selectFooter, selectNav } from '../store/store';
import { useDispatch } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faGithub,
  faFacebook,
  faGoogle,
} from '@fortawesome/free-brands-svg-icons';
import axios from 'axios';

const Login = () => {
  //아이디 비밀번호& 로그인 상태
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [isLogin, setIsLogin] = useState(false);

  //리덕스 툴킷-디스패치 생성
  const dispatch = useDispatch();
  // 유즈 이펙트로 한번만 렌더링될때 Nav와 Footer없애주기
  useEffect(() => {
    dispatch(selectFooter(false));
    dispatch(selectNav(false));
  }, []);
  //유즈네비게이트 생성
  const navigate = useNavigate();

  //로그인 axios.post요청실행하는 함수
  const loginAxios = () => {
    axios.defaults.withCredentials = true;
    axios
      .post(
        'http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/auth/login',
        {
          id,
          password,
        },
        {
          headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': 'http://localhost:3000',
            'Access-Control-Allow-Headers':
              'Origin, X-Requested-With, Content-Type, Accept',
            'Access-Control-Expose-Headers': 'Authorization',
          },
        }
      )
      .then((response) => {
        // 백엔드가 헤더에 담아서 보내준 토큰을 가져옴
        const token = response.headers.authorization;
        // 로컬에 토큰을 저장하는 함수 매개변수로 토큰을 받음
        const saveToken = (token) => {
          localStorage.setItem('token', token);
        };

        // 멤버id(유저id)도 헤더에 담긴 값을 가져옴
        const memberid = response.headers.memberid;
        // 로컬에 저장하는 함수 매개변수로 id를 받음
        const saveMemberId = (memberid) => {
          localStorage.setItem('memberid', memberid);
        };

        //응답 성공코드가 올때 실행
        if (response.status === 200 || response.status === 201) {
          /*  로그인 성공시
          => 로그인 상태 변경, 토큰과 아이디 로컬에 저장, 디스패치로 Nav와 Footer보이게 true설정, 네비게이트로 페이지 이동 */
          setIsLogin(false);
          saveToken(token);
          saveMemberId(memberid);
          dispatch(selectFooter(true));
          dispatch(selectNav(true));
          navigate('/question');
        }
      })
      .catch((err) => {
        /*응답이 안될때 로그인 상태 변경 / 콘솔 오류코드*/
        console.log(err);
        if (err.name === 'AxiosError') {
          setIsLogin(true);
        }

        if (err.response.status === 401) {
          setIsLogin(true);
        }
      });
  };
  // Oauth 미구현
  const googleLoginRequestHandler = () => {
    //오어스 인증링크로 이동
    window.location.assign(
      `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google`
    );
  };

  const githubLoginRequestHandler = () => {};
  const facebookLoginRequestHandler = () => {};

  //이메일 이벤트 onChange
  const emailHandler = (event) => {
    setId(event.target.value);
  };
  //비밀번호 이벤트 onChange
  const passwordHandler = (event) => {
    setPassword(event.target.value);
  };
  //form 이벤트 onSubmit
  const loginFormHandler = (event) => {
    event.preventDefault();

    //axios 함수 실행
    loginAxios();
  };

  return (
    <DivContainer>
      <DivContent>
        <DivItem>
          <LogoDiv>
            <img
              alt="stackoverflow-logo"
              src="https://cdn.cdnlogo.com/logos/s/63/stack-overflow.svg"
            />
          </LogoDiv>
          <OauthButtonDiv>
            <button className="google" onClick={googleLoginRequestHandler}>
              <span>
                <FontAwesomeIcon icon={faGoogle} size="xl" />
              </span>
              Log in with Google
            </button>
            <button className="github" onClick={githubLoginRequestHandler}>
              <span>
                <FontAwesomeIcon icon={faGithub} size="xl" />
              </span>
              Log in with GitHub
            </button>
            <button className="facebook" onClick={facebookLoginRequestHandler}>
              <span>
                <FontAwesomeIcon icon={faFacebook} size="xl" />
              </span>
              Log in with Facebook
            </button>
          </OauthButtonDiv>
          <DivFormContainer>
            <FormContent onSubmit={loginFormHandler}>
              <DivUserInput isLogin={isLogin}>
                <label htmlFor="email" className="email">
                  Email
                </label>
                <div>
                  {isLogin ? (
                    <>
                      <input
                        id="email"
                        type="email"
                        name="email"
                        onChange={emailHandler}
                      ></input>
                      <div>Invalid username or password.</div>
                    </>
                  ) : (
                    <input
                      id="email"
                      type="email"
                      name="email"
                      onChange={emailHandler}
                    ></input>
                  )}
                </div>
              </DivUserInput>
              <DivUserInput>
                <div className="password-div">
                  <label htmlFor="password" className="password">
                    Password
                  </label>
                  <span>Forgot password?</span>
                </div>
                <div>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    autoComplete="off"
                    onChange={passwordHandler}
                  ></input>
                </div>
              </DivUserInput>
              <DivButton>
                <button>Log in</button>
              </DivButton>
            </FormContent>
          </DivFormContainer>
          <div>
            Don’t have an account?
            <Link to={'/members'} className="link-signup">
              Sign up
            </Link>
          </div>
        </DivItem>
      </DivContent>
    </DivContainer>
  );
};

// 스타일드 컴포넌트
const DivContainer = styled.div`
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;

  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  width: 100%;
  height: 100vh;

  background-color: #f1f2f3;
`;

const DivContent = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  width: 100%;
  height: 100%;
  padding: 24px;
  max-width: 1264px;
`;

const DivItem = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  width: 290px;
  height: 570px;

  > div:last-child {
    padding: 16px;

    font-size: 13px;

    .link-signup {
      color: #0074cc;
      :hover {
        color: #0995ff;
      }
    }
  }
`;

const OauthButtonDiv = styled.div`
  display: flex;
  flex-direction: column;

  width: 290px;
  height: 137px;
  margin-bottom: 20px;

  span {
    margin: 5px;
  }

  > button {
    margin: 4px 0px 4px 0px;
    border: 1px;
    padding: 10px;

    font-size: 13px;
    line-height: 15px;
    cursor: pointer;
  }

  .google {
    color: #232629;
    background-color: #ffffff;
    border-radius: 5px;
    box-shadow: none;

    :hover {
      background-color: #f8f9f9;
    }
  }
  .github {
    color: #ffffff;
    background-color: #2f3337;
    border-radius: 5px;
    box-shadow: none;

    :hover {
      background-color: #232629;
    }
  }
  .facebook {
    color: #ffffff;
    background-color: #385499;
    border-radius: 5px;
    box-shadow: none;

    :hover {
      background-color: #314a89;
    }
  }
`;

const LogoDiv = styled.div`
  > img {
    width: 32px;
    height: 37px;
    margin-bottom: 24px;
  }
`;

const DivFormContainer = styled.div`
  background-color: white;

  padding: 24px;
  width: 240px;
  height: 186px;
`;

const FormContent = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  width: 240px;
  height: 188px;
`;

const DivUserInput = styled.div`
  width: 240px;
  height: 60px;

  > div {
    width: 240px;
    height: 30px;

    > div {
      color: red;
      font-size: 12px;
    }

    > input {
      width: 220px;
      height: 14px;

      padding: 7px 9px;
    }

    outline: none !important;
    > input {
      border-color: ${(props) => (props.isLogin ? '#f87171' : '')};
      box-shadow: ${(props) => (props.isLogin ? '0 0 0 2px #fca5a5' : '')};
    }

    > input:focus {
      /* #DDEAF7 */
      /* border-color: #58a4de;
      outline: none; */
      outline: none !important;
      border-color: #58a4de;
      box-shadow: 0 0 0 2px #ddeaf7;
    }
  }

  .password-div {
    display: flex;
    flex-direction: row;
    align-items: baseline;

    height: 18px;
  }

  .password-div > span {
    flex-grow: 1;
    font-size: 12px;
    line-height: 15px;

    text-align: right;
    color: #0074cc;
  }
`;

const DivButton = styled.div`
  > button {
    padding: 10px;
    width: 240px;
    height: 37px;

    font-size: 13px;
    line-height: 15px;

    border-radius: 3px;
    border: 2px 0px 2px 0px;
    border-style: none;

    color: white;
    background-color: #0a95ff;
    box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;

    cursor: pointer;

    :hover {
      background-color: #0074cc;
    }
  }
`;

export default Login;
