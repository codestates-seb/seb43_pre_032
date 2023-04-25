import axios from 'axios';
import { CommentWrite } from '../Comment';

function CommentUpdate({
  updateId,
  updateContent,
  setUpdateContent,
  qsId,
  useEffectget,
  setUpdateCommentOn,
}) {
  const token = localStorage.getItem('token');
  const handleUpdateContent = (e) => {
    setUpdateContent(e.target.value);
  };
  const handleUpdateContentClick = (updateId) => {
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
  };
  return (
    <CommentWrite>
      <input
        type="text"
        placeholder={updateContent}
        value={updateContent}
        onChange={handleUpdateContent}
      ></input>
      <button onClick={() => handleUpdateContentClick(updateId)}>submit</button>
    </CommentWrite>
  );
}

export default CommentUpdate;
