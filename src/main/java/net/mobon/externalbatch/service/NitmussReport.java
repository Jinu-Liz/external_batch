//package service;
//
//import net.mobon.externalbatch.mapper.ExternalMapper;
//import net.mobon.externalbatch.util.FileUtil;
//import net.mobon.externalbatch.util.NetworkUtils;
//import net.mobon.externalbatch.util.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//public class NitmussReport {
//  @Autowired
//  private ExternalMapper externalMapperMapper;
//
//  private final String path = "/home/dreamsearch/logs/cron/nitmuss/check.log";
//  private final String datepath = "/home/dreamsearch/logs/cron/nitmuss/batchdate.log";
//  private final String zoneidpath = "/home/dreamsearch/logs/cron/nitmuss/batchid.log";
//  private final String EXTERNALID = "nitmuss";
//
//  public static void main(String[] args) {
//    if ("local".equals(NetworkUtils.getServerType())) {
//      System.out.println("로컬에서는 동작하지 않습니다.");
//      return;
//    }
//    NitmussReport report = new NitmussReport();
//    report.start();
//    System.exit(0);
//  }
//
//  public void start() {
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//    Date date = new Date();
//    Calendar cal = Calendar.getInstance();
//    cal.add(Calendar.DATE, -3);     // 3일 전 데이터. 니트머스는 오후 4시30분에 2일전 데이터가 최종 갱신됨.
//    date.setTime(cal.getTimeInMillis());
//    String yyyymmdd = dateFormat.format(date);
//    String lastDate = StringUtils.nvl(FileUtil.readFile(path)).trim();
//    String localDate = StringUtils.nvl(FileUtil.readFile(datepath)).trim();
//    String localZoneid = StringUtils.nvl(FileUtil.readFile(zoneidpath)).trim();
//    HttpURLConnection conn = null;
//
//    if (StringUtils.isNotEmpty(localDate)) {
//      yyyymmdd = localDate;
//    }
//
//    if (yyyymmdd.equals(lastDate)) {
//      System.out.println("이미 리포트가 처리된 상태입니다. 다시한번 확인해주시기 바랍니다.");
//      return;
//    } else {
//      try {
//
////        ArrayList<ExternalLinkageDto> list;
////        list = (ArrayList<ExternalLinkageDto>) testMapper.selectTestList();
//        String apiUrl = "https://adxagy.netinsight.co.kr/api/AdsDailyReport";
//        String authKey = "6cbb542f9503572de006e55fb69a7a98";
//        URL url = new URL(apiUrl);
//        conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setDoOutput(true);
//        String sendJSON = "{\"auth_key\": \""+ authKey + "\", \"report_dt\": \"" + yyyymmdd + "\"}";
//        OutputStream os = conn.getOutputStream();
//        byte[] input = sendJSON.getBytes("utf-8");
//        os.write(input);
//
//        String buff = "";
//        JSONArray ntmArray = new JSONArray();
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//        while ((buff = br.readLine()) != null) {
//          ntmArray = JSONArray.fromObject(buff);
//        }
//
//        for (Object ntmJSONObject : ntmArray) {
//          JSONObject ntmObject = (JSONObject)ntmJSONObject;
//          String zoneID = String.valueOf(ntmObject.get("ad_tag"));
//          if(StringUtils.isEmpty(zoneID)) continue;
//
//          String adType = String.valueOf(ntmObject.get("type"));
//          if (adType.isEmpty() || !adType.equals("ADX")) continue;
//
//          if(StringUtils.isNotEmpty(localZoneid)){
//            if(!localZoneid.contains(zoneID)) continue;
//          }
//
//          List<ExternalLinkageData> zoneList = null;
//          zoneList = ExternalReportUtils.externalMatchList(EXTERNALID,zoneID,0);
//          if (CollectionUtils.isEmpty(zoneList)) continue;
//          try {
//            ExternalLinkageData data = null;
//            data = new ExternalLinkageData();
//            data.setUserid(EXTERNALID);     // 연동사 ID
//            data.setGubun("AD");    // 베이스광고
//            data.setSite_code("976997b56aa287e01268308940306b01");    // 사이트코드
//            data.setSdate(yyyymmdd);
//            data.setZoneid(zoneID);
//            data.setMedia_id(zoneList.get(0).getUserid());
//            data.setMedia_site(zoneList.get(0).getMedia_site());
//            data.setMedia_code(zoneList.get(0).getMedia_code());
//            data.setAd_type(zoneList.get(0).getAd_type());
//            data.setViewcnt(ntmObject.getInt("imps"));        // 노출
//            data.setClickcnt(ntmObject.getInt("clks"));        // 클릭
//            data.setPoint(ntmObject.getInt("revenue"));         // 수익금
//            data.setTotalcall(0);   // 요청수 데이터는 포함되지 않아 0으로 픽스.
//            list.add(data);
//          } catch (Exception e) {
//            System.out.println(" :::: data 처리 ::: ERROR :: " + e);
//            continue;
//          }
//        }
//
//        ExternalReportUtils.runProc(list, yyyymmdd, path);
//      } catch (Exception e) {
//        e.printStackTrace();
//      } finally {
//        if (conn != null) {
//          conn.disconnect();
//        }
//      }
//    }
//  }
//}