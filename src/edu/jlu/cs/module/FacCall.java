package edu.jlu.cs.module;

import edu.jlu.cs.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

  
public class FacCall extends HttpTools{ 
  
	/**
	 * 
	 * @param content
	 * @return
	 */
  public String register(String content)
  {
	  String urlPath=Global.FACregister;
	  String result = FacCall.sendPostMessage(content,urlPath); 
	  return result;
  }
  
  /**
   * 
   * @param facili
   * @param pic
   * @return
   */
  public String login(JSONArray facili,String pic)
  {
	  String urlPath=Global.FAClogin;
	  JSONObject facCallJson=new JSONObject();
	  facCallJson.put("facilitatorIds", facili);
	  facCallJson.put("picture", pic);
	  String result = FacCall.sendPostMessage(facCallJson.toString(),urlPath); 
	  return result;
  }
} 