package com.jspprj.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.dao.mybatis.MyBatisImageDao;
import com.jspprj.web.entities.Image;

@WebServlet("/customer/image")
public class ImageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String p = request.getParameter("p");
		String t = request.getParameter("t");
		String q = request.getParameter("q");
		
		int page=1;
		String field="TITLE";
		String query ="";
		
		if(p!=null && !p.equals(""))
		{
			page=Integer.parseInt(p);
		}
				
		if(t != null && !t.equals(""))	
			field = t;

		if(q != null)
			query=q;
		
		ImageDao imageDao = new MyBatisImageDao();
		//imageDao.getList(page, field, query);
		List<Image> list = imageDao.getList(page, field, query);
		int count= imageDao.getCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		//response.sendRedirect("notice.jsp");

		request.getRequestDispatcher("/WEB-INF/views/customer/image.jsp").forward(request,response);
		//Dispatcher �늻援곌�瑜� �샇異쒗븯湲� �쐞�븳�룄援� forward 
	}

}