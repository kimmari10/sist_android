package com.newlecture.mobile;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newlecture.mobile.adapter.LectureAdapter;
import com.newlecture.mobile.dao.LectureDao;
import com.newlecture.mobile.dao.NLLectureDao;
import com.newlecture.mobile.vo.Lecture;

public class LectureActivity extends Activity {
	
	private ListView lv;
	private EditText txtSearch;
	private Button btnOk;
	
	private LectureDao lectureDao;
	
	public LectureActivity() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.lecture);
	    lectureDao = new NLLectureDao(this);
	    
	    //객체 설정
	    lv = (ListView) findViewById(R.id.lv);
	    txtSearch = (EditText) findViewById(R.id.txtSearch);
	    btnOk = (Button) findViewById(R.id.btnOk);
	    
	    final List<Lecture> lectures = lectureDao.getLectures(); 
	    registerForContextMenu(lv);
	    

	    lv.setAdapter(new LectureAdapter(this, R.layout.lecture_item,  lectures));
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
//				Toast.makeText(getApplicationContext(), lectures.get(position).getTitle(), Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(LectureActivity.this, LectureDetailActivity.class);
				// putExtra를 이용해 정보저장해서 넘겨준다
				intent.putExtra("code", lectures.get(position).getCode());
				
				startActivity(intent);
				
				
			}
		});
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				return false;
			}
		});
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String query = txtSearch.getText().toString();
				List<Lecture> lectures = new NLLectureDao(LectureActivity.this).getLectures(query);
				
				lv.setAdapter(new LectureAdapter(LectureActivity.this, R.layout.lecture_item, lectures));
				
			}
		});

		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		menu.setHeaderTitle("MyContext Menu");
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lecture_ctx_menu, menu);
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch(item.getItemId())
		{
		case R.id.payment :
			Toast.makeText(this, "payment", Toast.LENGTH_SHORT).show();
			break;
		case R.id.preview :
			Toast.makeText(this, "preview", Toast.LENGTH_SHORT).show();
			break;
		case R.id.detail :
			Toast.makeText(this, "detail", Toast.LENGTH_SHORT).show();
			break;
			
		}
		return true;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.menu_lecture, menu);
		
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

//		Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
		
		switch(item.getItemId())
		{
		case R.id.mn_home:
			Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
			break;
		case R.id.mn_login:
			Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
			break;
		
		}
		return true;
	}
	
	

}
