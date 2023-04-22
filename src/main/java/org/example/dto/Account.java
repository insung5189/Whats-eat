package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Account { // 해당 테이블 칼럼명과 순서 일치시킬 것
    private int id;
    private String user_id;
    private String password;
    private String passwordConfirm;
    private String user_name;
    private String user_email;
    private String created_at;
    private String modified_at;
    private String birthday;
    /* 이전데이터 백업
    private int id;
    private String regDate;
    private String updateDate;
    private String loginNick;
    private String loginPw;
    private String email;
    private String name;
     */
    /* 이전데이터 백업
    String loginNick;
    String loginPw;
    String loginPwConfirm;
    String name;
    String email;
    String birth;
   */

    public Account(Map<String, Object> memberMap) { // 해당 칼럼명과 순서와 변수명 getter명 일치시킬 것
        this.id = (int) memberMap.get("id");
        this.user_id = (String) memberMap.get("user_id");
        this.password = (String) memberMap.get("password");
        this.user_name = (String) memberMap.get("user_name");
        this.user_email = (String) memberMap.get("user_email");
        this.created_at = (String) memberMap.get("created_at");
        this.modified_at = (String) memberMap.get("modified_at");
        this.birthday = (String) memberMap.get("birthday");
        /* 이전데이터 백업
        this.id = (int) memberMap.get("id");
        this.regDate = (String) memberMap.get("regDate");
        this.updateDate = (String) memberMap.get("updateDate");
        this.loginNick = (String) memberMap.get("user_nickname");
        this.loginPw = (String) memberMap.get("password");
        this.name = (String) memberMap.get("name");
        this.email = (String) memberMap.get("email");
         */
    }
}
