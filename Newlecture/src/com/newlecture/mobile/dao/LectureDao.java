package com.newlecture.mobile.dao;

import java.util.List;

import com.newlecture.mobile.vo.Lecture;

public interface LectureDao {
	
	public List<Lecture> getLectures();
	public List<Lecture> getLectures(String query);
	public Lecture getLecture(String code);
	public void insert(Lecture lecture);
	
	public void update(String code);

	public void delete();
	public void delete(String code);

}
