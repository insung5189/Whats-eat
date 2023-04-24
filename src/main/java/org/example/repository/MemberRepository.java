package org.example.repository;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.Map;

public class MemberRepository {

    public boolean isLoginIdDup(String loginId) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?", loginId);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }
    //회원가입..
    public int join(String loginId, String loginPw, String nickname, String e_mail, String birth) {
        SecSql sql = new SecSql();
        sql.append("INSERT INTO member");
        sql.append("SET regDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", loginId = ?", loginId);
        sql.append(", loginPw = ?", loginPw);
        sql.append(", nickname = ?", nickname);
        sql.append(", e_mail = ?", e_mail);
        sql.append(", birth = ?", birth);


        int id = DBUtil.insert(Container.conn, sql);

        return id;
    }
    public void modifyloginPw(String loginId,String modifyloginPw ){
        SecSql sql = new SecSql();
        sql.append("UPDATE `member`");
        sql.append("SET loginPw = ?",modifyloginPw);
        sql.append("where loginId = ?",loginId);//member Repository에서 where 는 sql에 if와 같다. 그 조건문 where를 loginId와 = < 값이 같을때 다음로직이 실행된다.
        DBUtil.update(Container.conn, sql);
    }

    public Member getMemberByLoginId(String loginId) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?", loginId);

        Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

        if (memberMap.isEmpty()) {
            return null;
        }

        return new Member(memberMap);
    }
    public void withdrawal(String loginId) {//탈퇴 기능을  가지게끔 Withdrawal 함수를 선언한다...
        System.out.println("로그인아이디"+ loginId);

        SecSql sql = new SecSql();

        sql.append("DELETE FROM `member`");//Mysql에 멤버안에 로우를 삭제할 로직 실행하게한다.
        sql.append("WHERE `loginId` = ?", loginId);
        DBUtil.delete(Container.conn, sql);
    }



    }

