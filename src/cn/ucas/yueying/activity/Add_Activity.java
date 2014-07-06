package cn.ucas.yueying.activity;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;
import cn.ucas.yueying.network.HttpUtil;
import cn.ucas.yueying.view.GalleryFlow;
import cn.ucas.yueying.view.ImageAdapter;

public class Add_Activity extends Activity {
	// ViewFlipper flipper;
	// GestureDetector detector;
	RadioButton bt_near_activ;
	RadioButton bt_find_view;
	RadioButton bt_manage;
	RadioButton bt_home;
	MyApplication myapplication;
	
	TextView tv1;
	Handler handler;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_activity);// 发起活动
		
		

		tv1 = (TextView) this.findViewById(R.id.textView6);
		myapplication = (MyApplication) getApplication();
		myapplication.setRadioButton_stat(2);
		bt_near_activ = (RadioButton) findViewById(R.id.nearby_activities);
		bt_find_view = (RadioButton) findViewById(R.id.find_view);
		bt_manage = (RadioButton) findViewById(R.id.manage);
		bt_home = (RadioButton) findViewById(R.id.home);

		// 判断哪个RadioButton应为被选中状态
		if (myapplication.getRadioButton_stat() == 1) {
			bt_near_activ.setChecked(true);
		} else if (myapplication.getRadioButton_stat() == 2) {

			bt_find_view.setChecked(true);

		} else if (myapplication.getRadioButton_stat() == 3) {
			bt_manage.setChecked(true);

		} else if (myapplication.getRadioButton_stat() == 4) {

			bt_home.setChecked(true);

		}

		bt_near_activ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(Add_Activity.this, nearby_activities.class);
				startActivity(intent);

				finish();

			}
		});

		bt_manage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(Add_Activity.this, manage.class);
				startActivity(intent);

				finish();

			}
		});

		bt_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Add_Activity.this, person_homepage.class);
				startActivity(intent);
				finish();

			}
		});

		bt_find_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Add_Activity.this, Add_Activity.class);
				startActivity(intent);
				finish();

			}
		});
		
		//

		// 开启消息监听
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) { 
					
					myapplication=(MyApplication)getApplication();
					tv1.setText(myapplication.getCity());
					
				} else if (msg.what == 2) { 

				} 
			}
		};

		//开启获取GPS位置线程
		new Thread(webreq).start();
		
		
		Integer[] images = { R.drawable.image1, R.drawable.image2,
				R.drawable.image3, R.drawable.image4, R.drawable.image5 };

		ImageAdapter adapter = new ImageAdapter(this, images);
		adapter.createReflectedImages();

		GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.Gallery);
		galleryFlow.setAdapter(adapter);

		galleryFlow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:

					Intent intent = new Intent();
					intent.setClass(Add_Activity.this, location.class);
					startActivity(intent);

					finish();
					break;
				case 1:

					break;
				default:
					break;
				}

			}

		});
		;
	}

	
	public   Runnable webreq = new Runnable(){
		 public void run() {
	  			// TODO Auto-generated method stub
			
			 getCity();
			 //获取消息后，向系统发送获取成功消息
			 Message message = new Message();
	          message.what=1;
	          handler.sendMessage(message);
			 }
		 
	};
	
	
	
	
	
	private void getCity() {
		String key = "JUKBZ-24NWJ-7VVFK-KDKT5-RQ3AF-NRFWP";
		String url = "http://apis.map.qq.com/ws/geocoder/v1/?location="
				+ myapplication.getLatitude() + "," + myapplication.getLongitude() + "&key=" + key + "&get_poi=1";
		HttpUtil httputil = new HttpUtil();
		HttpGet request = httputil.getHttpGet(url);
		myapplication=(MyApplication)getApplication();
		String retStr = httputil.queryStringForGet(request);
		try {
			JSONObject jo = new JSONObject(retStr);
			JSONObject jo_c = jo.getJSONObject("result");
			JSONObject jo_city=jo_c.getJSONObject("address_component");
			String str_city = jo_city.getString("city");
			myapplication.setCity(str_city);
			
			System.out.println(str_city);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 到此结束
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 返回主页面
			Intent intent = new Intent();
			intent.setClass(Add_Activity.this, nearby_activities.class);
			startActivity(intent);
			finish();

		}

		return false;

	}

}
