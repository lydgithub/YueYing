package cn.ucas.yueying.application;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

public class MyApplication extends Application {

	int nearby_activities_flash = 0;// 记录附近活动是否已经刷新

	int nearby_activities_allpage = 3;// 记录服务器中总共有多少条数据

	int RadioButton_stat = 1;// 用来记录RadioButton的状态

	String city;// 通过坐标获取城市名

	double latitude;//经度
	double longitude; //纬度
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public ArrayList<HashMap<String, Object>> huodongList = new ArrayList<HashMap<String, Object>>();

	public ArrayList<HashMap<String, Object>> manageList = new ArrayList<HashMap<String, Object>>();

	public ArrayList<HashMap<String, Object>> locationList = new ArrayList<HashMap<String, Object>>();

	public ArrayList<HashMap<String, Object>> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<HashMap<String, Object>> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<HashMap<String, Object>> getManageList() {
		return manageList;
	}

	public void setManageList(ArrayList<HashMap<String, Object>> manageList) {
		this.manageList = manageList;
	}

	public int getRadioButton_stat() {
		return RadioButton_stat;
	}

	public void setRadioButton_stat(int radioButton_stat) {
		RadioButton_stat = radioButton_stat;
	}

	public ArrayList<HashMap<String, Object>> getHuodongList() {
		return huodongList;
	}

	public void setHuodongList(ArrayList<HashMap<String, Object>> huodongList) {
		this.huodongList = huodongList;
	}

	public int getNearby_activities_flash() {
		return nearby_activities_flash;
	}

	public void setNearby_activities_flash(int nearby_activities_flash) {
		this.nearby_activities_flash = nearby_activities_flash;
	}

	public int getNearby_activities_allpage() {
		return nearby_activities_allpage;
	}

	public void setNearby_activities_allpage(int nearby_activities_allpage) {
		this.nearby_activities_allpage = nearby_activities_allpage;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
