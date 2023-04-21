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
    return memberRepository.join(loginId, loginPw, nickname,e_mail,birth);
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }
  public void modifyloginPw(String modifyloginPw){
     memberRepository.modifyloginPw(modifyloginPw);
  }
}
