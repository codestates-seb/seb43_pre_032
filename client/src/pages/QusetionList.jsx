import Sidebanner from '../components/question/Sidebanner.jsx';
import Questions from '../components/question/Questions.jsx';
import styled from 'styled-components';

function Viewcomponent() {
  return (
    <Questionscomponent>
      <Questions /> <Sidebanner />
    </Questionscomponent>
  );
}

export default Viewcomponent;

const Questionscomponent = styled.div`
  display: flex;
`;
