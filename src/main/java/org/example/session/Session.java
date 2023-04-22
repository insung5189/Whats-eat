package org.example.session;

import org.example.dto.Member;

public class Session {//Session의 역활은 db상의 유저의 정보를 담고있는 클래스.
  //Session 의 역할이 컬럼에서 정보값 loginId, pw nickname 등 다른곳에 호출할수있도록 하는 저장된 공간.
  public int loginedMemberId;//
  public Member loginedMember;//얘가 회원정보를 담음 변수

  public Session() {
    loginedMemberId = -1;
  }//생성자 함수 에 값이 loginedMemberId 를 -1로  지정하여 -1은 0부터 시작이기에
  //바로 실행되진않고 로그인이 됫을때 1로 재할당되어  로그인상태를 확인시켜는 함수다.

  public boolean isLogined() {
    return loginedMemberId != -1;
  }
  public void login(Member member) {
    loginedMemberId = member.getId();
    loginedMember = member;
  }

  public void logout() {
    loginedMemberId = -1;
    loginedMember = null;
  }
}
