package com.newlecture.mobile;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActionProvider extends ActionProvider{

	private Context context;
	
	public MyActionProvider(Context context) {
		super(context);
		this.context = context;
	}
	
	@Override
	@Deprecated
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		LayoutInflater inf = LayoutInflater.from(context);
		View view = inf.inflate(R.layout.action_provider, null);
		//������ ��ҿ� ���� Ŭ�� �̺�Ʈ�� ����
		
		return null;
	}

	//�׼ǹ� ��ü�� Ŭ������ ��� �̺�Ʈ
	@Override
	public boolean onPerformDefaultAction() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		super.onPrepareSubMenu(subMenu);
		
		
		
		subMenu.clear();
		subMenu.add("sub1").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Toast.makeText(context, "����1", Toast.LENGTH_SHORT).show();
				
				return true;
			}
		});
		
		subMenu.add("sub2").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Toast.makeText(context, "����2", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
	
	@Override
	public boolean hasSubMenu() {
		// TODO Auto-generated method stub
		return true;
	}
			
}
