package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Member {
  private int id;
  private String regDate;
  private String updateDate;
  private String loginNick;
  private String loginPw;
  private String email;
  private String name;

  public Member(Map<String, Object> memberMap) {
    this.id = (int) memberMap.get("id");
    this.regDate = (String) memberMap.get("regDate");
    this.updateDate = (String) memberMap.get("updateDate");
    this.loginNick = (String) memberMap.get("loginNick");
    this.loginPw = (String) memberMap.get("loginPw");
    this.name = (String) memberMap.get("name");
    this.email = (String) memberMap.get("email");
  }
}
