// import { useEffect, useState } from 'react';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { data } from '../data/tagdata';
// import axios from 'axios';

function Taglists() {
  const [tagData, setTagData] = useState([]);
  useEffect(() => {
    setTagData(data.data);
  }, []);
  console.log(tagData);
  //   const [data, setData] = useState(null);
  //   useEffect(() => {
  //     axios
  //       .get(
  //         'https://190d-61-254-8-200.ngrok-free.app/tags?page=1&size=20&sortBy=tagId'
  //       )
  //       .then(function (response) {
  //         // 성공한 경우 실행
  //         setData(response.data);
  //         console.log(data);
  //         console.log('wkfwkrehdgksek');
  //       })
  //       .catch(function (error) {
  //         // 에러인 경우 실행
  //         console.log('왜...에러났지...?');
  //         console.log(error);
  //       });
  //   }, []);
  return (
    <>
      <TagsPage>
        <h1>Tags</h1>
        <p>
          A tag is a keyword or label that categorizes your question with other,
          similar questions. Using the right tags makes it easier for others to
          find and answer your question.
        </p>
        <p>Show all tag synonyms</p>
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
              <div>{tag.tagName}</div>
              <div>{tag.tagDescription}</div>
            </div>
          ))}
        </TagContainer>
      </TagsPage>
    </>
  );
}

const TagContainer = styled.div`
  color: black;
  max-width: 1050;
  display: grid;
  gap: 5px;
  grid-template-rows: auto;
  grid-template-columns: 1fr 1fr 1fr 1fr;
`;

const TagsPage = styled.div`
  padding: 24px;
  .searchbar--tab {
    display: flex;
    justify-content: space-between;
  }
`;

// const Tab = styled.div`
//   display: flex;
//   width: 180px;
//   height: 38px;
//   border: 1px solid gray;
//   border-radius: 5px;
//   justify-content: center;
//   align-items: center;
//   text-align: center;
//   cursor: pointer;
//   > div {
//     justify-content: center;
//     height: 38px;
//     line-height: 38px;
//     &:hover {
//       background-color: rgba(0, 0, 0, 0.05);
//     }
//     &:active {
//       box-shadow: 0 0 10px gray;
//     }
//   }
//   .Tab1 {
//     width: 70px;
//   }
//   .Tab2 {
//     width: 60px;
//     border: 1px solid gray;
//   }
//   .Tab3 {
//     width: 50px;
//   }
// `;

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
  width: 190px;
  height: 38px;
  border: 1px solid lightgray;
  border-radius: 5px;
  font-size: 13px;
  &:focus {
    outline: 3px outset skyblue;
  }
`;
export default Taglists;
