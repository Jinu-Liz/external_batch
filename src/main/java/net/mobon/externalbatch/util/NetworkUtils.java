package net.mobon.externalbatch.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

@Slf4j
public class NetworkUtils {

  public static String getClientIP(HttpServletRequest request) {
    String deviceIp = request.getHeader("X-Forwarded-For");
    if (StringUtils.isEmpty(deviceIp) || "unknown".equalsIgnoreCase(deviceIp)) {
      deviceIp = request.getHeader("Proxy-Client-deviceIp");
    }
    if (StringUtils.isEmpty(deviceIp) || "unknown".equalsIgnoreCase(deviceIp)) {
      deviceIp = request.getHeader("WL-Proxy-Client-deviceIp");
    }
    if (StringUtils.isEmpty(deviceIp) || "unknown".equalsIgnoreCase(deviceIp)) {
      deviceIp = request.getHeader("HTTP_CLIENT_deviceIp");
    }
    if (StringUtils.isEmpty(deviceIp) || "unknown".equalsIgnoreCase(deviceIp)) {
      deviceIp = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (StringUtils.isEmpty(deviceIp) || "unknown".equalsIgnoreCase(deviceIp)) {
      deviceIp = request.getRemoteAddr();
    }
    return deviceIp;
  }

  public static String getServerType() {

    try {
      Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
      while (nienum.hasMoreElements()) {
        NetworkInterface ni = nienum.nextElement();
        Enumeration<InetAddress> kk = ni.getInetAddresses();
        while (kk.hasMoreElements()) {
          InetAddress inetAddress = (InetAddress) kk.nextElement();
          // logger.info(inetAddress.getHostAddress());

          if (inetAddress.getHostAddress().contains("119.205.238.")) {
            return "real";
          } else if (inetAddress.getHostAddress().contains("119.205.238.")) {
            return "script";
          }
          // 112.175.76.xxx 은 kakao
          else if (inetAddress.getHostAddress().contains("112.175.76.")) {
            return "kakao";
          } else if (inetAddress.getHostAddress().contains("119.205.215.")) {
            return "dev";
          } else if (inetAddress.getHostAddress().contains("192.168.100")) {
            return "local";
          }
        }
      }

      return "";
    } catch (SocketException e) {
      return "";
    }
  }

  /**
   * <pre>
   * 서버 IP 가져오기
   * </pre>
   *
   * @param
   * @return
   * @throws Exception
   */
  public static String getHostIp() throws SocketException {
    InetAddress returnInetAddress = null;
    String result = "";
    Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
    while (nienum.hasMoreElements()) {
      NetworkInterface ni = nienum.nextElement();
      Enumeration<InetAddress> kk = ni.getInetAddresses();
      while (kk.hasMoreElements()) {
        InetAddress address = kk.nextElement();
        if (!address.isLoopbackAddress()) {
          if (address.isSiteLocalAddress()) {
            return address.getHostAddress();
          } else if (returnInetAddress == null) {
            returnInetAddress = address;
          }
        }
      }
    }
    try {
      result = returnInetAddress != null ? returnInetAddress.getHostAddress()
        : InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      log.error(ErrorLog.getStack(e));
    }
    return result;
  }

  /**
   * 서버한대에서만 레디스 캐쉬 실행
   *
   * @return
   */
  public static String getInetInfo() {
    String hostAddr = "";
    try {
      Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
      while (nienum.hasMoreElements()) {
        NetworkInterface ni = nienum.nextElement();
        Enumeration<InetAddress> kk = ni.getInetAddresses();
        while (kk.hasMoreElements()) {
          InetAddress inetAddress = kk.nextElement();
          if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
            && inetAddress.isSiteLocalAddress()) {
            hostAddr = inetAddress.getHostAddress().toString();
          }
        }
      }
    } catch (SocketException e) {
      log.error(ErrorLog.getStack(e));
    }
    return hostAddr;
  }


  public static boolean abTestCheckServer(String serverList) {
    Boolean bool = false;
    if ("".equals(serverList))
      return false;
    ArrayList<String> abServerList = StringUtils.splitFunction(serverList, ",");
    try {
      Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
      while (nienum.hasMoreElements()) {
        NetworkInterface ni = nienum.nextElement();
        Enumeration<InetAddress> kk = ni.getInetAddresses();
        while (kk.hasMoreElements()) {
          InetAddress inetAddress = (InetAddress) kk.nextElement();
          // 로컬 테스트시 주석 해제
          if (inetAddress.getHostAddress().contains("192.168.100")) {
            log.debug("getHostAddress" + inetAddress.getHostAddress());
            bool = true;
            break;
          }
          for (String serverIp : abServerList) {
            if (inetAddress.getHostAddress().contains("119.205.238." + serverIp)) {
              bool = true;
              break;
            }
          }
        }
      }
    } catch (SocketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return bool;
  }

  public static boolean isListInTarget(String hostString, String list, String target) {
    boolean bool = false;

    if ("local".equals(hostString.toLowerCase()) || "dev".equals(hostString.toLowerCase())) {
      bool = true;
    } else if ("all".equals(list.toLowerCase())) {
      bool = true;
    } else if ("null".equals(target)) {
      bool = true;
    } else {
      ArrayList<String> lists = StringUtils.splitFunction(list, ",");
      for (String item : lists) {
        if (item.equals(target)) {
          bool = true;
          break;
        }
      }
    }

    return bool;
  }
}