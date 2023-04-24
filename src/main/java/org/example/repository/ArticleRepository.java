package org.example.repository;

import org.example.Container;
import org.example.dto.Article;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    public int write(String title, String content, int hit, int like, int account_id, int category_id) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO post");
        sql.append(" SET title = ?", title);
        sql.append(", `content` = ?", content);
        sql.append(", created_at = NOW()");
        sql.append(", modified_at = NOW()");
        sql.append(", hit = ?", hit);
        sql.append(", `like` = ?", like);
        sql.append(", account_id = ?", account_id);
        sql.append(", category_id = ?", category_id);
        /*
        insert into post
        set `title` = '이야 이 식빵 무지 달거같긴한데',
	    `content` = '모래반지 빵야빵야 이야 이 식빵 무지 달다',
        `created_at` = NOW(),
	    `modified_at` = NOW(),
	    `hit` = 1,
	    `like` = 0,
	    `account_id` = 1;
         */



        int id = DBUtil.insert(Container.conn, sql);
        return id;
    }

    public boolean articleExists(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM post");
        sql.append("WHERE id = ?", id);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public void delete(int id) {
        SecSql sql = new SecSql();

        sql.append("DELETE FROM post");
        sql.append("WHERE id = ?", id);

        DBUtil.delete(Container.conn, sql);
    }

    public void update(int id, String title, String content) {
        SecSql sql = new SecSql();

        sql.append("UPDATE post");
        sql.append("SET modified_at = NOW()");
        sql.append(", title = ?", title);
        sql.append(", `content` = ?", content);
        sql.append("WHERE id = ?", id);

        DBUtil.update(Container.conn, sql);
    }

    public Article getArticleById(int id) {
        // 게시글(post)에 대한 account_id값과 category_id값을 받아오는 과정
        SecSql sql = new SecSql();
        sql.append("SELECT P.*");
        sql.append(", C.category_name AS extra__category, A.user_name AS extra__writerName");
        sql.append("FROM post AS P");
        sql.append("INNER JOIN account AS A");
        sql.append("ON P.account_id = A.id");
        sql.append("INNER JOIN category AS C");
        sql.append("ON P.category_id = C.id");
        sql.append("WHERE P.id = ?", id);

/*
        SELECT P.*, C.category_name AS extra__category, A.user_name AS extra__writerName
        FROM post AS P
        INNER JOIN account AS A
        ON P.account_id = A.id
        INNER JOIN category AS C
        ON P.category_id = C.id
        WHERE P.id = 3;
 */

        /* 이전데이터 백업
        sql.append("SELECT A.*");
        sql.append(", M.name AS extra__writerName");
        sql.append("FROM article AS A");
        sql.append("INNER JOIN member AS M");
        sql.append("ON A.memberId = M.id");
        sql.append("WHERE A.id = ?", id);
        A => P
        M => A
        name => user_name
        article => post
        member => account
        memberId => account_id
         */


        Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);

        if (articleMap.isEmpty()) {
            return null;
        }
        return new Article(articleMap);
    }
    public Article getArticleByCategory(int id) {
        SecSql sql = new SecSql();
// 게시글(post)에 대한 category_id값을 받아오는 과정
        sql.append("SELECT P.*");
        sql.append(", C.category_name AS extra__category");
        sql.append("FROM post AS P");
        sql.append("INNER JOIN category AS C");
        sql.append("ON P.category_id = C.id");
        sql.append("WHERE P.id = ?", id);
        /*
        post와 category테이블 inner join

        post에서 모든 열을 P.*로 선택 (select P.*)
        category에서 category_name칼럼을 선택 (, C.category_name)
        category_name칼럼은 mapping_id_string 으로 별명지정

        조인 조건은 "post" 테이블의 "category_id" 열이 "category" 테이블의 "id" 열과 일치하는 행만 결과에 포함.
        WHERE 절은 "post" 테이블의 "id" 열이 2인 행만 결과에 포함.
         */
        /*
        post와 account 테이블 inner join

        post에서 모든 열을 P.*로 선택 (SELECT P.*)
        account에서 user_name칼럼을 선택 (, A.user_name)
        user_name칼럼은 extra__writerName 으로 별명지정

        조인 조건은 "post" 테이블의 "account_id" 열이 "account" 테이블의 "id" 열과 일치하는 행만 결과에 포함

        WHERE 절은 "post" 테이블의 "id" 열이 2인 행만 결과에 포함
         */

//        SELECT P.*, A.user_name AS extra__writerName FROM post AS P INNER JOIN account AS A ON P.account_id = A.id WHERE P.id = 2;

        /* 이전데이터 백업
        sql.append("SELECT A.*");
        sql.append(", M.name AS extra__writerName");
        sql.append("FROM article AS A");
        sql.append("INNER JOIN member AS M");
        sql.append("ON A.memberId = M.id");
        sql.append("WHERE A.id = ?", id);
        A => P
        M => A
        name => user_name
        article => post
        member => account
        memberId => account_id
         */

        Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);

        if (articleMap.isEmpty()) {
            return null;
        }

        return new Article(articleMap);
    }

    public List<Article> getArticles(Map<String, Object> args, String searchKeyword) {
        SecSql sql = new SecSql();

        if (args.containsKey("searchKeyword")) {
            searchKeyword = (String) args.get("searchKeyword");
        }

        int limitFrom = -1;
        int limitTake = -1;

        if (args.containsKey("limitFrom")) {
            limitFrom = (int) args.get("limitFrom");
        }

        if (args.containsKey("limitTake")) {
            limitTake = (int) args.get("limitTake");
        }

        /*
        A => P
        M => A
        name => user_name
        article => post
        member => account
        memberId => account_id
        sql.append("SELECT A.*, M.name AS extra__writerName");
        sql.append("FROM article AS A");
        sql.append("INNER JOIN member AS M");
        sql.append("ON A.memberId = M.id");
        if (searchKeyword.length() > 0) {
            sql.append("WHERE A.title LIKE CONCAT('%', ?, '%')", searchKeyword);
        }
        sql.append("ORDER BY A.id DESC");
         */
        /*chatGPT 추천쿼리
        SELECT P.*, A.user_name AS extra__writerName
        FROM post AS P
        INNER JOIN account AS A ON P.account_id = A.id
        ORDER BY P.id DESC
        LIMIT ?, ?
         */


        sql.append("SELECT P.*, C.category_name AS extra__category, A.user_name AS extra__writerName");
        sql.append("FROM post AS P");
        sql.append("INNER JOIN account AS A");
        sql.append("ON P.account_id = A.id");
        sql.append("INNER JOIN category AS C");
        sql.append("ON P.category_id = C.id");
        if (searchKeyword.length() > 0) {
            sql.append("WHERE P.title LIKE CONCAT('%', ?, '%')", searchKeyword);
        }
        sql.append("ORDER BY P.id DESC");

        if (limitFrom != -1) {
            sql.append("LIMIT ?, ?", limitFrom, limitTake);
        }

        List<Article> articles = new ArrayList<>();

        List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap)); //***
        }

        return articles;
    }


    public void increaseHit(int id) {
        SecSql sql = new SecSql();

        sql.append("UPDATE post");
        sql.append("SET hit = hit + 1");
        sql.append("WHERE id = ?", id);

        DBUtil.update(Container.conn, sql);
    }
}
