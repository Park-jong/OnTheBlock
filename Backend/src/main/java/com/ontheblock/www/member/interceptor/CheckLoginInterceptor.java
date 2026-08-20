package com.ontheblock.www.member.interceptor;

import com.ontheblock.www.common.exception.UnauthorizedException;
import com.ontheblock.www.member.JWT.JwtService;
import com.ontheblock.www.member.Member;
import com.ontheblock.www.member.repository.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

// AccessToken이 유효한지 확인해주는 클래스
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {

  private JwtService jwtService;
  private MemberRepository memberRepository;
  public CheckLoginInterceptor(JwtService jwtService, MemberRepository memberRepository) {
    super();
    this.jwtService = jwtService;
    this.memberRepository = memberRepository;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (PreflightChecker.isPreflightRequest(request)) {
      return true;
    }

    String accessToken = request.getHeader("accessToken"); // 헤더에서 토큰 꺼냄
    // AcessToken이 유효한지 체크
    if (!jwtService.checkToken(accessToken)) {
      throw new UnauthorizedException("TOKEN IS NOT VALID NEED REFRESHTOKEN");
    }

    Long id = jwtService.getIdFromToken(accessToken); // 토큰에서 id값을 꺼냄
    Optional<Member> member = memberRepository.findById(id);
    if (member.isEmpty()) {
      throw new UnauthorizedException("MEMBER IS NOT REGISTERED");
    }

    request.setAttribute("id", id); // reqeust에 id를 담아서 controller로 보냄
    return true;
  }
}
