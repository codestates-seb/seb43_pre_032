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
import { useNavigate } from 'react-router-dom';
axios.defaults.withCredentials = true;
const url =
  'http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/';

const CreateAsk = () => {
  let [word, setWord] = useState(''); // 태그 검색어
  let [selected, setSelected] = useState([]); // 선택한 태그
  let [filtered, setFiltered] = useState([]);
  let [getTag, setGetTag] = useState([]);
  useEffect(() => {
    searchedData(word);
    setFiltered(getTag.slice(0, 6));
  }, [word]);
  const searchedData = async (word) => {
    await axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/tags?page=1&keyword=${word}&tab=popular`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then((res) => setGetTag(res.data.data))
      .catch((err) => console.log(err));
  };
  useEffect(() => {
    searchedData(word);
    setFiltered(getTag.slice(0, 6));
  }, [word]);
  let [selectHelp, setSelectHelp] = useState(0);
  let [titleInput, setTitleInput] = useState('');
  let [body, setBody] = useState('');
  let [isReady, setIsReady] = useState(false);

  console.log(titleInput);
  console.log(body);

  useEffect(() => {
    body.length > 20 ? setIsReady(true) : setIsReady(false);
  }, [body]);
  let navigate = useNavigate();
  const postData = () => {
    let tagID = selected.map((el) => {
      return { tagId: el.tagId };
    });

    let newData = {
      questionTitle: titleInput,
      questionContent: body,
      tagName: tagID,
    };
    console.log(newData, localStorage.getItem('token'));
    axios
      .post(url, newData, {
        headers: {
          Authorization: localStorage.getItem('token'),
        },
      })
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
    navigate('/question');
    window.location.reload();
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
      'Tags help ensure that your question will get attention from the right people.',
      'Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.',
    ],
  ];
  const cancelHandler = () => {
    navigate('/question');
  };
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
            setTitle={setTitleInput}
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
            setBody={setBody}
            title={helpTitle[1]}
            help={helpSentances[1]}
          ></TextareaItem>
        </SingleContainer>
        <SingleContainer onClick={() => setSelectHelp(2)}>
          {selectHelp === 2 ? (
            <HelpItem
              title={bannerTitle[2]}
              help={bannerContents[2]}
            ></HelpItem>
          ) : null}
          <div className="tagCon">
            <TagBar
              setWord={setWord}
              selected={selected}
              filtered={filtered}
              setSelected={setSelected}
            ></TagBar>
          </div>
        </SingleContainer>
      </ItemContainer>
      <div className="flex-row">
        <BtnContainer
          className={isReady ? 'btn-blue-style' : 'btn-blue-style disabled'}
          onClick={postData}
        >
          Register
        </BtnContainer>
        <CancelBtn onClick={cancelHandler} className="flex-center">
          Cancel
        </CancelBtn>
      </div>
    </MainContainer>
  );
};
const CancelBtn = styled.button`
  margin-top: 5px;
  margin-left: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 35px;
  width: 75px;
  font-size: 13px;
  font-weight: 600;
  color: white;
  background-color: lightblue;
  :hover {
    background-color: skyblue;
  }
  border: none;
  border-radius: 3px;
`;
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
  .tagCon {
    position: relative;
  }
`;
const SingleContainer = styled.section`
  display: grid;
  grid-template-columns: 1fr;
`;

export default CreateAsk;
