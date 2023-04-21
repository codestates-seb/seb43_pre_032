import styled from 'styled-components';
import stackoverflowlogo from '../assets/logo-stackoverflow-icon.png';
import askubuntulogo from '../assets/askubuntu-logo.png';
import mathoverflowlogo from '../assets/mathoverflowlogo.png';
import stackappslogo from '../assets/stackapps.png';
import stackexchangelogo from '../assets/stackexchangelogo.png';
import superuserlogo from '../assets/superuserlogo.png';
import severfaultlogo from '../assets/serverfaultlogo.png';
function Logout() {
  const flowingDomainsData = [
    { logo: askubuntulogo, domain: 'askubuntu.com' },
    { logo: mathoverflowlogo, domain: 'mathoverflow.net' },
    { logo: severfaultlogo, domain: 'serverfault.com' },
    { logo: stackappslogo, domain: 'stackapps.com' },
    { logo: stackexchangelogo, domain: 'stackexchange.com' },
    { logo: stackoverflowlogo, domain: 'stackoverflow.com' },
    { logo: superuserlogo, domain: 'superuser.com' },
  ];
  return (
    <>
      <LogoutView>
        <LogoutContaioner>
          <div>
            <h2>
              Clicking “Log out“ will log you out of following domains on this
              device
            </h2>
          </div>
          <LogoutBox>
            <div className="logout-domains">
              <ul>
                {flowingDomainsData.map((domain, idx) => {
                  return (
                    <li key={idx}>
                      <a href={'https://' + domain.domain}>
                        <div className="domain-icon">
                          <img src={domain.logo} alt={domain.domain} />
                        </div>
                        <div className="domain-link">{domain.domain}</div>
                      </a>
                    </li>
                  );
                })}
              </ul>
            </div>
            <div className="logout-checkbox">
              <label>
                <input type="checkbox" /> Log out on all devices
              </label>
            </div>
            <div className="logout-btns">
              <button className="logout-btn">Log out</button>
              <button className="cancel-btn">Cancel</button>
            </div>
            <div className="logout-description">
              <p>
                If you’re on a shared computer, remember to log out of your Open
                ID provider (Facebook, Google, Stack Exchange, etc.) as well.
              </p>
            </div>
          </LogoutBox>
        </LogoutContaioner>
      </LogoutView>
    </>
  );
}

const LogoutView = styled.div`
  width: 100%;
  height: 100vh;
  background-color: #f1f2f3;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const LogoutContaioner = styled.div`
  width: 525px;
  height: 525px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  h2 {
    text-align: center;
    margin-bottom: 24px;
    font-weight: 400;
  }
`;
const LogoutBox = styled.div`
  width: 316px;
  height: 422px;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
  padding: 24px;
  display: flex;
  flex-direction: column;
  label {
    height: 18px;
    font-size: 12px;
  }

  .logout-domains {
    padding-bottom: 15px;
    margin-bottom: 15px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  }
  a {
    display: flex;
    text-decoration: none;
    color: #0074cc;
    font-size: 15px;
    :hover {
      color: #0a95ff;
    }

    .domain-icon {
      height: 27px;
      width: 27px;
      img {
        height: 20px;
        width: 20px;
      }
    }
  }
  .logout-checkbox {
    display: flex;
    align-items: center;
    margin-bottom: 16px;
  }
  button {
    padding: 10px;
    border: none;
    cursor: pointer;
    margin: 2px;
    border-radius: 4px;
  }
  .logout-btn {
    background-color: #0a95ff;
    color: white;
    :hover {
      background-color: #0074cc;
    }
  }
  .cancel-btn {
    color: #0a95ff;
    background-color: white;
    :hover {
      background-color: #f0f8ff;
    }
  }
  .logout-description {
    font-size: 12px;
    margin: 32px 0px;
    color: rgba(0, 0, 0, 0.6);
  }
`;

export default Logout;
