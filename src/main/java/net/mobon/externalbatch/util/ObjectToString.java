package net.mobon.externalbatch.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ObjectToString {
  /**
   * 조회용
   */
  protected String sendDate;
  protected String startDate;
  protected String endDate;
  protected String targetDate;
  protected int limit;
  protected String orderBy;
  private boolean isEmpty;

  private String className;
  private String hh;
}
