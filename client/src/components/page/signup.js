import { useState } from 'react';
import styled from 'styled-components';
import Header from '../../components/Header/HeaderCom';

const Signup = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const nameHandler = (evnet) => {
    setName(evnet.target.value);
  };
  const emailHandler = (evnet) => {
    setEmail(evnet.target.value);
  };
  const passwordHandler = (evnet) => {
    setPassword(evnet.target.value);
  };

  return (
    <>
      <Header />
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
              <button>
                <span>구글 이미지</span> Log in with Google
              </button>
              <button>
                <span>깃허브 이미지</span>Log in with GitHub
              </button>
              <button>
                <span>페북 이미지</span>Log in with Facebook
              </button>
            </OauthButtonDiv>
            <SignupDiv>
              <form>
                <div>
                  <label htmlFor="display-name">Display name</label>
                  <div>
                    <input
                      type="text"
                      name="display-name"
                      id="display-name"
                      onChange={nameHandler}
                    ></input>
                  </div>
                </div>
                <div>
                  <label htmlFor="email">Email</label>
                  <div>
                    <input
                      type="email"
                      name="email"
                      id="email"
                      onChange={emailHandler}
                    ></input>
                  </div>
                </div>
                <div>
                  <div>
                    <label htmlFor="password">Password</label>
                  </div>
                  <div>
                    <input
                      type="password"
                      name="password"
                      id="password"
                      onChange={passwordHandler}
                    ></input>
                  </div>
                  <p>
                    Passwords must contain at least eight characters, including
                    at least 1 letter and 1 number.
                  </p>
                </div>
                <div>사람인지 로봇인지 파악</div>
                <div>
                  <div>
                    <input type="checkbox" id="opt-in"></input>
                  </div>
                  <div>
                    <label htmlFor="opt-in">
                      Opt-in to receive occasional product updates, user
                      research invitations, company announcements, and digests.
                    </label>
                  </div>
                </div>
                <div>
                  <button name="submit-button">Sign up</button>
                </div>
              </form>
              <div></div>
            </SignupDiv>
          </RightDiv>
        </DivContent>
      </DivContainer>
    </>
  );
};

const DivContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100vh;
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
`;

const RightDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: blue;

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
  }
`;

const SignupDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  background-color: red;

  height: 658px;
`;

export default Signup;
