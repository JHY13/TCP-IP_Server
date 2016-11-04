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



@WebServlet("/customer/image-detail")
public class ImageDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _code = request.getParameter("code");

		ImageDao imageDao = new MyBatisImageDao();//분리:재사용,분업화
		Image image;
					
		image= imageDao.get(_code);
		request.setAttribute("n", image);	
			
		request.getRequestDispatcher("/WEB-INF/views/customer/image-detail.jsp")
		   .forward(request, response);
		
	}
}
