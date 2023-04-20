import { useEffect } from 'react';
import { useInView } from 'react-intersection-observer';

const InfiniteScroll = ({ onLoadMore }) => {
  const { ref, inView } = useInView();

  useEffect(() => {
    if (inView) {
      onLoadMore();
    }
  }, [inView]);

  return (
    <>
      <div ref={ref}></div>
    </>
  );
};

export default InfiniteScroll;
