import { configureStore, createSlice } from '@reduxjs/toolkit';

let showCom = createSlice({
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

export let { selectFooter, selectNav } = showCom.actions;
export let { setLogin, setLogout } = isLogin.actions;

export default configureStore({
  reducer: {
    showCom: showCom.reducer,
    isLogin: isLogin.reducer,
  },
});
