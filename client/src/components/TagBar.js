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
  z-index: 1;
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
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  background-color: white;
  border: 1px solid lightgray;
  position: absolute;
  top: 50px;
  @media screen and (max-width: 1300px) {
    grid-template-columns: 1fr 1fr;
  }
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
  const Ref = useRef(null);
  let [val, setVal] = useState('');

  const handleTagContainerClick = () => {
    Ref.current.focus();
  };
  const onChangeHandler = (e) => {
    setVal(e.target.value);
    setWord(e.target.value);
  };
  const clickHandler = (tag) => {
    setVal('');
    setSelected((pre) => [...pre, tag]);
  };
  const cancelHandler = (tag) => {
    setVal('');
    let newArr = selected.filter((el) => {
      return tag.tagName !== el.tagName;
    });
    setSelected(newArr);
  };
  return (
    <>
      <TagContainer onClick={handleTagContainerClick}>
        <ItemContainer className="flex-row flex-center">
          {selected.map((el, idx) => {
            return (
              <Select key={idx}>
                {el.tagName}{' '}
                <CancelBtn onClick={() => cancelHandler(el)}>X</CancelBtn>
              </Select>
            );
          })}
        </ItemContainer>
        <ItemContainer className="flex-row flex-center">
          <input
            value={val}
            onChange={onChangeHandler}
            className="searchtag"
            ref={Ref}
          />
        </ItemContainer>
      </TagContainer>
      {val === '' ? null : (
        <TagsContainer>
          {filtered.map((el, idx) => {
            return (
              <TagItem
                onClick={() => {
                  clickHandler(el);
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
