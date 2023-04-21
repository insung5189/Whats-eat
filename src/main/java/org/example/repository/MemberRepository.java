package org.example.repository;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.Map;

public class MemberRepository {

    public boolean isLoginIdDup(String loginId) { // member테이블상에 id중복확인용 메서드
        SecSql sql = new SecSql(); // sql객체를 인스턴스화 한 후 쿼리를 작성해서 넣어줄 용도로 사용

        sql.append("SELECT COUNT(*) > 0"); // loginid와 일치하는 레코드의 수가 0보다 큰지(있는지) 확인하기 위한 쿼리
        sql.append("FROM `member`"); // member테이블에서 수행할 것을 지칭
        sql.append("WHERE loginId = ?", loginId); // 테이블상 로그인아이디와 사용자가 입력한 로그인아이디가 일치하는지

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public int join(String loginId, String loginPw, String name) {
        SecSql sql = new SecSql();
        sql.append("INSERT INTO member");
        sql.append("SET regDate = NOW()");
        sql.append(", updateDate = NOW()");
        sql.append(", loginId = ?", loginId);
        sql.append(", loginPw = ?", loginPw);
        sql.append(", name = ?", name);

        int id = DBUtil.insert(Container.conn, sql);

        return id;
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
}
