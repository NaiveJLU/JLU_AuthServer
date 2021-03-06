package edu.jlu.cs.util;

import java.io.ByteArrayOutputStream; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.PrintStream;
import java.io.UnsupportedEncodingException; 
import java.net.HttpURLConnection; 
import java.net.URL; 

  
public class HttpTools { 
  
  // 请求服务器端的url 
  public static int responseCode;
  public HttpTools() {
    // TODO Auto-generated constructor stub 
  } 
  
  
  /** 
   * @param params 
   *      填写的url的参数 
   * @param encode 
   *      字节编码 
   * @return 
   */
  public static String sendPostMessage(String jsonMessage, String urlPath) { 
    // 作为StringBuffer初始化的字符串 
    try {
      URL url=new URL(urlPath);
      HttpURLConnection urlConnection = (HttpURLConnection) url 
          .openConnection(); 
      urlConnection.setConnectTimeout(3000); 
      urlConnection.setRequestMethod("POST"); 
      urlConnection.setDoInput(true);// 表示从服务器获取数据 
      urlConnection.setDoOutput(true);// 表示向服务器写数据 
      // 获得上传信息的字节大小以及长度 
      byte[] mydata = jsonMessage.getBytes(); 
      // 表示设置请求体的类型是文本类型 
      urlConnection.setRequestProperty("Content-Type", 
          "application/json"); 
      urlConnection.setRequestProperty("Content-Length", 
          String.valueOf(mydata.length)); 
      // 获得输出流,向服务器输出数据 
      OutputStream outputStream = urlConnection.getOutputStream(); 
      outputStream.write(mydata,0,mydata.length); 
      outputStream.close(); 
      // 获得服务器响应的结果和状态码 
      responseCode = urlConnection.getResponseCode();
      if(responseCode==200)
      return changeInputStream(urlConnection.getInputStream(), "ascii");
    } catch (UnsupportedEncodingException e) { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
    } catch (IOException e) { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
    } 
    return ""; 
  } 
  
  /** 
   * 将一个输入流转换成指定编码的字符串 
   * 
   * @param inputStream 
   * @param encode 
   * @return 
   */
  private static String changeInputStream(InputStream inputStream, 
      String encode) { 
    // TODO Auto-generated method stub 
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
    byte[] data = new byte[1024]; 
    int len = 0; 
    String result = ""; 
    if (inputStream != null) { 
      try { 
        while ((len = inputStream.read(data)) != -1) { 
          outputStream.write(data, 0, len); 
        } 
        result = new String(outputStream.toByteArray(), encode); 
      } catch (IOException e) { 
        // TODO Auto-generated catch block 
        e.printStackTrace(); 
      } 
    } 
    return result; 
  } 
  
} 