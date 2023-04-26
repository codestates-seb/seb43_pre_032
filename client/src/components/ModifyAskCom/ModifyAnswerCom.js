import styled from 'styled-components';
import {
  Sidebanners,
  Bannercontents,
  Bannertitle,
  Bannercomponent,
} from '../question/Sidebanner.jsx';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ModifyAnswerCom = ({ asId }) => {
  let titleHelp = [
    'Correct minor typos or mistakes',
    'Clarify meaning without changing it',
    'Add related resources or links',
    'Always respect the author’s intent',
    'Don’t use edits to reply to the author',
  ];
  let bodyHelp = [
    'create code fences with backticks',
    'add language identifier to highlight code',
    'put returns between paragraphs',
    'for linebreak add 2 spaces at end',
    '_italic_ or **bold**',
    'indent code by 4 spaces',
    'backtick escapes `like _so_`',
    'quote by placing > at start of line',
    'to make links (use https whenever possible) <https://example.com> [example](https://example.com) a href="https://example.com">example</a>',
  ];
  let tagHelp = [
    'complete the sentence: my question is about...',
    'use tags that describe things or concepts that are essential, not incidental to your question',
    'favor using existing popular tags',
    'read the descriptions that appear below the tag',
    'combine multiple words into single-words with hyphens (e.g. python-3.x), up to a maximum of 35 characters',
    `creating new tags is a privilege; if you can't yet create a tag you need, then post this question without it, then ask the community to create it for you`,
  ];
  const titles = ['How to Edit', 'How to Format', 'How to Tag'];
  const navigate = useNavigate();
  const [answerOrigin, setanswerOrigin] = useState([]);

  console.log(answerOrigin);

  useEffect(() => {
    console.log(asId);
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/answers/${asId.asId}`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
          withCredentials: true,
          credentials: 'include',
        }
      )
      .then(function (res) {
        // 성공한 경우 실행
        setanswerOrigin(res.data);
        setAnswerBody(res.data.answerContent);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, []);

  let [help, setHelp] = useState(titleHelp); //헬프 메세지
  let [helpTitle, setHelpTitle] = useState(titles[0]); // 헬프타이틀
  let [answerBody, setAnswerBody] = useState(''); // answer 컨텐츠

  let bodyInput = (e) => {
    setAnswerBody(e.target.value);
  };

  let helphandler = (type) => {
    type === 'title'
      ? (setHelp(titleHelp), setHelpTitle(titles[0]))
      : type === 'body'
      ? (setHelp(bodyHelp), setHelpTitle(titles[1]))
      : (setHelp(tagHelp), setHelpTitle(titles[2]));
  };

  const cancelClicked = () => {
    navigate('/detailquestion');
  };

  return (
    <TotalContainer>
      <ModifyContainer>
        <BodyContainer>
          <div className="modify-content-title">Body</div>
          <textarea
            value={answerBody}
            onClick={() => helphandler('body')}
            onChange={bodyInput}
            className="modify-textarea-content link-style-remove"
          />
        </BodyContainer>
        <div className="preview">{answerBody}</div>

        <BtnContainer>
          <button className="save flex-center btn-blue-style">
            Save edits
          </button>
          <button
            onClick={cancelClicked}
            className="cancel flex-center btn-skyblue-style"
          >
            Cancel
          </button>
        </BtnContainer>
      </ModifyContainer>
      <BannerContainer>
        <Bannercomponent>
          <Sidebanners>
            <Bannertitle>{helpTitle}</Bannertitle>
            <Bannercontents>
              <HelpContainer>
                {help.map((el, idx) => {
                  return (
                    <li className="list-style" key={idx}>
                      {el}
                    </li>
                  );
                })}
              </HelpContainer>
            </Bannercontents>
          </Sidebanners>
        </Bannercomponent>
      </BannerContainer>
    </TotalContainer>
  );
};
const TotalContainer = styled.main`
  display: flex;
  flex-direction: row;
  justify-content: center;
  @media screen and (max-width: 1300px) {
    flex-direction: column;
    align-items: center;
  }
  margin-right: 10px;
  transition-duration: 1s;
  .help {
    @media screen and (max-width: 1300px) {
      display: none;
    }
  }
`;
const BannerContainer = styled.div``;
const HelpContainer = styled.ul`
  display: grid;
  justify-content: flex-end;
`;
const ModifyContainer = styled.article`
  max-width: 660px;
  width: 60%;
  display: grid;
  margin-right: 20px;
  grid-template-columns: 1fr;
  grid-row-gap: 20px;
  .preview {
    display: flex;
    align-items: center;
    min-height: 30px;
  }
  .tagCon {
    position: relative;
  }

  @media screen and (max-width: 1300px) {
    width: 90vw;
    padding-left: 30px;
  }
`;

const BodyContainer = styled.section`
  width: 97.5%;
`;

const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 200px;
  .save {
    height: 35px;
    width: 80px;
    font-size: 13px;
    :hover {
      background-color: var(--signup-btn-after);
    }
  }
  .cancel {
    font-weight: 600;
    padding-top: 2px;
    padding-bottom: 2px;
    padding-left: 7px;
    padding-right: 7px;
    height: 30px;
    margin: 5px;
    font-size: 13px;
    border-radius: 3px;
    background-color: inherit;
    border: none;
    :hover {
      background-color: var(--login-btn-before);
    }
  }
`;

export default ModifyAnswerCom;
