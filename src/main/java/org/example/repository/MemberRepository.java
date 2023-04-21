package org.example.repository;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.Map;

public class MemberRepository {

    public boolean isloginNickDup(String loginNick) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `account`");
        sql.append("WHERE `user_nickname` = ?", loginNick);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }
    public boolean isemailDup(String email) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `account`");
        sql.append("WHERE `user_email` = ?", email);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public int join(String loginNick, String loginPw, String email, String birth, String name) {
        SecSql sql = new SecSql();
        /*
        sql.append("INSERT INTO account");
        sql.append("SET regDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", loginNick = ?", loginNick);
        sql.append(", loginPw = ?", loginPw);
        sql.append(", name = ?", name);
         */ // 파이널코드 원본데이터
        // 이하 뭐잡솨YOU?서비스에 맞춘 테이블정보
        sql.append("INSERT INTO account");
        sql.append("SET `created_at` = NOW()");
        sql.append(", `user_nickname` = ?", loginNick);
        sql.append(", `password` = ?", loginPw);
        sql.append(", `user_email` = ?", email);
        sql.append(", `birth` = ?", birth);
        /* 쿼리문 목표
        insert into account
        set `user_nickname` = 'inseong',
	        `password` = '123',
	        `user_email` = 'insung5189@gmail.com',
	        `created_at` = NOW(),
	        `birth` = '1993-11-03';
         */ // 쿼리문 목표

        int id = DBUtil.insert(Container.conn, sql);

        return id;
    }

    public Member getMemberByloginNick(String loginNick) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM `account`");
        sql.append("WHERE user_nickname = ?", loginNick);

        Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

        if (memberMap.isEmpty()) {
            return null;
        }

        return new Member(memberMap);
    }


}
