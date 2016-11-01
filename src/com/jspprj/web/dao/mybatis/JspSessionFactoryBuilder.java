package com.jspprj.web.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class JspSessionFactoryBuilder {
	static String src ="com/jspprj/web/dao/mybatis/config.xml";			
	static SqlSessionFactory ssf = null;
	
	static{
		InputStream is = null;
		try {
			is= Resources.getResourceAsStream(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ssf = new SqlSessionFactoryBuilder().build(is);
	}
	
	public static SqlSessionFactory getSqlSqlsessionFactory(){
		return ssf;
	}
}
