import { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { TagContainer } from '../../pages/Taglist';
import { Link } from 'react-router-dom';

function TagSearch({ setIsSearch }) {
  const [query, setQuery] = useState(''); // 입력값 상태
  const [results, setResults] = useState([]); // 검색 결과 상태
  const [filteredResults, setFilteredResults] = useState([]); //검색 결과

  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/tags?page=1&size=3276&sortBy=tagId`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (response) {
        // 성공한 경우 실행
        setResults(response.data.data);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, []);

  const handleChange = (event) => {
    const value = event.target.value;
    setQuery(value);
    let result = results.filter((tag) =>
      tag.tagName.includes(value.toLowerCase())
    );
    setFilteredResults(result);
    if (value === '') setIsSearch(true);
    else setIsSearch(false);

    // 검색 버튼 또는 엔터 키 입력 시 실행되는 함수
    // 검색 결과를 상태로 관리
  };

  return (
    <TagSearchMain>
      <div className="searchbar-tab">
        <TagSerachBar
          placeholder="  🔍 Filter by tag name"
          type="text"
          value={query}
          onChange={handleChange}
        />{' '}
        <TagsTab>
          <button>Popular</button>
          <button>Name</button>
          <button>New</button>
        </TagsTab>
      </div>
      <TagContainer className="tagcontainer-search">
        {query !== ''
          ? filteredResults.map((tag) => (
              <div className="sigleTag-search singleTag " key={tag.tagId}>
                <div className="singleTagNamePosition">
                  <Link to={'/tags/' + tag.tagId} className="singleTagNameBtn">
                    <span>{tag.tagName}</span>
                  </Link>
                </div>
                <div className="singleTagTagDescription">
                  {tag.tagDescription}
                </div>
              </div>
            ))
          : null}
      </TagContainer>
    </TagSearchMain>
  );
}

const TagSearchMain = styled.div`
  .searchbar-tab {
    display: flex;
    justify-content: space-between;
  }
  .tagcontainer-search {
    grid-template-columns: repeat(3, minmax(323px, 1fr));
  }

  .sigleTag-search {
    grid-template-rows: 0.5fr 1fr;
  }
`;
const TagSerachBar = styled.input`
  margin: 10px 0px;
  width: 190px;
  height: 38px;
  border: 1px solid lightgray;
  border-radius: 5px;
  font-size: 13px;
  &:focus {
    outline-style: outset;
    outline-width: 3px;
    outline-color: #ddeaf7;
  }
`;

const TagsTab = styled.div`
  display: flex;
  overflow: hidden;
  height: 35px;
  border: 1px solid hsl(210, 8%, 65%);
  border-radius: 3px;
  button {
    background: rgb(255, 255, 255);
    border: none;
    color: #6b6f73;
    border-left: 1px solid hsl(210, 8%, 65%);
    padding: 0px 10px;
  }
  button:nth-child(1) {
    border: none;
  }
  button:hover {
    background: #f5f5f5;
  }
  button:active {
    box-shadow: 0 0 10px gray;
  }
  button > span {
    padding: 0px 5px;
    margin-left: 3px;
    border-radius: 5px;
    color: #fff;
    background: hsl(206, 100%, 40%);
  }
`;

export default TagSearch;
