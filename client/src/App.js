import './App.css';
import Header from './components/Header/HeaderCom';
import Footer from './components/Footer';
import Nav from './components/Nav';
import styled from 'styled-components';
import Viewcomponent from './components/question/Viewcomponent.jsx';

function App() {
  return (
    <div className="App">
      <Headersection>
        <Header />
      </Headersection>
      <Maingroup>
        <Maincontents>
          <article className="side_menu">
            <div className="fixed_contents">
              <Nav />
            </div>
          </article>
          <article className="main_view">
            <Viewcomponent />
          </article>
        </Maincontents>
      </Maingroup>
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
  z-index: 99;
  .Area {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 1250px;
    height: 100%;
    text-align: center;
  }
`;

///메인섹션 - Main
const Maingroup = styled.section`
  position: relative;
  width: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
`;

const Maincontents = styled.div`
  position: relative;
  display: flex;
  margin-top: 50px;
  flex-direction: row;
  width: 1250px;
  .side_menu {
    min-width: 165px;
    height: 100%;
    border-right: 1px solid #ccc;
  }
  .fixed_contents {
    position: fixed;
  }
  .main_view {
    position: relative;
    width: 100%;
    min-height: 3000px;
  }
  @media (max-width: 800px) {
    .side_menu {
      display: none;
    }
  }
`;

//푸터 - Footer
const Footersection = styled.footer`
  z-index: 99;
  position: relative;
  width: 100%;
  height: 100px;
  background-color: hsl(210, 8%, 15%);
`;
