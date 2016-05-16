package edu.jlu.cs.module;

import edu.jlu.cs.util.*;
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
	 */
  public String register(String fac,String pic)
  {
	  String urlPath=Global.DBregister;
	  JSONObject dbCallJson=new JSONObject();
	  dbCallJson.put("facilitatorIds", fac);
	  dbCallJson.put("pictures", pic);
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
	  dbCallJson.put("UserId", usrId);
	  String result = sendPostMessage(dbCallJson.toString(),urlPath); 
	  return result;
  }
} 