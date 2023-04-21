import { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
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
  //아이디 비밀번호
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [isLogin, setIsLogin] = useState(false);

  const IdInputRef = useRef(null);
  const PasswordInputRef = useRef(null);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(selectFooter(false));
    dispatch(selectNav(false));
  }, []);

  // const authorizationToken =
  //   'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sIm1lbWJlcklkIjoxLCJ1c2VybmFtZSI6ImhnZEBnbWFpbC5jb20iLCJzdWIiOiJoZ2RAZ21haWwuY29tIiwiaWF0IjoxNjgxOTk5NDM0LCJleHAiOjE2ODIwMDEyMzR9.NLDsKQIVrAjy8UKEBBtklaQnZl82BALpSYHYTwJe1w4';
  // const refreshToken =
  //   'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZ2RAZ21haWwuY29tIiwiaWF0IjoxNjgxOTk5NDM0LCJleHAiOjE2ODIwMjQ2MzR9.oTavrta9wNNHhzOE6O4IKbnl1GPsoaPEZRqfJ6H_Auw';

  const loginAxios = () => {
    axios
      .post('https://0272-61-254-8-200.ngrok-free.app/auth/login', {
        username: id,
        password,
      })
      .then((response) => {
        // console.log(response.headers.get('authorization'));
        if (response.status === 200 || response.status === 201) {
          setIsLogin(false);
          console.log('로그인 성공');
        }
      })
      .catch((err) => {
        console.log(err.name === 'AxiosError');
        if (err.name === 'AxiosError') {
          setIsLogin(true);
        }

        if (err.response.status === 401) {
          setIsLogin(true);
          console.log('아이디 혹은 비밀번호를 잘못 적었습니다.');
        }
      });
  };

  //  Oauth 클라이언트 ID
  const GITHUB_CLIENT_ID = '6fec513b9c45ba90c613';

  // Oauth 함수
  const googleLoginRequestHandler = () => {
    return window.location.assign(
      `https://0272-61-254-8-200.ngrok-free.app/oauth2/authorization/google`
    );
    // console.log('준비완료');
  };
  const githubLoginRequestHandler = () => {
    return window.location.assign(
      `https://github.com/login/oauth/authorize?client_id=${GITHUB_CLIENT_ID}`
    );
    // console.log('준비완료');
  };
  const facebookLoginRequestHandler = () => {
    // return window.location.assign(
    //   `https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}`
    // );
    console.log('준비완료');
  };

  const emailHandler = () => {
    setId(IdInputRef.current.value);
  };

  const passwordHandler = () => {
    setPassword(PasswordInputRef.current.value);
  };

  const loginFormHandler = (event) => {
    event.preventDefault();

    emailHandler();
    passwordHandler();

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
              <DivUserInput>
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
                        ref={IdInputRef}
                      ></input>
                      <div>Invalid username or password.</div>
                    </>
                  ) : (
                    <input
                      id="email"
                      type="email"
                      name="email"
                      ref={IdInputRef}
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
                    ref={PasswordInputRef}
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
