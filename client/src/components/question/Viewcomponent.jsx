import Sidebanner from './Sidebanner.jsx';
import Questions from './Questions.jsx';
import styled from 'styled-components';

const Questionscomponent = styled.div`
  display: flex;
`;

function Viewcomponent() {
  return (
    <Questionscomponent>
      <Questions /> <Sidebanner />
    </Questionscomponent>
  );
}

export default Viewcomponent;
