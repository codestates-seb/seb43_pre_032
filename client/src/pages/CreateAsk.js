import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import { selectNav } from '../store/store';
import {
  InputItem,
  TextareaItem,
  BtnContainer,
} from '../components/CreateAsk.js/CreateContent';
import HelpItem from '../components/CreateAsk.js/CreateHelp';
import { useState, useEffect } from 'react';
const MainContainer = styled.div`
  max-width: 1000px;
  padding-left: 20px;
  padding-right: 20px;
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

const CreateAsk = () => {
  let [selectHelp, setSelectHelp] = useState(0);
  let [create, setCreate] = useState({
    memberId: null,
    questionTitle: '',
    questionContent: '',
    tagName: [],
  });

  let handleData = (data) => {
    console.log(data);
    setCreate(create);
  };

  let dispatch = useDispatch();

  useEffect(() => {
    dispatch(selectNav(false));
  }, []);

  const helpTitle = [
    ['Title', 'questionTitle'],
    ['What are the details of your problem?', 'questionTitle'],
    ['What did you try and what were you expecting?', 'questionContent'],
    ['Tags', 'tagName'],
  ];
  const helpSentances = [
    'Be specific and imagine you’re asking a question to another person.',
    'Introduce the problem and expand on what you put in the title. Minimum 20 characters.',
    'Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.',
    'Add up to 5 tags to describe what your question is about. Start typing to see suggestions.',
  ];
  const bannerTitle = [
    'Writing a good title',
    'Introduce the problem',
    'Expand on the problem',
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
          <TextareaItem
            title={helpTitle[2]}
            help={helpSentances[2]}
          ></TextareaItem>
        </SingleContainer>
        <SingleContainer onClick={() => setSelectHelp(3)}>
          {selectHelp === 3 ? (
            <HelpItem
              title={bannerTitle[3]}
              help={bannerContents[3]}
            ></HelpItem>
          ) : null}
          <InputItem
            handleData={handleData}
            title={helpTitle[3]}
            help={helpSentances[3]}
          ></InputItem>
        </SingleContainer>
      </ItemContainer>
      <BtnContainer className="btn-blue-style">Register</BtnContainer>
    </MainContainer>
  );
};
export default CreateAsk;
