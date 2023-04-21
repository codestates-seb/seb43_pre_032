import styled from 'styled-components';
import {
  Sidebanners,
  Bannercontents,
  Bannertitle,
  Bannercomponent,
} from '../question/Sidebanner.jsx';
import { useState } from 'react';

const ModifyCom = () => {
  let titleHelp = [
    'Correct minor typos or mistakes',
    'Clarify meaning without changing it',
    'Add related resources or links',
    'Always respect the author’s intent',
    'Don’t use edits to reply to the author',
  ];
  let bodyHelp = [
    'create code fences with backticks ` ',
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
  let [help, setHelp] = useState(titleHelp);
  let [helpTitle, setHelpTitle] = useState(titles[0]);
  let [body, setBody] = useState('');

  let Bodyinput = (e) => {
    setBody(e.target.value);
  };
  let helphandler = (type) => {
    type === 'title'
      ? (setHelp(titleHelp), setHelpTitle(titles[0]))
      : type === 'body'
      ? (setHelp(bodyHelp), setHelpTitle(titles[1]))
      : (setHelp(tagHelp), setHelpTitle(titles[2]));
  };
  return (
    <TotalContainer>
      <ModifyContainer>
        <TitleContainer>
          <div className="modify-content-title">Title</div>
          <input
            onClick={() => helphandler('title')}
            className="modify-input-content"
          />
        </TitleContainer>
        <BodyContainer>
          <div className="modify-content-title">Body</div>
          <textarea
            onClick={() => helphandler('body')}
            onChange={Bodyinput}
            className="modify-textarea-content link-style-remove"
          />
        </BodyContainer>
        <div className="preview">{body}</div>
        <div>
          <div className="modify-content-title">Tags</div>
          <input
            onClick={() => helphandler('tag')}
            className="modify-input-content"
          />
        </div>
        <BtnContainer>
          <div className="save flex-center btn-blue-style">Save edits</div>
          <div className="cancel flex-center btn-skyblue-style">Cancel</div>
        </BtnContainer>
      </ModifyContainer>
      <div className="help-animation flex-center">
        <Bannercomponent>
          <Sidebanners className="help">
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
      </div>
    </TotalContainer>
  );
};
const TotalContainer = styled.main`
  display: flex;
  flex-direction: row;
  justify-content: center;
  @media screen and (max-width: 800px) {
    flex-direction: column;
    align-items: center;
  }
  margin-right: 10px;
  transition-duration: 1s;
  .help-animation {
    animation-name: fadeIn;
    animation-duration: 1s;
    animation-delay: 1s;
    animation-timing-function: linear;
  }
`;
const HelpContainer = styled.ul`
  list-style: circle;
`;
const ModifyContainer = styled.article`
  max-width: 660px;
  width: 50%;
  display: grid;
  margin-right: 20px;
  grid-template-columns: 1fr;
  grid-row-gap: 20px;
  .preview {
    display: flex;
    align-items: center;
    min-height: 30px;
  }
  @media screen and (max-width: 800px) {
    width: 90vw;
    padding-left: 30px;
  }
`;

const TitleContainer = styled.section``;
const BodyContainer = styled.section`
  .modify-textarea-content {
    padding: 10px;
    height: 200px;
    width: 96.5%;
    box-shadow: none;
    border-radius: 3px;
    resize: none;
  }
`;

const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 200px;
  margin-top: 5px;
  .save {
    height: 35px;
    width: 75px;
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

export default ModifyCom;
