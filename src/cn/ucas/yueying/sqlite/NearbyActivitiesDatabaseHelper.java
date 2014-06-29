package cn.ucas.yueying.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NearbyActivitiesDatabaseHelper extends SQLiteOpenHelper{
	    private final static String DATABASE_NAME = "YueYing.db";  
	    private final static int DATABASE_VERSION = 1;  
	    public final static String TABLE_NAME = "nearbyactivities_table";  
	   
	public NearbyActivitiesDatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
    
	public NearbyActivitiesDatabaseHelper(Context context){
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " + TABLE_NAME + "(id INTEGER primary key autoincrement,view_name  text, "
				+ "per_img text,per_name text,seximg integer,age integer,favorite text,ticket integer,"
				+ "invitation text,time_num text,place text,explain text,visi_num integer,reg_num integer);";  
        db.execSQL(sql);  
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}
	
	

}
