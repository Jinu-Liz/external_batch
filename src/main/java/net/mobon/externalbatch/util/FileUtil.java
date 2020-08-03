package net.mobon.externalbatch.util;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class FileUtil {

  public static void mkFolder(String path) {

    File dir = new File(path);
    try {
      if (!dir.exists()) { // 폴더 없으면 폴더 생성
        dir.mkdirs();
      }
    } catch (Exception e) {
      log.error(ErrorLog.getStack(e));
    }
  }

  public static void fileCopy(String inFileName, String outFileName) {
    FileInputStream fis=null;
    FileOutputStream fos=null;
    try {
      fis = new FileInputStream(inFileName);
      fos = new FileOutputStream(outFileName);

      byte [] buffer=new byte[512];
      int data = 0;
      while( ( data=fis.read(buffer, 0, 512) )!=-1 ) {
        fos.write(buffer,0,data);
      }
    } catch (IOException e) {
      log.error("fileMove[41] err "+ e);
    }finally{
      try{	fis.close();	}catch(Exception e){
        log.error(ErrorLog.getStack(e));
      }
      try{	fos.close();	}catch(Exception e){
        log.error(ErrorLog.getStack(e));
      }
    }
  }
  public static void fileDelete(String deleteFileName) {
    try{
      File I = new File(deleteFileName);
      I.delete();
    }catch(Exception e){
      log.error("fileDelete[48] err "+ deleteFileName);
    }
  }
  public static void fileDelete(String deleteFileName, String fatternName) {
    try{
      File I = new File(deleteFileName);
      int i = 0;
      String fileList[] = I.list(new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
          return name.startsWith(fatternName);
        }
      });

      if (fileList.length > 0) {

        for (i = 0; i < fileList.length; i++) {
          I = new File(deleteFileName + fileList[i]);
          I.delete();
        }
      }
    }catch(Exception e){
      log.error(ErrorLog.getStack(e));
    }
  }
  public static String[] fileList(File directory, String fatternName) {

    String fileList[] = directory.list(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        return name.startsWith(fatternName);
      }
    });

    return fileList;
  }
  public static void writeFile(String destFile,String msg){
    BufferedWriter fw=null;
    try{
      File f=new File(destFile);
      fw = new BufferedWriter(new FileWriter(f,true));
      fw.write(msg);
      fw.flush();
    }catch(Exception e){
      log.error(ErrorLog.getStack(e));
    }finally{
      try {
        if(fw!=null)fw.close();
      } catch (IOException e) {
        log.error(ErrorLog.getStack(e));
      }
    }
  }
  public static void reWriteFile(String destFile,String msg){
    DataOutputStream outs=null;
    try{
      File file=new File(destFile);
      outs = new DataOutputStream(new FileOutputStream(file,false));
      outs.write(msg.getBytes());
      outs.close();
    }catch(Exception e){
      log.error(ErrorLog.getStack(e));
    }finally{
      try {
        if(outs!=null)outs.close();
      } catch (IOException e) {
        log.error(ErrorLog.getStack(e));
      }
    }
  }
  @Deprecated
  public static int saveUrlData(String saveDest,String downloadUrl){
    URL u=null;
    URLConnection conn=null;
    BufferedInputStream reader=null;
    BufferedOutputStream mywriter=null;
    int rt_int= 0;
    try{
      u=new URL(downloadUrl);
      conn = u.openConnection();
      conn.connect();

      String filename = saveDest;
      File myfile = new File(filename);
      reader = new BufferedInputStream(conn.getInputStream());
      mywriter = new BufferedOutputStream(new FileOutputStream(myfile));
      byte[]buf=new byte[4096];
      int n = 0;
      while ((n=reader.read(buf,0,buf.length))!=-1) {
        mywriter.write(buf,0,n);
      }
      reader.close();
      mywriter.close();

    }catch(Exception e){
      rt_int= 1;
      log.error(ErrorLog.getStack(e));
    }finally{
      if(reader !=null){
        try {
          reader.close();
        } catch (IOException e) {
          log.error(ErrorLog.getStack(e));
        }
      }
      if(mywriter !=null){
        try {
          mywriter.close();
        } catch (IOException e) {
          log.error(ErrorLog.getStack(e));
        }
      }
    }
    return rt_int;
  }
  public static boolean imgURLCheck(String imgUrl){
    try{
      URL url=new URL(imgUrl);
      Image image= ImageIO.read(url);
      image.getWidth(null);
      return true;
    }catch(Exception e){
      log.error(ErrorLog.getStack(e));
      return false;
    }
  }
  public static String readFile(String destFile){
    BufferedReader fr=null;
    StringBuffer sb=new StringBuffer();
    try{
      File f=new File(destFile);
      if (f.isFile()) {
        //fr = new BufferedReader(new FileReader(f));
        fr = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF8"));
        String line;
        while((line=fr.readLine())!=null){
          sb.append(line).append("\n");
        }
      } else {
        log.error("file is not found ############ " + destFile);
        return "";
      }
      return sb.toString();
    }catch(Exception e){
      log.error(ErrorLog.getStack(e));
      return "";
    }finally{
      try {
        if(fr!=null)fr.close();
      } catch (IOException e) {
        log.error(ErrorLog.getStack(e));
      }
    }
  }

  /**
   * 외부연동할때, 파라미터를 정리한 후 다시 리턴해주는 로직
   * @param queryString
   * @return	실제 유입된 url 에서 passback을 제거한후 다시금 url을 변환하여 리턴한다.
   */
  public static String getQueryString(String queryString) {
    String returnString = "";
    if (queryString != null && queryString.length() >0) {
      String[] list = queryString.split("&");
      for (int i = 0; i < list.length; i++) {
        String[] query = list[i].split("=");
        /**
         * passback 을 배열형태로 받게 되므로, 기존 passback을 삭제하거,
         * from 은 referrer 정보이므로, 외부연동에서 referrer 정보를 받을 이유가 없음.
         */
        if(query.length == 2 && !"passback".equals(query[0])) {
          try {
            if("from".equals(query[0]) && query[1].indexOf("%3A") > -1){
              returnString += query[0] + "=" + query[1] + "&";
              continue;
            }
            returnString += query[0] + "=" + URLEncoder.encode(query[1], "UTF-8") + "&";
          }
          catch (UnsupportedEncodingException e) {
            returnString += query[0] + "=" + query[1] + "&";
          }
        }
      }
    }
    return returnString;
  }

  public static String readTextFile(	String paramString1, String paramString2) throws Exception {
    File localFile = new File(paramString1);
    return readTextFile(localFile, paramString2);
  }

  public static String readTextFile(	File paramFile, String paramString) {
    try {
      byte[] arrayOfByte = readFile(paramFile);
      return new String(arrayOfByte, paramString);
    } catch (UnsupportedEncodingException e) {
      log.error(ErrorLog.getStack(e));
      return "";
    }
  }

  public static byte[] readFile(File paramFile) {
    ByteArrayOutputStream localByteArrayOutputStream = null;
    FileInputStream localFileInputStream = null;
    if (!(paramFile.exists())) {
      log.error("File '" + paramFile.getPath() + "' does not exist.");
      return null;
    }

    if (!(paramFile.isFile())) {
      log.error("File '" + paramFile.getPath() + "' is not a file.");
      return null;
    }

    try {
      localFileInputStream = new FileInputStream(paramFile);
      localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte1 = new byte[1024];
      int i = 0;
      while ((i = localFileInputStream.read(	arrayOfByte1, 0, 1024)) > 0)
        localByteArrayOutputStream.write(	arrayOfByte1, 0, i);
      localByteArrayOutputStream.flush();
      byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
      return arrayOfByte2;
    } catch (IOException localIOException1) {
    } finally {
      try {
        if (localFileInputStream != null)
          localFileInputStream.close();
      } catch (IOException localIOException4) {
      }
      try {
        if (localByteArrayOutputStream != null)
          localByteArrayOutputStream.close();
      } catch (IOException localIOException5) {
      }
    }
    return null;
  }

  public static org.w3c.dom.Document openXmlFromString(String paramString1, String paramString2) {
    ByteArrayInputStream localByteArrayInputStream = null;
    try {
      localByteArrayInputStream = new ByteArrayInputStream(paramString1.getBytes(paramString2));
      InputSource localInputSource = new InputSource(localByteArrayInputStream);
      DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
      org.w3c.dom.Document localDocument1 = localDocumentBuilder.parse(localInputSource);
      org.w3c.dom.Document localDocument2 = localDocument1;
      return localDocument2;
    } catch (Exception e) {
      log.error(ErrorLog.getStack(e));
    } finally {
      try {
        if (localByteArrayInputStream != null)
          localByteArrayInputStream.close();
      } catch (IOException localIOException2) {
      }
    }
    return null;
  }

  public static String deleteDoctype(String paramString) {
    int i = 0;
    int j = 0;
    i = paramString.indexOf("<!DOCTYPE");
    if (i > -1) {
      j = paramString.indexOf(">", i);
      return paramString.substring(0, i) + paramString.substring(j + 1);
    }
    return paramString;
  }

  public static String getNodeValue(Node paramNode) {
    if (paramNode != null) {
      Node localNode = paramNode.getNextSibling();
      if ((localNode != null) && (localNode.getNodeType() == 4))
        return localNode.getTextContent().trim();
      return paramNode.getTextContent().trim();
    }
    return "";
  }



  public static void saveObjectToFile(String destDir, Vector<?> v){
    FileOutputStream fileout = null;
    ObjectOutputStream out=null;
    try{
      fileout=new FileOutputStream(destDir);
      out = new ObjectOutputStream(fileout);
      out.writeObject(v);
    }catch(IOException e){
      log.error(ErrorLog.getStack(e));
    }finally{
      try{
        if(out !=null) out.close();
        if(fileout !=null) fileout.close();
        out = null;
        fileout = null;
      }catch(Exception e){
        log.error(ErrorLog.getStack(e));
      }
    }
  }

  public static void saveListToFile(String destDir, List<?> v){
    FileOutputStream fileout = null;
    ObjectOutputStream out=null;
    try{
      fileout=new FileOutputStream(destDir);
      out = new ObjectOutputStream(fileout);
      out.writeObject(v);
    }catch(IOException e){
      log.error(ErrorLog.getStack(e));
    }finally{
      try{
        if(out !=null) out.close();
        if(fileout !=null) fileout.close();
        out = null;
        fileout = null;
      }catch(Exception e){
        log.error(ErrorLog.getStack(e));
      }
    }
  }
}
