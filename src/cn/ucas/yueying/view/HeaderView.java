package cn.ucas.yueying.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.ucas.yueying.R;

public class HeaderView extends LinearLayout {

	protected final static int STATE_NORMAL = 0;
	protected final static int STATE_WILL_RELEASE = 1;
	public final static int STATE_REFRESHING = 2;
	public int mState = STATE_NORMAL;

	protected View mHeader = null;
	protected ImageView mArrow = null;
	protected ProgressBar mProgressBar = null;
	protected TextView mRefreshTips = null;
	// protected TextView mRefreshLastTime = null;
	protected RotateAnimation mRotateUp = null;
	protected RotateAnimation mRotateDown = null;
	protected final static int ROTATE_DURATION = 250;

	protected HeaderView(Context context) {
		this(context, null);
	}

	protected HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView(context);
	}

	protected void initHeaderView(Context context) {
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, 0);
		mHeader = LayoutInflater.from(context).inflate(R.layout.refresh_header,
				null);
		addView(mHeader, lp);
		setGravity(Gravity.BOTTOM);

		mArrow = (ImageView) mHeader.findViewById(R.id.ivArrow);
		mProgressBar = (ProgressBar) mHeader.findViewById(R.id.pbWaiting);
		mRefreshTips = (TextView) mHeader.findViewById(R.id.refresh_tips);
		// mRefreshLastTime = (TextView)
		// mHeader.findViewById(R.id.refresh_last_time);

		mRotateUp = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUp.setDuration(ROTATE_DURATION);
		mRotateUp.setFillAfter(true);

		mRotateDown = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDown.setDuration(ROTATE_DURATION);
		mRotateDown.setFillAfter(true);
	}

	protected void setHeaderState(int state) {
		if (mState == state) {
			return;
		}

		mArrow.clearAnimation();
		if (state == STATE_REFRESHING) {
			mProgressBar.setVisibility(View.VISIBLE);
			mArrow.setVisibility(View.GONE);
		} else {
			mProgressBar.setVisibility(View.GONE);
			mArrow.setVisibility(View.VISIBLE);
		}

		switch (state) {
		case STATE_NORMAL:
			mArrow.startAnimation(mRotateDown);
			mRefreshTips.setText(R.string.pull_down_for_refresh);
			break;

		case STATE_WILL_RELEASE:
			mArrow.startAnimation(mRotateUp);
			mRefreshTips.setText(R.string.release_for_refresh);
			break;

		case STATE_REFRESHING:
			mRefreshTips.setText(R.string.refreshing);
			break;

		default:
			break;
		}

		mState = state;
	}

	protected int getCurrentState() {
		return mState;
	}

	protected void setHeaderHeight(int height) {
		if (height <= 0) {
			height = 0;
		}
		LayoutParams lp = (LayoutParams) mHeader.getLayoutParams();
		lp.height = height;
		mHeader.setLayoutParams(lp);
	}

	protected int getHeaderHeight() {
		return mHeader.getHeight();
	}
}
