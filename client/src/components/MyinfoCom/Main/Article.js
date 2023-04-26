import styled from 'styled-components';
import CardHeader from './CardHeader';
import { Link } from 'react-router-dom';
const ArticleContainer = styled.article`
  min-height: 100px;
  margin-top: 15px;
`;
const ContentsContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  min-height: 65px;
  border: 1px solid var(--menu-hover-background);
  border-radius: 5px;

  .data {
    margin: 10px;
    font-size: 20px;
    font-weight: 600;
    color: #1972c5;
  }
`;
const Article = ({
  title,
  isFilter = true,
  options,
  data = [],
  select = '',
}) => {
  let id = data.map((el) => `/question/${el.questionId}`);
  console.log(id);

  return (
    <ArticleContainer>
      <CardHeader title={title} isFilter={isFilter} options={options} />
      <ContentsContainer>
        {data.map((el, idx) => (
          <Link to={id[idx]} key={idx}>
            <div className="data">{el[select]}</div>
          </Link>
        ))}
      </ContentsContainer>
    </ArticleContainer>
  );
};
export default Article;
