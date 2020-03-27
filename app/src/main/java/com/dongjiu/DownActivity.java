package com.dongjiu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dongjiu.utils.CustomDatePicker.CustomDatePicker;
import com.dongjiu.utils.CustomDatePicker.DateFormatUtils;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class DownActivity extends BaseActivity  implements View.OnClickListener , CustomAdapt {
    @BindView(R.id.tv_selected_date)
    TextView mTvSelectedDate;
   // @BindView(R.id.tv_selected_date);
    TextView mTvSelectedTime;
    private CustomDatePicker mDatePicker, mTimerPicker;
    private static final String TAG = "DownActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);
        Log.i(TAG, "创建界面 ");
        // 在onCreate()中开启线程

        BaseActivity.showToastView(String.valueOf(Math.random()*10000));
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        findViewById(R.id.ll_date).setOnClickListener(this);
        //mTvSelectedDate = findViewById(R.id.tv_selected_date);
        initDatePicker();

      /*  findViewById(R.id.ll_time).setOnClickListener(this);
        mTvSelectedTime = findViewById(R.id.tv_selected_time);
        initTimerPicker();*/


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_date:
                // 日期格式为yyyy-MM-dd
                mDatePicker.show(mTvSelectedDate.getText().toString());
                break;

          /*  case R.id.ll_time:
                // 日期格式为yyyy-MM-dd HH:mm
                mTimerPicker.show(mTvSelectedTime.getText().toString());
                break;*/
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatePicker.onDestroy();
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2020-01-01", false);
        long endTimestamp = System.currentTimeMillis();

        mTvSelectedDate.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedDate.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    private void initTimerPicker() {
        String beginTime = "2020-01-01 00:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        mTvSelectedTime.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }


    //到主界面  返回按钮
    public void  down_order(View view) {


       BaseActivity.showToastView( mTvSelectedDate.getText().toString());
      // onResume();
    }
    //到主界面  返回按钮
    public void back_to_main(View view) {

        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();

    }

    //到主界面  返回按钮
    public void back_to_main_1(View view) {

        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();

    }

    //到主界面
    public void main2main(View view) {

        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();
    }

    //到达下载
    public void main2down(View view) {
        /*openActivity(DownActivity.class);
        AppManager.getInstance().killAllActivityNotDown();*/
    }


    //返回上一个页面
    public void main2query(View view) {
        openActivity(QueryActivity.class);
        AppManager.getInstance().killAllActivityNotQuery();
    }


    //返回上一个页面
    public void main2upload(View view) {

        openActivity(UploadActivity.class);
        AppManager.getInstance().killAllActivityNotUpload();
    }


    //返回上一个页面
    public void main2all(View view) {
        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();

    }

    //返回上一个页面
    public void main2waitfor(View view) {


        openActivity(Main2Activity_2.class);
        AppManager.getInstance().killAllActivityNotMain2_2();

    }

    //返回上一个页面
    public void main2received(View view) {

        openActivity(Main2Activity_3.class);
        AppManager.getInstance().killAllActivityNotMain2_3();

    }
    //返回上一个页面
    public void main2finish(View view) {

        openActivity(Main2Activity_4.class);
        AppManager.getInstance().killAllActivityNotMain2_4();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        //setContentView(R.layout.activity_down);
        //onCreate(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  onCreate(null);
    }
/*class GameThread implements Runnable {
        public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
                } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                }

                // 使用postInvalidate可以直接在线程中更新界面
                mGameView.postInvalidate();
            }
        }
    }*/




    /**
     * 是否按照宽度进行等比例适配 (为了保证在高宽比不同的屏幕上也能正常适配, 所以只能在宽度和高度之中选择一个作为基准进行适配)
     *
     * @return {@code true} 为按照宽度进行适配, {@code false} 为按照高度进行适配
     */
    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    /**
     * 这里使用 iPhone 的设计图, iPhone 的设计图尺寸为 750px * 1334px, 高换算成 dp 为 667 (1334px / 2 = 667dp)
     * <p>
     * 返回设计图上的设计尺寸, 单位 dp
     * {@link #getSizeInDp} 须配合 {@link #isBaseOnWidth()} 使用, 规则如下:
     * 如果 {@link #isBaseOnWidth()} 返回 {@code true}, {@link #getSizeInDp} 则应该返回设计图的总宽度
     * 如果 {@link #isBaseOnWidth()} 返回 {@code false}, {@link #getSizeInDp} 则应该返回设计图的总高度
     * 如果您不需要自定义设计图上的设计尺寸, 想继续使用在 AndroidManifest 中填写的设计图尺寸, {@link #getSizeInDp} 则返回 {@code 0}
     *
     * @return 设计图上的设计尺寸, 单位 dp
     */
    @Override
    public float getSizeInDp() {
        return 533;
    }
}
