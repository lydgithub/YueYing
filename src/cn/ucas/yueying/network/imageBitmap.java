package cn.ucas.yueying.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class imageBitmap {
	
	public Bitmap getBitmap(String path){
		
		try {
            URL url = new URL(path);
            URLConnection con = (URLConnection) url.openConnection();
            InputStream stream = con.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            return bitmap;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return null;
	}

}
