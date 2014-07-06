package cn.ucas.yueying.network;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;



public class get_nearby_activities {
	
	//public int allpage;
	


/**
 * 获取指定类型的新闻列表
 * @param newstype 类型ID
 * @param newsList 保存新闻信息的集合
 * @param page 分页
 * @param firstTimes	是否第一次加载
 * @return 
 */
public HashMap<String, Object> get_nearby_activities_list(int page,double latitude,double longitude){
	
	//请求URL和字符串
	String url = "http://159.226.15.191:8080/yueying/nearby/getActlist/"+latitude+"/"+longitude+"/"+page;
	
	
	//////////////////////////////////////////////////////////////////////////////////
	 HttpUtil httputil=new HttpUtil();
	
		  HttpGet request=httputil.getHttpGet(url);
		  LinkedHashMap<String, Object> hashMapAll = new LinkedHashMap<String, Object>();
		  try{
		  String retStr=httputil.queryStringForGet(request);
		  JSONObject jsonObject = new JSONObject(retStr);
			//获取返回码，0表示成功
//			int retCode = jsonObject.getInt("nearbylist");
//			allpage=jsonObject.getInt("allpage");
//			if (0==retCode){
				JSONArray dataObject = jsonObject.getJSONArray("nearbylist");
				
				for(int i=0;i<dataObject.length();i++){
					JSONObject nearby=dataObject.getJSONObject(i);
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					
					hashMap.put("launchUserId",nearby.getString("launchUserId"));
					hashMap.put("photoUrl",nearby.getString("photoUrl"));
					hashMap.put("name", nearby.getString("name"));
					hashMap.put("gentle", nearby.getString("gentle"));
					hashMap.put("age", nearby.getString("age"));
					hashMap.put("tag", nearby.getString("tag"));
					hashMap.put("id", nearby.getString("id"));
					hashMap.put("partnerGentle", nearby.getString("partnerGentle"));
					hashMap.put("style", nearby.getString("style"));
					hashMap.put("expectation",nearby.getString("expectation"));
					hashMap.put("startTime",nearby.getString("startTime"));
					hashMap.put("cinemaId",nearby.getString("cinemaId"));
					hashMap.put("cinemaName",nearby.getString("cinemaName"));
					hashMap.put("cinemaAddress",nearby.getString("cinemaAddress"));
					hashMap.put("distance",nearby.getString("distance"));
					hashMap.put("filmId",nearby.getString("filmId"));
					hashMap.put("filmName",nearby.getString("filmName"));
					hashMap.put("filmType",nearby.getString("filmType"));
					hashMap.put("country",nearby.getString("country"));
					hashMap.put("score",nearby.getString("score"));
					hashMap.put("playMinutes",nearby.getString("playMinutes"));
					hashMap.put("showTime",nearby.getString("showTime"));
					hashMap.put("filmUrl",nearby.getString("filmUrl"));
					hashMapAll.put("nearby_"+i, hashMap);
				}
				
				
				return hashMapAll;
				
			
			}catch (Exception e){
				e.printStackTrace();
				return hashMapAll;
			}
		  
	  

	  
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
}

}
