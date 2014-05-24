package cn.ucas.yueying;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class CreateActivity extends Activity implements OnGestureListener {

	private ViewFlipper flipper;
	private TextView tvLocation;
	private Button btnCreate;
	private EditText edt1;

	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);

		// 发起活动，跳转

		btnCreate = (Button) findViewById(R.id.btn_createA_create);
		edt1 = (EditText) findViewById(R.id.et_Input);
		btnCreate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String strE = edt1.getText().toString();

				Intent intentV = new Intent();
				intentV.setClass(CreateActivity.this, TestActivity.class);
				Bundle bs = new Bundle();

				bs.putString("ddd", strE);
				intentV.putExtras(bs);
				startActivity(intentV);
			}
		});

		// videoshow
		flipper = (ViewFlipper) findViewById(R.id.flipper);

		ImageView iv1 = (ImageView) getImageView(R.drawable.image1);
		iv1.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageView iv2 = (ImageView) getImageView(R.drawable.image2);
		iv2.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageView iv3 = (ImageView) getImageView(R.drawable.image3);
		iv3.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageView iv4 = (ImageView) getImageView(R.drawable.image4);
		iv4.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageView iv5 = (ImageView) getImageView(R.drawable.image5);
		iv5.setScaleType(ImageView.ScaleType.FIT_XY);
		flipper.addView(iv1);
		flipper.addView(iv2);
		flipper.addView(iv3);
		flipper.addView(iv4);
		flipper.addView(iv5);

		flipper.setAutoStart(true);
		flipper.setFlipInterval(5000);// 5s
		flipper.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		flipper.startFlipping();

	}

	// 发起活动界面显示电影图片
	private View getImageView(int id) {
		ImageView imgView = new ImageView(this);
		imgView.setImageResource(id);
		return imgView;
	}

	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) { return
	 * detector.onTouchEvent(event); }
	 * 
	 * public boolean onDown(MotionEvent e) { return false; }
	 * 
	 * public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	 * float velocityY) { if (e1.getX() - e2.getX() > 120) {// 向右滑动
	 * flipper.setInAnimation(AnimationUtils.loadAnimation(this,
	 * R.anim.push_left_in));
	 * flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
	 * R.anim.push_left_out)); flipper.showNext(); } else if (e2.getX() -
	 * e1.getX() > 120) {// 向左滑动
	 * flipper.setInAnimation(AnimationUtils.loadAnimation(this,
	 * R.anim.push_right_in));
	 * flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
	 * R.anim.push_right_out)); flipper.showPrevious(); } return false; }
	 * 
	 * public void onLongPress(MotionEvent e) { }
	 * 
	 * public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	 * float distanceY) { return false; }
	 * 
	 * public void onShowPress(MotionEvent e) { }
	 * 
	 * public boolean onSingleTapUp(MotionEvent e) { return false; }
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */

}
