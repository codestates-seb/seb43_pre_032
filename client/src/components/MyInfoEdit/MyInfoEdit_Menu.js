import styled from 'styled-components';
import { menudata } from './MyInfoEdit_SideBar';
import { Fragment } from 'react';

function MyInfoEdit_Menu() {
  return (
    <>
      <MenuContainer>
        <h4>Navigation</h4>
        <p>view all settings pages</p>
        <select>
          {menudata.map((menu, index) => {
            return (
              <Fragment key={index}>
                <option disabled key={`${index}-disabled`}>
                  {menu.title}
                </option>
                {menu.subtitle.map((subtitle, idx) => {
                  return (
                    <>
                      <Fragment key={`${index}-${idx}`}>
                        {subtitle === 'Edit profile' ? (
                          <option selected>{subtitle}</option>
                        ) : (
                          <option>{subtitle}</option>
                        )}
                      </Fragment>
                    </>
                  );
                })}
              </Fragment>
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
