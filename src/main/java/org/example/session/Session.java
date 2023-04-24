package org.example.session;

import org.example.dto.Account;

public class Session {
  public int loginedMemberId;
  public Account loginedAccount;

  public Session() {
    loginedMemberId = -1; // 왜 초기화값을 -1로 두었을까?
  }

  public boolean isLogined() {
    return loginedMemberId != -1;
  }
  public void login(Account account) {
    loginedMemberId = account.getId();
    loginedAccount = account;
  }


  public void logout() {
    loginedMemberId = -1;
    loginedAccount = null;
  }

  public void guest() {

  }
}