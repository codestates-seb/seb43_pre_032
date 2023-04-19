import { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import InfiniteScroll from '../components/InfiniteScroll';

function Taglists() {
  const [tagData, setTagData] = useState([]);
  const [page, setPage] = useState(1);
  useEffect(() => {
    axios
      .get(
        `https://3596-61-254-8-200.ngrok-free.app/tags?page=${page}&size=40&sortBy=tagId`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
        }
      )
      .then(function (response) {
        // 성공한 경우 실행
        setTagData((prevData) => [...prevData, ...response.data.data]);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, [page]);

  const handleLoadMore = () => {
    setPage((prevPage) => prevPage + 1);
  };
  return (
    <>
      <TagsPage>
        <h1>Tags</h1>
        <p className="tags-title-description">
          A tag is a keyword or label that categorizes your question with other,
          similar questions. Using the right tags makes it easier for others to
          find and answer your question.
        </p>
        <p className="showAllTag">Show all tag synonyms</p>
        <div className="searchbar--tab">
          <TagSerachBar
            className="taglist-SerchBar"
            placeholder="🔍 Filter by tag name"
          />
          <TagsTab>
            <button>Popular</button>
            <button>Name</button>
            <button>New</button>
          </TagsTab>
        </div>
        <TagContainer>
          {tagData.map((tag) => (
            <div className="singleTag" key={tag.tagId}>
              <div className="singleTagNamePosition">
                <button className="singleTagNameBtn">
                  <span>{tag.tagName}</span>
                </button>
              </div>
              <div className="singleTagTagDescription">
                {tag.tagDescription}
              </div>
            </div>
          ))}
          <InfiniteScroll onLoadMore={handleLoadMore} />
        </TagContainer>
      </TagsPage>
    </>
  );
}

const TagContainer = styled.div`
  color: black;
  display: grid;
  max-width: 1050px;
  gap: 10px;
  grid-template-rows: repeat(autofit, 1fr);
  grid-template-columns: repeat(4, minmax(250px, 1fr));
  font-size: 13px;
  @media (max-width: 1050px) {
    grid-template-columns: repeat(3, minmax(250px, 1fr));
  }
  @media (max-width: 750px) {
    grid-template-columns: repeat(2, 1fr);
  }
  @media (max-width: 600px) {
    grid-template-columns: repeat(1, 1fr);
  }
  .singleTagNamePosition {
    height: 28px;
    margin-bottom: 12px;
  }
  .singleTagNameBtn {
    background-color: hsl(205, 54%, 88%);
    border: none;
    margin: 2px 2px 2px 0px;
    padding: 4.8px 6px;
    border-radius: 3px;
    color: #60849e;
    cursor: pointer;
    &:hover {
      opacity: 0.8;
    }
  }
  .singleTag {
    display: grid;
    grid-template-rows: 0.5fr 2fr 0.5fr;
    border: 1px solid #d6d9dc;
    padding: 12px;
    border-radius: 5px;
  }
  .singleTagTagDescription {
    overflow: none;
  }
`;

const TagsPage = styled.div`
  padding: 24px;
  max-width: 1050;
  h1 {
    font-weight: 500;
    font-size: 1.7rem;
    margin: 16px 0px;
  }
  p {
    font-size: 15px;
    margin-bottom: 16px;
  }
  .searchbar--tab {
    display: flex;
    justify-content: space-between;
  }
  .showAllTag {
    color: #0995ff;
    &:hover {
      color: #43a4ff;
      cursor: pointer;
    }
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
export default Taglists;
