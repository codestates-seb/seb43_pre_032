import styled from 'styled-components';
import Sidebanner from '../components/question/Sidebanner.jsx';
import DetailTitle from '../components/DetailQuestion/DetailTitle';
import DetailContent from '../components/DetailQuestion/DetailContent';
function DetailQuestion() {
  return (
    <DetailSection>
      <DetailTitle />
      <ContentsGroup>
        <DetailContent />
        <Sidebanner></Sidebanner>
      </ContentsGroup>
    </DetailSection>
  );
}

export default DetailQuestion;

const DetailSection = styled.section`
  display: flex;
  flex-direction: column;
`;

const ContentsGroup = styled.section`
  display: flex;
`;
