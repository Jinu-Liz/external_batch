package net.mobon.externalbatch.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@Getter
@Setter
@PropertySource("classpath:/kafka.properties")
public class ExternalLinkageDto implements Serializable {
  private static final long serialVersionUID = 1L;
  String sdate = "";		// 날짜 : yyyymmdd
  int media_site = 0;	// 매체 key
  int media_code = 0;	// 매체 스크립트 key
  String transmit = "R"; //  S: 송출 , R: 수신
  String zoneid = "1555";		// 와이더플래닛 key
  String external_id;
  String external_name;
  String userid = "widerplanet";		// 와이더플래닛 id
  String site_code = "8bf3b57466094cead02d2ac0226f0c3f";	// 와이더플래닛의 사이트코드
  String ad_type = "";	// 광고 크키 ex) i250_250
  int viewcnt = 0;	// 와이더플래닛 노출
  int viewcnt_mobon = 0;	// 인라이플 노출
  int clickcnt = 0;	// 와이더플래닛 클릭
  int clickcnt_mobon = 0;	// 인라이플 클릭
  int imv = 0;		// CTR * 노출
  int passback_cnt = 0;
  float point = 0;		// 매체비
  String regdate = "";	// 등록날짜
  String gubun = "";		// 광고 구분자
  int totalcall = 0;	// 총 요청 CALL
  int exl_seq = 0;	// 외부연동순서
  String exl_info = "";	// 외부연동 추가 정보
  String send_tp_code =""; // 송출구분코드 (01:송출, 02:수신, 03:미노출)
  String media_id = "";		// 매체ID
  String type = "";		// TYPE : 'P' 패스백
  String etc1;
  String etc2;
  String etc3;
  String etc4;
  String product = "01"; // 디폴트 배너 : 01

  String imgname;
  String imgtype;
  String site_name;


  private String dumpType; // dumpObject type

  private int partition;
  private Long offset;
  private String key;

  /**
   * 객체 생성시 boolean 값이 할당됨.
   */
  @Value("${IS_HANDLING_STATS_MOBON}")
  private boolean bHandlingStatsMobon;
  @Value("${IS_HANDLING_STATS_POINT_MOBON}")
  private boolean bHandlingStatsPointMobon;
}
