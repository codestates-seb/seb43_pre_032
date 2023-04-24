import styled from 'styled-components';
import Sidebanner from '../components/question/Sidebanner.jsx';
import DetailTitle from '../components/DetailQuestion/DetailTitle';
import DetailContent from '../components/DetailQuestion/DetailContent';
import { useEffect, useState } from 'react';
import axios from 'axios';
function DetailQuestion() {
  const [detailData, setDetailData] = useState([]);
  const [tagData, setTagData] = useState([]);
  console.log(tagData);

  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/13?page=1&size=5&sortBy=answerId`,
        {
          headers: {
            'ngrok-skip-browser-warning': '69420',
          },
          withCredentials: true,
          credentials: 'include',
        }
      )
      .then(function (res) {
        // 성공한 경우 실행
        setDetailData(res.data.data);
        setTagData(res.data.data.question.tagName);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, []);

  return (
    <DetailSection>
      <DetailTitle data={detailData} tagData={tagData} />
      <ContentsGroup>
        <DetailContent data={detailData} />
        <div className="media-fade">
          <Sidebanner></Sidebanner>
        </div>
      </ContentsGroup>
    </DetailSection>
  );
}

export default DetailQuestion;

const DetailSection = styled.section`
  display: flex;
  flex-direction: column;
  padding: 30px;
`;

const ContentsGroup = styled.section`
  display: flex;
  @media (max-width: 1000px) {
    .media-fade {
      display: none;
    }
  }
`;
