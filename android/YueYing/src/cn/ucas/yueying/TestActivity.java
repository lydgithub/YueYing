package cn.ucas.yueying;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends Activity{

	private TextView tv1;
	private Button btnOk;
	private EditText etInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		tv1=(TextView) findViewById(R.id.tvTemp);
		
		Bundle bs=this.getIntent().getExtras();
		String ss=bs.getString("ddd");
		tv1.setText(ss);
		
		btnOk=(Button) findViewById(R.id.btn_OK);
		etInput=(EditText) findViewById(R.id.et_Input);
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String strInput=etInput.getText().toString();
				
				Intent itV=new Intent();
				itV.setClass(TestActivity.this,Test2Activity.class);
				//创建key-value对
				Bundle bd = new Bundle();
				bd.putString("ceshiKey", strInput);
				//添加bundle到Intent中
				itV.putExtras(bd);
				//启动新的Activity
				startActivity(itV);

			}
		});
		
		
	}

}
