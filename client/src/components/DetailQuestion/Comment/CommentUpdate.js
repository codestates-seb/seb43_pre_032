import axios from 'axios';
import { CommentWrite } from '../Comment';

function CommentUpdate({
  updateId,
  updateContent,
  setUpdateContent,
  qsId,
  useEffectget,
  setUpdateCommentOn,
  isAnswerComment,
  answerId,
}) {
  const token = localStorage.getItem('token');
  const memberId = localStorage.getItem('memberid');
  const handleUpdateContent = (e) => {
    setUpdateContent(e.target.value);
  };
  const handlekeydown = (event) => {
    if (event.key === 'Enter') {
      handleUpdateContentClick(updateId);
    }
  };
  const handleUpdateContentClick = (updateId) => {
    if (!isAnswerComment) {
      axios
        .patch(
          `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questionanswers/${updateId}`,
          {
            questionAnswerId: Number(updateId),
            questionId: Number(qsId.qsId),
            questionAnswerContent: updateContent,
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
          setUpdateCommentOn(false);
        })
        .catch((error) => console.error(error));
    } else {
      axios
        .patch(
          `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/answeranswers/${updateId}`,
          {
            answerAnswerId: Number(updateId),
            memberId: memberId,
            answerId: answerId,
            answerAnswerContent: updateContent,
            modifiedAt: new Date(),
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
          setUpdateCommentOn(false);
        })
        .catch((error) => console.error(error));
      window.location.reload();
    }
  };
  return (
    <CommentWrite>
      <input
        type="text"
        placeholder={updateContent}
        value={updateContent}
        onChange={handleUpdateContent}
        onKeyDown={handlekeydown}
      ></input>
      <button onClick={() => handleUpdateContentClick(updateId)}>submit</button>
    </CommentWrite>
  );
}

export default CommentUpdate;
