import { configureStore, createSlice } from '@reduxjs/toolkit';

let showCom = createSlice({
  // Nav, Footer 컴포넌트 표현 및 제거 관리를 위한 store,  true시 표현 false시 제거
  name: 'showCom',
  initialState: { showNav: true, showFooter: true },
  reducers: {
    selectNav(state, action) {
      return { ...state, showNav: action.payload };
    },
    selectFooter(state, action) {
      return { ...state, showFooter: action.payload };
    },
  },
});

let isLogin = createSlice({
  //로그인 상태 관리를 위한 store, true시 로그인 상태 false시 로그아웃 상태
  name: 'isLogin',
  initialState: false,
  reducers: {
    setLogin() {
      return true;
    },
    setLogout() {
      return false;
    },
  },
});

let SearchData = createSlice({
  //검색어 관리를 위한 store
  name: 'SearchData',
  initialState: { data: [] },
  reducers: {
    setData(state, action) {
      console.log(action.payload);
      state.data = [...action.payload];
    },
  },
});

//reducer export
export let { selectFooter, selectNav } = showCom.actions;
export let { setLogin, setLogout } = isLogin.actions;
export let { setData } = SearchData.actions;

export default configureStore({
  reducer: {
    showCom: showCom.reducer,
    isLogin: isLogin.reducer,
    SearchData: SearchData.reducer,
  },
});
