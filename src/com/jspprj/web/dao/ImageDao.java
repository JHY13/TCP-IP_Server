package com.jspprj.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.jspprj.web.entities.Image;

public interface ImageDao {
	Image get(String code) ;//데이터베이스에 있는 데이터를 객체화시켜 받아 사용할 것이기 때문에 
	List<Image> getList(int page, String field, String query) ;
	List<Image> getList(int page);
	List<Image> getList() ;
	int getCount(String field, String query);
	int add(Image notice);
	int update(Image notice);
	int delete(String code) ;
	int insert(Image notice);
	String getLastCode();
	Image getPrev(String code);
	Image getNext(String code);
	int hitUp(String code);
	
}