import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.org.mozilla.javascript.internal.ObjToIntMap.Iterator;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


public class UploadServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
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
		doPost(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//在解析之前判断一下是否是文件上传类型
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		//创建文件上传处理器
		FileItemFactory factory = new DiskFileItemFactory();
		
		//创建上传服务解析器
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		java.util.List items=null;
		
		if(items==null)
		{
			try {
				//解析上传文件，存放在list中
				items=upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		//迭代上传解析出的数据项
		java.util.Iterator it=items.iterator();
		
		while(it.hasNext())
		{
			FileItem item=(FileItem) it.next();
			if(item.isFormField())
			{
				String name=item.getFieldName();
				String value=item.getString();
			}
			else
			{
				String name=item.getName();
				System.out.println(name);
				//截取字符串，拼出文件的名字
				int index = name.lastIndexOf("\\");
				name=name.substring(index+1);
				//将文件存在E盘的根目录之下
				File dir=new File("E:\\");
				File file = new File(dir, name);			
				try {
					item.write(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("upload success~~");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
