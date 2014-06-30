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
	double latitude;
	double longitude;
	TextView tv1;
	Handler handler;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_activity);// 发起活动
		openGPSSettings();
		getLocation();
		

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
	
	
	
	
	private void openGPSSettings() {
		LocationManager alm = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
			return;
		}

		Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
		startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面

	}

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) { // 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
			// log it when the location changes
			if (location != null) {
				System.out.println("Location changed : Lat: "
						+ location.getLatitude() + " Lng: "
						+ location.getLongitude());
			}
		};

		public void onProviderDisabled(String provider) {
			// Provider被disable时触发此函数，比如GPS被关闭
		}

		public void onProviderEnabled(String provider) {
			// Provider被enable时触发此函数，比如GPS被打开
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
		}
	};

	private void getLocation() {
		// 获取位置管理服务
		LocationManager locationManager;
		String serviceName = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) this.getSystemService(serviceName);
		// 查找到服务信息
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

		String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
		Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
		updateToNewLocation(location);

		// 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
		locationManager.requestLocationUpdates(provider, 1 * 1000, 500,
				locationListener);
	}

	private void updateToNewLocation(Location location) {

		tv1 = (TextView) this.findViewById(R.id.textView6);
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			tv1.setText("维度：" + latitude + "\n经度：" + longitude);
		} else {
			tv1.setText("无法获取地理信息");
		}

	}

	private void getCity() {
		String key = "JUKBZ-24NWJ-7VVFK-KDKT5-RQ3AF-NRFWP";
		String url = "http://apis.map.qq.com/ws/geocoder/v1/?location="
				+ latitude + "," + longitude + "&key=" + key + "&get_poi=1";
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
