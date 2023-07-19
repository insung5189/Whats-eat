package org.example;

import org.example.controller.ArticleController;
import org.example.controller.AccountController;
import org.example.repository.ArticleRepository;
import org.example.repository.AccountRepository;
import org.example.service.ArticleService;
import org.example.service.AccountService;
import org.example.session.Session;

import java.sql.Connection;
import java.util.Scanner;

public class Container {

  public static ArticleRepository articleRepository;
  public static AccountRepository accountRepository;

  public static ArticleService articleService;
  public static AccountService accountService;

  public static ArticleController articleController;
  public static AccountController accountController;

  public static Scanner scanner;
  public static Session session;
  public static Connection conn;
  public static Rq rq;

  public static void init() {
    articleRepository = new ArticleRepository();
    accountRepository = new AccountRepository();

    articleService = new ArticleService();
    accountService = new AccountService();

    articleController = new ArticleController();
    accountController = new AccountController();

    scanner = new Scanner(System.in);
    session = new Session();
  }

}
