package com.jspprj.web.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jspprj.web.dao.ImageDao;
import com.jspprj.web.entities.Image;

public class ImageNoticeDao implements ImageDao{
	
	private SqlSessionFactory ssf;
	
	public ImageNoticeDao(){
		ssf = JspSessionFactoryBuilder.getSqlSqlsessionFactory();
	}

	@Override
	public Image get(String code) {
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		return noticeDao.get(code);
	}

	@Override
	public List<Image> getList(int page, String field, String query){
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		return noticeDao.getList(page, field, query);
	}
	
	@Override
	public int getCount(String field, String query) {
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		int count = noticeDao.getCount(field, query);
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
	public int add(Image notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Image notice) {
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		
		int result = noticeDao.update(notice);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public int delete(String code) {
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		
		int result = noticeDao.delete(code);
		session.commit();
		session.close();
		return result;
	}
	
	@Override
	public int insert(Image notice) {
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		
		int result = noticeDao.insert(notice);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public String getLastCode() {
		SqlSession session = ssf.openSession(); //트랜잭션을 하기위한 도구
		ImageDao noticeDao = session.getMapper(ImageDao.class);
		String code = noticeDao.getLastCode();
		session.close();
		return code;
	}
	
	/* 준모 오빠가 주신 코드 16.10.31 */
	
	@Override
	   public Image getPrev(String code) {
	      // TODO Auto-generated method stub
	      SqlSession session = ssf.openSession(); //
	      ImageDao noticeDao = session.getMapper(ImageDao.class);
	      
	      Image notice =  noticeDao.getPrev(code);
	      
	      
	      
	      return notice;
	   }

	   @Override
	   public Image getNext(String code) {
	      // TODO Auto-generated method stub
	      SqlSession session = ssf.openSession(); //
	      ImageDao noticeDao = session.getMapper(ImageDao.class);
	         
	      Image notice =  noticeDao.getNext(code);
	      
	      
	      
	      return notice;
	   }

	   @Override
	   public int hitUp(String code) {
	      SqlSession session = ssf.openSession(); //
	      ImageDao noticeDao = session.getMapper(ImageDao.class);
	      
	      int result = noticeDao.hitUp(code);
	      
	      session.commit();
	      session.close();
	      
	      return result;
	   }
	   
	   
	 

}
