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

  public void modifyPassword(String user_id, String modifyPassword) {
    accountRepository.modifyPassword(user_id, modifyPassword);
  }

  public void withdrawal(String user_id) {//탈퇴 기능을  가지게끔 Withdrawal 함수를 선언한다....// 해당 자료값 user_id는 밑에 값을 담기위해 담았음.
    accountRepository.withdrawal(user_id);//accountRepository 클래스에서.withdrawal 을 선언하여. 위에 자료값 String user_id를 담아 불러온다.


  }


}
