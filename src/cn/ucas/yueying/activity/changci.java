package cn.ucas.yueying.activity;

import cn.ucas.yueying.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class changci  extends Activity{
    Button xuanze;
    Button back;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.changci);
		
		back=(Button)findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(changci.this, location.class);
				startActivity(intent);
				finish();
			}
		});
		xuanze=(Button)findViewById(R.id.xuanze);
		xuanze.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(changci.this, add_activity_after.class);
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
			intent.setClass(changci.this, location.class);
			startActivity(intent);
			finish();

		}

		return false;

	}

}
