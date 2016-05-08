package edu.jlu.cs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.sf.json.JSONObject;


public class CreateUser extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreateUser() {
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

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject json=new JSONObject();
		
		out.println(request.getParameter("abc"));
		
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

		/*DBCall dbcall=new DBCall();
		FacCall facCall=new FacCall();
		DecodeSave decodeSave=new DecodeSave();
		int responseCode=500;
		SaveUserImages2DB sui2db=new SaveUserImages2DB();
		JSONObject respJsonO=new JSONObject();
		String usr=request.getParameter("username");
		String passwd=request.getParameter("passwordMd5");
		String imageJson=request.getReader().readLine();
		JSONObject imageJsonO=new JSONObject(imageJson);
		String images=imageJsonO.getString("image");
		decodeSave.work(usr,images);
		String dbs=dbcall.work(usr, passwd);
		JSONObject dbjsonO= new JSONObject(dbs);
		int code=dbjsonO.getInt("code");
		if(code==201)
		{
			String indentifier=dbjsonO.getString("userFACIDs");
			String internal_ID=dbjsonO.getString("internal_ID");
			String facs=facCall.work(internal_ID,indentifier,images);
			JSONObject facjsonO=new JSONObject(facs);
			int faccode=facjsonO.getInt("code");
			if(faccode==201)
			{
				String user_FAC_ids=facjsonO.getString("user_FAC_ids");
				Boolean train_status=facjsonO.getBoolean("train_status");
				String suistr=sui2db.work(internal_ID, user_FAC_ids, train_status, images);
				JSONObject sui2dbJsonO=new JSONObject(suistr);
				responseCode=201;
			}
		}
		int appcode=dbjsonO.getInt("appCode");
		String Message=dbjsonO.getString("message");
		respJsonO.put("Message", Message);
		respJsonO.put("code", responseCode);
		response.setStatus(responseCode);*/
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
