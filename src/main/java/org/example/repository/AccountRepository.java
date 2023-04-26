package org.example.repository;

import org.example.Container;
import org.example.dto.Account;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.Map;

public class AccountRepository {

    public boolean isUser_IdDup(String user_id) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `account`");
        sql.append("WHERE `user_id` = ?", user_id);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }
    public boolean isUser_emailDup(String email) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `account`");
        sql.append("WHERE `user_email` = ?", email);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public int join(String user_id, String password, String user_name, String user_email, String birthday) {
        SecSql sql = new SecSql();
        /* 이전데이터 백업
        sql.append("INSERT INTO account");
        sql.append("SET regDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", loginNick = ?", loginNick);
        sql.append(", loginPw = ?", loginPw);
        sql.append(", name = ?", name);
         */ // 파이널코드 원본데이터
        // 이하 taste_genie에 맞춘 테이블정보
        sql.append("INSERT INTO account");
        sql.append("SET `user_id` = ?", user_id);
        sql.append(", `password` = ?", password);
        sql.append(", `user_name` = ?", user_name);
        sql.append(", `user_email` = ?", user_email);
        sql.append(", `created_at` = NOW()");
        sql.append(", `modified_at` = NOW()");
        sql.append(", `birthday` = ?", birthday);

        /* 쿼리문 목표
        insert into account
        set `user_id` = 'inseong',
            `password` = '123',
            `user_name` = '황인성',
            `user_email` = 'insung5189@gmail.com',
            `created_at` = NOW(),
            `modified_at` = NOW(),
            `birthday` = '1993-11-03';
         */ // 쿼리문 목표

        int id = DBUtil.insert(Container.conn, sql);

        return id;
    }

    public void modifyPassword(String user_id, String modifyPassword ){
        SecSql sql = new SecSql();
        sql.append("UPDATE `account`");
        sql.append("SET `password` = ?", modifyPassword);
        sql.append("where `user_id` = ?", user_id);//member Repository에서 where 는 sql에 if와 같다. 그 조건문 where를 loginId와 = < 값이 같을때 다음로직이 실행된다.
        DBUtil.update(Container.conn, sql);
    }

    public Account getAccountBy_user_id(String user_id) { // DB에서 SELECT문을 사용하여 account 테이블에 접근, 입력받은 user_id값을 담아서 쿼리실행
        SecSql sql = new SecSql(); // 쿼리문을 DB로 전달하기 위한 수단(객체형태)

        sql.append("SELECT *"); // DB로 전달되는 SELECT 쿼리문
        sql.append("FROM `account`");
        sql.append("WHERE user_id = ?", user_id);

        Map<String, Object> accountMap = DBUtil.selectRow(Container.conn, sql); // 연결정보랑 SQL문 정보를 담고 Map형태로 전달 후 조회

        if (accountMap.isEmpty()) { // 만약 Map에 데이터를 조회했는데 값이 없다면.
            return null; // null값을 리턴한다.
        }

        return new Account(accountMap); // accountMap값이 비어있지 않다면 해당로직을 실행.
        // Account클래스의 생성자 모음으로 보내고 결정적으론 Account클래스의 user_id값을 담게됨
        // 그렇게 user_id변수의 형태로 Account 생성자의 매개변수로 가게되고 그 user_id는 생성자 내부에 user_id에 부여되고 getter로 사용될 수 있음

    }

    public void withdrawal(String user_id) {//탈퇴 기능을  가지게끔 Withdrawal 함수를 선언한다...
        System.out.println("로그인아이디 : "+ user_id);

        SecSql sql = new SecSql();

        sql.append("DELETE FROM `account`");//Mysql에 멤버안에 로우를 삭제할 로직 실행하게한다.
        sql.append("WHERE `user_id` = ?", user_id);
        DBUtil.delete(Container.conn, sql); // <== 오류터짐



    }


}
