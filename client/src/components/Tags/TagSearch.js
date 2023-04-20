import { useState, useEffect } from 'react';
import axios from 'axios';
import styled from 'styled-components';

function TagSearch() {
  const [query, setQuery] = useState(''); // 입력값 상태
  const [results, setResults] = useState([]); // 검색 결과 상태
  const [filteredResults, setFilteredResults] = useState([]);

  useEffect(() => {
    axios
      .get(
        `https://8625-61-254-8-200.ngrok-free.app/tags?page=1&size=3276&sortBy=tagId`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (response) {
        // 성공한 경우 실행
        setResults(response.data.data);
        console.log(setResults);
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
    console.log(filteredResults);

    // 검색 버튼 또는 엔터 키 입력 시 실행되는 함수
    // 검색 결과를 상태로 관리
  };

  return (
    <div>
      <input type="text" value={query} onChange={handleChange} />{' '}
      {/* 검색어 입력 필드 */}
      {/* <ul>
        {results.map((result) => (
          <li key={result.id}>{result.title}</li> // 검색 결과를 리스트로 출력
        ))}
      </ul> */}
    </div>
  );
}

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

export default TagSearch;
