package org.example.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class Util {// 라이브러리라고 생각하고 상세로직은 자세히 볼 필요 없음

  public static String f(String format, Object... args) {
    return String.format(format, args);
  }

  public static Map<String, Object> mapOf(Object... args) {
    if (args.length % 2 != 0) {
      throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
    }

    int size = args.length / 2;

    Map<String, Object> map = new LinkedHashMap<>();

    for (int i = 0; i < size; i++) {
      int keyIndex = i * 2;
      int valueIndex = keyIndex + 1;

      String key;
      Object value;

      try {
        key = (String) args[keyIndex];
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("키는 String으로 입력해야 합니다. " + e.getMessage());
      }

      value = args[valueIndex];

      map.put(key, value);
    }

    return map;
  }

  public static String getNowDateStr() {
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String dateStr = format1.format(System.currentTimeMillis());

    return dateStr;
  }

  public static Map<String, String> getUrlParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2); // 물음표기준으로 두덩이로 나누고 String형태의 배열에 넣어줌

    if (urlBits.length == 1) { // 배열에 담긴 값이 1개이면 (명령어가 정상적? 비정상적? 으로 들어오면) 이해안됨
      return params; // Map 객체가 담긴 params를 리턴함.
    }

    for (String bit : urlBits[1].split("&")) { // 나눠진 명령어의 배열 중 1번 인덱스값을 &기준으로 나눠서 bit변수로 반복문을 실행
      String[] bits = bit.split("=", 2); // 그렇게 urlBits[1].split 처리된 요소들을 다시 '=' 기준으로 두덩이로 나누고 bits 배열에 넣음.

      if (bits.length == 1) { // 그렇게 나눠진 것들이 담긴 배열의 길이가 1일때
        continue; // 다음 반복으로 바로 실행하게 됨.
      }

      params.put(bits[0], bits[1]); // bits배열의 0번인덱스값을 key, 1번인덱스값을 value로 지정하여 params Map에 넣어준다.(반복문이 끝날때까지 반복)
    }

    return params; // 그렇게 값이 누적된 params를 반환한다.
  }

  public static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  public static <T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for (int i = list.size() - 1; i >= 0; i--) {
      reverse.add(list.get(i));
    }
    return reverse;
  }

  public static int getRandomInt(int start, int end) {
    int size = end - start + 1;
    return start + (int) (Math.random() * size);
  }
}