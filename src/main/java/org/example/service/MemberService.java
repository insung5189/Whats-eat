package org.example.service;

import org.example.Container;
import org.example.dto.Member;
import org.example.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;
  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public boolean isloginNickDup(String loginNick) {
    return memberRepository.isloginNickDup(loginNick);
  }

  public int join(String loginNick, String loginPw, String email, String birth, String name) {
    return memberRepository.join(loginNick, loginPw, email, birth, name);
  }

  public Member getMemberByloginNick(String loginNick) {
    return memberRepository.getMemberByloginNick(loginNick);
  }

  public boolean isemailDup(String email) {
    return memberRepository.isemailDup(email);
  }
}
