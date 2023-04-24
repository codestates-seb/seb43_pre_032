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
const ModifyAskPage = () => {
  let dispatch = useDispatch();
  const a = useParams();
  console.log(a);
  useEffect(() => {
    dispatch(selectNav(false));
  }, []);
  return (
    <ModifyContainer>
      <ModifyCom></ModifyCom>
    </ModifyContainer>
  );
};
export default ModifyAskPage;
