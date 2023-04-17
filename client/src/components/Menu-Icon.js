import styled from 'styled-components';

const IconContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 1rem;
`;
const BoldLine = styled.div`
  height: 2.7px;
  width: 1.3rem;
  background-color: black;
  :hover {
    transform: rotate(-30deg);
  }
`;
const ThinLine = styled.div`
  height: 1px;
  width: 1.3rem;
  background-color: black;
`;

const MenuIcon = () => {
  return (
    <IconContainer>
      <BoldLine deg="30"></BoldLine>
      <ThinLine></ThinLine>
      <BoldLine deg="30"></BoldLine>
    </IconContainer>
  );
};
export default MenuIcon;
