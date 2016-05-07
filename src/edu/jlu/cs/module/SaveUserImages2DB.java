package edu.jlu.cs.module;

import java.io.ByteArrayOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.UnsupportedEncodingException; 
import java.net.HttpURLConnection; 
import java.net.URL; 

import org.json.JSONObject;
  
public class SaveUserImages2DB { 
  
  // ����������˵�url 
  private static String PATH = ""; 
  
  public SaveUserImages2DB() { 
    // TODO Auto-generated constructor stub 
  } 
  
  
  /** 
   * @param params 
   *      ��д��url�Ĳ��� 
   * @param encode 
   *      �ֽڱ��� 
   * @return 
   */
  public static String sendPostMessage(String jsonMessage, String urlPath) { 
    // ��ΪStringBuffer��ʼ�����ַ��� 
    try {  
      URL url=new URL(urlPath);
      HttpURLConnection urlConnection = (HttpURLConnection) url 
          .openConnection(); 
      urlConnection.setConnectTimeout(3000); 
      urlConnection.setRequestMethod("POST"); 
      urlConnection.setDoInput(true);// ��ʾ�ӷ�������ȡ���� 
      urlConnection.setDoOutput(true);// ��ʾ�������д���� 
      // ����ϴ���Ϣ���ֽڴ�С�Լ����� 
      byte[] mydata = jsonMessage.getBytes(); 
      // ��ʾ������������������ı����� 
      urlConnection.setRequestProperty("Content-Type", 
          "application/x-www-form-urlencoded"); 
      urlConnection.setRequestProperty("Content-Length", 
          String.valueOf(mydata.length)); 
      // ��������,�������������� 
      OutputStream outputStream = urlConnection.getOutputStream(); 
      outputStream.write(mydata,0,mydata.length); 
      outputStream.close(); 
      // ��÷�������Ӧ�Ľ����״̬�� 
      int responseCode = urlConnection.getResponseCode(); 
      if (responseCode == 201||responseCode == 500) { 
        return changeInputStream(urlConnection.getInputStream(), "ascii"); 
      }
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
   * ��һ��������ת����ָ��������ַ��� 
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
  
  /** 
   * @param args 
   */
  public static String work(String internal_ID,String user_FAC_ids,Boolean train_status,String images)
  {
	  JSONObject jsonObject=new JSONObject();
	  String urlPath=PATH+"?internal_ID="+internal_ID+"&train_status="+train_status;
	  jsonObject.put("images", images);
	  jsonObject.put("user_FAC_ids", user_FAC_ids);
	  String result = SaveUserImages2DB.sendPostMessage(jsonObject.toString(),urlPath); 
	  return result;
  }
} 