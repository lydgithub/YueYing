package cn.ucas.yueying.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;
import cn.ucas.yueying.view.ListViewExt;

public class location extends Activity {
	ListViewExt locationlistview;
	MyApplication myapplication;
	Button back;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.location);

		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(location.this, Add_Activity.class);
				startActivity(intent);
				finish();
			}
		});
		myapplication = (MyApplication) getApplication();
		locationlistview = (ListViewExt) findViewById(R.id.locationlistView);
		ArrayList<HashMap<String, Object>> locationmap = myapplication
				.getLocationList();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("cinema_name", "五道口电影院");
		map1.put("cinema_value", "60元");
		map1.put("cinema_place", "北京海淀区成府路23号五道口电影院评分");

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("cinema_name", "嘉华国际影城");
		map2.put("cinema_value", "30元");
		map2.put("cinema_place", "北京市海淀区学清路甲8号圣熙8号购物中心1层评分购票");

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("cinema_name", "吉林剧场");
		map3.put("cinema_value", "15元");
		map3.put("cinema_place", "昌邑区重庆路380号站前（江城剧场）");

		locationmap.add(map1);
		locationmap.add(map2);
		locationmap.add(map3);

		SimpleAdapter locationAdapter = new SimpleAdapter(this, locationmap,
				R.layout.location_item, new String[] { "cinema_name",
						"cinema_value", "cinema_place" },
				new int[] { R.id.cinema_name, R.id.cinema_value,
						R.id.cinema_place });
		locationlistview.setAdapter(locationAdapter);
		locationlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(location.this, changci.class);
				startActivity(intent);
				finish();

			}
		});

	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 返回主页面
			Intent intent = new Intent();
			intent.setClass(location.this, Add_Activity.class);
			startActivity(intent);
			finish();

		}

		return false;

	}
}
