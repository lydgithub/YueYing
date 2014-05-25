package cn.ucas.yueying.view;







import cn.ucas.yueying.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.AbsListView.OnScrollListener;

public class ListViewExt extends ListView implements OnScrollListener {

	public final static String TAG = "ChrisLV";
	public float deltaY;
	public HeaderView mHeaderView = null;
	public RelativeLayout mHeaderContent = null;
	public int iHeaderHeight = 0;
	
	public FooterView mFooterView = null;
	public RelativeLayout mFooterContent = null;
	public int iFooterHeight = 0;
	
	public final static int SCROLL_HEADER = 0;
	public final static int SCROLL_FOOTER = 1;
	public int iScrollWhich = SCROLL_HEADER;
	
	public Scroller mScroller = null;
	public final static float OFFSET_Y = 0.7f;
	public float iLastY = 0;
	public int mTotalNumber = 0;
	
	public ListViewExt(Context context) {
		this(context, null, 0);
	}
	public ListViewExt(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public ListViewExt(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public void initView(Context context){
		/*
		 * mScroller用来回弹下拉刷新/上拉更多
		 * 配合computerScroll来使�?
		 */
		mScroller = new Scroller(context, new DecelerateInterpolator());
		super.setOnScrollListener(this);
		
		initHeaderView(context);
		initFooterView(context);
	}
	
	@Override
	public void setAdapter(ListAdapter adapter) {
		if(getFooterViewsCount() == 0){
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN:
			iLastY = ev.getY();
			break;
			
		case MotionEvent.ACTION_MOVE:
			deltaY = ev.getY() - iLastY;
			iLastY = ev.getY();
			if(canHeaderPull() && getFirstVisiblePosition() == 0 &&
			   (deltaY > 0 || mHeaderView.getHeaderHeight() > 0)){
				updateHeaderState(deltaY * OFFSET_Y);
			}else if(canFooterPull() && getLastVisiblePosition() == mTotalNumber - 1
					&& (deltaY < 0 || mFooterView.getFooterHeight() > 0)){
				updateFooterState(-deltaY * OFFSET_Y);
			}
			break;
			
		case MotionEvent.ACTION_UP:
			if(getFirstVisiblePosition() == 0){
				if(mHeaderView.getHeaderHeight() > iHeaderHeight){
				   mHeaderView.setHeaderState(HeaderView.STATE_REFRESHING);
				   if(mFooterView.getFooterViewOptions() == FooterView.FOOTER_OPTIONS_CLICK){
						mFooterView.hide();
					}
				}
				resetHeader();
			}else if(getLastVisiblePosition() == mTotalNumber - 1){
				if(mFooterView.getFooterHeight() > iFooterHeight){
					mFooterView.setFooterState(FooterView.STATE_LOADING);
				}
				resetFooter();
			}
			break;
		
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if(mScroller.computeScrollOffset()){
			if(iScrollWhich == SCROLL_HEADER){
				mHeaderView.setHeaderHeight(mScroller.getCurrY());
			}else if(iScrollWhich == SCROLL_FOOTER){
				mFooterView.setFooterHeight(mScroller.getCurrY());
			}
		}
		super.computeScroll();
	}
	
	/*
	 * 获取ListView有多少个item:
	 * 1. 在init中，�?��设置super.setOnScrollListener;
	 * 2. 重载以下两个函数;
	 * 3. 在onScroll中取得totalItemCount即可;
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mTotalNumber = totalItemCount;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
	/////////////////////////////////////////////////////////////////////////////
	public boolean canHeaderPull(){
		if(mFooterView.getCurrentState() == FooterView.STATE_NORMAL){
			return true;
		}
		return false;
	}
	
	public boolean canFooterPull(){
		if(mHeaderView.getCurrentState() == HeaderView.STATE_NORMAL){
			return true;
		}
		return false;
	}
	///////////////////////////////////// Header ////////////////////////////////
	public void stopRefresh(){
		if(mHeaderView.getCurrentState() == HeaderView.STATE_REFRESHING){
			mHeaderView.setHeaderState(HeaderView.STATE_NORMAL);
			resetHeader();
			if(mFooterView.getFooterViewOptions() == FooterView.FOOTER_OPTIONS_CLICK){
				mFooterView.show();
			}
		}
	}
	
	public void initHeaderView(Context context){
		mHeaderView = new HeaderView(context);
		mHeaderContent = (RelativeLayout) mHeaderView.findViewById(R.id.header_content);
		addHeaderView(mHeaderView);
		mHeaderView.getViewTreeObserver()
				   .addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
			@Override
			public void onGlobalLayout() {
				iHeaderHeight = mHeaderContent.getHeight();
				Log.d(TAG, "iHeaderHeight = " + iHeaderHeight);
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
	
	public void updateHeaderState(float delta){
		mHeaderView.setHeaderHeight((int)(delta + mHeaderView.getHeaderHeight()));
		if(mHeaderView.getCurrentState() != HeaderView.STATE_REFRESHING){
			if(mHeaderView.getHeaderHeight() > iHeaderHeight){
				mHeaderView.setHeaderState(HeaderView.STATE_WILL_RELEASE);
			}else{
				mHeaderView.setHeaderState(HeaderView.STATE_NORMAL);
			}
		}
		setSelection(0);
	}
	
	public void resetHeader(){
		int height = mHeaderView.getHeaderHeight();
		if(height == 0){
			return;
		}

		int finalHeight = 0;
		if(height > iHeaderHeight){
			/*
			 * 如果超过HeaderView高度，则回滚到HeaderView高度即可
			 */
			finalHeight = iHeaderHeight;
		}else if(mHeaderView.getCurrentState() == HeaderView.STATE_REFRESHING){
			/*
			 * 如果HeaderView未完全显�?
			 * 1. 处于正在刷新中，则不�?
			 * 2. 回滚HeaderView当前可视高度
			 */
			return;
		}
		
		iScrollWhich = SCROLL_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height, 250);
		invalidate();
	}
	/////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// Footer ////////////////////////////////
	public void setFooterMode(int options){
		mFooterView.setFooterViewOptions(options);
	}
	
	public void stopLoad(){
		if(mFooterView.getCurrentState() == FooterView.STATE_LOADING){
			mFooterView.setFooterState(FooterView.STATE_NORMAL);
			resetFooter();
		}
	}
	
	public void initFooterView(Context context){
		mFooterView = new FooterView(context);
		mFooterContent = (RelativeLayout) mFooterView.findViewById(R.id.footer_content);
		mFooterContent.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(mFooterView.getFooterViewOptions() == FooterView.FOOTER_OPTIONS_CLICK
						&& mFooterView.getCurrentState() == FooterView.STATE_NORMAL){
					mFooterView.setFooterState(FooterView.STATE_LOADING);
				}
			}
		});
		mFooterView.getViewTreeObserver()
				   .addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
			@Override
			public void onGlobalLayout() {
				iFooterHeight = mFooterContent.getHeight();
				Log.d(TAG, "iFooterHeight = " + iFooterHeight);
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
	
	public void updateFooterState(float delta){
		if(mFooterView.getFooterViewOptions() == FooterView.FOOTER_OPTIONS_CLICK){
			return;
		}
		
		mFooterView.setFooterHeight((int)(delta + mFooterView.getFooterHeight()));
		if(mFooterView.getCurrentState() != FooterView.STATE_LOADING){
			if(mFooterView.getFooterHeight() > iFooterHeight){
				mFooterView.setFooterState(FooterView.STATE_WILL_RELEASE);
			}else{
				mFooterView.setFooterState(FooterView.STATE_NORMAL);
			}
		}
	}
	
	public void resetFooter(){
		int height = mFooterView.getFooterHeight();
		if(height == 0){
			return;
		}
		
		if(mFooterView.getFooterViewOptions() == FooterView.FOOTER_OPTIONS_CLICK){
			return;
		}
		
		int finalHeight = 0;
		if(height > iFooterHeight){
			finalHeight = iFooterHeight;
		}else if(mFooterView.getCurrentState() == FooterView.STATE_LOADING){
			return;
		}

		iScrollWhich = SCROLL_FOOTER;
		mScroller.startScroll(0, height, 0, finalHeight - height, 250);
		invalidate();
	}
	/////////////////////////////////////////////////////////////////////////////
}