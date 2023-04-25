package org.example;


// JDBC를 사용하여 데이터베이스와 연결하고 쿼리를 실행하는 데 필요한 클래스
import java.sql.Connection; // 데이터베이스 연결을 나타냄
import java.sql.DriverManager; // JDBC 드라이버를 로드하고 Connection 객체를 생성하는 데 사용된다.
import java.sql.SQLException; // JDBC 작업 중에 발생할 수 있는 예외를 처리하기 위함임
import java.util.Scanner; // 스캐너유틸.

public class App {
    String guestCmd;
    public void run() { // Main클래스에서 호출당한 run메서드
        Container.scanner = new Scanner(System.in); // 컨테이너에 담긴 스캐너
        Container.init(); // 스캐너 기능 ON
        System.out.println("=".repeat(20));
        System.out.println("*".repeat(20));
        System.out.println("우리 오늘 뭐잡솨YOU?");
        System.out.println("*".repeat(20));
        System.out.println("=".repeat(20));

        while (true) { // 명령을 조건에 따라 반복적으로 물어볼 while문

//            System.out.println("**입력 가능한 명령어**");
//            System.out.println("회원가입");
//            System.out.println("로그인");
//            System.out.println("상태");
//            System.out.println("등록");
//            System.out.println("목록");
//            System.out.println("자세히보기");
//            System.out.println("수정");
//            System.out.println("삭제");
//            System.out.println("회원가입");
            System.out.println("");
            System.out.println("\"도움말\"을 입력하시면 입력 가능한 명령어를 볼 수 있습니다.");
            System.out.printf("명령어를 입력해주세요 :  "); // 명령어 입력 유도 출력문

            String cmd = Container.scanner.nextLine(); // 스캐너로 입력받은 명령어가 cmd변수에 저장됨.
            System.out.println("");
            Container.rq = new Rq(cmd); // 컨테이너클래스에 선언된 rq변수에 Rq(cmd)클래스 객체를 할당


      /*해석 :
      1. 입력받은 명령문을 Rq클래스로 보내서 url이라는 변수에 저장함.
      2. Rq생성자 내부에서 Util.getUrlParamsFromUrl로 명령을 보내서 split으로 문자열을 쪼개서 분석함
      3. key와 value값으로 나눠서 값들이 params와 urlPath로 들어옴
       */

            // DB 연결
            Connection conn = null; // null값을 넣어주는 이유? => 초기화 그냥 아무거나 일단 넣고 시작

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC드라이버를 로딩하여 메모리에 올리기 위한 함수(드라이버는 데이터베이스 벤더(vendor)에서 제공하는 jar 파일로 제공)
            } catch (ClassNotFoundException e) {
                System.out.println("예외 : MySQL 드라이버 로딩 실패");
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            String url = "jdbc:mysql://127.0.0.1:3306/taste_genie?"; // taste_genie == DB이름
            url += "useUnicode=true&";
            url += "characterEncoding=utf8&";
            url += "autoReconnect=true&";
            url += "serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&";
            url += "zeroDateTimeBehavior=convertToNull";
            // 드라이버를 연결하기 위한 url을 변수에 담음

            try {
                conn = DriverManager.getConnection(url, "root", ""); // 연결하고자 하는 주소, 설정, SQL유저이름과 비밀번호정보를 Connection객체가 할당된 conn에 담아줌

                Container.conn = conn; // 그렇게 Connection정보가 담긴 conn을 Container의 conn으로 전달함.(왜? 컨테이너에다 기능적인 부분을 모아두려고)
                //추후에 DB 연결이 필요한 다른 부분에서 Connection 객체를 사용할 수 있도록 컨테이너 클래스에 저장

                // action 메서드 실행
                action(Container.rq, cmd); // action메서드를 통해서 입력받은 명령어를 받아들이고 대조하여 해당 로직을 실행할 수 있도록 함.

            } catch (SQLException e) { // DB연결에 실패하거나 구문에 오류가 있는 경우 발생할 수 있는 오류
                System.out.println("예외 : MySQL 드라이버 로딩 실패");
                System.out.println("프로그램을 종료합니다.");
                break; // 드라이버 로딩에 실패하면 프로그램을 종료하기 위해 break로 while문을 빠져나감
            } finally { // DB연결을 종료하기 위한 finally문
                try {
                    if (conn != null && !conn.isClosed()) { // Connection타입의 변수conn의 값이 null이 아니거나 conn이 아직 열려있다면
                        conn.close(); // 위 조건이 만족한다면 conn.close();로 DB연결을 닫으라는 함수
                    }
                } catch (SQLException e) { // DB연결에 실패하거나 구문에 오류가 있는 경우 발생할 수 있는 오류
                    e.printStackTrace(); // 예외가 발생했을 때 콘솔에서 예외(에러)정보를 가져와주는 역할(디버그에 큰 도움이 됨.)
                }
            }
            // DB 연결 끝
        }
        Container.scanner.close(); // 위 블럭이 끝나면서 DB연결을 마쳤으니 스캐너도 종료
    }

    private void action(Rq rq, String cmd) { // 명령어를 받아서 while문 내부에서 실행해줄 action메서드
        if (rq.getUrlPath().equals("회원가입")) { // 회원가입 기능
            Container.accountController.join();
        } else if (rq.getUrlPath().equals("로그인")) { // 로그인 기능
            Container.accountController.login();
        } else if (rq.getUrlPath().equals("로그아웃")) { // 로그아웃 기능
            Container.accountController.logout();
        } else if (rq.getUrlPath().equals("비밀번호수정")) {
            Container.accountController.modifyPassword();
        } else if (rq.getUrlPath().equals("상태")) { // 로그인상태보기
            Container.accountController.whoami();
        } else if (rq.getUrlPath().equals("등록")) { // 등록 기능
            Container.articleController.write();
        } else if (rq.getUrlPath().equals("목록")) { // 목록 기능
            Container.articleController.showList();
        } else if (rq.getUrlPath().equals("자세히보기")) { // ??모름 아마 글 본문 보기 같은건가
            Container.articleController.showDetail();
        } else if (rq.getUrlPath().equals("수정")) { // 수정 기능
            Container.articleController.modify();
        } else if (rq.getUrlPath().equals("삭제")) { // 삭제 기능
            Container.articleController.delete();
        } else if (rq.getUrlPath().equals("게스트")) { // 삭제 기능
            Container.accountController.guest(cmd);
        } else if (rq.getUrlPath().equals("회원탈퇴")) {
            Container.accountController.withdrawal();
        } else if (cmd.equals("종료")) { // 프로그램 종료 기능
            System.out.println("시스템 종료");
            System.exit(0); // System.exit(0);을 이용하면 현재 사용중인 JVM자체가 종료되기 때문에 프로그램을 강제로 종료할 수 있다
            // 매개변수로 전달된 '0' 은 프로그램 종료 코드를 나타냄 0이면 정상적인 종료를 뜻함.
        } else if (cmd.equals("도움말")) {
            System.out.println("===============입력 가능한 명령어===============");
            System.out.println("게스트로 입장 : 게스트");
            System.out.println("회원가입 하기 : 회원가입");
            System.out.println("로그인 하기 : 로그인");
            System.out.println("비밀번호 수정하기 : 비밀번호수정");
            System.out.println("로그아웃 하기 : 로그아웃");
            System.out.println("게시물 등록하기 : 등록");
            System.out.println("목록보기: 목록");
            System.out.println("현재 로그인중인 계정을 보시려면 : 상태");
            System.out.println("게시물 본문보기 : 자세히보기?id=(게시물번호)");
            System.out.println("예) 자세히보기?id=1");
            System.out.println("게시물 수정하기 : 수정?id=(게시물번호)");
            System.out.println("예) 수정?id=1");
            System.out.println("게시물 삭제하기 : 삭제?id=(게시물번호)");
            System.out.println("예) 삭제?id=1");
            System.out.println("회원탈퇴 하기 : 회원탈퇴");
            System.out.println("================================================");
        } else { // 아무 조건도 해당하지 않을 때(명령어를 잘못 입력했을 때)
            System.out.println("명령어를 확인해주세요.");
        }
    }

}