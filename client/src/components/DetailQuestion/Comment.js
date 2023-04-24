import { useState } from 'react';
import styled from 'styled-components';

function Comment() {
  const [commentOn, setCommentOn] = useState(false);
  return (
    <>
      <CommentView>
        <div className="comment-list">
          <span>이렇게 코멘트가 써질 예정이에요!</span>
          <span className="comment-user">-JUBEE</span>
          <span>1 hour ago</span>
        </div>
        <div className="comment-list">
          <span>이렇게 코멘트가 써질 예정이에요!</span>
          <span className="comment-user">-JUBEE</span>
          <span>1 hour ago</span>
        </div>
        <div className="comment-list">
          <span>이렇게 코멘트가 써질 예정이에요!</span>
          <span className="comment-user">-JUBEE</span>
          <span>1 hour ago</span>
        </div>
        <div className="comment-list">
          <span>이렇게 코멘트가 써질 예정이에요!</span>
          <span className="comment-user">-JUBEE</span>
          <span>1 hour ago</span>
        </div>
      </CommentView>
      <CommentOpenBtn>
        <buttom
          onClick={() => {
            setCommentOn(!commentOn);
          }}
        >
          Add a comment
        </buttom>
      </CommentOpenBtn>
      {commentOn ? (
        <CommentWrite>
          <input type="text" placeholder="덧글 내용을 입력해주세요"></input>
          <button>submit</button>
        </CommentWrite>
      ) : null}
    </>
  );
}

export default Comment;

export const CommentView = styled.div`
  margin-top: 20px;

  .comment-list {
    display: flex;
    justify-content: flex-start;
    border-bottom: 1px solid #ddd;
    padding: 10px;
    font-size: 14px;
    flex-wrap: wrap;
  }
  .comment-list:nth-child(1) {
    border-top: 1px solid #ddd;
  }
  .comment-user {
    color: hsl(205, 47%, 42%);
    margin: 0px 10px;
  }
`;

export const CommentOpenBtn = styled.div`
  margin: 20px 0px;
  color: #666;
`;

export const CommentWrite = styled.div`
  display: flex;
  margin: 10px 0px;
  background-color: #f7f7f7;
  border-radius: 3px;
  border: 1px solid #ddd;
  padding: 20px;

  input {
    width: 100%;
    border: 1px solid #dfdfdf;
    padding: 20px;
  }
  button {
    border: 1px solid #dfdfdf;
    background-color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
    transition: 0.1s ease-in-out;
  }
  button:hover {
    background-color: #f7f7f7;
  }
  button:active {
    background-color: #e2e2e2;
    color: #507ca6;
  }
`;
