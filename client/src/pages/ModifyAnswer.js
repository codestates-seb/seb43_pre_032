import styled from 'styled-components';
import ModifyAnswerCom from '../components/ModifyAskCom/ModifyAnswerCom';
import { useDispatch } from 'react-redux';
import { selectNav } from '../store/store';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
const ModifyContainer = styled.div`
  padding-left: 20px;
  padding-top: 30px;
`;
const ModifyAnswer = () => {
  let dispatch = useDispatch();
  const asId = useParams();
  console.log(`asId 가 출력됩니다 : ${asId.asId}`);
  useEffect(() => {
    dispatch(selectNav(false));
  }, []);
  return (
    <ModifyContainer>
      <ModifyAnswerCom asId={asId}></ModifyAnswerCom>
    </ModifyContainer>
  );
};
export default ModifyAnswer;
