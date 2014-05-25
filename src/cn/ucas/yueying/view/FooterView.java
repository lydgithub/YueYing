package cn.ucas.yueying.view;







import cn.ucas.yueying.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FooterView extends LinearLayout {

	protected final static int FOOTER_OPTIONS_PULL = 0;
	protected final static int FOOTER_OPTIONS_CLICK = 1;
	protected static int sFooterOps = FOOTER_OPTIONS_PULL;
	
	protected final static int STATE_NORMAL = 0;
	protected final static int STATE_WILL_RELEASE = 1;
	public final static int STATE_LOADING = 2;
	public int mState = STATE_NORMAL;
	
	protected View mFooter = null;
	protected ImageView mArrow = null;
	protected ProgressBar mProgressBar = null;
	protected TextView mLoaderTips = null;
	
	protected RotateAnimation mRotateUp = null;
	protected RotateAnimation mRotateDown = null;
	protected final static int ROTATE_DURATION = 250;
	
	protected FooterView(Context context) {
		this(context, null);
	}
	
	protected FooterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFooterView(context);
	}
	
	protected void initFooterView(Context context){
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, 0);
		mFooter = LayoutInflater.from(context).inflate(R.layout.loader_footer, null);
		addView(mFooter, lp);
		
		mArrow = (ImageView) mFooter.findViewById(R.id.ivLoaderArrow);
		mProgressBar = (ProgressBar) mFooter.findViewById(R.id.pbLoaderWaiting);
		mLoaderTips = (TextView) mFooter.findViewById(R.id.loader_tips);
		
		mRotateDown = new RotateAnimation(0.0f, 180.0f,  
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateDown.setDuration(ROTATE_DURATION);
		mRotateDown.setFillAfter(true);
		
		mRotateUp = new RotateAnimation(180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateUp.setDuration(ROTATE_DURATION);
		mRotateUp.setFillAfter(true);
		
		setFooterViewOptions(FOOTER_OPTIONS_CLICK);
	}
	
	protected void setFooterViewOptions(int options){
		sFooterOps = options;
		
		switch(sFooterOps){
		case FOOTER_OPTIONS_PULL:
			hide();
			break;
			
		case FOOTER_OPTIONS_CLICK:
			show();
			break;
			
		default:
			break;
		}
	}
	
	protected int getFooterViewOptions(){
		return sFooterOps;
	}
	
	protected void setFooterState(int state){
		if(mState == state){
			return;
		}
		
		mArrow.clearAnimation();
		if(state == STATE_LOADING){
			mProgressBar.setVisibility(View.VISIBLE);
			mArrow.setVisibility(View.GONE);
		}else{
			mProgressBar.setVisibility(View.GONE);
			mArrow.setVisibility(View.VISIBLE);
		}
		
		switch(state){
		case STATE_NORMAL:
			mArrow.startAnimation(mRotateUp);
			mLoaderTips.setText(R.string.pull_up_for_more);
			break;
			
		case STATE_WILL_RELEASE:
			mArrow.startAnimation(mRotateDown);
			mLoaderTips.setText(R.string.release_for_more);
			break;
			
		case STATE_LOADING:
			mLoaderTips.setText(R.string.loading);
			break;
		
		default:
			break;
		}
		mState = state;
	}
	
	protected int getCurrentState(){
		return mState;
	}
	
	protected void setFooterHeight(int height){
		if(height <= 0){
			height = 0;
		}
		
		LayoutParams lp = (LayoutParams) mFooter.getLayoutParams();
		lp.height = height;
		mFooter.setLayoutParams(lp);
	}
	
	protected int getFooterHeight(){
		return mFooter.getHeight();
	}
	
	protected void hide(){
		mArrow.clearAnimation();
		mArrow.setVisibility(View.VISIBLE);
		mLoaderTips.setText(R.string.pull_up_for_more);
		setFooterHeight(0);
	}
	
	protected void show(){
		mArrow.clearAnimation();
		mArrow.setVisibility(View.GONE);
		mLoaderTips.setText(R.string.click_for_more);
		
		LayoutParams lp = (LayoutParams) mFooter.getLayoutParams();
		lp.height = LayoutParams.WRAP_CONTENT;
		mFooter.setLayoutParams(lp);
	}
}
