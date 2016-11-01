package com.jspprj.web.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.entities.Image;


public class Program {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		/*String src ="com/jspprj/web/dao/mybatis/config.xml";		
		InputStream is = Resources.getResourceAsStream(src);		
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);*/
		
		/////////Factory를 만드는 과정 (1회만 작성)전역화////////////////////////
	/*	
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		NoticeDao noticeDao = session.getMapper(NoticeDao.class);
		
		Notice n = noticeDao.get("981");
		
		System.out.println(n.getTitle());*/
	}
}
