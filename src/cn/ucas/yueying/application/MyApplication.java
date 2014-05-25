package cn.ucas.yueying.application;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

public class MyApplication extends Application{
	
	int RadioButton_stat=1;//用来记录RadioButton的状态
	
	public ArrayList<HashMap<String, Object>> huodongList=new ArrayList<HashMap<String,Object>>();
	
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
	
	
	
	
}
