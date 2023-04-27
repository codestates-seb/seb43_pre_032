import { useState } from 'react';
import styled from 'styled-components';

function TagSearch({ onSearch }) {
  const [query, setQuery] = useState(''); // 입력값 상태

  const handleChange = () => {
    onSearch(query);
  };
  const handlekeydown = (event) => {
    if (event.key === 'Enter') {
      handleChange();
      console.log(query);
    }
  };

  return (
    <TagSearchMain>
      <TagSerachBar
        placeholder="  🔍 Filter by tag name"
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        onKeyDown={handlekeydown}
      />{' '}
      <button onClick={handleChange} className="searchbtn">
        search
      </button>
    </TagSearchMain>
  );
}

const TagSearchMain = styled.div`
  .searchbtn {
    height: 30px;
    margin: 2px;
    border-radius: 4px;
    padding: 0px 12px;
    border: none;
    color: #0a95ff;
    background-color: white;
    :hover {
      background-color: #f0f8ff;
    }
  }
`;
export const TagSerachBar = styled.input`
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
