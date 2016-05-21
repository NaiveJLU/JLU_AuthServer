package edu.jlu.cs.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import edu.jlu.cs.module.DBCall;
import edu.jlu.cs.module.FacCall;
import edu.jlu.cs.util.Global;

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
		doPost(request,response);
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
		FileOutputStream fs = new FileOutputStream(new File("LoginLog.txt"));
		PrintStream p = new PrintStream(fs);
		try{
			DBCall dbCall=new DBCall();
			FacCall facCall=new FacCall();
			String imageJson=request.getReader().readLine();
			JSONObject clientReq=new JSONObject(imageJson);
			String pic=clientReq.getString("picture");
			String usrId=clientReq.getString("userId");
			p.println("get request success "+imageJson);
//			String usrId="hahah";
//			String pic=null;
			String dbRes=dbCall.login(usrId);
			JSONTokener jtk=new JSONTokener(dbRes);
			JSONObject dbJson=(JSONObject) jtk.nextValue();
			p.println("connect DB success "+dbJson);
			boolean dbSuccess=dbJson.getBoolean("success");
			p.print(dbSuccess);
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
			JSONArray facilitatorIds=dbJson.getJSONArray("facilitatorIds");
			String facRes=facCall.login(facilitatorIds,pic);
			p.println("connect fac success "+facRes);
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
			response.setStatus(500);//json∏Ò Ω“Ï≥£
			response.getWriter().write("json form error");
			p.println("Json form Error");
			e.printStackTrace();
		}
		finally{p.close();}
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
