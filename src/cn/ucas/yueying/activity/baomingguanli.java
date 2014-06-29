package cn.ucas.yueying.activity;

import cn.ucas.yueying.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class baomingguanli extends Activity{
  ImageButton yueta;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.baomingguanli);// 发起活动
		yueta=(ImageButton)findViewById(R.id.bt_yt);
		
				
		
		yueta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				yueta.setBackgroundResource(R.drawable.yueta_ed);

			}
		});
	}
}
