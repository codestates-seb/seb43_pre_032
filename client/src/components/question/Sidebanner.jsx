import styled from 'styled-components';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { faMessage } from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStackOverflow } from '@fortawesome/free-brands-svg-icons';

function Sidebanner() {
  const link = [
    'https://stackoverflow.blog/2023/04/19/ops-teams-are-pets-not-cattle/?cb=1&_ga=2.257486667.1725787379.1681729300-541917846.1681721665',
    'https://meta.stackexchange.com/questions/388030/improving-the-copy-in-the-close-modal-and-post-notices-2023-edition?cb=1',
    'https://meta.stackexchange.com/questions/388401/new-blog-post-from-our-ceo-prashanth-community-is-the-future-of-ai?cb=1',
    'https://meta.stackoverflow.com/questions/421831/temporary-policy-chatgpt-is-banned?cb=1',
    'https://meta.stackoverflow.com/questions/423798/content-discovery-initiative-4-13-update-related-questions-using-a-machine-lear?cb=1',
    'https://meta.stackoverflow.com/questions/424218/whats-the-preferred-tag-for-protection-in-spreadsheets?cb=1',
  ];
  return (
    <Bannercomponent>
      <Sidebanners>
        <ul>
          <Bannertitle>The Overflow Blog</Bannertitle>
          <Bannercontents>
            <span>
              <FontAwesomeIcon icon={faPen} size="xs" />
            </span>
            <span>
              <a href={link[0]}>Are meetings making you less productive?</a>
            </span>
          </Bannercontents>
          <Bannercontents>
            <span>
              <FontAwesomeIcon icon={faPen} size="xs" />
            </span>
            <span>
              <a href={link[0]}>The philosopher who believes in Web Assembly</a>
            </span>
          </Bannercontents>
        </ul>
        <ul>
          <Bannertitle>Featured on Meta</Bannertitle>
          <Bannercontents>
            <span className="font-blue">
              <FontAwesomeIcon icon={faMessage} size="xs" />
            </span>
            <span>
              <a href={link[1]}>
                Improving the copy in the close modal and post notices - 2023
                edition
              </a>
            </span>
          </Bannercontents>
          <Bannercontents>
            <span className="font-blue">
              <FontAwesomeIcon icon={faMessage} size="xs" />
            </span>
            <span>
              <a href={link[2]}>
                New blog post from our CEO Prashanth: Community is the future of
                AI
              </a>
            </span>
          </Bannercontents>
          <Bannercontents>
            <span>
              <FontAwesomeIcon icon={faStackOverflow} size="sm" />
            </span>
            <span>
              <a href={link[3]}>Temporary policy: ChatGPT is banned</a>
            </span>
          </Bannercontents>
          <Bannercontents>
            <span>
              <FontAwesomeIcon icon={faStackOverflow} size="sm" />
            </span>
            <span>
              <a href={link[4]}>
                Content Discovery initiative 4/13 update: Related questions
                using a Machine...
              </a>
            </span>
          </Bannercontents>
        </ul>
        <ul>
          <Bannertitle>Hot Meta Posts</Bannertitle>
          <Bannercontents>
            <span>47</span>
            <span>
              <a href={link[5]}>
                Voting reversal for smaller tags with few answerers
              </a>
            </span>
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

/** 2023.04.18 사이드 배너 스타일드 컴포넌트 - by 김주비*/
export const Bannercomponent = styled.section`
  width: 300px;
  padding: 20px;
  color: #232629;
  font-size: 13px;
  a {
    color: #232629;
    text-decoration: none;
    outline: none;
  }
`;

export const Sidebanners = styled.aside`
  box-shadow: 0px 0px 5px #f1e6b9;
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

export const Bannertitle = styled.li`
  background-color: #fbeec4;
  border-top: 1px solid #e8ddb3;
  border-bottom: 1px solid #e8ddb3;
  padding: 15px;
  font-weight: 800;
`;

export const Bannercontents = styled.li`
  display: flex;
  flex-direction: row;
  padding: 15px;
  font-weight: 500;
  :nth-child(3) {
    padding-top: 2px;
  }
  :nth-child(4) {
    padding-top: 2px;
    padding-bottom: 2px;
  }
  span:nth-child(1) {
    margin-right: 10px;
  }
  .font-blue {
    color: #2097ff;
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
