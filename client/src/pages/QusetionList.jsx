import Sidebanner from '../components/question/Sidebanner.jsx';
import Questions from '../components/question/Questions.jsx';
import { useDispatch } from 'react-redux';
import { selectFooter, selectNav } from '../store/store';
import styled from 'styled-components';

function Viewcomponent({ tagId }) {
  const searchParamsToken = new URLSearchParams(window.location.search);
  const token = searchParamsToken.get('Authorization');
  const searchParamsId = new URLSearchParams(window.location.search);
  const memberid = searchParamsId.get('Memberid');

  const dispatch = useDispatch();

  if (token && memberid) {
    localStorage.setItem('token', token);
    localStorage.setItem('memberid', memberid);
    dispatch(selectFooter(true));
    dispatch(selectNav(true));
  }

  return (
    <Questionscomponent>
      <Viewquestion>
        <Questions tagId={tagId} />
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
