import { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { TagSerachBar } from '../components/Tags/TagSearch';
import { Link } from 'react-router-dom';
import { TagContainer, TagsPage } from './Taglist';

function Userlist() {
  const [userData, setUserData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members?page=1&size=10&sortBy=memberId`
      )
      .then((response) => {
        setUserData(response.data.data);
      })
      .catch((error) => console.error(error));
  }, []);

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
    setFilteredData(
      userData.filter((user) =>
        user.name.toLowerCase().includes(e.target.value.toLowerCase())
      )
    );
  };

  return (
    <>
      <UserPage>
        <h1>Users</h1>
        <div className="searchbar--tab">
          <UserSearch
            placeholder="  🔍 Filter by User"
            className="taglist-SerchBar"
            onChange={handleSearch}
            value={searchTerm}
          ></UserSearch>
        </div>
        <UserContainer>
          {filteredData.length > 0
            ? filteredData.map((user, index) => (
                <div className="singleUser" key={`${index}-${user.memberId}`}>
                  <div className="singleTagNamePosition">
                    <Link
                      to={'/users/' + user.memberId}
                      className="singleUserNameBtn"
                    >
                      <span>{user.name}</span>
                    </Link>
                  </div>
                  <div className="singleTagTagDescription reputation">
                    reputation: {user.reputation}
                  </div>
                </div>
              ))
            : userData.map((user, index) => (
                <div className="singleUser" key={`${index}-${user.memberId}`}>
                  <div className="singleTagNamePosition">
                    <Link
                      to={'/users/' + user.memberId}
                      className="singleUserNameBtn"
                    >
                      <span>{user.name}</span>
                    </Link>
                  </div>
                  <div className="singleTagTagDescription reputation">
                    reputation: {user.reputation}
                  </div>
                </div>
              ))}
        </UserContainer>
      </UserPage>
    </>
  );
}

export const UserContainer = styled(TagContainer)`
  .singleUserNameBtn {
    text-decoration: none;
    border: none;
    padding: 4.8px 0px;
    font-size: 15px;
    font-weight: 600;
    color: #0a95ff;
    cursor: pointer;
    &:hover {
      opacity: 0.8;
    }
  }
  .singleUser {
    display: grid;
    grid-template-rows: 1fr 1fr;
    border: 1px solid #d6d9dc;
    padding: 12px;
    border-radius: 5px;
  }
  .reputation {
    color: rgba(0, 0, 0, 0.6);
  }
`;

const UserPage = styled(TagsPage)``;
export default Userlist;

const UserSearch = styled(TagSerachBar)`
  padding: 0px 10px;
`;
