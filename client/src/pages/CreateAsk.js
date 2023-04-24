import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import { selectNav } from '../store/store';
import {
  InputItem,
  TextareaItem,
} from '../components/CreateAsk.js/CreateContent';
import HelpItem from '../components/CreateAsk.js/CreateHelp';
import { useState, useEffect } from 'react';
import TagBar from '../components/TagBar';
import axios from 'axios';

axios.defaults.withCredentials = true;
const url = 'https://8625-61-254-8-200.ngrok-free.app/';

const CreateAsk = () => {
  let [tagData, setTagData] = useState([]); // 전체 태그 데이터
  let [word, setWord] = useState(''); // 태그 검색어
  let [selected, setSelected] = useState([]); // 선택한 태그
  let [filtered, setFiltered] = useState([]);
  tagData.length;
  console.log(word);
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

  let [selectHelp, setSelectHelp] = useState(0);
  let [create, setCreate] = useState({
    memberId: 1,
    questionTitle: '',
    questionContent: '',
    tagName: [{ tagId: 3 }],
  });
  let [isReady, setIsReady] = useState(false);
  let handleData = (data) => {
    let newData = { ...create, [data[0]]: data[1] };
    console.log(newData);
    newData.questionContent.length >= 20 ? setIsReady(true) : setIsReady(false);
    setCreate(newData);
  };
  const postData = () => {
    axios
      .post(`${url}/questions?page=1&size=10&sortBy=questionId`)
      .then(function (response) {
        // 성공한 경우 실행
        console.log(response);
      });
  };

  let dispatch = useDispatch();
  useEffect(() => {
    dispatch(selectNav(false));
  }, []);

  const helpTitle = [
    ['Title', 'questionTitle'],
    [
      'What are the details of your problem and what did you try to solve it ?',
      'questionContent',
    ],
    ['Tags', 'tagName'],
  ];
  const helpSentances = [
    'Be specific and imagine you’re asking a question to another person.',
    'Introduce the problem and expand on what you put in the title. Minimum 20 characters.',
    'Add up to 5 tags to describe what your question is about. Start typing to see suggestions.',
  ];
  const bannerTitle = [
    'Writing a good title',
    'Introduce the problem',
    'Adding tags',
  ];
  const bannerContents = [
    [
      'Your title should summarize the problem.',
      'You might find that you have a better idea of your title after writing out the rest of the question.',
    ],
    [
      'Explain how you encountered the problem you’re trying to solve, and any difficulties that have prevented you from solving it yourself.',
    ],
    [
      'Show what you’ve tried, tell us what happened, and why it didn’t meet your needs.',
      'Not all questions benefit from including code, but if your problem is better understood with code you’ve written, you should include a minimal, reproducible example.',
      'Please make sure to post code and errors as text directly to the question (and not as images), and format them appropriately.',
    ],
    [
      'Tags help ensure that your question will get attention from the right people.',
      'Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.',
    ],
  ];
  return (
    <MainContainer>
      <Head>
        <div>Ask a public question</div>
      </Head>
      <ItemContainer>
        <SingleContainer onClick={() => setSelectHelp(0)}>
          {selectHelp === 0 ? (
            <HelpItem
              title={bannerTitle[0]}
              help={bannerContents[0]}
            ></HelpItem>
          ) : null}
          <InputItem
            handleData={handleData}
            title={helpTitle[0]}
            help={helpSentances[0]}
          ></InputItem>
        </SingleContainer>
        <SingleContainer onClick={() => setSelectHelp(1)}>
          {selectHelp === 1 ? (
            <HelpItem
              title={bannerTitle[1]}
              help={bannerContents[1]}
            ></HelpItem>
          ) : null}
          <TextareaItem
            className="modify-textarea-content link-style-remove"
            handleData={handleData}
            title={helpTitle[1]}
            help={helpSentances[1]}
          ></TextareaItem>
        </SingleContainer>
        <SingleContainer onClick={() => setSelectHelp(3)}>
          {selectHelp === 3 ? (
            <HelpItem
              title={bannerTitle[3]}
              help={bannerContents[3]}
            ></HelpItem>
          ) : null}
          <TagBar
            setWord={setWord}
            selected={selected}
            filtered={filtered}
            setSelected={setSelected}
          ></TagBar>
        </SingleContainer>
      </ItemContainer>
      <BtnContainer
        className={isReady ? 'btn-blue-style' : 'btn-blue-style disabled'}
        onClick={postData}
      >
        Register
      </BtnContainer>
    </MainContainer>
  );
};

const MainContainer = styled.div`
  max-width: 1000px;
  padding-left: 20px;
  padding-right: 20px;
`;
const BtnContainer = styled.div`
  margin-top: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 35px;
  width: 75px;
  font-size: 13px;
  font-weight: 600;
  color: white;
  background-color: var(--signup-btn-before);
  :hover {
    background-color: var(--signup-btn-after);
  }
`;
const Head = styled.section`
  div {
    font-size: 30px;
    font-weight: 500;
  }
  padding-top: 40px;
  padding-bottom: 60px;
`;
const ItemContainer = styled.div`
  display: grid;
  grid-row-gap: 30px;
`;
const SingleContainer = styled.section`
  display: grid;
  grid-template-columns: 1fr;
`;

export default CreateAsk;
