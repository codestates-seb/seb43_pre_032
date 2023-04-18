import styled from 'styled-components';
const SideBarContainer = styled.div`
  width: 150px;
  @media (max-width: 1265px) {
    display: none;
  }
`;
const Menu = styled.div`
  display: flex;
  align-items: center;
  height: 30px;
  width: 70%;
  border-radius: 15px;
  padding-left: 10px;
  font-size: 13px;
  :hover {
    background-color: var(--menu-hover-background);
  }
`;

const Myinfo_SideBar = () => {
  let options = [
    'Summary',
    'Answers',
    'Questions',
    'Tags',
    'Articles',
    'Badges',
    'Following',
    'Bounties',
    'Reputation',
    'All actions',
    'Responses',
    'Votes',
  ];
  return (
    <SideBarContainer>
      {options.map((el, idx) => (
        <Menu key={idx}>{el}</Menu>
      ))}
    </SideBarContainer>
  );
};
export default Myinfo_SideBar;
