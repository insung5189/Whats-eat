package org.example.service;

import org.example.Container;
import org.example.dto.Account;
import org.example.repository.AccountRepository;

public class AccountService {
  private AccountRepository accountRepository;
  public AccountService() {
    accountRepository = Container.accountRepository;
  }

  public boolean isloginNickDup(String loginNick) {
    return accountRepository.isloginNickDup(loginNick);
  }

  public int join(String loginNick, String loginPw, String email, String birth, String name) {
    return accountRepository.join(loginNick, loginPw, email, birth, name);
  }

  public Account getMemberByloginNick(String loginNick) {
    return accountRepository.getMemberByloginNick(loginNick);
  }

  public boolean isemailDup(String email) {
    return accountRepository.isemailDup(email);
  }
}
