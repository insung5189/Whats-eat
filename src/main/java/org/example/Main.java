package org.example;

import org.example.exception.SQLErrorException;

public class Main {
  public static void main(String[] args) {
    try { // 먼저 시도되는 try문 
      new App().run(); // App 클래스의 run메서드를 실행함.
    }
    catch (SQLErrorException e) { // SQLErrorException이 발생하면 실행되는 catch문
      System.out.println(e.getMessage()); // 간결하게 에러 출력 한번 (아마 Database connection error)
      e.getOrigin().printStackTrace(); // 복잡하게 에러 출력 한번(에러 발생 근원지를 담고있음)
      // SQLErrorException클래스에 getOrigin메서드에 있는 Exception origin이 연결되서 예외 발생 원인에 대한 정보를 가져옴
      // 그리고 결국 그 정보는 Exception의 부모 클래스인 Throwable내부에 생성자를 호출하여 printStackTrace메서드를 호출하여 System.err를 가져옴
    }
  }
}