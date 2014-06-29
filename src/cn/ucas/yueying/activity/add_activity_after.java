package cn.ucas.yueying.activity;

import cn.ucas.yueying.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class add_activity_after extends Activity{
	Button faqi;
	Button back;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_activity_after);
		back=(Button)findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(add_activity_after.this, changci.class);
				startActivity(intent);
				finish();
				
				
			}
		});
		faqi=(Button)findViewById(R.id.bt_faqi);
		faqi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(add_activity_after.this, activity_details.class);
				
				startActivity(intent);
				finish();
				
			}
		});
	}
	

	
	
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			//返回主页面
			Intent intent = new Intent();
			intent.setClass(add_activity_after.this, changci.class);
			
			startActivity(intent);
			finish();

		}

		return false;

	}
}
