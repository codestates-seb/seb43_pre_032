import styled from 'styled-components';
import MyInfoEdit_imgSelect from './MyInfoEdit_imgSelect';

function MyInfoEdit_Main() {
  return (
    <>
      <MainContainer>
        <div className="maintitle">
          <h1>Edit your profile</h1>
        </div>
        <div className="subtitle">
          <h2>Public information</h2>
        </div>
        <InformationContainer>
          <div>
            <h3>Profile image</h3>
            <MyInfoEdit_imgSelect></MyInfoEdit_imgSelect>
          </div>
          <div className="simpleModifyContainer">
            <label htmlFor="display-name">Dispplay name</label>
            <input id="display-name" className="simpleModify"></input>
          </div>
          <div className="simpleModifyContainer">
            <h3>Location</h3>
            <input className="simpleModify"></input>
          </div>
          <div className="simpleModifyContainer">
            <h3>Title</h3>
            <input className="simpleModify"></input>
          </div>
          <div>
            <h3>About me</h3>
            <textarea></textarea>
          </div>
        </InformationContainer>
      </MainContainer>
    </>
  );
}

export default MyInfoEdit_Main;

const MainContainer = styled.div`
  width: 100%;
  h1 {
    font-size: 27px;
    font-weight: 500;
    margin: 0px 0px 24px;
    padding: 0px 0px 16px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  }
  h2 {
    font-size: 21px;
    font-weight: 500;
    margin: 0px 0px 8px;
  }
  label {
    font-size: 15px;
    height: 20px;
    margin-bottom: 5px;
  }
`;

const InformationContainer = styled.div`
  border: 1px solid rgba(0, 0, 0, 0.2);
  padding: 24px;
  margin: 0px 0px 48px;
  border-radius: 5px;
  .simpleModifyContainer {
    height: 60px;
    margin-bottom: 10px;
  }
  .simpleModify {
    width: 422px;
    height: 34px;
    border-radius: 5px;
    padding: 0px 10px;
  }
`;
