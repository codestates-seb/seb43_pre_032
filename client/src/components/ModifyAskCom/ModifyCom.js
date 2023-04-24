import styled from 'styled-components';
import {
  Sidebanners,
  Bannercontents,
  Bannertitle,
  Bannercomponent,
} from '../question/Sidebanner.jsx';
import { useState, useEffect } from 'react';
import TagBar from '../TagBar.js';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const url =
  'http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions?page=1&size=10&sortBy=questionId';
const ModifyCom = () => {
  let data = {
    title: '수정될 질문의 제목',
    content: '여기가 본문의 내용이 될거 같은데 ~~~~~~~~~~~~~~~~~~~~~~',
    tag: ['java', 'spring', '태그 !'],
  };

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
  const navigate = useNavigate();
  let [tagData, setTagData] = useState([]); // 전체 태그 데이터
  let [word, setWord] = useState(''); // 태그 검색어
  let [selected, setSelected] = useState(['java', 'spring']); // 선택한 태그
  let [filtered, setFiltered] = useState([]);

  useEffect(() => {
    // 솔님이 작성하신 코드     modify, create, tag 에서 쓰여서 hooks로 만드는건..?
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/tags?page=1&size=3276&sortBy=tagId`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (response) {
        setTagData(response.data.data); // 태그 전체
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  useEffect(() => {
    setFiltered(
      tagData
        .filter((tag) => tag.tagName.includes(word.toLowerCase()))
        .slice(0, 6)
    );
  }, [word]);
  let [help, setHelp] = useState(titleHelp);
  let [helpTitle, setHelpTitle] = useState(titles[0]);

  let [questionTitle, setQuestionTitle] = useState(data.title);
  let [questionBody, setQuestionBody] = useState(data.content);

  let titleInput = (e) => {
    setQuestionTitle(e.target.value);
  };
  let bodyInput = (e) => {
    setQuestionBody(e.target.value);
  };

  let saveOnClick = () => {
    let newData = {
      questionTitle: questionTitle,
      questionContent: questionBody,
      tagName: selected,
    };
    axios.patch(url, { ...newData });
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
        <TitleContainer>
          <div className="modify-content-title">Title</div>
          <input
            value={questionTitle}
            onClick={() => helphandler('title')}
            className="modify-input-content"
            onChange={titleInput}
          />
        </TitleContainer>
        <BodyContainer>
          <div className="modify-content-title">Body</div>
          <textarea
            value={questionBody}
            onClick={() => helphandler('body')}
            onChange={bodyInput}
            className="modify-textarea-content link-style-remove"
          />
        </BodyContainer>
        <div className="preview">{questionBody}</div>
        <TagBar
          setWord={setWord}
          selected={selected}
          filtered={filtered}
          setSelected={setSelected}
        ></TagBar>
        <BtnContainer>
          <button
            onClick={saveOnClick}
            className="save flex-center btn-blue-style"
          >
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
      <div className="help-animation flex-center">
        <Bannercomponent>
          <Sidebanners className="help">
            <Bannertitle>{helpTitle}</Bannertitle>
            <Bannercontents>
              <div className="test">
                <HelpContainer>
                  {help.map((el, idx) => {
                    return (
                      <li className="list-style" key={idx}>
                        {el}
                      </li>
                    );
                  })}
                </HelpContainer>
              </div>
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
  display: grid;
  justify-content: flex-end;
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
const BodyContainer = styled.section``;

const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 200px;
  margin-top: 5px;
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

export default ModifyCom;
