import styled from 'styled-components';
import CardHeader from './CardHeader';
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
const Article = ({ title, isFilter = true, options, data = [] }) => {
  // console.log('!!!!!!!', data);
  return (
    <ArticleContainer>
      <CardHeader title={title} isFilter={isFilter} options={options} />
      <ContentsContainer>
        {data.map((el, idx) => (
          <div className="data" key={idx}>
            {el.questionsTitle}
          </div>
        ))}
      </ContentsContainer>
    </ArticleContainer>
  );
};
export default Article;
