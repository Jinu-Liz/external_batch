package net.mobon.externalbatch.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @Deprecated 될 수 있으면 레디스에 에러를 쌓지 않도록 해야합니다.
 * 전체 서비스에 영향이 있을 수 있습니다.
 * 일반 로그를 사용하는 것을 권장합니다.
 */
@Slf4j
public class ErrorLog {

  /**
   * 오류메세지
   * @param e
   * @return
   */
  public static String getStack(Exception e){
    StringBuffer stack = new StringBuffer("\n###### ERROR LOG ######\n");
    StackTraceElement[] stacks = e.getStackTrace();
    int i = 0;
    boolean check = true;
    for(StackTraceElement element : stacks){
      if(element.getMethodName().equals("doGet")){
        stack.append("CLASS_NAME : ");
        stack.append(element.getFileName());
        stack.append(" / ");
        stack.append("LINE : ");
        stack.append(element.getLineNumber());
        stack.append(" / MESSAGE : ");
        stack.append(e);
        stack.append("\n");
        i++;
        break;
      }else{
        stack.append("CLASS_NAME : ");
        stack.append(element.getFileName());
        stack.append(" / ");
        stack.append("LINE : ");
        stack.append(element.getLineNumber());
        stack.append(" / MESSAGE : ");
        stack.append(e);
        stack.append("\n");
        i++;
      }
      if(i>1){
        break;
      }
      if(i > 0){
        check = false;
      }
    }
    if(check){
      stack.append(e);
      stack.append("\n");
    }
    stack.append("###### END ERROR LOG ######");
    return stack.toString();
  }

  /**
   * 사용하지마세요.
   * just use LOGGER.error("error", e)
   */
//  @Deprecated
//  public static String getStack(Exception e, String code, Object sessionInfo){
//    return getStack(e, code, sessionInfo, false);
//  }

  /**
   * 사용하지마세요.
   * just use LOGGER.error("error", e)
   */
//  @Deprecated
//  public static String getStack(Exception e, Object sessionInfo){
//    String code = "";
//    try {
//      if (isEmpty(e.getStackTrace())) return e.getMessage();
//      code = String.format("CLASS:%S:%S:%S"
//        , e.getStackTrace()[0].getClassName()
//        , e.getStackTrace()[0].getMethodName()
//        , e.getMessage());
//    } catch(Exception e1) {
//      logger.error("", e1);
//    }
//    return getStack(e, code, sessionInfo, false);
//  }


//  @Deprecated
//  public static String getStackIncreaseNum(Exception e, Object sessionInfo){
//    String code = String.format("CLASS:%S:%S:%S"
//      , e.getStackTrace()[0].getClassName()
//      , e.getStackTrace()[0].getMethodName()
//      , e.getMessage());
//    RedisUtil.incCount(DateUtils.getDate("yyyyMMdd")+":"+code);
//    return code;
//  }

//  @Deprecated
//  public static String getStack(Exception e, String code, Object sessionInfo, boolean fail){
//    return getStack(e,code, sessionInfo,fail,false);
//  }

  /**
//   * 에러 , 에러데이터 redis 수집
//   * @param e
//   * @param code
//   * @param sessionInfo
//   * @param fail
//   * @return
   */
//  public static String getStack(Exception e, String code, Object sessionInfo, boolean fail, boolean redisWrite){
//    StringBuffer stack = new StringBuffer();
//    StringBuffer retrurnStack = new StringBuffer();
//    StackTraceElement[] stacks = e.getStackTrace();
//    stack.append("\n###### ERROR LOG ######\n");
//    int i = 0;
//    boolean check = true;
//    for(StackTraceElement element : stacks){
//      if(element.getMethodName().equals("doGet")){
//        stack.append("CLASS_NAME : ");
//        stack.append(element.getFileName());
//        stack.append(" / ");
//        stack.append("LINE : ");
//        stack.append(element.getLineNumber());
//        stack.append(" / MESSAGE : ");
//        stack.append(e);
//        stack.append("\n");
//        i++;
//      }else{
//        stack.append("CLASS_NAME : ");
//        stack.append(element.getFileName());
//        stack.append(" / ");
//        stack.append("LINE : ");
//        stack.append(element.getLineNumber());
//        stack.append(" / MESSAGE : ");
//        stack.append(e);
//        stack.append("\n");
//        i++;
//      }
//      if(i>1){
//        if(check){
//          retrurnStack.append(stack.toString());
//          check = false;
//        }
//      }
//    }
//    if(check){
//      retrurnStack.append("\n###### ERROR LOG ######\n");
//      retrurnStack.append(e);
//      retrurnStack.append("\n");
//      stack.append(e);
//      stack.append("\n");
//    }
//    stack.append("###### END ERROR LOG ######");
//    retrurnStack.append("###### END ERROR LOG ######");
//
//    StringBuffer errorMessage = new StringBuffer();
//    errorMessage.append(stack.toString());
//    errorMessage.append("\n\n");
//    errorMessage.append("errorData:");
//
//    if(sessionInfo instanceof String){
//      errorMessage.append(sessionInfo);
//    }else if(sessionInfo instanceof ArrayList){
//      errorMessage.append(sessionInfo.toString());
//    }else{
//      errorMessage.append(JsonMapper.fromObject(sessionInfo).toString());
//    }
//
//    if (!ConfigServlet.allStopShoppul && redisWrite) {
//      String temp = fail?"":ConfigServlet.SERVER_HOST+":";
//      RedisUtilSub.setErrorLog(temp+code, errorMessage.toString(),fail);
//    }
//    return retrurnStack.toString();
//  }

//  @Deprecated
//  public static String getDebugStack( Exception e ){
//    return getDebugStack(e,false);
//  }


//  public static String getDebugStack( Exception e, boolean redisWrite){
//    StringBuffer stack = new StringBuffer("\n###### ERROR LOG ######\n");
//    StackTraceElement[] stacks = e.getStackTrace();
//    for(StackTraceElement element : stacks){
//      stack.append("CLASS_NAME : ");
//      stack.append(element.getFileName());
//      stack.append(" / ");
//      stack.append("LINE : ");
//      stack.append(element.getLineNumber());
//      stack.append(" / MESSAGE : ");
//      stack.append(e);
//      stack.append("\n");
//    }
//    stack.append("###### END ERROR LOG ######");
//
//    if (!ConfigServlet.allStopShoppul && redisWrite) {
//      RedisUtilSub.setErrorLog(e.getClass().toString(), stack.toString(),false);
//    }
//    return stack.toString();
//  }

}