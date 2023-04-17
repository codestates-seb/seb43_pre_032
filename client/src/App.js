import './App.css';
// import Header from './components/HeaderCom';
import Footer from './components/Footer';
import Nav from './components/Nav';
import styled from 'styled-components';

function App() {
  return (
    <div className="App">
      <Headersection>
        <div className="Area">헤더및 서칭바</div>
      </Headersection>
      <Maincontents>
        <SideNavigation>
          <div className="Navbox">
            <Nav />
          </div>
        </SideNavigation>
        <Viewsection>메인 컨텐츠</Viewsection>
      </Maincontents>
      <Footersection>
        <Footer />
      </Footersection>
    </div>
  );
}

export default App;

//서칭바 - Header
const Headersection = styled.section`
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 50px;
  border-top: 3px solid hsl(27, 90%, 55%);
  background-color: hsl(210, 8%, 97.5%);
  box-shadow: 0px 0px 8px rgb(0, 0, 0, 0.2);
  box-sizing: border-box;
  .Area {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 1250px;
    height: 100%;
    border: 1px solid red;
    text-align: center;
  }
`;

///메인섹션 - Main
const Maincontents = styled.header`
  display: flex;
  min-height: 100vh;
  height: 200%;
  width: 100%;
  box-sizing: border-box;
`;

const SideNavigation = styled.article`
  width: 25vw;
  height: auto;
  border-right: 1px solid #ccc;

  .Navbox {
    width: 25vw;
    margin-top: 50px;
    position: fixed;
    display: flex;
  }
  @media (max-width: 600px) {
    display: none;
  }
  @media (max-width: 1250px) {
    width: 200px;
    .Navbox {
      width: 200px;
    }
  }
`;

const Viewsection = styled.section`
  margin-top: 50px;
  height: 200vh;
`;

//푸터 - Footer
const Footersection = styled.footer`
  z-index: 99;
  width: 100%;
  height: 100px;
  background-color: hsl(210, 8%, 15%);
`;
