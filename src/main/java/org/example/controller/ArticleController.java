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
        String mapping_id_string;
        if (!Container.session.isLogined()) {
            System.out.println("게스트모드 에서는 등록할 수 없습니다.");
            System.out.println("목록과 글 내용 열람만 가능합니다.");
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
        /*
    if (cmd.equals("게스트")) {
      System.out.println("게스트모드 에서는 등록할 수 없습니다.");
      System.out.println("목록과 글 내용 열람만 가능합니다.");
//      continue;
    } else if(Container.session.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }
      */



        System.out.println("== 게시물 등록 ==");
        System.out.printf("1. 한식 2. 양식 3. 중식 4. 일식 5. 퓨전 6. 공지(관리자용)");
        System.out.printf("카테고리 넘버 입력 : \n");
        int category_id = Integer.parseInt(Container.scanner.nextLine());
        switch (category_id) {
            // 결국 actionCode = commandBits[0] 이기 때문에 switch문이 받는 매개변수값은 commandBits[0]의 값이다.(자세한 내용은 Rq생성자 내부로직 참고)
            // switch문을 이용해서 명령을 구분하여 해당 케이스별로 로직을 실행하도록 함.
            case 1:
                mapping_id_string = "한식";
                break; // switch문에선 break;가 아닌 return;으로 탈출한다
            case 2:
                mapping_id_string = "양식";
                break;
            case 3:
                mapping_id_string = "중식";
                break;
            case 4:
                mapping_id_string = "일식";
                break;
            case 5:
                mapping_id_string = "퓨전";
                break;
            case 6:
                mapping_id_string = "공지(관리자용)";
                break;
            default: mapping_id_string = null;
        }
        /*
        String mapping_id_string;
        if (category_id == 1) {
            mapping_id_string = "한식";
        } else if (category_id == 2) {
            mapping_id_string = "양식";
        } else if (category_id == 3) {
            mapping_id_string = "중식";
        } else if (category_id == 4) {
            mapping_id_string = "일식";
        } else if (category_id == 5) {
            mapping_id_string = "퓨전";
        } else if (category_id == 6) {
            mapping_id_string = "공지(관리자용)";
        } else {
            System.out.println("카테고리 넘버를 잘못 입력하셨습니다.");
            System.out.printf("1. 한식 2. 양식 3. 중식 4. 일식 5. 퓨전 6. 공지(관리자용)");
            System.out.println("위 넘버 중 한 개를 입력 해주세요.");
        }

         */
        System.out.printf("제목 : ");
        String title = Container.scanner.nextLine();
        System.out.printf("내용 : ");
        String content = Container.scanner.nextLine();
        int hit = 0;
        int like = 0;

        int account_id = Container.session.loginedMemberId;
        int id = articleService.write(title, content, hit, like, account_id, category_id);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
    }


    public void showList() {
        /*
        if (!Container.session.isLogined()) {
            System.out.println("로그인 하시거나 게스트 모드를 이용해보세요");
            return;
        }
         */
        /*
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 하시거나 게스트 모드를 이용해보세요");
            return;
        }
         */

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

        System.out.println("카테고리 / 번호 / 작성날짜 / 작성자 / 제목 / 조회수 / 좋아요 수");

        for (Article article : articles) {
            System.out.printf("%s / %d / %s / %s / %s / %d / %d\n", article.extra__category, article.id, article.created_at, article.extra__writerName, article.title, article.hit, article.like);
        }
    }

    public void showDetail() { // 본문보기 자세히보기?id=1
        /*
        if (cmd.equals("게스트")) {
            System.out.println("게스트모드 입니다 목록과 글 내용 열람만 가능합니다.");
//      continue;
        }
         */
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

        System.out.printf("카테고리 : %s\n", article.extra__category);
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("등록날짜 : %s\n", article.created_at);
        System.out.printf("수정날짜 : %s\n", article.modified_at);
        System.out.printf("작성자 : %s\n", article.extra__writerName);
        System.out.printf("조회수 : %d\n", article.hit);
        System.out.printf("좋아요 수 : %d\n", article.like);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);
    }

    public void modify() {
        if (!Container.session.isLogined()) {
            System.out.println("게스트모드 에서는 수정할 수 없습니다.");
            System.out.println("목록과 글 내용 열람만 가능합니다.");
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
        /*
        if (cmd.equals("게스트")) {
            System.out.println("게스트모드 에서는 수정할 수 없습니다.");
            System.out.println("목록과 글 내용 열람만 가능합니다.");
//      continue;
        } else if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
         */

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

        if (article.account_id != Container.session.loginedMemberId) {
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
        if (!Container.session.isLogined()) {
            System.out.println("게스트모드 에서는 삭제할 수 없습니다.");
            System.out.println("목록과 글 내용 열람만 가능합니다.");
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
    /* 원본데이터 백업
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }
     */

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

        if (article.account_id != Container.session.loginedMemberId) {
            System.out.println("권한이 없습니다");
            return;
        }

        articleService.delete(id);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
    }

}