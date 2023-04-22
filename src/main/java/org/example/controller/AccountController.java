package org.example.controller;

import org.example.Container;
import org.example.dto.Account;
import org.example.service.AccountService;

public class AccountController {
  private AccountService accountService;

  public AccountController() {
    accountService = Container.accountService;
  }

  public void join() { // 회원가입 메서드
    String user_id;
    String password;
    String passwordConfirm;
    String user_name;
    String user_email;
    String birthday;
    /* 이전데이터 백업
    String loginNick;
    String loginPw;
    String loginPwConfirm;
    String name;
    String email;
    String birth;
     */

    System.out.println("== 회원 가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.printf("사용할 닉네임 : ");
      user_id = Container.scanner.nextLine().trim();

      if (user_id.length() == 0) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      boolean isUser_IdDup = accountService.isUser_IdDup(user_id);

      if (isUser_IdDup) {
        System.out.printf("%s(은)는 이미 사용중인 로그인 아이디입니다.\n", user_id);
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      password = Container.scanner.nextLine().trim();

      if (password.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      // 로그인 비밀번호 확인 입력
      boolean passwordConfirmIsSame = true;

      while (true) {
        System.out.printf("로그인 비밀번호 확인 : ");
        passwordConfirm = Container.scanner.nextLine().trim();

        if (passwordConfirm.length() == 0) {
          System.out.println("로그인 비밀번호를 입력해주세요.");
          continue;
        }

        if (password.equals(passwordConfirm) == false) {
          System.out.println("로그인 비밀번호가 일치하지 않습니다.");
          passwordConfirmIsSame = false;
          break;
        }
        break;
      }

      if (passwordConfirmIsSame) {
        break;
      }
    }

    // 이메일 입력
    while (true) {
      System.out.printf("사용할 이메일 : ");
      user_email = Container.scanner.nextLine().trim();

      if (user_email.length() == 0) {
        System.out.println("사용할 이메일을 입력해주세요.");
        continue;
      }

      boolean isUser_emailDup = accountService.isUser_emailDup(user_email);

      if (isUser_emailDup) {
        System.out.printf("%s(은)는 이미 사용중인 이메일 입니다.\n", user_email);
        continue;
      }

      break;
    }

    // 이름 입력
    while (true) {
      System.out.printf("이름 : ");
      user_name = Container.scanner.nextLine().trim();

      if (user_name.length() == 0) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }
      break;
    }

    // 생년월일 입력
    while (true) {
      System.out.printf("생년월일(YYYY-MM-DD) : ");
      birthday = Container.scanner.nextLine().trim();

      if (birthday.length() == 0) {
        System.out.println("생년월일을 입력해주세요(YYYY-MM-DD)");
        continue;
      }
      break;
    }

    int id = accountService.join(user_id, password, user_name, user_email, birthday);

    System.out.printf("%d번 회원이 등록되었습니다.\n", id);
  }


  public void login() {
    String user_id;
    String password;
    /* 이전데이터 백업
    String loginNick;
    String loginPw;
    */

    System.out.println("== 로그인 ==");

    System.out.printf("로그인 아이디 : ");
    user_id = Container.scanner.nextLine().trim();

    if (user_id.length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Account account = accountService.getAccountBy_user_id(user_id);

    if (account == null ) {
      System.out.println("입력하신 로그인 아이디는 존재하지 않습니다.");
      return;
    }

    int loginTryMaxCount = 3;
    int loginTryCount = 0;

    // 로그인 비밀번호 입력
    while (true) {
      if(loginTryCount >= loginTryMaxCount) {
        System.out.println("비밀번호 확인 후 다음에 다시 시도해주세요.");
        break;
      }

      System.out.printf("로그인 비밀번호 : ");
      password = Container.scanner.nextLine().trim();

      if (password.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      if(account.getPassword().equals(password) == false) {
        loginTryCount++;
        System.out.println("비밀번호가 일치하지 않습니다.");
        continue;
      }

      System.out.printf("\"%s\"님 환영합니다.\n", account.getUser_name());
      Container.session.login(account);

      break;
    }
  }

  public void whoami() {
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 상태가 아닙니다.");
    }
    else {
      System.out.println(Container.session.loginedAccount.getUser_id());
    }
  }

  public void logout() {
    Container.session.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
