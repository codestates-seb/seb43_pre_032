import styled from 'styled-components';
import { faPenFancy } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  Sidebanners,
  Bannercomponent,
  Bannercontents,
  Bannertitle,
} from '../question/Sidebanner.jsx';

const HelpContainer = styled.section`
  position: absolute;
  right: -50%;
  width: 300px;
  .title {
    background-color: var(--menu-hover-background);
    font-size: 15px;
    font-weight: 500;
  }
  .white {
    background-color: white;
    box-shadow: none;
    border-top: none;
  }
  .flex-col {
    margin-left: 15px;
  }
  .list {
    margin-bottom: 15px;
  }
  @media screen and (max-width: 1100px) {
    width: 100%;
    position: initial;
    .resize {
      width: 100%;
      padding: 0px;
      margin-bottom: 30px;
    }
  }
`;

const HelpItem = ({ title, help }) => {
  return (
    <HelpContainer>
      <Bannercomponent className="resize">
        <Sidebanners className="white">
          <Bannertitle className="title">{title}</Bannertitle>
          <Bannercontents>
            <FontAwesomeIcon icon={faPenFancy} size="2xl" />
            <ul className="flex-col">
              {help.map((el, idx) => {
                return (
                  <li className="list" key={idx}>
                    {el}
                  </li>
                );
              })}
            </ul>
          </Bannercontents>
        </Sidebanners>
      </Bannercomponent>
    </HelpContainer>
  );
};
export default HelpItem;
