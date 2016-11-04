package com.jspprj.web.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.dao.mybatis.MyBatisImageDao;
import com.jspprj.web.entities.Image;



@WebServlet("/customer/image-reg")
public class ImageRegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/customer/image-reg.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String regdate = request.getParameter("regdate");
		String delivery_number = request.getParameter("delivery_number");
		String item = request.getParameter("item");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		
		ImageDao imageDao = new MyBatisImageDao();
		
		Image n = new Image();		
		n.setTitle(title);
		n.setContent(content);
		n.setName("name");
		//n.setAddress(regdate);
		n.setDelivery_number(delivery_number);
		n.setItem(item);
		n.setName(name);
		n.setPhone(phone);
		n.setAddress(address);
		
		imageDao.insert(n);
		
		response.setContentType("text/html; charset=UTF-8");
		response.sendRedirect("image");
	}
}






