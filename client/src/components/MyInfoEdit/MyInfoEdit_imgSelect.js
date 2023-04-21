import styled from 'styled-components';
import { useState } from 'react';

function MyInfoEdit_imgSelect() {
  const [, setSelectedFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);

  const fileSelectedHandler = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
    const reader = new FileReader();
    reader.onload = () => {
      setPreviewUrl(reader.result);
    };
    reader.readAsDataURL(file);
  };
  return (
    <>
      <ImgSelectContainer>
        <input
          onChange={fileSelectedHandler}
          className="img-select-input"
          id="imgSelect"
          type="file"
        />

        {previewUrl ? (
          <img className="selectedImg" src={previewUrl} alt="Selected file" />
        ) : (
          <div className="defalutProfile">호재</div>
        )}
        <label
          className="img-select-label img-select-label-btn"
          htmlFor="imgSelect"
        >
          Change picture
        </label>
      </ImgSelectContainer>
    </>
  );
}

export default MyInfoEdit_imgSelect;

const ImgSelectContainer = styled.div`
  display: flex;
  flex-direction: column;
  .selectedImg {
    width: 164px;
    height: 164px;
    border-radius: 5px;
  }
  .defalutProfile {
    width: 164px;
    height: 164px;
    color: white;
    background-color: indigo;
    text-align: center;
    line-height: 131px;
    font-size: 72px;
    border-radius: 5px;
  }
  .img-select-input {
    display: none;
  }
  .img-select-label-btn {
    position: relative;
    display: inline-block;
    text-align: center;
    line-height: 33px;
    width: 164px;
    height: 33px;
    top: -33px;
    border: none;
    background-color: #525960;
    color: white;
    cursor: pointer;
    border-radius: 0px 0px 5px 5px;
  }
`;
