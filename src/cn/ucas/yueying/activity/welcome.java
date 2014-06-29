package cn.ucas.yueying.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.widget.Toast;
import cn.ucas.yueying.R;

public class welcome extends Activity {

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
}
