YueYing
=======

3+2
 flipper = (ViewFlipper) findViewById(R.id.flipper);
  	  
  	  ImageView iv1=(ImageView) getImageView(R.drawable.image1);
  	  iv1.setScaleType(ImageView.ScaleType.FIT_XY);
  	ImageView iv2=(ImageView) getImageView(R.drawable.image2);
	  iv2.setScaleType(ImageView.ScaleType.FIT_XY);
	  ImageView iv3=(ImageView) getImageView(R.drawable.image3);
  	  iv3.setScaleType(ImageView.ScaleType.FIT_XY);
  	ImageView iv4=(ImageView) getImageView(R.drawable.image4);
	  iv4.setScaleType(ImageView.ScaleType.FIT_XY);
	  ImageView iv5=(ImageView) getImageView(R.drawable.image5);
  	  iv5.setScaleType(ImageView.ScaleType.FIT_XY);
  	  flipper.addView(iv1);
  	  flipper.addView(iv2);
  	  flipper.addView(iv3);
  	  flipper.addView(iv4);
  	  flipper.addView(iv5);
  	  
  	  flipper.setAutoStart(true);
  	  flipper.setFlipInterval(3000);
  	  flipper.setInAnimation(AnimationUtils.loadAnimation(this,  
            android.R.anim.fade_in));  
      flipper.setOutAnimation(AnimationUtils.loadAnimation(this,  
            android.R.anim.fade_out));  
  	  flipper.startFlipping();
  	  
private View getImageView(int id){
	     ImageView imgView = new ImageView(this);
	     imgView.setImageResource(id);
	     return imgView;
	    }
	 
	
	
	
	
	//主界面图片滑动功能
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	     return detector.onTouchEvent(event);
	    }
	   
	 @Override
	 public boolean onDown(MotionEvent e) {
	  return false;
	 }

	 @Override
	 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	  if(e1.getX() - e2.getX() > 120){//向右滑动
	   flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
	   flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
	   flipper.showNext();
	  }else if(e2.getX() - e1.getX() > 120){//向左滑动
	   flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
	   flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
	   flipper.showPrevious();
	  }
	  return false;
	 }

	 @Override
	 public void onLongPress(MotionEvent e) {}

	 @Override
	 public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
	  return false;
	 }

	 @Override
	 public void onShowPress(MotionEvent e) {}

	 @Override
	 public boolean onSingleTapUp(MotionEvent e) {
	  return false;
	 }
	
	 //到此结束
