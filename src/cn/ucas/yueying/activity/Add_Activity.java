package cn.ucas.yueying.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;
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
	

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_activity);// 发起活动
		
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
		
		
		 Integer[] images = { R.drawable.image1, 
                 R.drawable.image2, R.drawable.image3, R.drawable.image4,
                 R.drawable.image5};
         
         ImageAdapter adapter = new ImageAdapter(this, images);
         adapter.createReflectedImages();

         GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.Gallery);
         galleryFlow.setAdapter(adapter);
         
         galleryFlow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 switch(position){
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
        	 
        	 
		});;
	}

	
	// 到此结束
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			//返回主页面
			Intent intent = new Intent();
				intent.setClass(Add_Activity.this, nearby_activities.class);
				startActivity(intent);
				finish();

		}

		return false;

	}

}
