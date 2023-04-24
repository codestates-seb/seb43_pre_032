import { useState } from 'react';
// import styled from 'styled-components';
import { CommentView, CommentOpenBtn, CommentWrite } from './Comment';

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
