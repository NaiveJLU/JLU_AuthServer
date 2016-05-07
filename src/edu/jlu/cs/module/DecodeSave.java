package edu.jlu.cs.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class DecodeSave {
	/** 
     * @param bytes 
     * @return 
     */  
	static Map<String,Integer> mp;
	static
	{
		mp=new HashMap<String,Integer>();
	}
    public static byte[] decode(final byte[] bytes) {  
        return Base64.decodeBase64(bytes);  
    }  
  
    /** 
     * ���������ݱ���ΪBASE64�ַ��� 
     * 
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode(final byte[] bytes) {  
        return new String(Base64.encodeBase64(bytes));  
    }
    public static String work(String username, String image)
    {
    	int index=1;
    	if(mp.containsKey(username))
    		index=mp.get(username)+1;
    	mp.put(username, index);
    	String filePath="~/UserImagesCache/"+username;
    	File file =new File(filePath);    
    	//����ļ��в������򴴽�    
    	if  (!file .exists()  && !file .isDirectory())      
    	{       
    	    System.out.println("//������");  
    	    file .mkdir();
    	}
    	else   
    	{  
    	    System.out.println(filePath+"//Ŀ¼����");  
    	} 
    	filePath=filePath+"/"+username;
       	File file2=new File(filePath);
       	if  (!file2 .exists()  && !file2 .isDirectory())      
       	{       
       	    System.out.println("//������");  
       	    file2 .mkdir();
       	} 
       	else   
       	{
       		System.out.println(filePath+"//Ŀ¼����");  
       	}
        filePath=filePath+"/"+index;  
    	byte[] bs=decode(image.getBytes());
    	FileOutputStream os;
		try {
			os = new FileOutputStream(filePath);
			os.write(bs);
			os.close();
		} catch (FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		return filePath;
    }
}
