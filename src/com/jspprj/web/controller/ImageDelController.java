package com.jspprj.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.dao.mybatis.MyBatisImageDao;


@WebServlet("/customer/image-del")
public class ImageDelController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		
		ImageDao imageDao = new MyBatisImageDao();
				
		imageDao.delete(code);
		
		response.sendRedirect("image");
	}
}
