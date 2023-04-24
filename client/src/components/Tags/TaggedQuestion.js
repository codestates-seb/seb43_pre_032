import { useParams } from 'react-router-dom';
import Viewcomponent from '../../pages/QusetionList.jsx';

function TaggedQuestion() {
  const tagId = useParams();
  console.log(tagId);
  return (
    <>
      <Viewcomponent tagId={tagId.tagId}></Viewcomponent>
    </>
  );
}

export default TaggedQuestion;
