package com.newlecture.mobile;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.newlecture.mobile.dao.LectureDao;
import com.newlecture.mobile.dao.NLLectureDao;
import com.newlecture.mobile.vo.Lecture;

public class MainActivity extends Activity {
	
	private EditText tx;
	private TextView tt;
	private String[] data = {
			"삼천포",
			"조윤진",
			"ㅇㅇㅇ",
			"ㅁㅁㅁ"
	};
	
	private LectureDao lectureDao;
	
	public MainActivity() {
	
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		lectureDao = new NLLectureDao(this);
		setContentView(R.layout.main);
		
		ActionBar actionBar = getActionBar();
		
		Tab tab1 = actionBar.newTab();
		tab1.setText("tab1");
		tab1.setTabListener(new MyTabListener(this, "frag1", Fragment.class));
		actionBar.addTab(tab1);
		
		Tab tab2 = actionBar.newTab();
		tab2.setText("tab2");
		tab2.setTabListener(new MyTabListener(this, "frag2", Fragment.class));
		actionBar.addTab(tab2);
		
		
		
		
		DataDownladTask task = new DataDownladTask();
		task.execute("http://211.238.142.188/");
		
		ImageView iv = (ImageView) findViewById(R.id.main_img);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				
				Intent intent = new Intent(MainActivity.this, LectureActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.mainmenu_lecture, menu);
		
		
		return true;
	}
	
	class DataDownladTask extends AsyncTask<String, ArrayList<Lecture>, Boolean>
	{
		private ProgressDialog dlg = null;
				
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			dlg = ProgressDialog.show(MainActivity.this, "강좌 업뎃중", "로딩.....");
			dlg.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					
					DataDownladTask.this.cancel(true);
				}
			});
		}
		
		@Override
		protected Boolean doInBackground(String... params) {

			
			try {

				/*String url = params[0];
				HttpResponse response;
				HttpClient client = new DefaultHttpClient();
				
				HttpGet request = new HttpGet(url);
				response = client.execute(request);
				InputStream ins = response.getEntity().getContent();
				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(ins);
				
				Element root = document.getDocumentElement();
				NodeList codes = root.getElementsByTagName("code");
				NodeList titles = root.getElementsByTagName("title");
				NodeList degrees = root.getElementsByTagName("degree");
				NodeList prices = root.getElementsByTagName("price");
				
				int count = codes.getLength();
				lectureDao.delete(""); //기존데이터 삭제
				
				for(int i = 0; i <count; i++)
				{
					Lecture lec = new Lecture();
					lec.setCode(codes.item(i).getTextContent());
					lec.setTitle(titles.item(i).getTextContent());
					lec.setDegree(degrees.item(i).getTextContent());
					lec.setPrice(Integer.parseInt(prices.item(i).getTextContent()));

					lectureDao.insert(lec);
					
				}*/
				
				/*Log.d("개수", codes.getLength()+"");
				Log.d("강좌명", titles.item(0).getTextContent());*/
				
				// SAX XML Push Parser ====================================
				SAXParserFactory factory = SAXParserFactory.newInstance();
				
				//Create a SAXParser
				SAXParser saxParser = factory.newSAXParser();
				
				DefaultHandler handler  = new LectureSAXHandler();
				saxParser.parse("http://211.238.142.188", handler);
				
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			dlg.dismiss();
		}
		
	}
	
	
	class MyTabListener<T> implements TabListener
	{
		private Context context;
		private Fragment fragment;
		
		public MyTabListener(Context context, String tag, Class<T> _class) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
		

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(fragment == null)
				fragment.instantiate(context, fname.getName());
			
			ft.attach(fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//이너클래스로 SAX핸들러추가
	class LectureSAXHandler extends DefaultHandler
	{
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
		}
		
		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.endDocument();
		}
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			System.out.println("Start Element : " + qName);
			
			for(int i=0; i<attributes.getLength(); i++)
			{
				System.out.println("attr name : " + attributes.getQName(i));
				System.out.println("attr val : " + attributes.getValue(i));
			}
			
			
			
		}
		
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			System.out.println("end element : "+ qName);
			
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			String str = (new String(ch, start, length));
			System.out.println("str ===========" +str);
			
		}
		
		@Override
		public void error(SAXParseException e) throws SAXException {
			// TODO Auto-generated method stub
			super.error(e);
		}
		
		@Override
		public void fatalError(SAXParseException e) throws SAXException {
			// TODO Auto-generated method stub
			super.fatalError(e);
		}
		
		@Override
		public void warning(SAXParseException e) throws SAXException {
			// TODO Auto-generated method stub
			super.warning(e);
		}
		
	}
	
}
