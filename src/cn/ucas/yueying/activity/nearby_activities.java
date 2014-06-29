package cn.ucas.yueying.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.ucas.yueying.R;
import cn.ucas.yueying.application.MyApplication;
import cn.ucas.yueying.network.ConnectionDetector;
import cn.ucas.yueying.view.FooterView;
import cn.ucas.yueying.view.HeaderView;
import cn.ucas.yueying.view.ListViewExt;

public class nearby_activities extends Activity {

	ListViewExt huodonglistview;
	public MyApplication myapplication;
	RadioButton bt_near_activ;
	RadioButton bt_find_view;
	RadioButton bt_manage;
	RadioButton bt_home;
	public Handler handler;
	boolean stopThread = false; // 用于在退出界面后结束监听线程
	int page = 1; // 初始化加载第几页
	int position = 0; // 记录滚动位置，用于恢复listview位置
	Boolean isInternetPresent;
	ConnectionDetector cd;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.nearby_activities);
		cd = new ConnectionDetector(getApplicationContext()); // 检测网络连接
		myapplication = (MyApplication) getApplication();
		myapplication.setRadioButton_stat(1);

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
				intent.setClass(nearby_activities.this, nearby_activities.class);
				startActivity(intent);

				finish();

			}
		});

		bt_manage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(nearby_activities.this, manage.class);
				startActivity(intent);
				finish();

			}
		});

		bt_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(nearby_activities.this, person_homepage.class);
				startActivity(intent);
				finish();

			}
		});

		bt_find_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(nearby_activities.this, Add_Activity.class);
				startActivity(intent);
				finish();

			}
		});

		// 开启消息监听
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) { // 如果是刷新或者第一次载入

					huodonglistview.stopRefresh(); // 重置刷新图标状态

					initialize(); // 重新载入界面
				} else if (msg.what == 2) { // 如果是加载更多
					huodonglistview.stopLoad(); // 重置加载图标状态

					initialize(); // 重新载入界面

					// 自动滚动到记录的位置
					huodonglistview.setSelection(position);

				} else if (msg.what == 3) { // 请求加载的页数超过总页数，停止加载，提示没有更多消息了

					huodonglistview.stopLoad(); // 重置加载图标状态

					// 提示用户已全部加载
					Context context = getApplicationContext();
					CharSequence text = "已全部加载！";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

					stopThread = true;

					Thread tt = new Thread(k);
					try {
						tt.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stopThread = false;
					tt.start();

				} else if (msg.what == 4) { // 网络不通

					huodonglistview.stopLoad(); // 重置加载图标状态
					huodonglistview.stopRefresh();

					// 提示用户网络不通
					Context context = getApplicationContext();
					CharSequence text = "网络不通，请检查网络！";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

					stopThread = true;

					Thread tt = new Thread(k);
					try {
						tt.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stopThread = false;
					tt.start();

				}
			}
		};

		initialize(); // 第一次载入时调用

	}

	// 界面初始化
	public void initialize() {

		myapplication = (MyApplication) getApplication();
		huodonglistview = (ListViewExt) findViewById(R.id.huodonglistView);

		if (myapplication.getNearby_activities_flash() == 1) { // 如果已经加载过且全局变量中有数据，则直接显示数据

			SimpleAdapter huodongAdapter = new SimpleAdapter(this,
					myapplication.getHuodongList(),
					R.layout.nearby_activities_item, new String[] {
							"view_name", "per_img", "per_name", "seximg",
							"age", "favorite", "ticket", "invitation",
							"time_num", "place", "explain", "heart",
							"visi_num", "reg_num" }, new int[] {
							R.id.view_name, R.id.per_img, R.id.per_name,
							R.id.seximg, R.id.age, R.id.favorite, R.id.ticket,
							R.id.invitation, R.id.time_num, R.id.place,
							R.id.explain, R.id.heart, R.id.visi_num,
							R.id.reg_num });
			huodonglistview.setAdapter(huodongAdapter);

			huodonglistview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					HashMap<String, Object> item = (HashMap<String, Object>) parent
							.getItemAtPosition(position);
					Intent intent = new Intent();
					intent.setClass(nearby_activities.this,
							other_activity_details.class);
					/**
					 * Bundle bs=new Bundle(); bs.putString("url",
					 * item.get("link").toString()); bs.putString("title",
					 * "新闻快讯"); bs.putString("_title",
					 * item.get("title").toString());
					 * bs.putString("introduction",
					 * item.get("introduction").toString());
					 * 
					 * intent.putExtras(bs);
					 */
					startActivity(intent);
				}
			});

			// 记录listview滚动位置
			huodonglistview.setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
					// TODO Auto-generated method stub
					// 不滚动时保存当前滚动到的位置
					if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {

						position = huodonglistview.getFirstVisiblePosition();

					}
				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub

				}
			});

			stopThread = true;

			Thread tt = new Thread(k);
			try {
				tt.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stopThread = false;
			tt.start();

		} else if (myapplication.getNearby_activities_flash() == 0) { // 如果是第一次加载，则开启更新线程进行加载数据
			// 开启更新新闻线程
			new Thread(r).start();

		}

	}

	// 监听刷新或者加载指令的线程

	public Runnable k = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!stopThread) {

				// 监听图标状态，如果是刷新或者加载，则开启加载线程
				if (huodonglistview.mHeaderView.mState == HeaderView.STATE_REFRESHING
						|| huodonglistview.mFooterView.mState == FooterView.STATE_LOADING) {

					Thread t = new Thread(r);
					try {
						t.sleep(1000); // 如果不延迟，则图标可能回不到正常位置
						t.start();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				}

			}
		}
	};

	// 加载数据线程
	public Runnable r = new Runnable() {
		public void run() {
			// TODO Auto-generated method stub

			if (huodonglistview.mHeaderView.mState == HeaderView.STATE_REFRESHING) { // 如果是刷新的话，调用
				myapplication.setNearby_activities_flash(0);

				isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
				// 先检查网络是否联通
				if (isInternetPresent) {

					page = 1;
					load(1);

				} else {

					// 如果网络不通，则发送信息
					Message message = new Message();
					message.what = 4;
					handler.sendMessage(message);

				}

			} else if (huodonglistview.mFooterView.mState == FooterView.STATE_LOADING) { // 如果是加载更多的话，调用
				myapplication.setNearby_activities_flash(0);

				isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
				// 先检查网络是否联通
				if (isInternetPresent) {

					page++;
					load(page);
				} else {
					// 如果网络不通，则发送信息
					Message message = new Message();
					message.what = 4;
					handler.sendMessage(message);

				}

			} else {

				isInternetPresent = cd.isConnectingToInternet(); // true or
																	// false
				// 先检查网络是否联通
				if (isInternetPresent) {

					load(1);
				} else {
					// 如果网络不通，则发送信息
					Message message = new Message();
					message.what = 4;
					handler.sendMessage(message);

				}

			}

		}

	};

	// 加载方法
	public void load(int page) {

		myapplication = (MyApplication) getApplication();
		int allpage = myapplication.getNearby_activities_allpage();

		if (page == 1) { // 第一次加载或者刷新调用此方法
			myapplication.huodongList.clear();
			ArrayList<HashMap<String, Object>> huodongmap = myapplication
					.getHuodongList();
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("view_name", "沉睡魔咒");
			map1.put("per_img", R.drawable.u20_normal);
			map1.put("per_name", "电影达人小曼");
			map1.put("seximg", R.drawable.boy);
			map1.put("age", "23岁");
			map1.put("favorite", "动作 冒险");
			map1.put("ticket", "我请客");
			map1.put("invitation", "邀请1名女生");
			map1.put("time_num", "6月22日 19:15");
			map1.put("place", "五道口影院");
			map1.put("explain", "诚邀卡哇伊单身美眉");
			map1.put("heart", R.drawable.heart_red);
			map1.put("visi_num", "62");
			map1.put("reg_num", "已有23人报名");

			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("view_name", "哥斯拉");
			map2.put("per_img", R.drawable.bdl);
			map2.put("per_name", "布达拉佩斯的雪橇");
			map2.put("seximg", R.drawable.girl);
			map2.put("age", "21岁");
			map2.put("favorite", "爱情 悬疑");
			map2.put("ticket", "AA");
			map2.put("invitation", "邀请1名男生");
			map2.put("time_num", "6月22日 18:25");
			map2.put("place", "五道口影院");
			map2.put("explain", "诚邀大叔");
			map2.put("heart", R.drawable.heart_red);
			map2.put("visi_num", "620");
			map2.put("reg_num", "已有2300人报名");
			huodongmap.add(map1);
			huodongmap.add(map2);

			// 将数据存入到全局变量中
			myapplication.setHuodongList(huodongmap);
			myapplication.setNearby_activities_flash(1);
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
			System.out.println("刷新完成");

		} else { // 加载更多时调用此方法

			if (page > allpage) { // 判断要加载的页码，如果大于总页码，则停止加载

				Message message = new Message();
				message.what = 3;
				handler.sendMessage(message);

			} else { // 如果不大于总页码，则开始加载
				ArrayList<HashMap<String, Object>> huodongmap = myapplication
						.getHuodongList();
				HashMap<String, Object> map1 = new HashMap<String, Object>();

				map1.put("view_name", "沉睡魔咒");
				map1.put("per_img", R.drawable.u20_normal);
				map1.put("per_name", "电影达人小曼");
				map1.put("seximg", R.drawable.boy);
				map1.put("age", "23岁");
				map1.put("favorite", "动作 冒险");
				map1.put("ticket", "我请客");
				map1.put("invitation", "邀请1名女生");
				map1.put("time_num", "6月22日 19:15");
				map1.put("place", "五道口影院");
				map1.put("explain", "诚邀卡哇伊单身美眉");
				map1.put("heart", R.drawable.heart_red);
				map1.put("visi_num", "62");
				map1.put("reg_num", "已有23人报名");

				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("view_name", "哥斯拉");
				map2.put("per_img", R.drawable.bdl);
				map2.put("per_name", "布达拉佩斯的雪橇");
				map2.put("seximg", R.drawable.girl);
				map2.put("age", "21岁");
				map2.put("favorite", "爱情 悬疑");
				map2.put("ticket", "AA");
				map2.put("invitation", "邀请1名男生");
				map2.put("time_num", "6月22日 18:25");
				map2.put("place", "五道口影院");
				map2.put("explain", "诚邀大叔");
				map2.put("heart", R.drawable.heart_red);
				map2.put("visi_num", "620");
				map2.put("reg_num", "已有2300人报名");

				huodongmap.add(map1);
				huodongmap.add(map2);

				// 将数据存入到全局变量中
				myapplication.setHuodongList(huodongmap);
				myapplication.setNearby_activities_flash(1);
				Message message = new Message();
				message.what = 2;
				handler.sendMessage(message);
				System.out.println("加载更多完成");

			}

		}

	}

	// 在主界面按返回键后弹出退出确认对话框
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 创建退出对话框

			AlertDialog isExit = new AlertDialog.Builder(this).create();

			// 设置对话框标题

			isExit.setTitle("提示");

			// 对话框显示的内容

			isExit.setMessage("确定要退出么？");

			// 给提示框里的按钮添加监听

			isExit.setButton("确定", hello);

			isExit.setButton2("取消", hello);

			// 显示对话框

			isExit.show();

		}

		return false;

	}

	// 给对话框里的按钮注册事件

	DialogInterface.OnClickListener hello = new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {

			switch (which) {

			case AlertDialog.BUTTON_POSITIVE:// 点击 确认，退出程序

				finish();

				break;

			case AlertDialog.BUTTON_NEGATIVE:// 点击 取消 取消对话框

				break;

			default:

				break;

			}

		}

	};

}
