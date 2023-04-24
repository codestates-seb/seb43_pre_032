import { VoteIcon } from './DetailContent';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faPlay,
  faClockRotateLeft,
  faBookmark as faSolidBookmark,
} from '@fortawesome/free-solid-svg-icons';
import { faBookmark as faRegularBookmark } from '@fortawesome/free-regular-svg-icons';
import { useState } from 'react';

function VoteGroup() {
  const [bookmark, setBookmark] = useState(false);
  return (
    <VoteIcon>
      <span className="side-icon-color">
        <FontAwesomeIcon icon={faPlay} rotation={270} />
      </span>
      <span>0</span>
      <span className="side-icon-color">
        <FontAwesomeIcon icon={faPlay} rotation={90} />
      </span>
      <button
        className="side-icon-size side-icon-color"
        onClick={() => {
          setBookmark(!bookmark);
        }}
      >
        {bookmark ? (
          <FontAwesomeIcon icon={faSolidBookmark} className="color-orange" />
        ) : (
          <FontAwesomeIcon icon={faRegularBookmark} />
        )}
      </button>
      <span className="side-icon-size side-icon-color">
        <FontAwesomeIcon icon={faClockRotateLeft} />
      </span>
    </VoteIcon>
  );
}

export default VoteGroup;
