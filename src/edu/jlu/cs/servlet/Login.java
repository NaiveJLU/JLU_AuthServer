package edu.jlu.cs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jlu.cs.module.DBCall;
import edu.jlu.cs.module.FacCall;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		response.getWriter().write("Hello");
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
		response.setContentType("application/json");
		try{
			DBCall dbCall=new DBCall();
			FacCall facCall=new FacCall();
			String imageJson=request.getReader().readLine();
			JSONObject clientReq=new JSONObject(imageJson);
			String pic=clientReq.getString("picture");
			String usrId=clientReq.getString("userId");
			String dbRes=dbCall.login(usrId);
			
			JSONObject dbJson=new JSONObject(dbRes);
			boolean dbSuccess=dbJson.getBoolean("success");
			if(dbSuccess==false)
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
			String facilitatorIds=dbJson.getString("facilitatorIds");
			String facRes=facCall.login(facilitatorIds,pic);
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
		}catch(JSONException e) {
			response.setStatus(500);//json��ʽ�쳣
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
