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

export let { selectFooter, selectNav } = showCom.actions;
// export let { create } = createQuestion.actions;

export default configureStore({
  reducer: {
    showCom: showCom.reducer,
    // createQuestion: createQuestion.reducer,
  },
});
