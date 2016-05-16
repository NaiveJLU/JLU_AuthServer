package edu.jlu.cs.util;

import java.io.IOException;
import java.util.PropertyResourceBundle;

public class Global {
	
	/**
	 * Database Server url 
	 */
	static public String DBregister;
	
	
	/**
	 * 
	 */
	static public String DBlogin;
	

	/**
	 * Facilitator url 
	 */
	static public String FACregister;

	/**
	 * 
	 */
	static public String FAClogin;
	

	private static PropertyResourceBundle bundle; // 配置资源文件
	
	static
	{
			// 读取配置文件
			try {
				bundle = new PropertyResourceBundle(Global.class
							.getResourceAsStream("config.properties"));
				
				//加载配置文件属性
				DBregister = bundle.getString("DBregister"); // 读取主机�?
				DBlogin = bundle.getString("DBlogin"); // 读取主机�?
				
				FACregister = bundle.getString("FACregister"); // 读取主机�?
				FAClogin = bundle.getString("FAClogin"); // 读取主机�?
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
}
