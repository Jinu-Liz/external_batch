package net.mobon.externalbatch.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MediaScriptDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String Y = "Y";

  private String userId; //사용자 아이디
  private String AD_TYPE;//광고타입
  private String no;
  private String H_TYPE; //하우스배너타입
  private String H_BANNER; //하우스배너
  private String mediasite_no;
  private String accept_sr; //본상품 승인여부
  private String accept_rc; //리사이클 승인여부
  private String accept_um; //성향광고승인여부
  private String accept_kl; //키워드광고승인여부
  private String accept_mm; //문맥타기팅 배너 광고승인여부
  private String accept_mr; //문맥 리타기팅 배너 광고승인여부
  private String accept_ib; //유입키워드매칭 배너 광고승인여부_20180508추가됨
  private String accept_km; //키워드매칭 배너 광고승인여부_20180607추가됨
  private String accept_ad; //기본광고승인여부
  private String accept_st; //투데이베스트 승인여부
  private String accept_sp; //추천상품승인여부
  private String accept_rm; //리턴매칭 승인여부
  private String accept_cw; //장바구니 승인여부
  private String accept_pb; //퍼포먼스배너(퀴즈형) 승인여부
  private String accept_hu; //헤비유저 승인여부
  private String accept_pm; //프리미엄광고 승인여부
  private String accept_sj; //쇼핑입점 승인여부
  private String accept_ik; //네이티브 광고 > 유입키워드
  private String accept_mk; //네이티브 광고 > 핵심키워드
  private String accept_cm; //네이티브 광고 > 카테고리 매칭
  private String accept_au; //오디언스 by sunys
  private String accept_at; //앱 프로파일 타게팅
  private String accept_pr; //프로모션 타게팅 광고승인여부(2019.09 chlee2)
  private String accept_ru; //휴면유저 타게팅 shbae
  private String accept_mt; //신규 문맥 매칭 광고 승인 여부 (hcson)


  private String siteurl;
  private String limit_domains;
  private int weight_pct;
  private int bid_price;
  private String price_type;
  private String limit_pop;
  private String weight_type;
  private String imgcode = "01";
  private String imgcodeTemp;
  private String w_type;
  private String actype;
  private float acprice;
  private int acper;
  private String deduct;
  private String frameRtb_yn;
  private String r_gubun;
  private String ms_icover_useyn;
  private float ms_icover_time;
  private String ad_icover_useyn;
  private float ad_icover_time;
  private boolean isEmpty = false;
  private int pb_weight; // 퀴즈형 배너 비율
  private String two_yn; // 엠커버 투뎁스 사용여부
  private String mcover_type;
  private String psb_url;
  private String corpname;
  private String sitename;
  private String scate;
  @Getter @Setter
  private String mediaTpCode;
  private String mediaExposureType; // 매체 광고 노출 타입
  private String passYn; // 매체 패스백 사용여부
  private String chargeType; //과금형식(매체,광고주) 01:동일하게, 02:다르게
  private int frequencyMinute; // 지면별 프리퀀시 시간(분)
  private String mediaInterlockCode; // 매체 연동 구분 코드
  private String chargeSectionCode; // 클릭 구간 구분 코드 01:캠페인 + 상품, 02:캠페인
  private int avaialeChargeCntByIp; // 클릭 IP 유효 횟수
  private int viewLimitRate; // 노출 제한 비율
  private String prtc_use_yn;
  private String par_sort_tp_code;
  private String imageFilterUseYn;
  private String cr_intrcp_yn;
  private String advrtsPrdtCode; // 상품 구분 코드(배너, 아이커버, 브랜드링크, 네이티브, 플레이링크, 소셜링크)
  private String grade; // 권한, 빈값:일반, 2:대행사, 3:직매체, 4:신디, 6:네트워크 매체, 7:관리자
  private String pltfom_tp_code; // 플랫폼 구분 코드 (01:WEB, 02:MOBILE, 03:APP, 04:미승인)
}
