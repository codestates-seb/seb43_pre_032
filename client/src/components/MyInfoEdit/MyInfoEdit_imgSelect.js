import styled from 'styled-components';
import { useState } from 'react';
import axios from 'axios';

function MyInfoEdit_imgSelect({ membername, memberId }) {
  const [, setSelectedFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);
  const formData = new FormData();
  const fileSelectedHandler = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
    const reader = new FileReader();
    reader.onload = () => {
      setPreviewUrl(reader.result);
    };
    reader.readAsDataURL(file);
    formData.append('file', file);
  };
  const handleImg = () => {
    axios
      .post(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/${memberId}`,
        formData
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  return (
    <>
      <ImgSelectContainer>
        <label htmlFor="imgSelect">Profile image</label>
        <input
          onChange={fileSelectedHandler}
          className="img-select-input"
          id="imgSelect"
          type="file"
          accept="image/*"
        />

        {previewUrl ? (
          <img className="selectedImg" src={previewUrl} alt="Selected file" />
        ) : (
          <div className="defalutProfile">
            {membername ? membername.slice(1) : ''}
          </div>
        )}
        <label
          className="img-select-label img-select-label-btn"
          htmlFor="imgSelect"
        >
          Change picture
        </label>
        <button className="imgSubmit" onClick={handleImg}>
          image Submit
        </button>
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
  .imgSubmit {
    margin-top: -20px;
    width: 164px;
  }
`;
