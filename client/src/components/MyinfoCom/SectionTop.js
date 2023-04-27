import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { faStackExchange } from '@fortawesome/free-brands-svg-icons';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
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
    color: gray;
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
const UserTitle = styled.div`
  min-height: 40px;
  font-size: 20px;
  font-weight: bolder;
  color: gray;
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
function DateFormat(now) {
  // 날짜 형식 변환
  const isoDateString = now;
  const date = new Date(isoDateString);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const Formated = `${year}-${month}-${day}`;
  return Formated;
}
function Diff(now, at) {
  // 현재 시간과 입력 시간과의 차이
  const startDate = new Date(now);
  const endDate = new Date(at);
  const timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
  const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
  return diffDays + 1;
}
const MyPageTop = ({ topData }) => {
  let navigate = useNavigate();

  let now = DateFormat(new Date(), new Date()); // 현재시간
  let at = DateFormat(new Date(), topData.createAt); // 회원가입 시간
  let modifiedWhen = DateFormat(new Date(), topData.modifiedAt); // 회원정보 변경 시간
  // console.log(img);
  let [error, setError] = useState(true);

  const modifiedTime = Diff(now, modifiedWhen); // 정보수정후 얼마나 시간이 지났는지
  const since = Diff(now, at); // 생성후 얼마나 시간이 지났는지

  const history = {
    // 회원관련 시간 정보
    signupDate: since,
    modified: modifiedTime,
  };
  const clickEdit = () => {
    //변경 버튼 클릭 시 회원정보 변경 페이지로 이동
    navigate(`/users/edit/${topData.memberId}`);
  };
  return (
    <ProfileContainer>
      <div className="top-items">
        {error ? (
          <img
            style={{ width: '130px' }}
            src={topData.memberJpegUrl}
            alt="프로필"
            onError={() => {
              setError(false);
            }}
          ></img>
        ) : (
          <Profile className="flex-center">
            {topData.name ? topData.name.slice(topData.name.length - 2) : ''}
          </Profile>
        )}

        <ItemContainer>
          <div className="display-name">{topData.name}</div>
          <UserTitle className="flex-center">{topData.myPageTitle}</UserTitle>
          <div className="time-history-container">
            <div className="info">{`🎂 Member for ${history.signupDate} days`}</div>
            <div className="info">{`📝 Modified before ${
              history.modified - 1
            } days`}</div>
          </div>
        </ItemContainer>
        <ProfileBtnContainer className="flex-space-between">
          <button onClick={clickEdit} className="hover edit flex-center">
            <div className="margin-right-3px">
              <FontAwesomeIcon className=" margin-right-3px" icon={faPencil} />
            </div>
            Edit profile
          </button>
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
