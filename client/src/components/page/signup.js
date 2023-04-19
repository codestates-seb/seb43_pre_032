import { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';
import Header from '../Header/HeaderCom';

const Signup = () => {
  //이름, 이메일, 비밀번호 입력 값
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  //유효성 검사 상태
  const [isName, setIsName] = useState(false);
  const [isEmail, setIsEmail] = useState(false);
  const [isPassword, setIsPassword] = useState(false);
  const [isSignup, setIsSignup] = useState(false);
  const [isCheck, setIsCheck] = useState(false);

  // const navigate = useNavigate();

  const signupAxios = () => {
    axios
      .post('https://2e9b-61-254-8-200.ngrok-free.app/members', {
        email,
        password,
        name,
      })
      .then((res) => {
        if (res.status === 201 || res.status === 200) {
          console.log('완료');
          // navigate('/');
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const nameHandler = (evnet) => {
    setName(evnet.target.value);

    // 유효성 검사 (2글자 이상 5글자 미만으로 입력)
    if (evnet.target.value.length < 2 || evnet.target.value.length > 5) {
      setIsName(true);
    } else {
      setIsName(false);
    }
  };

  const emailHandler = (evnet) => {
    setEmail(evnet.target.value);

    //이메일 유효성검사 정규식
    const emailRegex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    //이메일 유효성 검사
    if (!emailRegex.test(evnet.target.value)) {
      setIsEmail(true);
    } else {
      setIsEmail(false);
    }
  };

  const passwordHandler = (evnet) => {
    setPassword(evnet.target.value);

    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    if (!passwordRegex.test(evnet.target.value)) {
      setIsPassword(true);
    } else {
      setIsPassword(false);
    }
  };

  const formSubmitHandler = (evnet) => {
    evnet.preventDefault();

    //유효성 검사 통과하면 axios요청 보내기
    if (isName === false && isEmail === false && isPassword === false) {
      signupAxios();
      setIsSignup(false);
    } else {
      setIsSignup(true);
      console.log('다시 검사해봐');
    }

    //지우기 css끝나면
    isSignup;
  };

  const checkboxHandler = () => {
    setIsCheck(!isCheck);
  };

  return (
    <div>
      <div>
        <Header />
      </div>
      <div>
        <DivContainer>
          <DivContent>
            <LeftDiv>
              <h1>Join the Stack Overflow community</h1>
              <div>
                <div></div>
                <div>Get unstuck — ask a question</div>
              </div>
              <div>
                <div></div>
                <div>Unlock new privileges like voting and commenting</div>
              </div>
              <div>
                <div></div>
                <div>
                  Save your favorite questions, answers, watch tags, and more
                </div>
              </div>
              <div>
                <div></div>
                <div>Earn reputation and badges</div>
              </div>
              <div>
                <div></div>
                <div>
                  Collaborate and share knowledge with a private group for FREE.
                  <br />
                  Get Stack Overflow for Teams free for up to 50 users
                </div>
              </div>
            </LeftDiv>
            <RightDiv>
              <OauthButtonDiv>
                <button className="google">
                  <span>구글 이미지</span> Log in with Google
                </button>
                <button className="github">
                  <span>깃허브 이미지</span>Log in with GitHub
                </button>
                <button className="facebook">
                  <span>페북 이미지</span>Log in with Facebook
                </button>
              </OauthButtonDiv>
              <SignupDiv>
                <FormContainer onSubmit={formSubmitHandler}>
                  <div>
                    <label htmlFor="display-name">Display name</label>
                    <div>
                      {isName ? (
                        <>
                          <input
                            type="text"
                            name="display-name"
                            id="display-name"
                            onChange={nameHandler}
                          ></input>{' '}
                          <p>Please re-enter your name</p>
                        </>
                      ) : (
                        <input
                          type="text"
                          name="display-name"
                          id="display-name"
                          onChange={nameHandler}
                        ></input>
                      )}
                    </div>
                  </div>
                  <div>
                    <label htmlFor="email">Email</label>
                    <div>
                      {isEmail ? (
                        <>
                          <input
                            type="email"
                            name="email"
                            id="email"
                            onChange={emailHandler}
                          ></input>
                          <p>{email} is not a valid email address.</p>
                        </>
                      ) : (
                        <input
                          type="email"
                          name="email"
                          id="email"
                          onChange={emailHandler}
                        ></input>
                      )}
                    </div>
                  </div>
                  <div>
                    <div>
                      <label htmlFor="password">Password</label>
                    </div>
                    <div>
                      {isPassword ? (
                        <>
                          <input
                            type="password"
                            name="password"
                            id="password"
                            onChange={passwordHandler}
                          ></input>
                          <p>Combine numbers and letters</p>
                        </>
                      ) : (
                        <input
                          type="password"
                          name="password"
                          id="password"
                          onChange={passwordHandler}
                        ></input>
                      )}
                    </div>
                    <p>
                      Passwords must contain at least eight characters,
                      including at least 1 letter and 1 number.
                    </p>
                  </div>
                  <div>
                    <DivCheckOne>
                      <div>
                        <div>
                          <input
                            type="checkbox"
                            id="not-robot"
                            onClick={checkboxHandler}
                          ></input>
                          <label htmlFor="not-robot">{`I'm not a robot`}</label>
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
                    {isCheck ? (
                      <button name="submit-button">Sign up</button>
                    ) : (
                      <div>
                        <button name="submit-button" disabled>
                          Sign up
                        </button>
                        <div>robot check please</div>
                      </div>
                    )}
                  </div>
                </FormContainer>
                <div></div>
              </SignupDiv>
            </RightDiv>
          </DivContent>
        </DivContainer>
      </div>
    </div>
  );
};

const DivContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100%;

  background-color: #f1f2f3;
`;

const DivContent = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  max-width: 1264px;
  height: 100vh;
  width: 100%;
`;

const LeftDiv = styled.div`
  background-color: yellow;

  width: 500px;
  height: 400px;

  margin: 0px 48px 128px 0px;

  > h1 {
    font-size: 27px;
    font-weight: 500;
    line-height: 27px;
    text-align: left;
    letter-spacing: normal;

    color: #232629;
  }

  div {
    font-size: 15px;
    line-height: 19px;

    margin: 0px 0px 24px 0px;
  }
`;

const RightDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #f1f2f3;

  width: 316px;
  height: 933px;
`;

const OauthButtonDiv = styled.div`
  display: flex;
  flex-direction: column;

  width: 316px;
  height: 137px;

  > button {
    margin: 4px 0px 4px 0px;
    border: 1px;
    padding: 10px;

    font-size: 13px;
    line-height: 15px;
  }

  .google {
    color: #232629;
    background-color: #f8f9f9;
    border-radius: 5px;
    box-shadow: none;
  }
  .github {
    color: #ffffff;
    background-color: #2f3337;
    border-radius: 5px;
    box-shadow: none;
  }
  .facebook {
    color: #ffffff;
    background-color: #385499;
    border-radius: 5px;
    box-shadow: none;
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
    width: 268px;
    height: 32px;
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

    :hover {
      background-color: #0074cc;
    }
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
