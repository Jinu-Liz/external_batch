package net.mobon.externalbatch.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringUtils {
  /**
   * null 체크
   *
   * @param str
   * @return
   */
  public static boolean isEmpty(String str) {

    return org.apache.commons.lang3.StringUtils.isEmpty(str);
  }

  /**
   * null 체크
   *
   * @param str
   * @return
   */
  public static boolean isNotEmpty(String str) {
    return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
  }

  /**
   * 문자열을 구분자로 구분하여 잘라 리스트에 담기 구분
   * StringTokenizer 클래스: 생성자에 단 한 문자 구분자만 사용하여 토큰으로 분리한다.(여러개의 구분자는 사용가능하다)
   * 작성자: 조규홍
   * 작성일자 : 2016-06-16
   *
   * @param ktype
   * @param pattern
   * @return
   */
  public static ArrayList<String> splitFunction(String ktype, String pattern) {
    StringTokenizer st = new StringTokenizer(ktype, pattern);
    ArrayList<String> userList = new ArrayList<String>();
    while (st.hasMoreTokens()) {
      userList.add(st.nextToken());
    }
    return userList;
  }

  /**
   * Null 체크 후 빈값 return;
   *
   * @param nvl
   * @return
   */
  public static String nvl(String nvl) {
    return nvl(nvl, "");
  }

  /**
   * Null 체크 후 대체값 return;
   *
   * @param nvl
   * @param rep
   * @return
   */
  public static String nvl(String nvl, String rep) {
    if (nvl != null) {
      return nvl.trim();
    } else {
      return rep;
    }
  }
}
