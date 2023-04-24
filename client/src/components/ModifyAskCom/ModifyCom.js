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
  'http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/1';
const ModifyCom = () => {
  // const [token, setToken] = useState('');
  let data = {
    title: '수정될 질문의 제목',
    content: '여기가 본문의 내용이 될거 같은데 ~~~~~~~~~~~~~~~~~~~~~~',
  };

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
  let [tagData, setTagData] = useState([]); // 전체 태그 데이터
  let [word, setWord] = useState(''); // 태그 검색어
  let [original, setOriginal] = useState({});
  let [selected, setSelected] = useState([
    {
      tagDescription:
        'For questions about programming in ECMAScript (JavaScript/JS) and its different dialects/implementations (except for ActionScript). Keep in mind that JavaScript is NOT the same as Java! Include all la…',
      tagId: 1,
      tagName: 'javascript',
    },
  ]); // 선택한 태그
  let [filtered, setFiltered] = useState([]);
  let qsId = 1;
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
        console.log(original);
      })
      .catch(function (error) {
        console.log(error);
      });
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/${qsId.qsId}?page=1&size=5&sortBy=answerId`,
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
        setOriginal(res.data.data.question);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, []);
  useEffect(() => {
    setFiltered(
      tagData
        .filter((tag) => {
          return tag.tagName.includes(word.toLowerCase());
        })
        .slice(0, 6)
    );
  }, [word]);
  let [help, setHelp] = useState(titleHelp);
  let [helpTitle, setHelpTitle] = useState(titles[0]);

  let [questionId, setQuestionId] = useState(1);
  let [questionTitle, setQuestionTitle] = useState(data.title);
  let [questionBody, setQuestionBody] = useState(data.content);

  let titleInput = (e) => {
    setQuestionId(1); //바꿔야함--> props로 넘겨준걸로 set 되게
    setQuestionTitle(e.target.value);
  };
  let bodyInput = (e) => {
    setQuestionBody(e.target.value);
  };

  let saveOnClick = () => {
    let tagID = selected.map((el) => {
      return { tagId: el.tagId };
    });
    let newData = {
      questionId: questionId,
      questionTitle: questionTitle,
      questionContent: questionBody,
      tagName: tagID,
      modifiedAt: new Date().toISOString(),
    };
    console.log(localStorage.getItem('token'));
    axios
      .patch(url, newData, {
        header: {
          Authorization: localStorage.getItem('token'),
          'ngrok-skip-browser-warning': '69420',
          'Content-Type': 'application/json',
        },
      })
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
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
        <div onFocus={() => helphandler('tag')} className="tagCon">
          <TagBar
            setWord={setWord}
            selected={selected}
            filtered={filtered}
            setSelected={setSelected}
          ></TagBar>
        </div>
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
      <BannerContainer
        className={word === '' ? 'flex-center' : 'flex-center help'}
      >
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

const TitleContainer = styled.section`
  width: 100%;
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

export default ModifyCom;
