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

    public Account getAccountBy_user_id(String user_id) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM `account`");
        sql.append("WHERE user_id = ?", user_id);

        Map<String, Object> accountMap = DBUtil.selectRow(Container.conn, sql);

        if (accountMap.isEmpty()) {
            return null;
        }

        return new Account(accountMap);
    }


}
