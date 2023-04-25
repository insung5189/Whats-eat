package org.example.controller;

import org.example.App;
import org.example.Container;
import org.example.dto.Account;
import org.example.service.AccountService;

public class AccountController {
    private AccountService accountService;

    private String withdrawaluser_id;

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
            System.out.printf("사용할 아이디 : ");
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


    public void login() { // 로그인 메서드
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

            System.out.printf("\"%s\"님 오늘은 뭐잡솨YOU?.\n", account.getUser_name());
            withdrawaluser_id = user_id;
            Container.session.login(account);

            break;
        }
    }

    public void modifyPassword() {
        String user_id;// 자료형 스트렝이 loginId  인스턴스 변수 선언
        String password;// 자료형 스트렝이 loginPw  인스턴스 변수 선언

        System.out.println("== 로그인 ==");//위에 로직이 실행될경우 출력되는문구.

        System.out.printf("로그인 아이디 : ");//위에 로직이 실행될경우 출력되는문구
        user_id = Container.scanner.nextLine().trim();//컨테이너어 클래스에있는 스캐너의 입력값(공백없이 엔터칠대까지 한줄을 loginId라는 변수에 담는다.

        if (user_id.length() == 0) {// 조건문 if문을 선언하여 loginId 입력값이 .length 가 ==0 한줄도없을시 다음 로직을 실행하게한다.
            System.out.println("로그인 아이디를 입력해주세요.");//위에 로직이 실행되면 출력되는문구.
            return;
        }

        Account account = accountService.getAccountBy_user_id(user_id);//자료형 클래스  Member의 지역변수 member를 선언하여 accountService 에 있는 함수 MemberByLoginId를
        //get 하여 가져와서 변수 member에 담는다

        if (account == null) {//지역변수 member에 값이 null 일경우 다음 로직을 실행한다.
            // 여기에 담긴 지역변수 member는 데이터 베이스 sql 에 연동된 레퍼지토리로 이동이 되는 지역변수다.
            System.out.println("입력하신 로그인 아이디는 존재하지 않습니다.");//위에 로직이 실행시 출력되는문구
            return;
        }

        int loginTryMaxCount = 3;//정수형 loginTryMaxCount 에 3값을 준다.(로그인을 3번틀렷을시 멈춘다)
        int loginTryCount = 0;//정수형 loginTryCount 초기화값으로 선언한다.

        // 로그인 비밀번호 입력
        while (true) {//반복문 while를  선언하여 (true)값일때 다음로직을 실행할수있게한다.
            if (loginTryCount >= loginTryMaxCount) { //조건문 if 를 선언하여 loginTryCount가 loginTryMaxCount 보다 크거나 같을때 당므로직을 실행하게 조건을 건다.
                System.out.println("비밀번호 확인 후 다음에 다시 시도해주세요.");//위에 로직이 실행될경우 출력되는 문구
                break;
            }

            System.out.printf("로그인 비밀번호 : ");//위에 로직이 실행될경우 출력되는 문구
            password = Container.scanner.nextLine().trim();// 컨테이너 클래스에 있는 공백없이 엔터칠수있는 문구를 가진 스캐너기능을 loginPw 변수에 담는다.

            if (password.length() == 0) {//조건문 if문을 선언하여 loginPw 의 변수를 .length(길이)로 ==0 공백이엇을경우 다음로직을 실행할수있게한다
                System.out.println("로그인 비밀번호를 입력해주세요.");//위에 로직이 실행시 출력되는문구
                continue;
            }

            if (account.getPassword().equals(password) == false) {//if 조건문을 선언하여서 db값이 담아져있는 지역변수 account 와 // 클래스 변수에 있는 loginPw를 get로 가져오고 해당
                //LoginPw값을 . equals로 비교하여  조건이 LoginPw일때  데이터베이스 에 저장된값과 비교하여 false 일경우 로직이 실행된다.
                loginTryCount++;//위에 조건문이 실행되어 초기화값 loginTryCount을 증가 시킨다.
                System.out.println("비밀번호가 일치하지 않습니다.");//위에 로직이 실행될경우 출력되는 문구
                continue;
            }

            System.out.printf("\"%s\"님 .\n", account.getUser_name());//해당 위에 로직이 정상적으로 다 실행이 됫을경우에 출력되는 문구
            Container.session.login(account);// Container 클래스에 있는 session 클래스 타입의 변수 session을 선언된것을 가지고와서 session 안에 있는 선언된 함수 login
            //불러와
            break;
        }
        System.out.println("수정을 시작합니다.");
        System.out.println("새 비밀번호를 입력해주세요.");
        String modifyPassword = Container.scanner.nextLine().trim();

        accountService.modifyPassword(user_id, modifyPassword);//memberService의 클래스에서modifyloginPw에 함수를 불러와서 그값이 loginId,modifyloginPw일때 뢰직이 실행되게한다.
        System.out.println("비밀번호 변경이 완료 되었습니다.");
    }

    public void whoami() { // 로그인상태 메서드
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

    public void logout() { // 로그아웃 메서드
        Container.session.logout(); // 세션에서 logout메서드를 실행함.
        System.out.println("로그아웃 되었습니다.");
    }

    public void guest(String cmd) { // 게스트메서드
        if (cmd.equals("게스트")) {
            System.out.println("guest님 환영합니다.");
            System.out.println("글, 댓글 작성이 제한되며 목록과 본문 열람만 가능합니다.");
            Container.session.guest();
        }
    }

    public void withdrawal() {//탈퇴 기능을  가지게끔 Withdrawal 함수를 선언한다...

        System.out.println("정말 떠나시려구요 ㅠㅠ?");
        System.out.println("확인 / 취소 중 입력해주세요");
        String flag = Container.scanner.nextLine().trim();//컨테이너어 클래스에있는 스캐너의 입력값(공백없이 엔터칠대까지 한줄을 loginId라는 변수에 담는다.
        if (flag.equals("확인")) {
            System.out.println(withdrawaluser_id);
            accountService.withdrawal(Container.session.loginedAccount.getUser_name());// accountService 안에 withdrawal 함수를 작동하기 위해서  . 이후 login이 된 정보값 withdrawloginId로
            // 함수의 인자로 값을 넣어줫다.
            System.out.println("회원탈퇴가 정상적으로 되었습니다.");//위에 로직이 실행됫을경우 출력되는문구
        } else if (flag.equals("취소")) {//else 추가 조건문으로 flag 로 비교equals를 취소로 비교햇을시 그다음로직이 실행되게한다.
            System.out.println("취소되었습니다.");//위에 로직이 실행됫을경우 출력된다
        } else {//equals로 비교 대상이 아닌 다른 값을 입력햇을경우 밑에 로직이 실행된다.
            System.out.println("확인/ 취소 둘중에 하나만 고르시오");//위에 로직이 실행됫을경우 출력되는 문구
        }


    }

}

