import styled from 'styled-components';

const Nav = () => {
  return (
    <DivContainer>
      <div>
        <nav>
          <NavOlst>
            <li>
              <p>
                <div>Home</div>
              </p>
            </li>
            <LiChild>
              <ol>
                <li>PUBLIC</li>
                <li>
                  <p>
                    <div>Questions</div>
                  </p>
                </li>
                <li>
                  <p>
                    <div>
                      <div>Tags</div>
                    </div>
                  </p>
                </li>
                <li>
                  <p>
                    <div>
                      <div>Users</div>
                    </div>
                  </p>
                </li>
                <li>
                  <p>
                    <div>
                      <div>Companies</div>
                    </div>
                  </p>
                </li>
                <li>
                  <div>COLLECTIVES</div>
                </li>
                <li>
                  <p>
                    <span>Explore Collectives</span>
                  </p>
                </li>
              </ol>
            </LiChild>
            <li>
              <div>TEAMS</div>
            </li>
            <li>
              <p>
                <div>
                  <span>Create free Team</span>
                </div>
              </p>
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
  /* border: 1px solid red; */
  margin: 0px;
  padding: 0px;
  display: flex;
  width: 200px;

  > div {
    padding: 0px 0px 0px 0px;
    /* background-color: red; */
  }
  //전체 li태그
  li {
    list-style: none;
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

  //TEAMS 소제목
  > li:nth-last-child(3) {
    font-size: 11px;
    color: #6a737c;

    margin: 24px 0px 0px 8px;
  }

  //버튼
  button {
    margin: 8px 8px 0px 0px;
    padding: 8px;

    border: none;
    background: none;
    outline: none;
    box-shadow: none;

    font-size: 12px;
    cursor: pointer;

    color: #0063bf;
    background-color: #eff8ff;

    :hover {
      color: #004585;
    }
  }

  //버튼 부모 li요소
  > li:last-child {
    padding: 0px 8px 0px 1px;
  }
`;

const LiChild = styled.li`
  > ol {
    margin-bottom: 12px;
    padding: 0px;
  }

  //PUBLIC 소제목
  li:first-child {
    margin: 16px 0px 4px 8px;
    padding: 0px;
    font-size: 11px;
    color: #6a737c;
  }

  //COLLECTIVES 소제목
  li:nth-last-child(2) {
    margin: 16px 0px 0px 8px;
    font-size: 11px;
    cursor: auto;
    color: #6a737c;
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
    padding: 0px;

    color: #525960;
    :hover {
      color: #0c0d0e;
    }
  }

  //p요소 Questions부터 ~ Companies까지
  > ol > li:not(:last-child) p {
    padding: 4px 4px 4px 30px;
  }
`;

export default Nav;
