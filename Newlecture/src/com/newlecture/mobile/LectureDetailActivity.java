package com.newlecture.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newlecture.mobile.dao.NLLectureDao;
import com.newlecture.mobile.vo.Lecture;

public class LectureDetailActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lecturedetail);
		
		Intent intent = getIntent();
		
		String code = intent.getStringExtra("code");
		
//		Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
		
		Lecture lec = new NLLectureDao(this).getLecture(code);
		
		ImageView ivImage = (ImageView) findViewById(R.id.sam);
		TextView tvTitle = (TextView) findViewById(R.id.title);
		TextView tvPrice = (TextView) findViewById(R.id.price);
		TextView tvDegree = (TextView) findViewById(R.id.degree);
		
		tvTitle.setText(lec.getTitle());
		tvPrice.setText(String.valueOf(lec.getPrice()));
		tvDegree.setText(lec.getDegree());
		ivImage.setImageBitmap(lec.getImage());
		
		
	}
	

}
