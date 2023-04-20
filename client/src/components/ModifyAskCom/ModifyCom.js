import styled from 'styled-components';

const ModifyContainer = styled.article`
  max-width: 660px;
  display: grid;
  grid-template-columns: 1fr;
  grid-row-gap: 20px;

  .preview {
    display: flex;
    align-items: center;
    min-height: 30px;
    border: 1px solid lightblue;
  }
`;

const TitleContainer = styled.section``;
const BodyContainer = styled.section`
  .modify-input-content {
    height: 200px;
  }
`;
const TagsContainer = styled.section``;
const EditContainer = styled.section``;

const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 200px;
  margin-top: 5px;
  .save {
    height: 35px;
    width: 75px;
    font-size: 13px;
    :hover {
      background-color: var(--signup-btn-after);
    }
  }
  .cancel {
    font-weight: 600;
    padding-top: 2px;
    padding-bottom: 2px;
    padding-left: 7px;
    padding-right: 7px;
    height: 30px;
    margin: 5px;
    font-size: 13px;
    border-radius: 3px;
    background-color: inherit;
    border: none;
    :hover {
      background-color: var(--login-btn-before);
    }
  }
`;

const ModifyCom = () => {
  return (
    <ModifyContainer>
      <TitleContainer>
        <div className="modify-content-title">Title</div>
        <input className="modify-input-content" />
      </TitleContainer>
      <BodyContainer>
        <div className="modify-content-title">Body</div>
        <input className="modify-input-content link-style-remove" />
      </BodyContainer>
      <div className="preview">Body 미리 보기</div>
      <TagsContainer>
        <div className="modify-content-title">Tags</div>
        <input className="modify-input-content" />
      </TagsContainer>
      <EditContainer>
        <div className="modify-content-title">Edit Summary</div>
        <input className="modify-input-content link-style-remove" />
      </EditContainer>
      <BtnContainer>
        <div className="save flex-center btn-blue-style">Save edits</div>
        <div className="cancel flex-center btn-skyblue-style">Cancel</div>
      </BtnContainer>
    </ModifyContainer>
  );
};
export default ModifyCom;
