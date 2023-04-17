import styled from 'styled-components';

const Questionscomponent = styled.section`
  width: 100%;
  border: 1px solid red;
`;

const QuestionFilter = styled.div``;
const QuestionList = styled.ul``;

function Questions() {
  return (
    <>
      <Questionscomponent>
        <QuestionFilter></QuestionFilter>
        <QuestionList></QuestionList>
      </Questionscomponent>
    </>
  );
}

export default Questions;
