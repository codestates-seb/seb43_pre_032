import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const MemberDelete = () => {
  const [deleteBtn, setDeleteBtn] = useState(false);
  const [isChecked, setIsChecked] = useState(false);

  const navigate = useNavigate();

  const getToken = localStorage.getItem('token');
  const getMemberid = localStorage.getItem('memberid');

  const removeToken = () => {
    localStorage.removeItem('token');
  };
  const removeMemberId = () => {
    localStorage.removeItem('memberid');
  };

  const checkboxHandler = () => {
    setIsChecked(!isChecked);
  };

  const checkDeleteHandler = () => {
    setDeleteBtn(!deleteBtn);
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
            <input
              type="checkbox"
              id="deleteCheck"
              onChange={checkboxHandler}
            />
            <label htmlFor="deleteCheck">
              정말로 계정을 삭제하시겠습니까? 계정을 다시 되돌릴 수 없습니다.
            </label>
          </div>
          {isChecked ? (
            <>
              <button onClick={deleteHandler} className="hover">
                계정삭제
              </button>
              <button onClick={checkDeleteHandler} className="hover">
                취소
              </button>
            </>
          ) : (
            <>
              <button onClick={deleteHandler} disabled>
                계정삭제
              </button>
              <button onClick={checkDeleteHandler} className="hover">
                취소
              </button>
            </>
          )}
        </DivDelete>
      ) : (
        <DivDelete>
          <button onClick={checkDeleteHandler} className="hover">
            계정삭제
          </button>
        </DivDelete>
      )}
    </DivContainer>
  );
};

const DivContainer = styled.div`
  width: 600px;
  height: 60px;
`;

const DivDelete = styled.div`
  text-align: right;

  > button {
    width: 100px;
    border-radius: 5px;
    border: 2px solid #fca5a5;
    background-color: transparent;
  }

  .hover {
    :hover {
      color: white;
      background-color: #fca5a5;
      cursor: pointer;
    }
  }
`;

export default MemberDelete;
