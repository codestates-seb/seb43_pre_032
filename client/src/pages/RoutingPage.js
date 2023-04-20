import Nav from '../components/Nav.js';
import Footer from '../components/Footer';
import styled from 'styled-components';
import { Routes, Route, Navigate } from 'react-router-dom';
import CreateAsk from './CreateAsk.js';
import { useSelector } from 'react-redux';
import MyinfoPage from './MyinfoPage.js';
import ModifyAskPage from './ModifyAsk.js';

const Paging = () => {
  let showNav = useSelector((state) => state.showCom.showNav); // showNav 가져옴
  let showFooter = useSelector((state) => state.showCom.showFooter);

  return (
    <>
      <Maingroup>
        <Maincontents>
          {showNav ? (
            <article className="side_menu">
              <div className="fixed_contents">
                <Nav />
              </div>
            </article>
          ) : null}

          <article className="main_view">
            {/* 페이지 경로 및 네이밍 확인 필요 */}
            <Routes>
              <Route path="/" element={<Navigate replace to="/signup" />} />

              <Route path="/users/3355" element={<MyinfoPage />} />
              <Route path="/question/ask" element={<CreateAsk />} />
              <Route path="/question/modify" element={<ModifyAskPage />} />
            </Routes>
          </article>
        </Maincontents>
      </Maingroup>
      {showFooter ? (
        <Footersection>
          <Footer />
        </Footersection>
      ) : null}
    </>
  );
};

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
export default Paging;