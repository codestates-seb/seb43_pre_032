import { useState, useRef, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { selectFooter, selectNav } from '../store/store';
import axios from 'axios';
import styled from 'styled-components';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faGithub,
  faFacebook,
  faGoogle,
} from '@fortawesome/free-brands-svg-icons';
import {
  faCircleQuestion,
  faSort,
  faTags,
  faTrophy,
} from '@fortawesome/free-solid-svg-icons';

const Signup = () => {
  //이름, 이메일, 비밀번호 입력 값 상태
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  //유효성 검사 상태
  const [isName, setIsName] = useState(false);
  const [isEmail, setIsEmail] = useState(false);
  const [isPassword, setIsPassword] = useState(false);
  const [isCheck, setIsCheck] = useState(false);

  /*
  useRef를 사용하여 값을 가져옴
  => 이유: onChange로 가져오면 상태가 계속 바뀌어 회원가입 클릭시 유효성 검사가 실시 되는 로직이 안이루어져서 useRef로 값을 받아옴
  */
  const NameInputRef = useRef(null);
  const EamilInputRef = useRef(null);
  const PasswordInputRef = useRef(null);

  //네비게이트 생성
  const navigate = useNavigate();
  //디스패치 생성
  const dispatch = useDispatch();

  //유즈 이펙트로 처음 한번 렌더링될때 리덕스 툴킷을통해 디스패치로 Nav와 Footer을 지움
  useEffect(() => {
    dispatch(selectFooter(false));
    dispatch(selectNav(false));
  }, []);

  /* 유효성 검사후 중속성 배열을 통하여 값이 변할때 조건문을 통과하면 함수가 실행될 수 있게 함 
  => 이유: 처음 함수에(formSubmitHandler)서 유효성 검사후 axios요청을 보내는 로직이였는데, useRef이다 보니 실시간으로
  변경이 이루어지지 않아 두번클릭을 해야 정상적인 실행이 되어서 유즈 이펙트로 상태를 추적하여 요청을 보냄 */
  useEffect(() => {
    const isVaild = !isName && !isEmail && !isPassword && isCheck;
    if (isVaild) signupAxios();
  }, [email, password, name]);

  //회원가입 axios.post요청하는 함수
  const signupAxios = () => {
    axios.defaults.withCredentials = true;
    axios
      .post(
        'http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members',
        {
          email,
          password,
          name,
        },
        {
          headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': 'http://localhost:3000',
            'Access-Control-Allow-Headers':
              'Origin, X-Requested-With, Content-Type, Accept',
          },
        }
      )
      .then((res) => {
        // 응답 코드를 통해 네비게이트로 페이지 전환
        if (res.status === 201 || res.status === 200) {
          alert('Register now complete.');
          navigate('/auth/login');
        }
      })
      .catch((err) => {
        //이메일 중복 유효성
        if (err.response.status === 409) {
          alert('Email address is already in use.');
          setIsEmail(true);
        }
        console.log(err);
      });
  };

  //이름
  const nameHandler = () => {
    let nameInputText = NameInputRef.current.value;
    setName(nameInputText);

    // 유효성 검사 (2글자 이상 5글자 미만으로 입력)
    if (nameInputText.length < 2 || nameInputText.length > 5) {
      setIsName(true);
    } else {
      setIsName(false);
    }
  };

  //이름(name) - useRef값 받아와 상태 업데이트와 유효성 검사하는 함수
  const emailHandler = () => {
    let emailInputText = EamilInputRef.current.value;
    setEmail(emailInputText);

    //이메일 유효성검사 정규식
    const emailRegex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    //이메일 유효성 검사
    if (!emailRegex.test(emailInputText)) {
      setIsEmail(true);
    } else {
      setIsEmail(false);
    }
  };
  //비밀번호(password) - useRef값 받아와 상태 업데이트와 유효성 검사하는 함수
  const passwordHandler = () => {
    let passwordInputText = PasswordInputRef.current.value;
    setPassword(passwordInputText);

    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    if (!passwordRegex.test(passwordInputText)) {
      setIsPassword(true);
    } else {
      setIsPassword(false);
    }
  };
  //체크박스(checkbox) - 체크했는지 상태업데이트를 통해 확인하는 함수
  const checkboxHandler = () => {
    setIsCheck((prev) => !prev);
  };

  //form-onSubmit이벤트로 유효성 검사와 useRef로 받아온 값을 업데이트하여 => useEffet(55번줄)함수가 실행되어 axios요청이 가게 됨
  const formSubmitHandler = (event) => {
    event.preventDefault();

    //유효성 검사하는 함수들
    nameHandler();
    emailHandler();
    passwordHandler();
  };

  // Oauth 함수 미구현 (구글,깃헙,페북)
  const googleLoginRequestHandler = () => {
    return window.location.assign(
      `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google`
    );
  };
  const githubLoginRequestHandler = () => {};
  const facebookLoginRequestHandler = () => {};

  return (
    <>
      <DivContainer>
        <DivContent>
          <LeftDiv>
            <h1>Join the Stack Overflow community</h1>
            <div>
              <IconDiv>
                <FontAwesomeIcon
                  icon={faCircleQuestion}
                  style={{ color: '#0a95ff' }}
                  size="xl"
                />
              </IconDiv>
              <div>Get unstuck — ask a question</div>
            </div>
            <div>
              <IconDiv>
                <FontAwesomeIcon
                  icon={faSort}
                  style={{ color: '#0a95ff' }}
                  size="xl"
                />
              </IconDiv>
              <div>Unlock new privileges like voting and commenting</div>
            </div>
            <div>
              <IconDiv>
                <FontAwesomeIcon
                  icon={faTrophy}
                  style={{ color: '#0a95ff' }}
                  size="xl"
                />
              </IconDiv>
              <div>
                Save your favorite questions, answers, watch tags, and more
              </div>
            </div>
            <div>
              <IconDiv>
                <FontAwesomeIcon
                  icon={faTags}
                  flip="horizontal"
                  style={{ color: '#0a95ff' }}
                  size="xl"
                />
              </IconDiv>
              <div>Earn reputation and badges</div>
            </div>
            <div>
              <div>
                Collaborate and share knowledge with a private group for FREE.
              </div>
              <div>Get Stack Overflow for Teams free for up to 50 users</div>
            </div>
          </LeftDiv>
          <RightDiv>
            <div>
              Create your Stack Overflow account. It’s free and only takes a
              minute.
            </div>
            <OauthButtonDiv>
              <button className="google" onClick={googleLoginRequestHandler}>
                <span>
                  <FontAwesomeIcon icon={faGoogle} size="xl" />
                </span>
                Sign up with Google
              </button>
              <button className="github" onClick={githubLoginRequestHandler}>
                <span>
                  <FontAwesomeIcon icon={faGithub} size="xl" />
                </span>
                Sign up with GitHub
              </button>
              <button
                className="facebook"
                onClick={facebookLoginRequestHandler}
              >
                <span>
                  <FontAwesomeIcon icon={faFacebook} size="xl" />
                </span>
                Sign up with Facebook
              </button>
            </OauthButtonDiv>
            <SignupDiv>
              <FormContainer
                onSubmit={formSubmitHandler}
                isName={isName}
                isEmail={isEmail}
                isPassword={isPassword}
              >
                {/* name */}
                <div>
                  <label htmlFor="display-name">Display name</label>
                  <div className="isName">
                    {isName ? (
                      <>
                        <input
                          type="text"
                          name="display-name"
                          id="display-name"
                          ref={NameInputRef}
                        ></input>
                        <p>Please re-enter your name</p>
                      </>
                    ) : (
                      <input
                        type="text"
                        name="display-name"
                        id="display-name"
                        ref={NameInputRef}
                      ></input>
                    )}
                  </div>
                </div>
                {/* email */}
                <div>
                  <label htmlFor="email">Email</label>
                  <div className="isEmail">
                    {isEmail ? (
                      <>
                        <input
                          type="email"
                          name="email"
                          id="email"
                          ref={EamilInputRef}
                        ></input>
                        <p>{email} is not a valid email address.</p>
                      </>
                    ) : (
                      <input
                        type="email"
                        name="email"
                        id="email"
                        ref={EamilInputRef}
                      ></input>
                    )}
                  </div>
                </div>
                {/* password */}
                <div>
                  <div>
                    <label htmlFor="password">Password</label>
                  </div>
                  <div className="isPassword">
                    {isPassword ? (
                      <>
                        <input
                          type="password"
                          name="password"
                          id="password"
                          ref={PasswordInputRef}
                        ></input>
                        <p>Combine numbers and letters</p>
                      </>
                    ) : (
                      <input
                        type="password"
                        name="password"
                        id="password"
                        ref={PasswordInputRef}
                      ></input>
                    )}
                  </div>
                  <p>
                    Passwords must contain at least eight characters, including
                    at least 1 letter and 1 number.
                  </p>
                </div>
                <div>
                  <DivCheckOne>
                    <div>
                      <div>
                        <input
                          type="checkbox"
                          id="not-robot"
                          checked={isCheck}
                          onChange={checkboxHandler}
                          // ref={CheckInputRef}
                        ></input>
                        {/* 체크가 되어있다면 true 안되어있다면 false */}
                        {isCheck ? (
                          <div className="checkBox">
                            <label htmlFor="not-robot">{`I'm not a robot`}</label>
                          </div>
                        ) : (
                          <div className="checkBox">
                            <label htmlFor="not-robot">{`I'm not a robot`}</label>
                            <div>robot check please</div>
                          </div>
                        )}
                      </div>
                    </div>
                  </DivCheckOne>
                  <DivCheckTwo>
                    <div>
                      <input type="checkbox" id="opt-in"></input>
                    </div>
                    <div>
                      <label htmlFor="opt-in">
                        Opt-in to receive occasional product updates, user
                        research invitations, company announcements, and
                        digests.
                      </label>
                    </div>
                  </DivCheckTwo>
                </div>
                <div>
                  <button name="submit-button">Sign up</button>
                </div>
              </FormContainer>
            </SignupDiv>
            <div>
              Already have an account?
              <Link to={'/auth/login'} className="link-login">
                Log in
              </Link>
            </div>
          </RightDiv>
        </DivContent>
      </DivContainer>
    </>
  );
};

//스타일드 컴포넌트
const DivContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  margin: 0px;
  padding: 0px;

  /* height: 100vh; */
  /* width: 100vw; */
  background-color: #f1f2f3;

  @media (min-height: 985px) {
    width: 100vw;
    height: 100vh;
  }
`;

const DivContent = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  max-width: 1264px;
  width: 100%;
`;

const LeftDiv = styled.div`
  display: flex;
  flex-direction: column;

  width: 500px;
  height: 400px;

  margin: 0px 48px 128px 0px;

  @media (max-width: 816px) {
    background-color: red;
    display: none;
  }

  > h1 {
    font-size: 27px;
    font-weight: 500;
    line-height: 27px;
    text-align: left;
    letter-spacing: normal;

    margin-bottom: 32px;

    color: #232629;
  }

  > div:not(:last-child) {
    display: flex;

    font-size: 15px;
    line-height: 19px;

    margin: 0px 0px 24px 0px;
  }

  > div:last-child {
    font-size: 13px;
    color: #6a737c;
    > div:last-child {
      color: #0074cc;
      cursor: pointer;

      :hover {
        color: #0a95ff;
      }
    }
  }
`;

const IconDiv = styled.div`
  margin-right: 10px;
  width: 30px;
  text-align: center;
`;

const RightDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #f1f2f3;

  width: 316px;
  height: 933px;

  @media (min-width: 817px) {
    > div:first-child {
      display: none;
    }
  }
  @media (max-width: 816px) {
    > div:first-child {
      margin-bottom: 24px;
      font-size: 21px;
      font-weight: 500;
      line-height: 27px;
      text-align: center;
      width: 421px;
      color: #232629;
    }
  }

  > div:last-child {
    margin-top: 50px;

    .link-login {
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

  width: 316px;
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

const SignupDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  background-color: #ffffff;

  border-radius: 7px;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 10px 24px 0px;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 20px 48px 0px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 4px 0px;

  height: 658px;

  font-size: 13px;
  line-height: 17px;
  text-align: left;
  letter-spacing: normal;
`;

const FormContainer = styled.form`
  height: 559px;
  width: 268px;

  padding: 24px;
  margin-bottom: 24px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;

  /* password div */
  > div:nth-child(3) > p {
    font-size: 12px;
    line-height: 15px;

    /* color: #6a737c; */

    /* 프롭스 주는법 */
    /* color: ${(props) => (props.isPassword ? 'red' : '#6a737c')}; */
  }

  button {
    padding: 10px;
    width: 268px;
    height: 37px;

    font-size: 13px;
    line-height: 15px;
    letter-spacing: normal;

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

  .isName {
    color: red;

    > input {
      padding-left: 5px;
      padding-right: 5px;

      border-color: ${(props) => (props.isName ? '#f87171' : '')};
      box-shadow: ${(props) => (props.isName ? '0 0 0 2px #fca5a5' : '')};

      :focus {
        /* #DDEAF7 */
        /* border-color: #58a4de;
      outline: none; */
        outline: none !important;
        border-color: #58a4de;
        box-shadow: 0 0 0 2px #ddeaf7;
      }
    }
  }
  .isEmail {
    color: red;

    > input {
      padding-left: 5px;
      padding-right: 5px;

      border-color: ${(props) => (props.isName ? '#f87171' : '')};
      box-shadow: ${(props) => (props.isName ? '0 0 0 2px #fca5a5' : '')};

      :focus {
        outline: none !important;
        border-color: #58a4de;
        box-shadow: 0 0 0 2px #ddeaf7;
      }
    }
  }
  .isPassword {
    color: red;

    > input {
      padding-left: 5px;
      padding-right: 5px;

      border-color: ${(props) => (props.isName ? '#f87171' : '')};
      box-shadow: ${(props) => (props.isName ? '0 0 0 2px #fca5a5' : '')};

      :focus {
        outline: none !important;
        border-color: #58a4de;
        box-shadow: 0 0 0 2px #ddeaf7;
      }
    }
  }
  .checkBox > div {
    color: red;
  }

  > div {
    margin: 6px 0px 6px 0px;
  }

  > div:not(:nth-child(4)) label {
    font-size: 15px;
    font-weight: bold;
    line-height: 19px;

    color: #0c0d0e;
  }

  > div:not(:nth-child(4)) input {
    width: 250px;
    height: 32px;
  }
`;

const DivCheckOne = styled.div`
  > div {
    display: flex;
    justify-content: center;
    align-items: center;

    width: 268px;
    height: 156px;
    padding: 8px 0px 2px 0px;
    margin: 6px 0px 6px 0px;
    border-radius: 3px;

    background-color: #f1f2f3;

    > div {
      display: flex;
      justify-content: center;
      align-items: center;

      width: 170px;
      height: 140px;

      background-color: #ffffff;
      box-shadow: rgba(0, 0, 0, 0.05) 0px 10px 24px 0px;
      box-shadow: rgba(0, 0, 0, 0.05) 0px 20px 48px 0px;
      box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 4px 0px;
    }

    input {
      width: 30px;
      height: 30px;
      margin: 10px;
    }

    label {
      font-size: 15px;
      margin: 10px;
    }
  }
`;

const DivCheckTwo = styled.div`
  display: flex;
`;

export default Signup;
