import Sidebanner from './Sidebanner.jsx';
import Questions from './Questions.jsx';
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
