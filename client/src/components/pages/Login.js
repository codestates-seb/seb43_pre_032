// import { useState } from 'react';

const Login = () => {
  // const [id, setId] = useState('');
  // const [password, setPassword] = useState('');

  const emailHandler = (evnet) => {
    console.log(evnet.target.value);
  };

  const passwordHandler = (evnet) => {
    console.log(evnet.target.value);
  };

  return (
    <div>
      <div>
        <div>
          <div>
            <p>
              <span>이미지</span>
            </p>
          </div>
          <div>
            <button>
              <span>구글 이미지</span> Log in with Google
            </button>
            <button>
              <span>깃허브 이미지</span>Log in with GitHub
            </button>
            <button>
              <span>페북 이미지</span>Log in with Facebook
            </button>
          </div>
          <div>
            <form>
              <div>
                <label htmlFor="email">Email</label>
                <div>
                  <input
                    id="email"
                    type="email"
                    name="email"
                    onChange={emailHandler}
                  ></input>
                </div>
              </div>
              <div>
                <div>
                  <label htmlFor="password">Password</label>
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
              </div>
              <div>
                <button>Log in</button>
              </div>
            </form>
          </div>
          <div></div>
        </div>
      </div>
    </div>
  );
};

export default Login;
