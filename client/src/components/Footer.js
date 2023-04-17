import styled from 'styled-components';
import logo from '../logo-stackoverflow-icon.png';

function Footer() {
  const stackoverflowMenu = [
    ['https://stackoverflow.com/questions', 'Questions'],
    ['https://stackoverflow.com/help', 'Help'],
  ];
  const productsMenu = [
    ['https://stackoverflow.co/teams/', 'Teams'],
    ['https://stackoverflow.co/advertising/', 'Advertising'],
    ['https://stackoverflow.co/collectives/', 'Collectives'],
    ['https://stackoverflow.co/talent/', 'Talent'],
  ];
  const companyMenu = [
    ['https://stackoverflow.co/', 'About'],
    ['https://stackoverflow.co/company/press', 'Press'],
    ['https://stackoverflow.co/company/work-here', 'Work Here'],
    ['https://stackoverflow.com/legal/terms-of-service', 'Legal'],
    ['https://stackoverflow.com/legal/privacy-policy', 'Privacy Policy'],
    ['https://stackoverflow.com/legal/terms-of-service', 'Terms of Service'],
    ['https://stackoverflow.co/company/contact', 'Contact Us'],
    ['https://stackoverflow.com/legal/cookie-policy', 'Cookie Settings'],
    ['https://stackoverflow.com/legal/cookie-policy', 'Cookie Policy'],
  ];
  const stackexchangenetworkMenu = [
    ['https://stackexchange.com/sites#technology', 'Technology'],
    [
      'https://stackexchange.com/sites#culturerecreation',
      'Culture & recreation',
    ],
    ['https://stackexchange.com/sites#lifearts', 'Life & arts'],
    ['https://stackexchange.com/sites#science', 'Science'],
    ['https://stackexchange.com/sites#professional', 'Professional'],
    ['https://stackexchange.com/sites#business', 'business'],
    ['https://api.stackexchange.com/', 'API'],
    ['https://data.stackexchange.com/', 'Data'],
  ];
  const snsMenu = [
    ['https://stackoverflow.blog/', 'blog'],
    ['https://www.facebook.com/officialstackoverflow/', 'Facebook'],
    ['https://twitter.com/stackoverflow', 'Twitter'],
    ['https://www.linkedin.com/company/stack-overflow', 'linkedin'],
    ['https://www.instagram.com/thestackoverflow/', 'Instagram'],
  ];
  return (
    <>
      <Footerbox>
        <div className="footer-logo">
          <img src={logo} alt="stackoverflowlogo"></img>
        </div>
        <div className="footer-menu1">
          <h5>STACK OVERFLOW</h5>
          <ul>
            {stackoverflowMenu.map((el) => (
              <li key={el[1]}>
                <a href={el[0]}>{el[1]}</a>
              </li>
            ))}
          </ul>
        </div>
        <div className="footer-menu2">
          <h5>PRODUCTS</h5>

          <ul>
            {productsMenu.map((el) => (
              <li key={el[1]}>
                <a href={el[0]}>{el[1]}</a>
              </li>
            ))}
          </ul>
        </div>
        <div className="footer-menu3">
          <h5>COMPANY</h5>
          <ul>
            {companyMenu.map((el) => (
              <li key={el[1]}>
                <a href={el[0]}>{el[1]}</a>
              </li>
            ))}
          </ul>
        </div>
        <div className="footer-menu4">
          <h5>STACK EXCHANGE NETWORK</h5>
          <ul>
            {stackexchangenetworkMenu.map((el) => (
              <li key={el[1]}>
                <a href={el[0]}>{el[1]}</a>
              </li>
            ))}
          </ul>
        </div>
        <div className="footer-footer">
          <ul>
            {snsMenu.map((el) => (
              <li key={el[1]}>
                <a href={el[0]}>{el[1]}</a>
              </li>
            ))}
          </ul>
          <p>
            Site design/logo © 2023 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. rev 2023.4.13.4387
          </p>
        </div>
      </Footerbox>
    </>
  );
}

const Footerbox = styled.div`
  background-color: #232629;
  display: flex;
  gap: 10px;
  position: absolute;
  bottom: 0;
  justify-content: space-around;
  width: 100%;
  color: whitesmoke;
  img {
    width: 44px;
    height: 44px;
  }
  /* div {
    text-align: start;
  } */
  ul {
    margin: 0;
    padding: 0;
    list-style: none;
    li {
      margin: 4px 0px;
    }
    a {
      text-decoration: none;
      color: #80878e;
      &:hover {
        color: whitesmoke;
      }
    }
  }
  .footer-footer {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 30%;
    color: #80878e;
    ul {
      margin: 22px 0px;
    }
    li {
      display: inline;
      margin: 3px;
    }
  }

  @media (max-width: 1024px) {
    flex-direction: column;
    padding: 6px;
    li {
      display: inline;
      padding: 6px 12px 6px 0px;
    }
  }
`;

export default Footer;
