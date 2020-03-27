package com.dongjiu.utils.DateTimeDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.R;
import com.dongjiu.utils.CustomDatePicker.CustomDatePicker;
import com.dongjiu.utils.CustomDatePicker.PickerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTimeWheelDialog extends Dialog implements View.OnClickListener, PickerView.OnSelectListener{

    private static final String TAG = "DateTimeWheelDialog";
    private Context mContext;
    private CustomDatePicker.Callback mCallback;
    private Calendar mBeginTime, mEndTime, mSelectedTime;
    private boolean mCanDialogShow;

    private Dialog mPickerDialog;
    private PickerView mDpvYear, mDpvMonth, mDpvDay, mDpvHour, mDpvMinute;
    private TextView mTvHourUnit, mTvMinuteUnit,tv_title,tv_cancel,tv_confirm;

    private int mBeginYear, mBeginMonth, mBeginDay, mBeginHour, mBeginMinute,
            mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    private List<String> mYearUnits = new ArrayList<>(), mMonthUnits = new ArrayList<>(), mDayUnits = new ArrayList<>(),
            mHourUnits = new ArrayList<>(), mMinuteUnits = new ArrayList<>();
    private DecimalFormat mDecimalFormat = new DecimalFormat("00");

    private boolean mCanShowPreciseTime;
    private int mScrollUnits = SCROLL_UNIT_HOUR + SCROLL_UNIT_MINUTE;
    private static Context context;

    /**
     * 时间单位：时、分
     */
    private static final int SCROLL_UNIT_HOUR = 0b1;
    private static final int SCROLL_UNIT_MINUTE = 0b10;

    /**
     * 时间单位的最大显示值
     */
    private static final int MAX_MINUTE_UNIT = 59;
    private static final int MAX_HOUR_UNIT = 23;
    private static final int MAX_MONTH_UNIT = 12;

    /**
     * 级联滚动延迟时间
     */
    private static final long LINKAGE_DELAY_DEFAULT = 100L;

    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onTimeSelected(long timestamp);
    }

    public DateTimeWheelDialog(@NonNull Context context) {
        super(context);
    }

    public DateTimeWheelDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override
    public void create() {
        super.create();
       // initView();
    }

    @Override
    public void show() {
        super.show();
        if (getWindow() != null) {
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MApplication.getContext();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setBackgroundDrawable(null);
            getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.dialog_date_picker);
        initView();



    }


    private void initView() {
        mPickerDialog = new Dialog(context, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.dialog_date_picker);

        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }


        tv_cancel = mPickerDialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.showToastView("取消");
                mPickerDialog.dismiss();
            }
        });
        tv_confirm = mPickerDialog.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.showToastView( "确认");
                mPickerDialog.dismiss();
            }
        });

//        mPickerDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(BaseActivity.context,"取消",Toast.LENGTH_LONG).show();
//            }
//        });
//        mPickerDialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(BaseActivity.context,"确认",Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//        mTvHourUnit = mPickerDialog.findViewById(R.id.tv_hour_unit);
//        mTvMinuteUnit = mPickerDialog.findViewById(R.id.tv_minute_unit);
//        tv_title = mPickerDialog.findViewById(R.id.tv_title);
//
//        mDpvYear = mPickerDialog.findViewById(R.id.dpv_year);
//        mDpvYear.setOnSelectListener(new PickerView.OnSelectListener() {
//            @Override
//            public void onSelect(View view, String selected) {
//                Toast.makeText(BaseActivity.context,"年",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        mDpvMonth = mPickerDialog.findViewById(R.id.dpv_month);
//        mDpvMonth.setOnSelectListener(new PickerView.OnSelectListener() {
//            @Override
//            public void onSelect(View view, String selected) {
//                Toast.makeText(BaseActivity.context,"月",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        mDpvDay = mPickerDialog.findViewById(R.id.dpv_day);
//        mDpvDay.setOnSelectListener(new PickerView.OnSelectListener() {
//            @Override
//            public void onSelect(View view, String selected) {
//                Toast.makeText(BaseActivity.context,"日",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        mDpvHour = mPickerDialog.findViewById(R.id.dpv_hour);
//        mDpvHour.setOnSelectListener(new PickerView.OnSelectListener() {
//            @Override
//            public void onSelect(View view, String selected) {
//                Toast.makeText(BaseActivity.context,"小时",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        mDpvMinute = mPickerDialog.findViewById(R.id.dpv_minute);
//        mDpvMinute.setOnSelectListener(new PickerView.OnSelectListener() {
//            @Override
//            public void onSelect(View view, String selected) {
//                Toast.makeText(BaseActivity.context,"分钟",Toast.LENGTH_LONG).show();
//
//            }
//        });
//





    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public Window getWindow() {
        return super.getWindow();
    }

    @Nullable
    @Override
    public View getCurrentFocus() {
        return super.getCurrentFocus();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
    }

    @Override
    public void setTitle(@Nullable CharSequence title) {
        super.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void cancel() {
        super.cancel();
    }


    protected DateTimeWheelDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSelect(View view, String selected) {

    }
}
