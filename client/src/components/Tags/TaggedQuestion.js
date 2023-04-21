import { useParams } from 'react-router-dom';
import TagsQuestions from './TagsQuestions';

function TaggedQuestion() {
  const tagId = useParams();
  return (
    <>
      <TagsQuestions tagId={tagId.tagId}></TagsQuestions>
    </>
  );
}

export default TaggedQuestion;
