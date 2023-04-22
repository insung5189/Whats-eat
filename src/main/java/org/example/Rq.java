package org.example;

import org.example.util.Util; // Util 클래스 import(호출)

import java.util.Map; // map 유틸불러옴

public class Rq {
  String url; // App에서 스캐너 입력으로 받아오는 cmd변수를 담을 용도로 선언
  Map<String, String> params; // Map 타입으로 params 선언해주고 명령어 담을 용도로 활용
  String urlPath;

  public Rq(String url) { // 명령어(cmd)값을 Rq생성자 매개변수로 받아온다
    this.url = url; // 받아온 cmd값을 클래스 필드변수로 전달
    params = Util.getUrlParamsFromUrl(this.url); // 명령어문을 ?와 &와 =을 기준으로 나누어져 가공된 값들이 들어간 Map형태의 params가 담김.
    urlPath = Util.getUrlPathFromUrl(this.url); // 물음표 기준으로 두덩이로 나뉜 명령문 중 0번째 인덱스만 추출한 값.

  }

  public Map<String, String> getParams() {
    return params;
  }

  public int getIntParam(String paramName, int defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public String getUrlPath() {
    return urlPath;
  }
}