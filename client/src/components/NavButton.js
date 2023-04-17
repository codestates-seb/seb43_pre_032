import styled from 'styled-components';
import achieve from '../assets/achieve.png';
import event from '../assets/event.png';
import help from '../assets/help.png';
import info from '../assets/info.png';
export const LoginBtn = styled.div`
  height: 2.3rem;
  border: 1px solid var(--login-btn-border);
  border-radius: 0.2rem;
  background-color: var(--login-btn-before);
  padding-left: 0.7rem;
  padding-right: 0.7rem;
  color: var(--login-btn-border);
  margin: 0.3rem;
  :hover {
    background-color: var(--login-btn-after);
  }
`;

export const SignInBtn = styled.div`
  width: 4rem;
  height: 2.3rem;
  border: 1px solid skyblue;
  border-radius: 0.2rem;
  box-shadow: 0px 0.5px 1px#dadce0 inset;
  border: 1px solid skyblue;
  background-color: var(--signup-btn-before);
  padding-left: 0.7rem;
  padding-right: 0.7rem;
  color: white;
  :hover {
    background-color: var(--signup-btn-after);
  }
`;
const OtherContainer = styled.div`
  width: 20rem;
  height: 100%;
`;
const Menu = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 100%;
  padding: 0.3rem;
  margin: 0.5rem;
  :hover {
    background-color: var(--menu-hover-background);
  }
`;
const OtherButtons = () => {
  const example = [{ data1: '1' }, { data2: '2' }];
  return (
    <OtherContainer className="flex-center">
      <Menu className="pro-icon">
        <div className="profile">호재</div>
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
