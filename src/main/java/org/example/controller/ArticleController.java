package org.example.controller;


import org.example.Container;
import org.example.dto.Article;
import org.example.service.ArticleService;

import java.util.List;

import static org.example.Container.rq;

public class ArticleController {
  /*
  public int id;
  public String title;
  public String content;
  public String create_at;
  public String modified_at;
  public int hit;
  public int like;
  public int account_id;
  public String extra__writerName;
   */

  private ArticleService articleService;

  public ArticleController() {
    articleService = Container.articleService;
  }

  public void write() {
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.scanner.nextLine();
    System.out.printf("내용 : ");
    String content = Container.scanner.nextLine();
    int hit = 0;
    int like = 0;

    int account_id = Container.session.loginedMemberId;
    int id = articleService.write(title, content, hit, like, account_id);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }
  public void showList() {
    System.out.println("== 게시물 리스트 ==");
    int page = rq.getIntParam("page", 1);
    String searchKeyword = rq.getParam("searchKeyword", "");
    int pageItemCount = 10;

    // 임시
    pageItemCount = 10;

    List<Article> articles = articleService.getForPrintArticleById(page, pageItemCount, searchKeyword);

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("번호 / 작성날짜 / 작성자 / 제목 / 조회수 / 좋아요 수");

    for (Article article : articles) {
      System.out.printf("%d / %s / %s / %s / %d / %d\n", article.id, article.create_at, article.extra__writerName, article.title, article.hit, article.like);
    }
  }

  public void showDetail() {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    articleService.increaseHit(id);
    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("등록날짜 : %s\n", article.create_at);
    System.out.printf("수정날짜 : %s\n", article.modified_at);
    System.out.printf("작성자 : %s\n", article.account_id);
    System.out.printf("조회수 : %d\n", article.hit);
    System.out.printf("좋아요 수 : %d\n", article.like);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.content);
  }

  public void modify() {
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    boolean articleExists = articleService.articleExists(id);

    if (articleExists == false) {
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

    if(article.account_id != Container.session.loginedMemberId) {
      System.out.println("권한이 없습니다");
      return;
    }

    System.out.printf("새 제목 : ");
    String title = Container.scanner.nextLine();
    System.out.printf("새 내용 : ");
    String content = Container.scanner.nextLine();

    articleService.update(id, title, content);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void delete() {
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    System.out.printf("== %d번 게시글 삭제 ==\n", id);

    Article article = articleService.getArticleById(id);

    boolean articleExists = articleService.articleExists(id);

    if (articleExists == false) {
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

    if(article.account_id != Container.session.loginedMemberId) {
      System.out.println("권한이 없습니다");
      return;
    }

    articleService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

}
