import styled from 'styled-components';
import CardHeader from './CardHeader';
const ArticleContainer = styled.article`
  height: 100px;
  margin-top: 15px;
  @media (max-width: 980px) {
    margin-right: 40px;
  }
`;
const ContentsContainer = styled.div`
  width: 100%;
  height: 65px;
  border: 1px solid var(--menu-hover-background);
  border-radius: 5px;
`;
const Article = ({ title, isFilter = true, options }) => {
  return (
    <ArticleContainer>
      <CardHeader title={title} isFilter={isFilter} options={options} />
      <ContentsContainer></ContentsContainer>
    </ArticleContainer>
  );
};
export default Article;
