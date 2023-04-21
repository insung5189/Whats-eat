package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Account {
  private int id;
  private String regDate;
  private String updateDate;
  private String loginNick;
  private String loginPw;
  private String email;
  private String name;

  public Account(Map<String, Object> memberMap) {
    this.id = (int) memberMap.get("id");
    this.regDate = (String) memberMap.get("regDate");
    this.updateDate = (String) memberMap.get("updateDate");
    this.loginNick = (String) memberMap.get("user_nickname");
    this.loginPw = (String) memberMap.get("password");
    this.name = (String) memberMap.get("name");
    this.email = (String) memberMap.get("email");
  }
}
