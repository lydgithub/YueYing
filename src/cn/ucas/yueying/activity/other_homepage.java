package cn.ucas.yueying.activity;


import cn.ucas.yueying.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class other_homepage extends Activity{
	RadioButton bt_near_activ;
	RadioButton bt_find_view;
	RadioButton bt_manage;
	RadioButton bt_home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_homepage);
		bt_near_activ=(RadioButton) findViewById(R.id.nearby_activities);
		bt_near_activ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent();
				intent.setClass(other_homepage.this, nearby_activities.class);
				startActivity(intent);

				finish();
				
				
			}
		});
		bt_home=(RadioButton) findViewById(R.id.home);
		bt_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(other_homepage.this, other_homepage.class);
				startActivity(intent);

				finish();
			}
		});
	}
	
	

}
