package org.example.service;

import org.example.Container;
import org.example.dto.Account;
import org.example.repository.AccountRepository;

public class AccountService {
  private AccountRepository accountRepository;
  public AccountService() {
    accountRepository = Container.accountRepository;
  }

  public boolean isUser_IdDup(String user_id) { // 사용자계정 중복확인
    return accountRepository.isUser_IdDup(user_id);
  }
  public boolean isUser_emailDup(String user_email) { // 사용자이메일 중복확인
    return accountRepository.isUser_emailDup(user_email);
  }

  public int join(String user_id, String password, String user_name, String user_email, String birthday) { // 회원가입에 필요한 정보 전달
    return accountRepository.join(user_id, password, user_name, user_email, birthday); // 회원가입에 필요한 정보를 받아서 쿼리문이 있는 메서드로 전달
  }

  public Account getAccountBy_user_id(String user_id) {
    return accountRepository.getAccountBy_user_id(user_id);
  }


}
