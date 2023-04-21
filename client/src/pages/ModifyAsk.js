import styled from 'styled-components';
import ModifyCom from '../components/ModifyAskCom/ModifyCom';
const ModifyContainer = styled.div`
  padding-left: 20px;
  padding-top: 30px;
`;
const ModifyAskPage = () => {
  return (
    <ModifyContainer>
      <ModifyCom></ModifyCom>
    </ModifyContainer>
  );
};
export default ModifyAskPage;
