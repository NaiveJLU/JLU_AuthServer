package edu.jlu.cs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.jlu.cs.module.DBCall;
import edu.jlu.cs.module.FacCall;

public class Register extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Register() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		FileOutputStream fs = new FileOutputStream(new File("D:\\text.txt"));
//		PrintStream p = new PrintStream(fs);
		response.setContentType("application/json");
		try{
			DBCall dbCall=new DBCall();
			FacCall facCall=new FacCall();
			String imageJson=request.getReader().readLine();
			JSONObject clientReq=new JSONObject(imageJson);
			String pic=clientReq.getString("pictures");
			String facRes=facCall.register(imageJson);
			JSONObject facJson=new JSONObject(facRes);
			boolean facSuccess=facJson.getBoolean("success");
			if(facSuccess==false)
			{
				response.setStatus(200);
				PrintWriter out = null;
				try{
					out = response.getWriter();
					out.append(facRes); 
				}catch (IOException e) {  
					e.printStackTrace();  
				}finally{ 
					if (out != null) {  
						out.flush();
						out.close();  
					}
				}
				return;
			}
			String facilitatorIds=facJson.getString("facilitatorIds");
			String dbRes=dbCall.register(facilitatorIds,pic);
			JSONObject dbResJson=new JSONObject(dbRes);
			boolean dbSuccess=dbResJson.getBoolean("success");
			if(dbSuccess==true)
			{
				response.setStatus(200);
				PrintWriter out = null;
				try{
					out = response.getWriter();
					out.append(dbRes); 
				}catch (IOException e) {  
					e.printStackTrace();  
				}finally{ 
					if (out != null) {  
						out.flush();
						out.close();  
					}
				}
				return;
			}
			else
			{
				String errors=dbResJson.getString("errors");
				JSONArray errorsJson=new JSONArray(errors);
				JSONArray newrespJson=new JSONArray();
				for(int i=1;i<=5;i++)
				{
					JSONObject tmp=errorsJson.getJSONObject(i-1);
					tmp.put("pictureId", i);
					newrespJson.put(i-1,tmp);
				}
				JSONObject respJson=new JSONObject();
				respJson.put("success", false);
				respJson.put("pictureErrors", newrespJson.toString());
				response.setStatus(200);
				PrintWriter out = null;
				try{
					out = response.getWriter();
					out.append(respJson.toString()); 
				}catch (IOException e) {  
					e.printStackTrace();  
				}finally{ 
					if (out != null) {  
						out.flush();
						out.close();  
					}
				}
				return;
			}
		}catch(JSONException e) {
			response.setStatus(500);//json¸ñÊ½Òì³£
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
