import styled from 'styled-components';
import { useState } from 'react';
import axios from 'axios';

function MyInfoEdit_imgSelect({ membername, memberId, imgUrl }) {
  const [selectedFile, setSelectedFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);
  const formData = new FormData();
  const token = localStorage.getItem('token');
  let [error, setError] = useState(true);

  const fileSelectedHandler = (event) => {
    //이미지파일을 선택하면, 미리보기를 진행합니다.
    const file = event.target.files[0];
    setSelectedFile(file);
    const reader = new FileReader();
    reader.onload = () => {
      setPreviewUrl(reader.result);
    };
    reader.readAsDataURL(file);
  };
  const handleImg = () => {
    formData.append('file', selectedFile);
    axios
      .post(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/upload/${memberId}`,
        formData,
        {
          headers: {
            Authorization: token,
            'Content-Type': 'multipart/form-data',
          },
        }
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
        ) : error ? (
          <img
            className="initialImg"
            src={imgUrl}
            alt="initialUrl "
            onError={() => {
              setError(false);
            }}
          />
        ) : (
          <div className="defalutProfile">
            {membername ? membername.slice(-2) : ''}
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
  .initialImg {
    width: 164px;
    height: 164px;
  }
`;
