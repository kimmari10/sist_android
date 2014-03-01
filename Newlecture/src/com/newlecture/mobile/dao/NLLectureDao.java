package com.newlecture.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.newlecture.mobile.R;
import com.newlecture.mobile.vo.Lecture;

public class NLLectureDao implements LectureDao{

	private Context context;
	
	public NLLectureDao(Context context){
		this.context = context;
		
		SQLiteDatabase newlecDB = context.openOrCreateDatabase("newlecture", Context.MODE_PRIVATE, null);
		newlecDB.execSQL("CREATE TABLE IF NOT EXISTS Lectures(" +
				"Code Text, Title Text, Degree Text, Price integer, Image Text)");
		
	}
	
	@Override
	public List<Lecture> getLectures() {

		return getLectures("");
	}

	@Override
	public List<Lecture> getLectures(String query) {

		String sql = null;
		Cursor cur = null;
		SQLiteDatabase newlecDB = context.openOrCreateDatabase("newlecture", Context.MODE_PRIVATE, null);
		
		if(query.equals("") || query == null)
		{
			sql = "SELECT * FROM Lectures";
			cur = newlecDB.rawQuery(sql, null);	
		}
		else
		{
			sql = "SELECT * FROM Lectures WHERE Title like ?";
			cur = newlecDB.rawQuery(sql, new String[]{"%"+query+"%"});
		}

		
		List<Lecture> list = new ArrayList<Lecture>();
		while(cur.moveToNext())
		{
			Lecture lecture = new Lecture();
			lecture.setCode(cur.getString(0));
			lecture.setTitle(cur.getString(1));
			lecture.setDegree(cur.getString(2));
			lecture.setPrice(cur.getInt(3));
			lecture.setImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.sam));
			
			list.add(lecture);
		}
		
		cur.close();
		newlecDB.close();
		
		
		return list;
		
	}
	
	
	@Override
	public Lecture getLecture(String code) {
		String sql =  "SELECT * FROM Lectures where code =?";

		SQLiteDatabase newlecDB = context.openOrCreateDatabase("newlecture", Context.MODE_PRIVATE, null);
		Cursor cur = newlecDB.rawQuery(sql, new String[]{code});

		Lecture lecture = null;
		if(cur.moveToNext())
		{
			lecture = new Lecture();
			lecture.setCode(cur.getString(0));
			lecture.setTitle(cur.getString(1));
			lecture.setDegree(cur.getString(2));
			lecture.setPrice(cur.getInt(3));
			lecture.setImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.sam));
		}
		
		cur.close();
		newlecDB.close();
		
		
		
		return lecture;
	}

	@Override
	public void insert(Lecture lecture) {
		SQLiteDatabase newlecDB = context.openOrCreateDatabase("newlecture", Context.MODE_PRIVATE, null);
		String sql = "insert into lectures values(?,?,?,?,?)";
		
		ContentValues values = new ContentValues();
		values.put("Code", lecture.getCode());
		values.put("Title", lecture.getTitle());
		values.put("Degree", lecture.getDegree());
		values.put("Price", lecture.getPrice());
		values.put("Image", "sam");
		
		newlecDB.insert("lectures", null, values);
		newlecDB.close();
		
	}

	@Override
	public void update(String code) {
		
	}
	
	@Override
	public void delete() {
		delete("");
	}

	@Override
	public void delete(String code) {
		String sql = null;
		SQLiteDatabase newlecDB = context.openOrCreateDatabase("newlecture", Context.MODE_PRIVATE, null);
		
		if(code.equals("")||code == null){
			sql = "delete from Lectures";
			newlecDB.execSQL(sql);
		}
		else{
			sql = "delete from Lectures where code = ?";
			newlecDB.execSQL(sql, new String[]{code});
		}
		
		newlecDB.close();
	}
	




}
