package cn.ucas.yueying.activity;






import java.util.ArrayList;
import java.util.HashMap;



import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;
import cn.ucas.yueying.view.ListViewExt;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class nearby_activities extends Activity{

	ListViewExt huodonglistview;
	public MyApplication myapplication;
	RadioButton bt_near_activ;
	RadioButton bt_find_view;
	RadioButton bt_manage;
	RadioButton bt_home;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby_activities);
		myapplication=(MyApplication)getApplication();
		bt_near_activ=(RadioButton) findViewById(R.id.nearby_activities);
		bt_near_activ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent();
				intent.setClass(nearby_activities.this, nearby_activities.class);
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
				intent.setClass(nearby_activities.this, person_homepage.class);
				startActivity(intent);

				finish();
			}
		});
		huodonglistview=(ListViewExt) findViewById(R.id.huodonglistView);
		ArrayList<HashMap<String, Object>> huodongmap=myapplication.getHuodongList();
		HashMap<String, Object> map1=new HashMap<String, Object>();
		map1.put("view_name", "神偷奶爸");
		map1.put("per_img", R.drawable.u20_normal);
		map1.put("per_name", "电影达人小曼");
		map1.put("seximg", R.drawable.bg_1);
		map1.put("age", "23岁");
		map1.put("favorite", "动作喜剧");
		map1.put("ticket", "我请客");
		map1.put("invitation", "邀请1名女生");
		map1.put("time_num", "6月1日 19:00");
		map1.put("place", "五道口影院");
		map1.put("explain", "诚邀卡哇伊单身美眉");
		map1.put("heart", R.drawable.bg_1);
		map1.put("visi_num", "62");
		map1.put("reg_num", "已有23人报名");
		
		HashMap<String, Object> map2=new HashMap<String, Object>();
		map2.put("view_name", "风声");
		map2.put("per_img", R.drawable.u55_normal);
		map2.put("per_name", "布达拉佩斯的雪橇");
		map2.put("seximg", R.drawable.bg_1);
		map2.put("age", "21岁");
		map2.put("favorite", "悬疑爱情");
		map2.put("ticket", "AA");
		map2.put("invitation", "邀请1名男生");
		map2.put("time_num", "6月2日 19:00");
		map2.put("place", "五道口影院");
		map2.put("explain", "诚邀已婚大叔");
		map2.put("heart", R.drawable.bg_1);
		map2.put("visi_num", "620");
		map2.put("reg_num", "已有2300人报名");
		huodongmap.add(map1);
		huodongmap.add(map2);
	
		
		 SimpleAdapter huodongAdapter=new SimpleAdapter(this, huodongmap, R.layout.nearby_activities_item, new String[]{"view_name","per_img","per_name","seximg","age","favorite","ticket","invitation","time_num","place","explain","heart","visi_num","reg_num"}, new int[]{R.id.view_name,R.id.per_img,R.id.per_name,R.id.seximg,R.id.age,R.id.favorite,R.id.ticket,R.id.invitation,R.id.time_num,R.id.place,R.id.explain,R.id.heart,R.id.visi_num,R.id.reg_num});
		 huodonglistview.setAdapter(huodongAdapter);
		 
		 huodonglistview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					HashMap<String, Object> item=(HashMap<String, Object>) parent.getItemAtPosition(position);
					Intent intent=new Intent();
					intent.setClass(nearby_activities.this,person_homepage.class);
					/**
					Bundle bs=new Bundle();
					bs.putString("url", item.get("link").toString());
					bs.putString("title", "新闻快讯");
					bs.putString("_title", item.get("title").toString());
					bs.putString("introduction", item.get("introduction").toString());
					
					intent.putExtras(bs);
					*/
					startActivity(intent);
				}
			});
		 
	}
	

}
