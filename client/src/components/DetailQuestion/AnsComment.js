import axios from 'axios';
import { useState } from 'react';
// import styled from 'styled-components';
import { CommentView, CommentOpenBtn, CommentWrite } from './Comment';
import CommentUpdate from './Comment/CommentUpdate';

function AnsComment({ answerComment, answerId }) {
  const [commentOn, setCommentOn] = useState(false);
  const [newComment, setNewComment] = useState('');
  const [updateId, setUpdateId] = useState(null);
  const [updateContent, setUpdateContent] = useState(null);
  const [updateCommentOn, setUpdateCommentOn] = useState(false);
  const url = `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080`;
  const token = localStorage.getItem('token');
  const memberId = localStorage.getItem('memberid');
  // console.log(memberId);
  // console.log(answerComment, answerId);

  const handleCommentContent = (e) => {
    setNewComment(e.target.value);
  };
  const handlekeydown = (event) => {
    if (event.key === 'Enter') {
      hadleCreateComment();
    }
  };
  const hadleCreateComment = () => {
    axios
      .post(
        `${url}/answeranswers`,
        {
          memberId: memberId,
          answerId: answerId,
          answerAnswerContent: newComment,
          createdAt: new Date(),
        },
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: token,
          },
        }
      )
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
      .catch((error) => console.error(error));
    setNewComment('');
  };

  const handleDeleteComment = (commentId) => {
    axios
      .delete(`${url}/answeranswers/${commentId}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => console.error(error));

    window.location.reload();
  };

  return (
    <>
      <CommentView>
        {answerComment.map((comment, idx) => {
          return (
            <div className="comment-list" key={idx}>
              <span>{comment.answerAnswerContent}</span>
              <span className="comment-user">-{comment.memberName}</span>
              <span>{displayedAt(new Date(comment.createdAt))}</span>
              <span>{comment.memberId === memberId}</span>
              {Number(memberId) === comment.memberId ? (
                <>
                  <button
                    onClick={() => {
                      setUpdateCommentOn(!updateCommentOn);
                      setUpdateId(comment.answerAnswerId);
                      setUpdateContent(comment.answerAnswerContent);
                    }}
                  >
                    수정
                  </button>
                  <button
                    onClick={() => handleDeleteComment(comment.answerAnswerId)}
                  >
                    삭제
                  </button>
                </>
              ) : null}
            </div>
          );
        })}
      </CommentView>
      {updateCommentOn ? (
        <CommentUpdate
          updateId={updateId}
          updateContent={updateContent}
          setUpdateContent={setUpdateContent}
          setUpdateCommentOn={setUpdateCommentOn}
          isAnswerComment={true}
          answerId={answerId}
        />
      ) : null}
      <CommentOpenBtn>
        <button
          className="addAComment"
          onClick={() => {
            setCommentOn(!commentOn);
          }}
        >
          Add a comment
        </button>
      </CommentOpenBtn>
      {commentOn ? (
        <CommentWrite>
          <input
            type="text"
            placeholder="덧글 내용을 입력해주세요"
            value={newComment}
            onChange={handleCommentContent}
            onKeyDown={handlekeydown}
          ></input>
          <button onClick={hadleCreateComment}>submit</button>
        </CommentWrite>
      ) : null}
    </>
  );
}

function displayedAt(createdAt) {
  const milliSeconds = new Date() - createdAt;
  const seconds = milliSeconds / 1000;
  if (seconds < 60) return `${Math.floor(seconds)} secs ago`;
  const minutes = seconds / 60;
  if (minutes < 60) return `${Math.floor(minutes)} min ago`;
  const hours = minutes / 60;
  if (hours < 24) return `${Math.floor(hours)} hour ago`;
  const days = hours / 24;
  if (days < 7) return `${Math.floor(days)} days ago`;
  const weeks = days / 7;
  if (weeks < 5) return `${Math.floor(weeks)} weeks ago`;
  const months = days / 30;
  if (months < 12) return `${Math.floor(months)} months ago`;
  const years = days / 365;
  return `${Math.floor(years)}years ago`;
}

export default AnsComment;
