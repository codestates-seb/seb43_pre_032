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
        <div className="media-fade">
          <Sidebanner></Sidebanner>
        </div>
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
  @media (max-width: 1000px) {
    .media-fade {
      display: none;
    }
  }
`;
