import styled from 'styled-components';
import ModifyCom from '../components/ModifyAskCom/ModifyCom';
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
  useEffect(() => {
    dispatch(selectNav(false));
  }, []);
  return (
    <ModifyContainer>
      <ModifyCom asId={asId}></ModifyCom>
    </ModifyContainer>
  );
};
export default ModifyAnswer;
