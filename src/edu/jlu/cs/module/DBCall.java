package edu.jlu.cs.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.jlu.cs.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author 
 *
 */
public class DBCall extends HttpTools{ 
  
  /*public static String checkExternalId(String ExternalId)
  {
	  JSONObject jsonObject=new JSONObject();
	  String urlPath=PATH+"/checkExternalId";
	  jsonObject.put("ExternalId", ExternalId);
	  String result = sendPostMessage(jsonObject.toString(),urlPath); 
	  return result;
  }
  public static String createUser(String ExternalId,String UserId,String Name,String Password,String Gender,String Images,String FacilitatorIds)
  {
	  JSONObject jsonObject=new JSONObject();
	  String urlPath=PATH+"/createUser";
	  jsonObject.put("ExternalId", ExternalId);
	  jsonObject.put("UserId", UserId);
	  jsonObject.put("Name", Name);
	  jsonObject.put("Password", Password);
	  jsonObject.put("Gender", Gender);
	  jsonObject.put("Images", Images);
	  jsonObject.put("FacilitatorIds", FacilitatorIds);
	  String result = sendPostMessage(jsonObject.toString(),urlPath); 
	  return result;
  }*/
	
	/**
	 * 
	 * @param fac 
	 * @param pic
	 * @return 
	 * @throws FileNotFoundException 
	 */
  public String register(JSONArray fac,JSONArray pic) throws FileNotFoundException
  {
	  String urlPath=Global.DBregister;
	  JSONObject dbCallJson=new JSONObject();
	  dbCallJson.put("facilitatorIds", fac);
	  dbCallJson.put("pictures", pic);

  	FileOutputStream fs = new FileOutputStream(new File("DBCallLog.txt"));
		PrintStream p = new PrintStream(fs);
		p.println(dbCallJson.toString());
		p.close();
	  String result = sendPostMessage(dbCallJson.toString(),urlPath); 
	  return result;
  }
  
 /**
  * 
  * @param usrId
  * @return
  */
  public String login(String usrId)
  {
	  String urlPath=Global.DBlogin;
	  JSONObject dbCallJson=new JSONObject();
	  dbCallJson.put("userId", usrId);
	  String result = sendPostMessage(dbCallJson.toString(),urlPath); 
	  return result;
  }
} 