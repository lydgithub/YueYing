package cn.ucas.yueying.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;
import cn.ucas.yueying.view.ListViewExt;

public class manage extends Activity {

	ListViewExt managelistview;
	public MyApplication myapplication;
	RadioButton bt_near_activ;
	RadioButton bt_find_view;
	RadioButton bt_manage;
	RadioButton bt_home;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.manage);
		myapplication = (MyApplication) getApplication();
		myapplication.setRadioButton_stat(3);
		
		bt_near_activ = (RadioButton) findViewById(R.id.nearby_activities);
		bt_find_view=(RadioButton) findViewById(R.id.find_view);
		bt_manage=(RadioButton) findViewById(R.id.manage);
		bt_home = (RadioButton) findViewById(R.id.home);
		//判断哪个RadioButton应为被选中状态
				if(myapplication.getRadioButton_stat()==1){
				  bt_near_activ.setChecked(true);
				 }else if(myapplication.getRadioButton_stat()==2){
					 
					 bt_find_view.setChecked(true); 
					 
					 
				 }else if(myapplication.getRadioButton_stat()==3){
					 bt_manage.setChecked(true);
					 
					 
					 
				 }else if(myapplication.getRadioButton_stat()==4){
					 
					 
					 bt_home.setChecked(true);
					 
				 }
		
		bt_near_activ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(manage.this, nearby_activities.class);
				startActivity(intent);

				finish();

			}
		});
		
		bt_manage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(manage.this, manage.class);
				startActivity(intent);

				finish();

			}
		});
		
		bt_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(manage.this, person_homepage.class);
				startActivity(intent);
                finish();
				
			}
		});
		
		
		bt_find_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(manage.this, Add_Activity.class);
				startActivity(intent);
                finish();
				
			}
		});
		managelistview = (ListViewExt) findViewById(R.id.managelistView);
		ArrayList<HashMap<String, Object>> managemap = myapplication
				.getManageList();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("manage_title", "系统秘书");
		map1.put("manage_img", R.drawable.mishu);
		map1.put("manage_text", "晴天报名参加了你发起的活动");
		map1.put("manage_time", "10:34");
		
		

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("manage_title", "巴黎咖啡香");
		map2.put("manage_img", R.drawable.u55_normal);
		map2.put("manage_text", "xxxxxxx");
		map2.put("manage_time", "10:34");
		
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("manage_title", "雪漫富士山");
		map3.put("manage_img", R.drawable.u20_normal);
		map3.put("manage_text", "xxxxxxx");
		map3.put("manage_time", "10:34");
		
		managemap.add(map1);
		managemap.add(map2);
		managemap.add(map3);

		SimpleAdapter manageAdapter = new SimpleAdapter(this, managemap,
				R.layout.manage_item, new String[] { "manage_title",
						"manage_img", "manage_text", "manage_time"}, new int[] {
						R.id.manage_title, R.id.manage_img, R.id.manage_text,
						R.id.manage_time});
		managelistview.setAdapter(manageAdapter);

		
		managelistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(manage.this, baomingguanli.class);
				startActivity(intent);
			}
		});
		

	}
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			//返回主页面
			Intent intent = new Intent();
				intent.setClass(manage.this, nearby_activities.class);
				startActivity(intent);
				finish();

		}

		return false;

	}
	
}
