import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const MemberDelete = () => {
  const [deleteBtn, setDeleteBtn] = useState(false);
  console.log(deleteBtn);

  const navigate = useNavigate();

  const getToken = localStorage.getItem('token');
  const getMemberid = localStorage.getItem('memberid');

  const removeToken = () => {
    localStorage.removeItem('token');
  };
  const removeMemberId = () => {
    localStorage.removeItem('memberid');
  };

  const checkDeleteHandler = () => {
    setDeleteBtn(true);
  };

  const deleteHandler = () => {
    deleteAxios();
  };

  const deleteAxios = () => {
    axios
      .delete(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/members/${getMemberid}`,
        { headers: { Authorization: getToken } }
      )
      .then((res) => {
        if (res.status === 204) {
          removeToken();
          removeMemberId();
          navigate('/members');
        }
      })
      .catch((err) => console.log(err));
  };

  return (
    <DivContainer>
      {deleteBtn ? (
        <DivDelete>
          <div>
            정말로 계정을 삭제하시겠습니까? 계정을 다시 되돌릴 수 없습니다.
          </div>
          <button onClick={deleteHandler}>계정삭제</button>
        </DivDelete>
      ) : (
        <DivDelete>
          <button onClick={checkDeleteHandler}>계정삭제</button>
        </DivDelete>
      )}
    </DivContainer>
  );
};

const DivContainer = styled.div`
  width: 600px;
`;

const DivDelete = styled.div`
  > button {
    height: 30px;
    width: 215px;
    border-radius: 5px;
    border: 2px solid #fca5a5;
    background-color: transparent;
  }
`;

export default MemberDelete;
