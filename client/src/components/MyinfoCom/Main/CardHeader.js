import { useState } from 'react';
import styled from 'styled-components';
const CardContainer = styled.div`
  .sub-title {
    font-size: 22px;
    padding: 5px;
  }
`;
const FilterBar = styled.div`
  height: 27px;
  border: 1px solid lightgray;
  border-radius: 5px;
`;
const Menu = styled.div`
  font-size: 12px;
  padding: 5px;
  border: 1px solid lightgray;

  :hover {
    background-color: var(--menu-hover-background);
  }
`;
const CardHeader = ({ title, isFilter, options = [] }) => {
  // 제목, 필터 유무, 옵션 값 전달 받음
  let [onClick, setOnClick] = useState(0);
  const onClickHandler = (idx) => {
    setOnClick(idx);
  };
  return (
    <CardContainer className="flex-row flex-space-between">
      <div className="sub-title">{title}</div>
      {isFilter ? (
        <FilterBar className="flex-row">
          {options.map((el, idx) => (
            <Menu
              onClick={() => onClickHandler(idx)}
              className={idx === onClick ? 'selected' : null}
              key={idx}
            >
              {el}
            </Menu>
          ))}
        </FilterBar>
      ) : null}
    </CardContainer>
  );
};
export default CardHeader;
