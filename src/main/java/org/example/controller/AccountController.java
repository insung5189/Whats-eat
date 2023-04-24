package org.example.controller;

import org.example.App;
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

        Account account = accountService.getAccountBy_user_id(user_id); // DB에서 SELECT, WHERE문으로 해당 user_id 조회 후 담긴 값(실제 user_id값이거나 없다면 null)

        if (account == null) { // account = 실제 account 테이블 user_id 칼럼에 id값에 맞는 로데이터가 담겨있음(문자열형태로)
            System.out.println("입력하신 로그인 아이디는 존재하지 않습니다.");
            return;
        } // account값이 null이 아니라면 위 if문 타지않고 바로 아래 로그인 비밀번호 입력로직으로 진행

        int loginTryMaxCount = 3; // 최대 로그인 시도 가능 횟수
        int loginTryCount = 0; // 실제 로그인 시도 횟수(초기값 = 0)
        // loginTryCount 값이 최대시도가능횟수를 넘기지 않을 경우앤 아래 while문을 타지 않고 바로 비밀번호 입력 안내

        // 로그인 비밀번호 입력
        while (true) {
            if (loginTryCount >= loginTryMaxCount) { // 최대시도가능횟수보다 초과할 땐
                System.out.println("비밀번호 확인 후 다음에 다시 시도해주세요.");
                break;
            }

            System.out.printf("로그인 비밀번호 : ");
            password = Container.scanner.nextLine().trim(); // 스캐너로 비밀번호 입력받아서 password에 넣어줌

            if (password.length() == 0) { // 비밀번호 미입력시 검증로직
                System.out.println("로그인 비밀번호를 입력해주세요.");
                continue;
            } // 비밀번호 입력시 아래 if문을 타게됨

            if (account.getPassword().equals(password) == false) { // 비밀번호 대조해서 다를경우
                loginTryCount++; // 실제 로그인시도횟수가 올라가고
                System.out.println("비밀번호가 일치하지 않습니다.");
                continue; // if문 탈출하고 다시 while문으로 돌아가서 비밀번호 입력하라고 안내.
            }

            System.out.printf("\"%s\"님 환영합니다.\n", account.getUser_name());
            Container.session.login(account);

            break;
        }
    }

    public void whoami() {
        if (!Container.session.isLogined()) {
            System.out.println("게스트모드 입니다.");
            System.out.println("목록과 글 내용 열람만 가능합니다.");
            System.out.println("로그인 후 이용해주세요.");
            return;
        } else {
            System.out.println(Container.session.loginedAccount.getUser_id());
        }
    /*
    if (cmd.equals("게스트")) {
      System.out.println("게스트모드 입니다 목록과 글 내용 열람만 가능합니다.");
//      continue;
    } else if(Container.session.isLogined() == false) {
      System.out.println("로그인 상태가 아닙니다.");
    } else {
      System.out.println(Container.session.loginedAccount.getUser_id());
    }
     */


  /*
  if(Container.session.isLogined() == false) {
      System.out.println("로그인 하시거나 게스트 모드를 이용해보세요");
      return;
    }
   */
    }

    public void logout() {
        Container.session.logout();
        System.out.println("로그아웃 되었습니다.");
    }

    public void guest(String cmd) {
        if (cmd.equals("게스트")) {
            System.out.println("guest님 환영합니다.");
            System.out.println("글, 댓글 작성이 제한되며 열람과 좋아요만 가능합니다.");
            Container.session.guest();
        }
    }

}

