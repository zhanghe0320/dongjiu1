//package com.dongjiu.utils.DownloadService;
//
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.view.animation.LinearInterpolator;
//
//import com.dongjiu.R;
//
//public class LoadingLine extends View {
//
//    private Paint paint;
//    private Paint backPaint;
//    private Paint textPaint;
//    private boolean init = false;
//    private ValueAnimator valueAnimator;
//    private float param = 0;
//    private int length = 0;
//
//    public LoadingLine(Context context) {
//        super(context);
//        init();
//    }
//
//
//    public LoadingLine(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    private void init() {
//        paint = new Paint();
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(25);
//        backPaint = new Paint();
//        backPaint.setColor(Color.RED);
//        backPaint.setStrokeWidth(25);
//        textPaint = new Paint();
//        textPaint.setColor(Color.WHITE);
//        textPaint.setTextSize(10);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (!init) {
////            start();
//            init = true;
//        }
//        int width = getWidth();
//        int height = getHeight();
//        canvas.drawLine(0, height / 2, width, height / 2, backPaint);
//        canvas.drawLine(0, height / 2, length * width / 100, height / 2, paint);
//        if (length<100){
//            canvas.drawText(length + "", length * width / 100 - 22, height / 2 + 6, textPaint);
//        }else {
//            canvas.drawText(length + "", length * width / 100 - 42, height / 2 + 6, textPaint);
//        }
////        if (valueAnimator.isRunning()) {
////            param = (float) valueAnimator.getAnimatedValue();
////            setLength((int) param);
////        }
//    }
//    /**
//     * 也可在外部手动控制进度条,自行关闭valueAnimator
//     *
//     * @param param
//     */
//    public void setLength(int param) {
//        this.length = param;
//        invalidate();
//    }
//    private void start() {
//        if (valueAnimator == null) {
//            valueAnimator = ValueAnimator.ofFloat(0, 100);
//            valueAnimator.setInterpolator(new LinearInterpolator());
//            valueAnimator.setDuration(6000);
//            valueAnimator.start();
//        } else {
//            valueAnimator.start();
//        }
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                start();
//                invalidate();
//            }
//        }, valueAnimator.getDuration());
//        invalidate();
//    }
//
//
////  xml  控件
////<com.administrator.tests.LoadingLine
////    android:id="@+id/myloadline"
////    android:layout_width="match_parent"
////    android:layout_height="5dp" />
//
//
//
//
//    //activity 内部进行初始化
////    private boolean running;
////    private LoadingLine loadingLine;
////    int i = 0;
////
////    static final int REFRESH_COMPLETE = 0X1112;
////    private Handler mHandler = new Handler() {
////        public void handleMessage(android.os.Message msg) {
////            switch (msg.what) {
////                case REFRESH_COMPLETE:
////                    if (i==100){
////                        running=false;
////                    }
////                    Log.i("lgq","...===="+i);
////                    loadingLine.setLength(i++);//主要加载进度方法
////                //todo...
////                    break;
////            }
////        }
////    };
//
//
//
//    // 加载进度
////    loadingLine =(LoadingLine)findViewById(R.id.myloadline);
////      new Thread() {
////        @Override
////        public void run() {
////            super.run();
////            running = true;
////            while (running) {
////                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 0);
////                try {
////                    sleep(100);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }.start();
//
//
//}
