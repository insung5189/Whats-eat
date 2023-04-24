package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;


// JDBC를 사용하여 데이터베이스와 연결하고 쿼리를 실행하는 데 필요한 클래스
import java.sql.Connection; // 데이터베이스 연결을 나타냄
import java.sql.DriverManager; // JDBC 드라이버를 로드하고 Connection 객체를 생성하는 데 사용된다.
import java.sql.SQLException; // JDBC 작업 중에 발생할 수 있는 예외를 처리하기 위함임
import java.util.Scanner; // 스캐너유틸.

public class App {
    public void run() {
        Container.scanner = new Scanner(System.in); // 컨테이너에 담긴 스캐너
        Container.init(); // 스캐너 기능 ON

        while (true) { // 명령을 조건에 따라 반복적으로 물어볼 while문
            System.out.printf("명령어) "); // 명령어 입력 유도 출력문
            String cmd = Container.scanner.nextLine(); // 스캐너로 입력받은 명령어가 cmd변수에 저장됨.

            Container.rq = new Rq(cmd); // 컨테이너클래스에 선언된 rq변수에 Rq(cmd)클래스 객체를 할당


            // DB 연결
            Connection conn = null; // null값을 넣어주는 이유?

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("예외 : MySQL 드라이버 로딩 실패");
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";


            try {
                conn = DriverManager.getConnection(url, "root", ""); // 연결하고자 하는 주소, 설정, SQL유저이름과 비밀번호정보를 Connection객체가 할당된 conn에 담아줌

                Container.conn = conn;


                // action 메서드 실행
                action(Container.rq, cmd);

            } catch (SQLException e) { // DB연결에 실패하거나 구문에 오류가 있는 경우 발생할 수 있는 오류
                System.out.println(e.getMessage());
                System.out.println("예외 : MySQL 드라이버 로딩 실패111");
                System.out.println("프로그램을 종료합니다.");
                break;
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        Container.scanner.close();
    }

    private void action(Rq rq, String cmd) {
        if (rq.getUrlPath().equals("회원가입")) {//회원가입 입력시. 그다음로직을 탈수있게한다.
            Container.memberController.join();//컨테이너 클래스에 있는 멤버십 컨트롤에 있는 조인 함수를 사용하겟다.
        } else if (rq.getUrlPath().equals("로그인")) {
            Container.memberController.login();
        } else if (rq.getUrlPath().equals("로그아웃")) {
            Container.memberController.logout();
        } else if (rq.getUrlPath().equals("수정")) {
            Container.memberController.modifyloginPw();
        } else if (rq.getUrlPath().equals("회원탈퇴")) {
            Container.memberController.withdrawal();
        }else if (rq.getUrlPath().equals("댓글")){
            Container.articleController.comment();
        }
        else if (cmd.equals("system exit")) {
            System.out.println("시스템 종료");
            System.exit(0); // System.exit(0);을 이용하면 현재 사용중인 JVM자체가 종료되기 때문에 프로그램을 강제로 종료할 수 있다
            // 매개변수로 전달된 '0' 은 프로그램 종료 코드를 나타냄 0이면 정상적인 종료를 뜻함.
        } else { // 아무 조건도 해당하지 않을 때(명령어를 잘못 입력했을 때)
            System.out.println("명령어를 확인해주세요.");
        }
    }

}