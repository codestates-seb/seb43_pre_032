import './App.css';
import Header from './components/Header/HeaderCom';
import styled from 'styled-components';

import Paging from './pages/RoutingPage';
import { Provider } from 'react-redux';
import store from './store/store';

function App() {
  return (
    <Provider store={store}>
      <div className="App">
        <Headersection>
          <Header />
        </Headersection>
        <Paging />
      </div>
    </Provider>
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
