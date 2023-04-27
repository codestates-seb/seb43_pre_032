import styled from 'styled-components';
import ModifyCom from '../components/ModifyAskCom/ModifyCom';
import { useDispatch } from 'react-redux';
import { selectNav } from '../store/store';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
const ModifyContainer = styled.div`
  height: 100vh;
  padding-left: 20px;
  padding-top: 30px;
`;
const ModifyAskPage = () => {
  let dispatch = useDispatch();
  const qsId = useParams(); // 파라미터 값 가져오기 - 질문 ID
  useEffect(() => {
    dispatch(selectNav(false)); // 질문 수정 페이지시 nav 제거
  }, []);
  return (
    <ModifyContainer>
      <ModifyCom qsId={qsId}></ModifyCom>
    </ModifyContainer>
  );
};
export default ModifyAskPage;
