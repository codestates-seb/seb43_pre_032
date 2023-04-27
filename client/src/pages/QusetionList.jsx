import Sidebanner from '../components/question/Sidebanner.jsx';
import Questions from '../components/question/Questions.jsx';
import { useDispatch } from 'react-redux';
import { selectFooter, selectNav } from '../store/store';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { useEffect } from 'react';

function Viewcomponent({ tagId }) {
  //oauth 인증 토큰&멤버 아이디 가져오기
  const searchParamsToken = new URLSearchParams(window.location.search);
  const token = searchParamsToken.get('Authorization');

  const searchParamsId = new URLSearchParams(window.location.search);
  const memberid = searchParamsId.get('MemberId');

  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    if (token && memberid) {
      localStorage.setItem('token', token);
      localStorage.setItem('memberid', memberid);
      dispatch(selectFooter(true));
      dispatch(selectNav(true));
      navigate('/question');
      window.location.reload();
    }
  }, []);

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
