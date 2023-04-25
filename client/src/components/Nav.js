import styled from 'styled-components';
import {
  faEarthAmericas,
  faCircleExclamation,
  faStar,
  faBagShopping,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';

const Nav = () => {
  return (
    <DivContainer>
      <div>
        <nav>
          <NavOlst>
            <Link to={'/question'}>
              <li>
                <HoverDiv>Home</HoverDiv>
              </li>
            </Link>
            <LiChild>
              <ol>
                <li>PUBLIC</li>
                <li>
                  <div>
                    <FontAwesomeIcon
                      className="font-awesome"
                      icon={faEarthAmericas}
                    />
                    <span className="font-icon">Questions</span>
                  </div>
                </li>
                <li>
                  <Link to={'/tags'}>
                    <span>Tags</span>
                  </Link>
                </li>
                <li>
                  <span>Users</span>
                </li>
                <li>
                  <span>Companies</span>
                </li>
                <li>
                  <FontAwesomeDiv>
                    COLLECTIVES
                    <FontAwesomeIcon icon={faCircleExclamation} />
                  </FontAwesomeDiv>
                </li>
                <li>
                  <HoverDiv>
                    <FontAwesomeIcon
                      className="font-awesome"
                      icon={faStar}
                      style={{ color: '#f4861f' }}
                    />
                    <span>Explore Collectives</span>
                  </HoverDiv>
                </li>
              </ol>
            </LiChild>
            <li>
              <FontAwesomeDiv>
                TEAMS
                <FontAwesomeIcon icon={faCircleExclamation} />
              </FontAwesomeDiv>
            </li>
            <li>
              <div>
                <div>
                  <FontAwesomeIcon
                    icon={faBagShopping}
                    style={{ color: '#f4861f' }}
                  />
                  <span>Create free Team</span>
                </div>
              </div>
            </li>
            <li>
              <button>Looking for your Teams?</button>
            </li>
          </NavOlst>
        </nav>
      </div>
    </DivContainer>
  );
};

const DivContainer = styled.div`
  margin: 30px 0px 0px 0px;
  padding-left: 10px;

  > div {
    padding: 0px 0px 0px 0px;
  }

  //전체 li태그
  li {
    list-style: none;
  }

  a {
    text-decoration: none; /* 밑줄 제거 */
    color: inherit; /* 상속받은 색상 사용 */
    font-size: inherit;
  }
`;

const NavOlst = styled.ol`
  //Home
  > li:first-child {
    font-size: 13px;
    line-height: 26px;
    color: #525960;

    > p {
      padding: 4px 4px 4px 8px;
    }

    div {
      cursor: pointer;
    }

    :hover {
      color: #0c0d0e;
    }
  }

  // span요소들 (Explore Collectives, Create free Team)
  span {
    padding: 8px 6px 8px 8px;
    font-size: 13px;
    cursor: pointer;

    color: #525960;
    :hover {
      color: #0c0d0e;
    }
  }

  //버튼
  button {
    margin: 8px 8px 0px 0px;
    padding: 8px;

    border: none;
    background: none;
    outline: none;
    box-shadow: none;

    font-size: 11px;
    cursor: pointer;

    color: #0063bf;
    background-color: #eff8ff;

    :hover {
      color: #004585;
    }
  }

  .font-awesome + div {
    background-color: red;
  }

  //TEAMS 소제목
  > li:nth-last-child(3) {
    font-size: 11px;
    color: #6a737c;

    margin: 17px 0px 0px 0px;
  }

  //버튼 부모 li요소
  > li:last-child {
    padding: 0px 8px 0px 1px;
  }

  > li:nth-last-child(3) {
  }
`;

const LiChild = styled.li`
  //PUBLIC 소제목
  li:first-child {
    margin: 16px 0px 4px 0px;
    padding: 0px;
    font-size: 11px;
    color: #6a737c;
  }

  //COLLECTIVES 소제목
  li:nth-last-child(2) {
    margin: 16px 0px 0px 0px;
    font-size: 11px;
    cursor: auto;
    color: #6a737c;
  }

  .font-icon > svg {
    color: red;
  }

  > ol {
    margin-bottom: 12px;
    padding: 0px;
  }

  //Explore Collectives
  > ol > li:last-child > p {
    /* margin: 0px; */
  }

  //li요소 Questions부터 ~ Companies까지
  > ol > li:not(:first-child, :last-child, :nth-last-child(2)) {
    text-align: left;
    cursor: pointer;
    font-size: 13px;
    line-height: 26px;
    color: #525960;
    padding: 4px 4px 4px 14px;

    height: 30px;
    :hover {
      color: #0c0d0e;
      background-color: #f9f9f9;
      border-right: 3px solid orange;
    }
  }

  //p요소 Questions부터 ~ Companies까지
  > ol > li:nth-last-child(6) {
    padding: 4px 4px 4px 0px;
  }
`;

const FontAwesomeDiv = styled.div`
  display: flex;
  justify-content: space-between;
  margin-right: 15px;
`;

const HoverDiv = styled.div`
  height: 30px;
  padding: 4px 4px 4px 0px;

  :hover {
    background-color: #f9f9f9;
    border-right: 3px solid orange;
  }
`;
export default Nav;
