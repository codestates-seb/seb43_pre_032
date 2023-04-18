import styled from 'styled-components';
import achieve from '../../assets/achieve.png';
import event from '../../assets/event.png';
import help from '../../assets/help.png';
import info from '../../assets/info.png';
export const LoginBtn = styled.div`
  height: 30px;
  border: 1px solid var(--login-btn-border);
  border-radius: 4px;
  background-color: var(--login-btn-before);
  padding-left: 3px;
  padding-right: 3px;
  color: var(--login-btn-border);
  margin: 5px;
  :hover {
    background-color: var(--login-btn-after);
  }
`;

export const SignInBtn = styled.div`
  height: 30px;
  font-weight: bold;
  width: 60px;
  border: 1px solid skyblue;
  border-radius: 4px;
  box-shadow: 0px 0.5px 1px#dadce0 inset;
  border: 1px solid skyblue;
  background-color: var(--signup-btn-before);
  padding-left: 7px;
  padding-right: 7px;
  color: white;
  :hover {
    background-color: var(--signup-btn-after);
  }
`;

const OtherContainer = styled.div`
  width: 230px;
  height: 100%;
`;
const Menu = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 100%;
  padding: 5px;
  :hover {
    background-color: var(--menu-hover-background);
  }
`;
const Profile = styled.div`
  padding-top: 7px;
  padding-bottom: 7px;
  padding-left: 4px;
  padding-right: 4px;
  background-color: brown;
  margin-right: 2px;
  border-radius: 4px;
  color: white;
  font-size: 10px;
`;
const OtherButtons = () => {
  const example = [{ data1: '1' }, { data2: '2' }];
  return (
    <OtherContainer className="flex-center">
      <Menu>
        <Profile>호재</Profile>
        {example.length}
      </Menu>
      <Menu>
        <img className="other-img-size" src={event} alt="이벤트" />
      </Menu>
      <Menu>
        <img className="other-img-size" src={achieve} alt="성취" />
      </Menu>
      <Menu>
        <img className="other-img-size" src={help} alt="도움" />
      </Menu>
      <Menu>
        <img className="other-img-size" src={info} alt="기타정보" />
      </Menu>
    </OtherContainer>
  );
};

export { OtherButtons };
