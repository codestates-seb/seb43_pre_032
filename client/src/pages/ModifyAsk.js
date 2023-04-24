import styled from 'styled-components';
import ModifyCom from '../components/ModifyAskCom/ModifyCom';
import { useDispatch } from 'react-redux';
import { selectNav } from '../store/store';
import { useEffect } from 'react';
const ModifyContainer = styled.div`
  padding-left: 20px;
  padding-top: 30px;
`;
const ModifyAskPage = () => {
  let dispatch = useDispatch();
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
