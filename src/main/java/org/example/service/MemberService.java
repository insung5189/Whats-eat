package org.example.service;

import org.example.Container;
import org.example.dto.Member;
import org.example.repository.MemberRepository;

import java.sql.Connection;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public boolean isLoginIdDup(String loginId) {
    return memberRepository.isLoginIdDup(loginId);
  }

  public int join(String loginId, String loginPw, String nickname, String e_mail, String birth) {
    return memberRepository.join(loginId, loginPw, nickname, e_mail, birth);
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public void modifyloginPw(String loginId, String modifyloginPw) {
    memberRepository.modifyloginPw(loginId, modifyloginPw);//
  }

  public void withdrawal(String loginId) {//탈퇴 기능을  가지게끔 Withdrawal 함수를 선언한다....// 해당 자료값 loginId는 밑에 값을 담기위해 담았음.
    memberRepository.withdrawal(loginId);//memberRepository 클래스에서.withdrawal 을 선언하여. 위에 자료값 String loginId를 담아 불러온다.
  }

   }

