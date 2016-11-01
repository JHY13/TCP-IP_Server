package com.jspprj.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.dao.mybatis.ImageNoticeDao;
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
		
		ImageDao noticeDao = new ImageNoticeDao();
		//noticeDao.getList(page, field, query);
		List<Image> list = noticeDao.getList(page, field, query);
		int count= noticeDao.getCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);
			
		//1.그냥부르기: 니가 새로해 
		//response.sendRedirect("notice.jsp");

		//2.자원을 공유하면서 부르기: 일을 이서서 계속
		request.getRequestDispatcher("/WEB-INF/views/customer/notice.jsp").forward(request,response);
		//Dispatcher 누군가를 호출하기 위한도구 forward 
	}

}