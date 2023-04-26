import styled from 'styled-components';

const MenuContainer = styled.div`
  width: 278px;
`;
const Menu = styled.div`
  padding: 5px;
  border-radius: 15px;
  :hover {
    background-color: var(--menu-hover-background);
  }
`;

const NavigateBar = () => {
  return (
    <MenuContainer className="flex-space-between">
      <Menu>Profile</Menu>
      <Menu>Activity</Menu>
      <Menu>Saves</Menu>
      <Menu>Settings</Menu>
    </MenuContainer>
  );
};
export default NavigateBar;
