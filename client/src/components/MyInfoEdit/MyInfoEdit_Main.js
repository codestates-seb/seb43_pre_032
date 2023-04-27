import styled from 'styled-components';
import MyInfoEdit_imgSelect from './MyInfoEdit_imgSelect';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTwitter, faGithub } from '@fortawesome/free-brands-svg-icons';
import { faLink } from '@fortawesome/free-solid-svg-icons';
import MyInfoEdit_Menu from './MyInfoEdit_Menu';
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function MyInfoEdit_Main({ memberId, topData, imgUrl }) {
  const token = localStorage.getItem('token');
  const navigate = useNavigate();
  const [name, setName] = useState(topData.name);
  const [title, setTitle] = useState(topData.myPageTitle || '');
  const [email, setEmail] = useState('');
  const [aboutme, setAboutme] = useState(topData.aboutMe || '');

  const handleChange = (e, func) => {
    func(e.target.value);
  };

  const handleSubmit = () => {
    axios
      .patch(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/mypage/${memberId}`,
        {
          meberId: memberId,
          name: name,
          email: email,
          myPageTitle: title,
          aboutMe: aboutme,
        },
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: token,
          },
        }
      )
      .then((response) => {
        console.log(response);
        navigate(`/users/${memberId}`);
      })
      .catch((error) => console.error(error));
  };
  return (
    <>
      <MainContainer>
        <MyInfoEdit_Menu></MyInfoEdit_Menu>
        <div className="maintitle">
          <h1>Edit your profile</h1>
        </div>
        <div className="subtitle">
          <h2>Public information</h2>
        </div>
        <InformationContainer className="informationContainer">
          <div>
            <MyInfoEdit_imgSelect
              membername={topData.name}
              memberId={memberId}
              imgUrl={imgUrl}
            ></MyInfoEdit_imgSelect>
          </div>
          <div className="simpleModifyContainer">
            <label htmlFor="display-name">Dispplay name</label>
            <input
              id="display-name"
              className="simpleModify"
              value={name}
              onChange={(e) => handleChange(e, setName)}
            ></input>
          </div>
          <div className="simpleModifyContainer">
            <label htmlFor="email">Email</label>
            <input
              id="email"
              onChange={(e) => handleChange(e, setEmail)}
              value={email}
              className="simpleModify"
            ></input>
          </div>
          <div className="simpleModifyContainer">
            <label htmlFor="title">Title</label>
            <input
              value={title}
              onChange={(e) => handleChange(e, setTitle)}
              id="title"
              className="simpleModify"
            ></input>
          </div>
          <div className="abouteditContainer">
            <label htmlFor="aboutme">About me</label>
            <input
              id="aboutme"
              className="aboutedit"
              onChange={(e) => handleChange(e, setAboutme)}
              value={aboutme}
            ></input>
          </div>
        </InformationContainer>
        <div className="subtitle">
          <h2>Links</h2>
        </div>
        <LinksContainer className="linksContainer">
          <div className="links">
            <label htmlFor="websitelink">Website link</label>
            <div className="linkInput">
              <FontAwesomeIcon className="icon" icon={faLink} />
              <input id="websitelink"></input>
            </div>
          </div>
          <div className="links">
            <label htmlFor="twitterlinkOrUsername">
              Twitter link or username
            </label>
            <div className="linkInput">
              <FontAwesomeIcon className="icon" icon={faTwitter} />
              <input id="twitterlinkOrUsername"></input>
            </div>
          </div>
          <div className="links">
            <label htmlFor="githublinkOrUsername">
              GitHub link or username
            </label>
            <div className="linkInput">
              <FontAwesomeIcon className="icon" icon={faGithub} />
              <input id="githublinkOrUsername"></input>
            </div>
          </div>
        </LinksContainer>
        <div className="subtitle">
          <h2>Private information</h2>
        </div>
        <PrivateinfoContainer>
          <div className="simpleModifyContainer">
            <label htmlFor="fullName">Full name</label>
            <input id="fullName" className="simpleModify"></input>
          </div>
        </PrivateinfoContainer>

        <div className="profileEdit-btns">
          <button className="profileEdit-btn" onClick={handleSubmit}>
            Save profile
          </button>
          <button className="cancel-btn">Cancel</button>
        </div>
      </MainContainer>
    </>
  );
}

export default MyInfoEdit_Main;

const MainContainer = styled.div`
  padding: 24px 16px;
  flex-grow: 1;
  margin: 0px;
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
    font-weight: 700;
    height: 20px;
    margin-bottom: 5px;
  }

  button {
    padding: 10px;
    border: none;
    cursor: pointer;
    margin: 2px;
    border-radius: 4px;
  }
  .profileEdit-btn {
    background-color: #0a95ff;
    color: white;
    margin: 0px 10px 0px 0px;
    :hover {
      background-color: #0074cc;
    }
  }
  .cancel-btn {
    color: #0a95ff;
    background-color: white;
    :hover {
      background-color: #f0f8ff;
    }
  }
  p {
    margin-top: -10px;
    color: red;
    font-size: 8px;
  }
  @media (max-width: 1050px) {
    .linksContainer {
      flex-direction: column;
    }
    .links {
      width: 444px;
    }
  }
  @media (max-width: 640px) {
  }
`;

const InformationContainer = styled.div`
  border: 1px solid rgba(0, 0, 0, 0.2);
  padding: 24px;
  margin: 0px 0px 48px;
  border-radius: 5px;
  div {
    display: flex;
    flex-direction: column;
    margin-bottom: 10px;
  }
  .simpleModifyContainer {
    height: 60px;
  }
  .simpleModify {
    width: 422px;
    height: 34px;
    padding: 0px 10px;
    border-radius: 5px;
  }
  .aboutedit {
    height: 250px;
    border-radius: 5px;
    padding: 10px;
    margin: 6px 0px;
  }
`;

const LinksContainer = styled(InformationContainer)`
  display: flex;
  justify-content: space-between;
  div {
    width: 100%;
    justify-content: center;
  }
  label {
    margin: 5px 10px;
  }
  input {
    margin: 0px 10px;
    height: 34px;
    border-radius: 5px;
    padding: 0px 0px 0px 32px;
  }
  .linkInput {
    display: flex;
    position: relative;
    flex-shrink: 1;
  }
  .icon {
    position: absolute;
    margin-top: -9px;
    top: 50%;
    left: 1.2em;
    width: 18px;
    height: 18px;
    color: hsl(210, 8%, 55%);
  }
`;

const PrivateinfoContainer = styled(InformationContainer)`
  div {
    display: flex;
    flex-direction: column;
  }
`;
