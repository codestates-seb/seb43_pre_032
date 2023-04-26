import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import CommentUpdate from './Comment/CommentUpdate';

function Comment() {
  const [commentOn, setCommentOn] = useState(false);
  const [updateCommentOn, setUpdateCommentOn] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [updateContent, setUpdateContent] = useState(null);
  const [comments, setComments] = useState([]);
  const qsId = useParams();
  const url = `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080`;
  const token = localStorage.getItem('token');
  const [newComment, setNewComment] = useState('');
  const memberId = localStorage.getItem('memberid');

  const useEffectget = () => {
    axios
      .get(`${url}/questions/${qsId.qsId}?page=1&answertab=score`, {
        headers: {
          'ngrok-skip-browser-warning': '69420',
        },
        withCredentials: true,
        credentials: 'include',
      })
      .then(function (res) {
        // console.log(res.data.data);
        // 성공한 경우 실행
        setComments(res.data.data.question.questionAnswers);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  };

  useEffect(() => {
    useEffectget();
  }, []);
  const handlekeydown = (event) => {
    if (event.key === 'Enter') {
      hadleCreateComment();
    }
  };
  const handleCommentContent = (e) => {
    setNewComment(e.target.value);
  };
  const hadleCreateComment = () => {
    axios
      .post(
        `${url}/questions/${qsId.qsId}?page=1&answertab=score`,
        {
          questionId: Number(qsId.qsId),
          questionAnswerContent: newComment,
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
        useEffectget();
      })
      .catch((error) => console.error(error));
    setNewComment('');
  };

  const handleDeleteComment = (commentId) => {
    axios
      .delete(`${url}/questionanswers/${commentId}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      })
      .then((response) => {
        console.log(response);
        useEffectget();
      })
      .catch((error) => console.error(error));
  };

  return (
    <>
      <CommentView>
        {comments.map((comment, idx) => {
          return (
            <div className="comment-list" key={idx}>
              <span>{comment.questionAnswerContent}</span>
              <span className="comment-user">-{comment.memberName}</span>
              <span>{displayedAt(new Date(comment.createdAt))}</span>
              {Number(memberId) === comment.memberId ? (
                <>
                  <button
                    onClick={() => {
                      setUpdateCommentOn(!updateCommentOn);
                      setUpdateId(comment.questionAnswerId);
                      setUpdateContent(comment.questionAnswerContent);
                    }}
                  >
                    수정
                  </button>
                  <button
                    onClick={() =>
                      handleDeleteComment(comment.questionAnswerId)
                    }
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
          qsId={qsId}
          useEffectget={useEffectget}
          setUpdateCommentOn={setUpdateCommentOn}
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
  button {
    border: none;
    background-color: white;
    margin-left: 20px;
    color: rgba(0, 0, 0, 0.2);
  }
`;

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

export const CommentOpenBtn = styled.div`
  margin: 20px 0px;
  color: #666;
  .addAComment {
    border: none;
    background-color: white;
  }
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
