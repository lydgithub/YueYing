package cn.ucas.yueying.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class TestJson {

	private void HttpPostData() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String uri = "http://localhost:8080/yueying/activity/location";
			HttpPost httppost = new HttpPost(uri);
			// 添加http头信息
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("User-Agent", "imgfornote");
			// http post的json数据格式： {"name": "your name","parentId":
			// "id_of_parent"}
			JSONObject obj = new JSONObject();
			obj.put("xpoint", 9.3);
			obj.put("ypoint", 3.2);
			httppost.setEntity(new StringEntity(obj.toString()));
			HttpResponse response;
			response = httpclient.execute(httppost);
			// 检验状态码，如果成功接收数据
			//JSONObject jsonOb = new JSONObject(response.toString());
			//System.out.println(jsonOb);

			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				System.out.println("sucess");
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestJson tj = new TestJson();
		tj.HttpPostData();
	}

}
