import styled from 'styled-components';

/** 2023.04.18 사이드 배너 스타일드 컴포넌트 - by 김주비*/
const Bannercomponent = styled.section`
  width: 300px;
  padding: 10px;
  color: #232629;
  font-size: 13px;
`;

const Sidebanners = styled.aside`
  box-shadow: 0px 0px 5px #e8ddb3;
  border: 1px solid #e8ddb3;
  border-radius: 5px;
  overflow: hidden;
  opacity: 0.75;
  width: 100%;
  background-color: #fbf3d5;
  ul {
    padding: 0;
    margin: 0;
  }
`;

const Bannertitle = styled.li`
  background-color: hsl(47, 65%, 84%);
  border: 1px solid #e8ddb3;
  padding: 15px;
  font-weight: 800;
`;

const Bannercontents = styled.li`
  padding: 15px;
  font-weight: 500;
  :nth-child(3) {
    padding-top: 2px;
  }
  :nth-child(4) {
    padding-top: 2px;
    padding-bottom: 2px;
  }
`;

const Suboptions = styled(Bannercomponent)`
  padding: 0px;
  font-size: 15px;
`;

const Optiongroup = styled(Sidebanners)`
  box-shadow: 0px 0px 5px #ccc;
  opacity: 0.75;
  margin-top: 20px;
  border-color: #ccc;
  background-color: #fff;
`;
const Optiontitle = styled(Bannertitle)`
  border: none;
  border-bottom: 1px solid #e3e3e3;
  background-color: #f5f5f5;
  font-weight: 500;
`;
const Optioncontents = styled(Bannercontents)`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  p {
    background-color: hsl(205, 46%, 92%);
    color: hsl(205, 47%, 42%);
    border-radius: 3px;
    border: 1px solid hsl(205, 47%, 42%);
    font-size: 12px;
    padding: 5px 10px;
    margin: 3px;
    transition: 0.2s ease-in-out;
  }
  p:hover {
    background-color: #afccdf;
    cursor: pointer;
  }
  span {
    color: hsl(205, 47%, 42%);
    cursor: pointer;
  }
`;

function Sidebanner() {
  return (
    <Bannercomponent>
      <Sidebanners>
        <ul>
          <Bannertitle>The Overflow Blog</Bannertitle>
          <Bannercontents>
            Are meetings making you less productive?
          </Bannercontents>
          <Bannercontents>
            The philosopher who believes in Web Assembly
          </Bannercontents>
        </ul>
        <ul>
          <Bannertitle>Featured on Meta</Bannertitle>
          <Bannercontents>
            Improving the copy in the close modal and post notices - 2023
            edition
          </Bannercontents>
          <Bannercontents>Temporary policy: ChatGPT is banned</Bannercontents>
          <Bannercontents>
            The [protection] tag is being burninated
          </Bannercontents>
          <Bannercontents>
            Content Discovery initiative 4/13 update: Related questions using a
            Machine...
          </Bannercontents>
        </ul>
        <ul>
          <Bannertitle>Hot Meta Posts</Bannertitle>
          <Bannercontents>
            47 Voting reversal for smaller tags with few answerers
          </Bannercontents>
        </ul>
      </Sidebanners>
      <Suboptions>
        <Optiongroup>
          <Optiontitle>Custom Filters</Optiontitle>
          <Optioncontents>
            <span>Create a custom filter</span>
          </Optioncontents>
        </Optiongroup>
        <Optiongroup>
          <Optiontitle>Watched Tags</Optiontitle>
          <Optioncontents>
            <p>css</p>
            <p>react</p>
            <p>html</p>
            <p>javascript</p>
            <p>styled-components</p>
          </Optioncontents>
        </Optiongroup>
        <Optiongroup>
          <Optiontitle>Ignored Tags</Optiontitle>
          <Optioncontents>
            <p>Add an ignored tag</p>
          </Optioncontents>
        </Optiongroup>
      </Suboptions>
    </Bannercomponent>
  );
}

export default Sidebanner;
