import { useState } from 'react';
import styled from 'styled-components';

function AnsComment() {
  const [commentOn, setCommentOn] = useState(false);
  return (
    <>
      <CommentView>
        <div className="comment-list">
          <span className="comment-text">답변 코멘트도 구현했지요</span>
          <span className="comment-user">-JUBEE</span>
          <span className="comment-creatAt">1 hour ago</span>
        </div>
        <div className="comment-list">
          <span className="comment-text">정말 힘드네요</span>
          <span className="comment-user">-JUBEE</span>
          <span className="comment-creatAt">1 hour ago</span>
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

export default AnsComment;

const CommentView = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  .comment-list {
    border-bottom: 1px solid #ddd;
    padding: 10px;
    font-size: 14px;
  }
  .comment-list:nth-child(1) {
    border-top: 1px solid #ddd;
  }
  .comment-user {
    background-color: hsl(205, 46%, 92%);
    color: hsl(205, 47%, 42%);
    border-radius: 3px;
    padding: 0px 10px;
    margin: 0px 10px;
  }
`;

const CommentOpenBtn = styled.div`
  margin: 20px 0px;
  color: #666;
`;

const CommentWrite = styled.div`
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
