import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { RecordComponent, SessionSearchComponent } from "@/components";

function Record() {
  const navigate = useNavigate();

  return (
    <S.Wrap>
      <RecordComponent />
      <SessionSearchComponent />
    </S.Wrap>
  );
}

const S = {
  Wrap: styled.div`
    min-height: 100vh;
    background: #252525;
    padding: 0px;
    color: #d7d7d7;
  `,
};

export default Record;
