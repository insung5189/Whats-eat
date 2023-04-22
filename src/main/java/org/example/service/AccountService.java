package org.example.service;

import org.example.Container;
import org.example.dto.Account;
import org.example.repository.AccountRepository;

public class AccountService {
  private AccountRepository accountRepository;
  public AccountService() {
    accountRepository = Container.accountRepository;
  }

  public boolean isUser_IdDup(String user_id) {
    return accountRepository.isUser_IdDup(user_id);
  }
  public boolean isUser_emailDup(String user_email) {
    return accountRepository.isUser_emailDup(user_email);
  }

  public int join(String user_id, String password, String user_name, String user_email, String birthday) {
    return accountRepository.join(user_id, password, user_name, user_email, birthday);
  }

  public Account getAccountBy_user_id(String user_id) {
    return accountRepository.getAccountBy_user_id(user_id);
  }


}
