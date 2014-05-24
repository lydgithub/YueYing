package cn.ucas.yueying;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Test2Activity extends Activity{

	private TextView tvView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2);
		
		tvView=(TextView) findViewById(R.id.tvShow);
		
		
		Bundle bd=this.getIntent().getExtras();
		String ss=bd.getString("ceshiKey");
		tvView.setText(ss);
		
	}

}
