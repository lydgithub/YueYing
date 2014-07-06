package cn.ucas.yueying.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;

public class welcome extends Activity {
	MyApplication myapplication;
	Handler mHandler;
	
	private static final int GOTO_MAIN_ACTIVITY = 0;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);

		/*
		 * mHandler = new Handler(){ public void
		 * handleMessage(android.os.Message msg) {
		 * 
		 * switch (msg.what) { case GOTO_MAIN_ACTIVITY: Intent intent = new
		 * Intent(); intent.setClass(welcome.this, nearby_activities.class);
		 * startActivity(intent); finish(); break;
		 * 
		 * default: break; } }; };
		 * mHandler.sendEmptyMessageAtTime(GOTO_MAIN_ACTIVITY, 100000);//10秒跳转
		 */

		new Handler().postDelayed(new Runnable() {
			public void run() {
				/* Create an Intent that will start the Main WordPress Activity. */
				Intent mainIntent = new Intent(welcome.this,
						nearby_activities.class);
				welcome.this.startActivity(mainIntent);
				welcome.this.finish();
			}
		}, 2900); // 2900 for release

		
		
		openGPSSettings();
		getLocation();
	}

	
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
		myapplication = (MyApplication) getApplication();
		
		if (location != null) {
			myapplication.setLatitude(location.getLatitude());
			myapplication.setLongitude(location.getLongitude());
			
		} else {
			Context context=getApplicationContext();
   	        CharSequence text="无法获取GPS信息！";
   	        int duration =Toast.LENGTH_SHORT;
   	        Toast toast=Toast.makeText(context, text, duration);
   	        toast.show();
		}

	}

	
	
}
