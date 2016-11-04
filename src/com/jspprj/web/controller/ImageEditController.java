package com.jspprj.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.dao.mybatis.MyBatisImageDao;
import com.jspprj.web.entities.Image;


@WebServlet("/customer/image-edit")
public class ImageEditController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String address = request.getParameter("address");
	
		ImageDao imageDao = new MyBatisImageDao();
		
		Image n = new Image();
		n.setCode(code);
		n.setTitle(title);
		n.setContent(content);
		n.setAddress(address);
		
		imageDao.update(n);
		
		response.sendRedirect("image-detail?code="+code);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String _code = request.getParameter("code");

		ImageDao imageDao = new MyBatisImageDao();//�и�:����,�о�ȭ
		Image image;
			
		image = imageDao.get(_code);
		request.setAttribute("n", image);
		
		request.getRequestDispatcher("/WEB-INF/views/customer/image-edit.jsp")
		   .forward(request, response);
	}
}






