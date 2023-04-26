import styled from 'styled-components';
import { menudata } from './MyInfoEdit_SideBar';

function MyInfoEdit_Menu() {
  return (
    <>
      <MenuContainer>
        <h4>Navigation</h4>
        <p>view all settings pages</p>
        <select>
          {menudata.map((menu, index) => {
            return (
              <optgroup key={`${index}-menu`} label={menu.title}>
                {menu.subtitle.map((subtitle, idx) => {
                  return (
                    <option
                      key={`${index}-${idx}`}
                      value={subtitle}
                      defaultValue={subtitle === 'Edit profile'}
                    >
                      {subtitle}
                    </option>
                  );
                })}
              </optgroup>
            );
          })}
        </select>
      </MenuContainer>
    </>
  );
}

const MenuContainer = styled.div`
  margin-bottom: 10px;
  h4 {
    margin: 5px 0px;
  }
  p {
    margin: 5px 0px;
    font-size: 12px;
  }
  select {
    width: 100%;
    padding: 9px;
    border-radius: 5px;
    border: 1px solid rgba(0, 0, 0, 0.2);
  }
  @media (min-width: 1050px) {
    display: none;
  }
`;

export default MyInfoEdit_Menu;
