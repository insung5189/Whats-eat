package org.example.controller;

import org.example.Container;
import org.example.dto.Member;
import org.example.service.MemberService;

public class MemberController {
  private MemberService memberService;
  private String withdrawalloginId;//클래스 안에서만 사용할수있는 (member) or (전역)변수로 선언하여 다른 함수에서 loginId를 사용할수있게 하기위해 선언을하였다.

  public MemberController() {
    memberService = Container.memberService;
  }

  public void join() {
    String loginId; //데이터 베이스에 들어갈 컬럼으로 스트링 loginId  변수 선언
    String loginPw; //데이터 베이스에 들어갈 컬럼으로 스트링 loginPw 변수 선언
    String loginPwConfirm; // 데이터 베이스에 들어갈 컬럼으로 스트링 loginPwConfirm 변수 선언
    String nickname; // 데이터 베이스에 들어갈 컬럼으로 스트링 nickname 변수선언
    String e_mail; //데이터 베이스에 들어갈 컬럼으로 스트링 e_mail 변수선언
    String birth; // 데이터베이스에 들어갈 birth 변수선언/


    System.out.println("== 회원 가입 ==");//App 클래스에 회원가입 이라고 입력시 ==회원가입  == 출력

    // 로그인 아이디 입력
    while (true) {//반복문 while 로  참 (true)일시 계속 반복할수있게
      System.out.printf("로그인 아이디 : ");//위에 회원가입 로직 실행시   출렫뢷되는문구
      loginId = Container.scanner.nextLine().trim();//컨테이너어 클래스에있는 스캐너의 입력값(공백없이 엔터칠대까지 한줄을 loginId라는 변수에 담는다.

      if (loginId.length() == 0) {// 조건문 if문을 선언하여 loginId 입력값이 .length 가 ==0 한줄도없을시 다음 로직을 실행하게한다.
        System.out.println("로그인 아이디를 입력해주세요.");// 위에 로직이 공백 이거나  펄스 일때. 해당 출력 되는 문구
        continue;//  그다음 로직으로 탈수있게 해주는 컨티뉴.
      }

      boolean isLoginIdDup = memberService.isLoginIdDup(loginId); // 자료형 boolean (true)이거나 (false)일경우  memberService 클래스에 있는  isLoginIdDup 메소드를 위에
      //선언된 변수 loginId 값을 담아 boolean 으로 true 이거나 false 일경우 다음 로직이 실행되게한다.

      if (isLoginIdDup) {// 조건문 if로   isLoginIdDup 가 false 일경우 밑에 로직을 실행할수있게한다.
        System.out.printf("%s(은)는 이미 사용중인 로그인 아이디입니다.\n", loginId);//위 if 조건문이 실행됫을경우 출력되는문구
        continue;//
      }

      break;//위에 boolean자료형이 만나는즉시 멈추는 것
    }

    // 로그인 비밀번호 입력
    while (true) {//반복문 while로 값이 (true)일때 그다음 로직을 탈수있게한다
      System.out.printf("로그인 비밀번호 : ");//위에 반복문이 실행됫을때 출력되는문구
      loginPw = Container.scanner.nextLine().trim();// loginPw 변수에 컨테이너 클래스에있는 공백없고 엔터칠대까지 스캐너기능을 담긴 값을 loginPw에 담는다.

      if (loginPw.length() == 0) {//조건문 if 로 loginPw 변수에. length (길이)로 == 0 으로 입력값이 0일경우 다음 로직을 탈수있게한다.
        System.out.println("로그인 비밀번호를 입력해주세요.");// 위에 조건문 loginPw.length() == 0 실행시 출력되는문구
        continue;
      }

      // 로그인 비밀번호 확인 입력
      boolean loginPwConfirmIsSame = true;// 자료형 boolean (true)이거나 (false)일경우   loginPwConfirmIsSame 변수가 true 값을 담는다.

      while (true) {//반복문 while로 값이 (true)일때 그다음 로직을 탈수있게한다
        System.out.printf("로그인 비밀번호 확인 : ");//위에 반복문 이 실행될시 출력되는 문구
        loginPwConfirm = Container.scanner.nextLine().trim();// 컨테이너 클래스에 있는 공백없이 엔터칠수있는 문구를 가진 스캐너기능을 loginPwConfirm라는 변수에 담는다.

        if (loginPwConfirm.length() == 0) {//조건문 if 를 선언 하여 해당변수 loginPwConfirm에 length() == 0 길이가 입력값이 0일때 다음 로직을 탈수있게한다./
          System.out.println("로그인 비밀번호를 입력해주세요."); //위에 조건문이 실행될시.  출력되는문구
          continue;
        }

        if (loginPw.equals(loginPwConfirm) == false) {//조건문 if 를 선언하여 loginPw 가 .equals로 비교 하여 거짓일시 다음 로직을 탈수있게한다.
          System.out.println("로그인 비밀번호가 일치하지 않습니다.");//위에 로직이 실행될시 출력되는문구
          loginPwConfirmIsSame = false;// false 를 loginPwConfirmIsSame 해당 변수에 담는다.
          break;
        }
        break;
      }

      if (loginPwConfirmIsSame) {//위에 로직이 true 일때  반복이 아닌 다음 로직을 실행할수있게한다.
        break;
      }
    }

    // 이름 입력
    while (true) {//반복문 while로 값이 (true)일때 그다음 로직을 탈수있게한다
      System.out.printf("닉네임 : ");//위에 로직이 실행될경우 췰력되는문구
      nickname = Container.scanner.nextLine().trim();//컨테이너 클래스에 있는 공백없이 한줄을 엔터칠때까지 출력할수있는 스캐너 기능값을 가진값을 nickname변수에 담는다.

      if (nickname.length() == 0) {//조건문 if 를 선언하여 nickname 변수를 넣어 length() == 0 길이가 0값 일때 다음로직을 실행하게한다.
        System.out.println("닉네임을 입력해주세요.");//위에 로직이 실행될시 출력되는문구
        continue;
      }
      break;
    }
    while (true) {//반복문 while를  선언하여 (true)값일때 다음로직을 실행할수있게한다.
      System.out.printf("이메일 : ");//위에 로직이 실행될경우 출력되는문구
      e_mail = Container.scanner.nextLine().trim();// 컨테이너 클래스에 있는 공백없이 한줄을 엔터칠때까지의 스캐너 기능값을 가진값을 변수 e_mail에 담는다.

      if (e_mail.length() == 0) { //조건문 if 를 선언하여 e_mail 변수를 넣어 length() == 0 길이가 0값 일때 다음로직을 실행하게한다.
        System.out.println("이메일을 입력해주세요.");//위에 로직이 실행할시 출력되는문구.
        continue;
      }
      break;
    }
    while (true) {//반복문 while를  선언하여 (true)값일때 다음로직을 실행할수있게한다.
      System.out.printf("생일 : ");//위에 로직이 실행될경우 출력되는문구
      birth = Container.scanner.nextLine().trim();// 컨테이너 클래스에 있는 공백없이 한줄을 엔터칠때까지의 스캐너 기능값을 가진값을 변수 birth 담는다.

      if (birth.length() == 0) {//조건문 if 를 선언하여 birth 변수를 넣어 length() == 0 길이가 0값 일때 다음로직을 실행하게한다.
        System.out.println("생일을 입력해주세요.");//위에 로직이 실행될경우 출력되는문구
        continue;
      }
      break;
    }

    int id = memberService.join(loginId, loginPw, nickname, e_mail, birth);//지역변수 id를 선언하여 memberService 클래스에 있는 메소드의 함수를 위에 인스턴스 변수
    //loginId, loginPw, nickname,e_mail,birth 값을 담았다.

    System.out.printf("%d번 회원이 등록되었습니다.\n", id);//위에 로직이 실행 되었을 경우 출력되는 문구,.
  }


  public void login() { // login 이라는 함수를 선언.
    String loginId;// 자료형 스트렝이 loginId  인스턴스 변수 선언
    String loginPw;// 자료형 스트렝이 loginPw  인스턴스 변수 선언

    System.out.println("== 로그인 ==");//위에 로직이 실행될경우 출력되는문구.

    System.out.printf("로그인 아이디 : ");//위에 로직이 실행될경우 출력되는문구
    loginId = Container.scanner.nextLine().trim();//컨테이너어 클래스에있는 스캐너의 입력값(공백없이 엔터칠대까지 한줄을 loginId라는 변수에 담는다.

    if (loginId.length() == 0) {// 조건문 if문을 선언하여 loginId 입력값이 .length 가 ==0 한줄도없을시 다음 로직을 실행하게한다.
      System.out.println("로그인 아이디를 입력해주세요.");//위에 로직이 실행되면 출력되는문구.
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);//자료형 클래스  Member의 지역변수 member를 선언하여 memberService 에 있는 함수 MemberByLoginId를
    //get 하여 가져와서 변수 member에 담는다


    if (member == null) {//지역변수 member에 값이 null 일경우 다음 로직을 실행한다.
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
      loginPw = Container.scanner.nextLine().trim();// 컨테이너 클래스에 있는 공백없이 엔터칠수있는 문구를 가진 스캐너기능을 loginPw 변수에 담는다.

      if (loginPw.length() == 0) {//조건문 if문을 선언하여 loginPw 의 변수를 .length(길이)로 ==0 공백이엇을경우 다음로직을 실행할수있게한다
        System.out.println("로그인 비밀번호를 입력해주세요.");//위에 로직이 실행시 출력되는문구
        continue;
      }

      if (member.getLoginPw().equals(loginPw) == false) {//if 조건문을 선언하여서 db값이 담아져있는 지역변수 member 와 // 클래스 변수에 있는 loginPw를 get로 가져오고 해당
        //LoginPw값을 . equals로 비교하여  조건이 LoginPw일때  데이터베이스 에 저장된값과 비교하여 false 일경우 로직이 실행된다.
        loginTryCount++;//위에 조건문이 실행되어 초기화값 loginTryCount을 증가 시킨다.
        System.out.println("비밀번호가 일치하지 않습니다.");//위에 로직이 실행될경우 출력되는 문구
        continue;
      }

      System.out.printf("\"%s\"님 환영합니다.\n", member.getNickname());//해당 위에 로직이 정상적으로 다 실행이 됫을경우에 출력되는 문구
      withdrawalloginId = loginId;
      Container.session.login(member);// 컨테이너 클래스에 있는  ????????????????????????????????

      break;
    }

  }

  public void modifyloginPw() {
    String loginId;// 자료형 스트렝이 loginId  인스턴스 변수 선언
    String loginPw;// 자료형 스트렝이 loginPw  인스턴스 변수 선언

    System.out.println("== 로그인 ==");//위에 로직이 실행될경우 출력되는문구.

    System.out.printf("로그인 아이디 : ");//위에 로직이 실행될경우 출력되는문구
    loginId = Container.scanner.nextLine().trim();//컨테이너어 클래스에있는 스캐너의 입력값(공백없이 엔터칠대까지 한줄을 loginId라는 변수에 담는다.

    if (loginId.length() == 0) {// 조건문 if문을 선언하여 loginId 입력값이 .length 가 ==0 한줄도없을시 다음 로직을 실행하게한다.
      System.out.println("로그인 아이디를 입력해주세요.");//위에 로직이 실행되면 출력되는문구.
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);//자료형 클래스  Member의 지역변수 member를 선언하여 memberService 에 있는 함수 MemberByLoginId를
    //get 하여 가져와서 변수 member에 담는다

    if (member == null) {//지역변수 member에 값이 null 일경우 다음 로직을 실행한다.
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
      loginPw = Container.scanner.nextLine().trim();// 컨테이너 클래스에 있는 공백없이 엔터칠수있는 문구를 가진 스캐너기능을 loginPw 변수에 담는다.

      if (loginPw.length() == 0) {//조건문 if문을 선언하여 loginPw 의 변수를 .length(길이)로 ==0 공백이엇을경우 다음로직을 실행할수있게한다
        System.out.println("로그인 비밀번호를 입력해주세요.");//위에 로직이 실행시 출력되는문구
        continue;
      }

      if (member.getLoginPw().equals(loginPw) == false) {//if 조건문을 선언하여서 db값이 담아져있는 지역변수 member 와 // 클래스 변수에 있는 loginPw를 get로 가져오고 해당
        //LoginPw값을 . equals로 비교하여  조건이 LoginPw일때  데이터베이스 에 저장된값과 비교하여 false 일경우 로직이 실행된다.
        loginTryCount++;//위에 조건문이 실행되어 초기화값 loginTryCount을 증가 시킨다.
        System.out.println("비밀번호가 일치하지 않습니다.");//위에 로직이 실행될경우 출력되는 문구
        continue;
      }

      System.out.printf("\"%s\"님 환영합니다.\n", member.getLoginId());//해당 위에 로직이 정상적으로 다 실행이 됫을경우에 출력되는 문구
      Container.session.login(member);// Container 클래스에 있는 session 클래스 타입의 변수 session을 선언된것을 가지고와서 session 안에 있는 선언된 함수 login
      //불러와
      break;
    }
    System.out.println("수정을 시작합니다.");
    System.out.println("새 비밀번호를 입력해주세요.");
    String modifyloginPw = Container.scanner.nextLine().trim();

    memberService.modifyloginPw(loginId, modifyloginPw);//memberService의 클래스에서modifyloginPw에 함수를 불러와서 그값이 loginId,modifyloginPw일때 뢰직이 실행되게한다.
    System.out.println("비밀번호 변경이 완료 되었습니다.");
  }


  public void logout() {//logout 메소드를 선언한다.
    Container.session.logout();//세션 이라는 기능은 해당 정보를 저장하는 공간이라 보면된다..
    System.out.println("로그아웃 되었습니다.");
  }

  public void withdrawal() {//탈퇴 기능을  가지게끔 Withdrawal 함수를 선언한다...

    System.out.println("정말 떠나시려구요 ㅠㅠ?");
    System.out.println("확인 / 취소 중 입력해주세요");
    String flag = Container.scanner.nextLine().trim();//컨테이너어 클래스에있는 스캐너의 입력값(공백없이 엔터칠대까지 한줄을 flag라는 변수에 담는다.
    if (flag.equals("확인")) {
      System.out.println(withdrawalloginId);
      memberService.withdrawal(Container.session.loginedMember.getLoginId());// memberService 안에 withdrawal 함수를 작동하기 위해서  . 이후 login이 된 정보값 withdrawloginId로
      // 함수의 인자로 값을 넣어줫다.
      System.out.println("회원탈퇴가 정상적으로 되었습니다.");//위에 로직이 실행됫을경우 출력되는문구
    } else if (flag.equals("취소")) {//else 추가 조건문으로 flag 로 비교equals를 취소로 비교햇을시 그다음로직이 실행되게한다.
      System.out.println("취소되었습니다.");//위에 로직이 실행됫을경우 출력된다
    } else {//equals로 비교 대상이 아닌 다른 값을 입력햇을경우 밑에 로직이 실행된다.
      System.out.println("확인/ 취소 둘중에 하나만 고르시오");//위에 로직이 실행됫을경우 출력되는 문구
    }


  }



}