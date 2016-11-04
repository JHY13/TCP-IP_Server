package com.jspprj.web.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.entities.Image;

public class MyBatisImageDao implements ImageDao{
	
	private SqlSessionFactory ssf;
	
	public MyBatisImageDao(){
		ssf = JspSessionFactoryBuilder.getSqlSqlsessionFactory();
	}

	@Override
	public Image get(String code) {
		SqlSession session = ssf.openSession(); //?Š¸?œ?­?…˜?„ ?•˜ê¸°ìœ„?•œ ?„êµ?
		ImageDao imageDao = session.getMapper(ImageDao.class);
		return imageDao.get(code);
	}

	@Override
	public List<Image> getList(int page, String field, String query){
		SqlSession session = ssf.openSession(); //?Š¸?œ?­?…˜?„ ?•˜ê¸°ìœ„?•œ ?„êµ?
		ImageDao imageDao = session.getMapper(ImageDao.class);
		return imageDao.getList(page, field, query);
	}
	
	@Override
	public int getCount(String field, String query) {
		SqlSession session = ssf.openSession(); //?Š¸?œ?­?…˜?„ ?•˜ê¸°ìœ„?•œ ?„êµ?
		ImageDao imageDao = session.getMapper(ImageDao.class);
		int count = imageDao.getCount(field, query);
		session.close();
		return count;
	}

	@Override
	public List<Image> getList(int page) {

		return getList(page, "TITLE", "");
	}

	@Override
	public List<Image> getList() {

		return getList(1, "TITLe", "");
	}

	@Override
	public int add(Image image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Image image) {
		SqlSession session = ssf.openSession(); //?Š¸?œ?­?…˜?„ ?•˜ê¸°ìœ„?•œ ?„êµ?
		ImageDao imageDao = session.getMapper(ImageDao.class);
		
		int result = imageDao.update(image);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public int delete(String code) {
		SqlSession session = ssf.openSession(); 
		ImageDao imageDao = session.getMapper(ImageDao.class);
		
		int result = imageDao.delete(code);
		session.commit();
		session.close();
		return result;
	}
	
	@Override
	public int insert(Image image) {
		SqlSession session = ssf.openSession(); //?Š¸?œ?­?…˜?„ ?•˜ê¸°ìœ„?•œ ?„êµ?
		ImageDao imageDao = session.getMapper(ImageDao.class);
		
		int result = imageDao.insert(image);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public String getLastCode() {
		SqlSession session = ssf.openSession(); //?Š¸?œ?­?…˜?„ ?•˜ê¸°ìœ„?•œ ?„êµ?
		ImageDao imageDao = session.getMapper(ImageDao.class);
		String code = imageDao.getLastCode();
		session.close();
		return code;
	}
	
	/* ì¤?ëª? ?˜¤ë¹ ê? ì£¼ì‹  ì½”ë“œ 16.10.31 */
	
	@Override
	   public Image getPrev(String code) {
	      // TODO Auto-generated method stub
	      SqlSession session = ssf.openSession(); //
	      ImageDao imageDao = session.getMapper(ImageDao.class);
	      
	      Image image =  imageDao.getPrev(code);
	      
	      
	      
	      return image;
	   }

	   @Override
	   public Image getNext(String code) {
	      // TODO Auto-generated method stub
	      SqlSession session = ssf.openSession(); //
	      ImageDao imageDao = session.getMapper(ImageDao.class);
	         
	      Image image =  imageDao.getNext(code);
	      
	      
	      
	      return image;
	   }

	   @Override
	   public int hitUp(String code) {
	      SqlSession session = ssf.openSession(); //
	      ImageDao imageDao = session.getMapper(ImageDao.class);
	      
	      int result = imageDao.hitUp(code);
	      
	      session.commit();
	      session.close();
	      
	      return result;
	   }
	   
	   
	 

}
