package com.dongjiu.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.R;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.activity.SystemActivity;
import com.dongjiu.fragment.adapter.MsgContentFragmentAdapter;
import com.dongjiu.utils.CustomAdapter.MyRecyclerViewAdapter;
import com.dongjiu.utils.CustomDatePicker.CustomDatePicker;
import com.dongjiu.utils.CustomDatePicker.DateFormatUtils;
import com.dongjiu.utils.CustomDatePicker.PickerView;
import com.dongjiu.utils.DateTimeDialog.DateTimeWheelDialog;
import com.dongjiu.utils.datescroller.widget.DateScrollerDialog;
import com.dongjiu.utils.datescroller.widget.data.Type;
import com.dongjiu.utils.datescroller.widget.listener.OnDateSetListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 通讯录
 * <p>展示居中位置的tab页卡</p>
 * 下载界面显示
 */
public class DownloadFragment extends Fragment implements CustomAdapt {
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;
private static final String TAG = "DownloadFragment";

    @BindView(R.id.tv_selected_date)
    TextView mTvSelectedDate;
    @BindView(R.id.down_order)
    TextView down_order;

    @BindView(R.id.ll_date)
    LinearLayout ll_date;


    private static Context context;



//    @BindView(R.id.timepicker)
//    DatePicker datePicker;


    // @BindView(R.id.tv_selected_date);
    TextView mTvSelectedTime;
   // @BindView(R.id.tv_selected_date)
    CustomDatePicker mDatePicker;
   // @BindView(R.id.tv_selected_date)
    CustomDatePicker  mTimerPicker;
    private MsgContentFragmentAdapter adapter;
    private List<String> names;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("---", String.valueOf(Math.random()*10000));
//        initData();


    }
    //private FragmentManager mFragmentManager;
    private static final long HUNDRED_YEARS = 1L * 30 * 1000 * 60 * 60 * 24L;
    protected TextView btnYearMonthDay;
    protected TextView tvTime;
    protected RelativeLayout activityMain;

    private TextView mTvTime;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    /**
     * 上次设置的时间
     */
    private long mLastTime = System.currentTimeMillis();
    /**
     * 数据的回调
     */
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            String text = getDateToString(milliseconds);
            mTvSelectedDate.setText(text);
        }
    };
    /**
     * 显示日期
     *
     * @param view 视图
     */
    public void showDate(View view) {
        // 出生日期
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("请选择日期")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis())
                .setCurMilliseconds(mLastTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(this.getFragmentManager(), "year_month_day");
            }
        }
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_download, container, false);
        ButterKnife.bind(this, view);
        context = MApplication.getContext();
//        mFragmentManager = BaseActivity.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = MainActivity.mFragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_activity_main, new TestFragment());
//        fragmentTransaction.commitAllowingStateLoss();

//        adapter = new MsgContentFragmentAdapter(getChildFragmentManager());
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//
//        // 更新适配器数据
//        adapter.setList(names);

        //刷新Fragment数据的方法
       // registerReceiver();

        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        long fiveYears = 1L * 365 * 100 * 60 * 60 * 24L;
        final TimePickerDialog mDialogAll;

        mTvSelectedDate.setText(DateFormatUtils.StringData());
        down_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mDialogAll = new TimePickerDialog.Builder(BaseActivity.context)
//                        .setCallBack(this)
//                        .setCancelStringId("Cancel")
//                        .setSureStringId("Sure")
//                        .setTitleStringId("TimePicker")
//                        .setYearText("Year")
//                        .setMonthText("Month")
//                        .setDayText("Day")
//                        .setHourText("Hour")
//                        .setMinuteText("Minute")
//                        .setCyclic(false)
//                        .setMinMillseconds(System.currentTimeMillis())
//                        .setMaxMillseconds(System.currentTimeMillis() + tenYears)
//                        .setCurrentMillseconds(System.currentTimeMillis())
//                        .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
//                        .setType(Type.ALL)
//                        .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
//                        .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
//                        .setWheelItemTextSize(12)
//                        .build();
              //  showDate(mTvSelectedDate);
                BaseActivity.showToastView(mTvSelectedDate.getText().toString());
//
//                final DateTimeWheelDialog dialog = new DateTimeWheelDialog(getActivity());
//                dialog.show();
//               // dialog.findViewById(R.id.dpv_year).
//
//
//
//                dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                       // BaseActivity.showToastView("取消");
//                        mTvSelectedDate.setText("日期取消按钮");
//
//                        dialog.hide();
//                    }
//                });
//                dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                      //  BaseActivity.showToastView("确认");
//                        mTvSelectedDate.setText("日期确认按钮");
//                        dialog.hide();
//                    }
//                });
               // createDialog();
                Toast.makeText(MApplication.getContext(),"开始下载",Toast.LENGTH_SHORT).show();
            }
        });
        //DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity());
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // mDatePicker.show(mTvSelectedDate.getText().toString());
//                Toast.makeText(BaseActivity.context,"开始下载",Toast.LENGTH_SHORT).show();
//                Calendar calendar = Calendar.getInstance();
//
//
//               int year1 = calendar.get(Calendar.YEAR);
//               int  month1=calendar.get(calendar.MONTH)+1;
//               int  day1=calendar.get(calendar.DAY_OF_MONTH);
//
//
//                int year =  datePicker.getYear();
//                int month= datePicker.getMonth()+1;
//                int day =  datePicker.getDayOfMonth();
//                Log.i(TAG, year+"-"+month+"-"+day);
//                mTvSelectedDate.setText(year+"-"+month+"-"+day);


                showDate(mTvSelectedDate);
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//                builder.setTitle("请输入");     //设置对话框标题
//
//                builder.setIcon(android.R.drawable.btn_star);      //设置对话框标题前的图标
//
//                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//
//                    @Override
//
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                        Toast.makeText(getActivity(), "dialog" , Toast.LENGTH_SHORT).show();
//                        Log.i("---", String.valueOf(Math.random()*10000));
//
//                        int year =  datePicker.getYear();
//                        int month= datePicker.getMonth()+1;
//                        int day =  datePicker.getDayOfMonth();
//                        mTvSelectedDate.setText(year+"-"+month+"-"+day);
//
//                    }
//
//                });
//
//                builder.setCancelable(true);   //设置按钮是否可以按返回键取消,false则不可以取消
//
//                AlertDialog dialog = builder.create();  //创建对话框
//
//                dialog.setCanceledOnTouchOutside(true);      //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
//
//                dialog.show();


            }
        });

       //Calendar calendar = new CustomDatePicker()
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        //showToast("刷新数据1");
        Log.i("下载界面+++", "刷新数据");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
      /*  if (fragmentView != null && !hidden) {
            showToast("刷新数据2");
        }*/

        Log.i("下载界面+++", "刷新数据");

    }

    private boolean isGetData = false;
    /**
     * 切换刷新
     */
    protected boolean isCreated = false;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    /**
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //页面刷新操作
//        if (!isCreated) {
//            return;
//        }
//
//        if (isVisibleToUser) {
//            setUI();
//        }
    }



    //用来设置UI，更新UI时重新从数据库获取数据，进行设置
    public void setUI(){
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        long fiveYears = 1L * 365 * 100 * 60 * 60 * 24L;
        final TimePickerDialog mDialogAll;

        mTvSelectedDate.setText(DateFormatUtils.StringData());
        down_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseActivity.showToastView(mTvSelectedDate.getText().toString());
                Toast.makeText(context,"开始下载",Toast.LENGTH_SHORT).show();
            }
        });
        //DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity());
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showDate(mTvSelectedDate);

            }
        });

        //Calendar calendar = new CustomDatePicker()
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mDatePicker.onDestroy();
    }










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
