import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { faStackExchange } from '@fortawesome/free-brands-svg-icons';

const ProfileContainer = styled.section`
  padding: 10px;
  .top-items {
    position: relative;
    display: flex;
    flex-direction: row;
  }
`;
const Profile = styled.div`
  padding-top: 7px;
  padding-bottom: 7px;
  padding-left: 4px;
  padding-right: 4px;
  background-color: brown;
  margin-right: 2px;
  border-radius: 4px;
  color: white;
  font-size: 55px;
  width: 120px;
  height: 120px;
  margin-top: 10px;
`;
const ProfileBtnContainer = styled.div`
  position: absolute;
  top: 10px;
  right: 10px;
  height: 30px;
  width: 215px;
  font-size: 12px;
  color: gray;
  .hover {
    :hover {
      background-color: var(--menu-hover-background);
    }
  }

  .edit {
    height: 100%;
    padding-left: 5px;
    padding-right: 5px;
    border: 1px solid gray;
    border-radius: 4px;
  }
  .network {
    padding-left: 5px;
    padding-right: 5px;
    height: 100%;
    border: 1px solid gray;
    border-radius: 4px;
  }
`;
const ItemContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  width: 500px;
  padding: 10px;
  margin: 10px;
  .display-name {
    font-size: 35px;
    color: black;
  }
  .time-history-container {
    display: flex;
    align-items: center;
    height: 30px;
    width: 100%;
    .info {
      margin: 5px;
    }
  }
`;
const MyPageTop = () => {
  const history = {
    signupDate: '8',
    lastseenDate: '7',
    visitedDate: '6',
  };

  return (
    <ProfileContainer>
      <div className="top-items">
        <Profile className="flex-center">호재</Profile>
        <ItemContainer>
          <div className="display-name">정호재</div>
          <div className="time-history-container">
            <div className="info">{`🎂 Member for ${history.signupDate} days`}</div>
            <div className="info">{`🕔 Last seen this ${history.lastseenDate} days`}</div>
            <div className="info">{`🗓️ Visited ${history.visitedDate} days`}</div>
          </div>
        </ItemContainer>
        <ProfileBtnContainer className="flex-space-between">
          <div className="hover edit flex-center">
            <div className="margin-right-3px">
              <FontAwesomeIcon className=" margin-right-3px" icon={faPencil} />
            </div>
            Edit profile
          </div>
          <div className="hover network flex-center">
            <div className="margin-right-3px">
              <FontAwesomeIcon
                className="margin-right-3px"
                icon={faStackExchange}
              />
            </div>
            Network profile
          </div>
        </ProfileBtnContainer>
      </div>
    </ProfileContainer>
  );
};
export default MyPageTop;
