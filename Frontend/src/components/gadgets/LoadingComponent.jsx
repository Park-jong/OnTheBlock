import styled from "styled-components";

function LoadingComponent() {
  
  return (
    <S.Ripple class="ld-ripple">
      <div></div>
      <div></div>
    </S.Ripple>
  );
}

const S = {
  Ripple: styled.div`
      position: relative;
      width: 80px;
      height: 80px;


    > div {
      position: absolute;
      border: 4px solid #fff;
      opacity: 1;
      border-radius: 50%;
      animation: ld-ripple 1s cubic-bezier(0, 0.2, 0.8, 1) infinite;
    }
  `,

  
};


export default LoadingComponent;