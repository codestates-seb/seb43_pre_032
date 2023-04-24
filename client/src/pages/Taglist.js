import { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import InfiniteScroll from '../components/InfiniteScroll';
import TagSearch from '../components/Tags/TagSearch';
import { Link } from 'react-router-dom';

function Taglist() {
  const [tagData, setTagData] = useState([]);
  const [page, setPage] = useState(1);
  const [searchQuery, setSearchQuery] = useState('');
  let url = searchQuery
    ? `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/tags?page=1&keyword=${searchQuery}&tab=popular`
    : `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/tags?page=${page}&tab=popular`;

  useEffect(() => {
    axios
      .get(url, {
        headers: {
          'ngrok-skip-browser-warning': '69420',
        },
      })
      .then(function (response) {
        // 성공한 경우 실행
        if (searchQuery) {
          setTagData(response.data.data);
        } else {
          setTagData((prevData) => [...prevData, ...response.data.data]);
        }
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, [page, searchQuery]);

  const handleLoadMore = () => {
    setPage((prevPage) => prevPage + 1);
  };

  const handleSearch = (search) => {
    setSearchQuery(search);
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
          <TagSearch
            className="taglist-SerchBar"
            onSearch={handleSearch}
          ></TagSearch>

          <TagsTab>
            <button className="tab">Popular</button>
            <button className="tab">Name</button>
            <button className="tab">New</button>
          </TagsTab>
        </div>
        <TagContainer>
          {tagData.map((tag, index) => (
            <div className="singleTag" key={`${index}-${tag.tagId}`}>
              <div className="singleTagNamePosition">
                <Link to={'/tags/' + tag.tagId} className="singleTagNameBtn">
                  <span>{tag.tagName}</span>
                </Link>
              </div>
              <div className="singleTagTagDescription">
                {tag.tagDescription}
              </div>
              <div className="tag-questions">
                <div className="tag-questions-num">
                  {tag.questions + ' questions'}
                </div>
              </div>
            </div>
          ))}
          <InfiniteScroll onLoadMore={handleLoadMore} />
        </TagContainer>
      </TagsPage>
    </>
  );
}

export const TagContainer = styled.div`
  color: black;
  display: grid;
  max-width: 1050px;
  gap: 10px;
  grid-template-rows: repeat(auto, 1fr);
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
    margin-top: 5px;
  }
  .tag-questions {
    font-size: 12px;
    color: #b9bec3;
  }
  .tag-questions-num {
    margin-top: 5px;
    margin-bottom: -5px;
  }
  .tag-questions-word {
    margin: 0px;
  }
  .singleTagNameBtn {
    text-decoration: none;
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
    grid-template-rows: 0.7fr 2fr 0.5fr;
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
  .tab {
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

export default Taglist;
