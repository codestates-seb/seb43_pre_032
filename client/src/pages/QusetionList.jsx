import Sidebanner from '../components/question/Sidebanner.jsx';
import Questions from '../components/question/Questions.jsx';
import styled from 'styled-components';

function Viewcomponent() {
  return (
    <Questionscomponent>
      <Viewquestion>
        <Questions />
      </Viewquestion>
      <Viewsidebanner>
        <Sidebanner />
      </Viewsidebanner>
    </Questionscomponent>
  );
}

export default Viewcomponent;

const Questionscomponent = styled.div`
  display: flex;
`;

const Viewquestion = styled.div`
  width: 100%;
`;

const Viewsidebanner = styled.div`
  @media (max-width: 1000px) {
    display: none;
  }
`;
