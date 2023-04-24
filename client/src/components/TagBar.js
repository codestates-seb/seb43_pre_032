import { useState, useRef } from 'react';
import styled from 'styled-components';

const TagContainer = styled.div`
  height: 50px;
  border: 1px solid lightgray;
  border-radius: 3px;
  max-width: 100%;
  padding-left: 10px;
  padding-right: 10px;
  display: flex;
  align-items: center;
  .searchtag {
    height: 50%;
    border: gray;
    background-color: inherit;
  }
  gap: 10px;
`;

const ItemContainer = styled.div`
  gap: 10px;

  height: 100%;
`;

const Select = styled.div`
  padding: 5px;
  background-color: skyblue;
  border-radius: 5px;
  font-size: 14px;
`;
const TagsContainer = styled.div`
  width: 900px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  background-color: white;
  border: 1px solid lightgray;
  position: absolute;
  left: 100px;
  top: 658px;
`;
const TagItem = styled.div`
  padding: 5px;
  background-color: white;
  .title {
    margin-bottom: 20px;
    background-color: skyblue;
    padding: 3px;
    border-radius: 3px;
  }
  .description {
    margin: 5px;
    margin-top: 10px;
  }
`;

const CancelBtn = styled.span`
  color: white;
  font-weight: bolder;
`;

const TagBar = ({ setWord, selected, filtered, setSelected }) => {
  const inputRef = useRef(null);
  let [val, setVal] = useState('');
  const handleTagContainerClick = () => {
    inputRef.current.focus();
  };
  const onChangeHandler = (e) => {
    setVal(e.target.value);
    setWord(e.target.value);
  };
  const clickHandler = (title) => {
    setVal('');
    setSelected((pre) => [...pre, title]);
  };
  const cancelHandler = (title) => {
    setVal('');
    // console.log(title);
    let newArr = selected.filter((el) => {
      return title !== el;
    });
    console.log(newArr);
    setSelected(newArr);
  };

  return (
    <>
      <TagContainer onClick={handleTagContainerClick}>
        <ItemContainer className="flex-row flex-center">
          {selected.map((el, idx) => {
            return (
              <Select key={idx}>
                {el} <CancelBtn onClick={() => cancelHandler(el)}>X</CancelBtn>
              </Select>
            );
          })}
        </ItemContainer>
        <ItemContainer className="flex-row flex-center">
          <input
            value={val}
            onChange={onChangeHandler}
            className="searchtag"
            ref={inputRef}
          />
        </ItemContainer>
      </TagContainer>
      {val === '' ? null : (
        <TagsContainer>
          {filtered.map((el, idx) => {
            return (
              <TagItem
                onClick={() => {
                  clickHandler(el.tagName);
                }}
                key={idx}
              >
                <span className="title">{el.tagName}</span>
                <div className="description">{el.tagDescription}</div>
              </TagItem>
            );
          })}
        </TagsContainer>
      )}
    </>
  );
};

export default TagBar;
