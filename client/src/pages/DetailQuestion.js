import styled from 'styled-components';
import Sidebanner from '../components/question/Sidebanner.jsx';
import DetailTitle from '../components/DetailQuestion/DetailTitle';
import DetailContent from '../components/DetailQuestion/DetailContent';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { selectFooter, selectNav } from '../store/store';
import { useDispatch } from 'react-redux';
function DetailQuestion() {
  const [detailData, setDetailData] = useState([]); //상세페이지 질문 data
  const [answerData, setAnswerData] = useState([]); //상세페이지 답글 data
  const [tagData, setTagData] = useState([]); //상세페이지 태그 data

  const qsId = useParams(); // 파람스
  const dispatch = useDispatch(); //디스패치

  //헤더 푸터 여부
  useEffect(() => {
    dispatch(selectFooter(true));
    dispatch(selectNav(true));
  }, []);

  //상세페이지 답글 get 요청
  useEffect(() => {
    axios
      .get(
        `http://ec2-13-125-39-247.ap-northeast-2.compute.amazonaws.com:8080/questions/${qsId.qsId}?page=1&answertab=score`,
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
        setDetailData(res.data.data.question);
        if (res.data.data.answer !== null) {
          setAnswerData(res.data.data.answer);
        }
        setTagData(res.data.data.question.tagName);
      })
      .catch(function (error) {
        // 에러인 경우 실행
        console.log(error);
      });
  }, []);

  return (
    <DetailSection>
      <DetailTitle data={detailData} />
      <ContentsGroup>
        <DetailContent
          data={detailData}
          tagData={tagData}
          answerData={answerData}
          qsId={qsId}
        />
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
  max-width: 1000px;
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
