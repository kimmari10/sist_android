package com.newlecture.mobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newlecture.mobile.R;
import com.newlecture.mobile.vo.Lecture;

public class LectureAdapter extends ArrayAdapter<Lecture>{

	private List<Lecture> lectures;
	private Context context;
	
	public LectureAdapter(Context context, int resource, List<Lecture> lectures) 
	{
		super(context, resource, lectures);
		this.context = context;
		this.lectures = lectures;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
		{
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.lecture_item, null);
		}
		
		ImageView ivImage = (ImageView) convertView.findViewById(R.id.sam);
		TextView tvTitle = (TextView) convertView.findViewById(R.id.title);
		TextView tvPrice = (TextView) convertView.findViewById(R.id.price);
		TextView tvDegree = (TextView) convertView.findViewById(R.id.degree);
		
		Lecture lec = lectures.get(position);
		
		
		tvTitle.setText(lec.getTitle());
		tvPrice.setText(String.valueOf(lec.getPrice()));
		tvDegree.setText(lec.getDegree());
		ivImage.setImageBitmap(lec.getImage());
		
		return convertView;
	}

}
