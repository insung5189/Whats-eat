package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Article {
  public int id;
  public String title;
  public String content;
  public String created_at;
  public String modified_at;
  public int hit;
  public int like;
  public int account_id;
  public int category_id;
  public String extra__writerName;
  public String extra__category;

  /* 원본데이터 백업
  public int id;
  public String regDate;
  public String updateDate;
  public int memberId;
  public String title;
  public String body;
  public int hit;
  public String extra__writerName;
   */

  /*
  CREATE TABLE `post` (
	`id`			INT 		NOT NULL auto_increment PRIMARY KEY,
	`title`			CHAR(255)	NOT NULL,
	`content`		TEXT		NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`hit`			INT			NOT NULL,
	`like`			INT			NOT NULL,
	`account_id`	INT			NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id)
);
   */

  public Article(Map<String, Object> articleMap) {
    this.id = (int) articleMap.get("id");
    this.title = (String) articleMap.get("title");
    this.content = (String) articleMap.get("content");
    this.created_at = (String) articleMap.get("created_at");
    this.modified_at = (String) articleMap.get("modified_at");
    this.hit = (int) articleMap.get("hit");
    this.like = (int) articleMap.get("like");
    this.account_id = (int) articleMap.get("account_id");
    this.category_id = (int) articleMap.get("category_id");
    /* 원본데이터 백업
    this.id = (int) articleMap.get("id");
    this.regDate = (String) articleMap.get("regDate");
    this.updateDate = (String) articleMap.get("updateDate");
    this.memberId = (int) articleMap.get("memberId");
    this.title = (String) articleMap.get("title");
    this.body = (String) articleMap.get("body");
    this.hit = (int) articleMap.get("hit");
     */

    if(articleMap.get("extra__writerName") != null) {
      this.extra__writerName = (String) articleMap.get("extra__writerName");
    }
    if (articleMap.get("extra__category") != null) {
      this.extra__category = (String) articleMap.get("extra__category");
    }
  }
}