package cn.ucas.yueying.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import cn.ucas.yueying.R;

public class other_activity_details extends Activity {

	ImageView iv_collection;
	ImageView other;
    Button back;
    ImageView iv_Apply;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.other_activity_details);
		back=(Button)findViewById(R.id.back);
		
		iv_Apply=(ImageView) findViewById(R.id.iv_Baoming);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		

		
		iv_Apply.setOnClickListener(new OnClickListener() {
				
			
			@Override
			public void onClick(View arg0) {
				
				iv_Apply.setBackgroundResource(R.drawable.yibaoming);
				
			}
			
			 

			

		});	
		
		
		iv_collection = (ImageView) findViewById(R.id.imageView5);

		iv_collection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				iv_collection.setImageResource(R.drawable.heart_red);
			}
		}

		);
		other=(ImageView)findViewById(R.id.other);
		other.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(other_activity_details.this, other_homepage.class);
				startActivity(intent);
				
			}
		});

	}
	


}
