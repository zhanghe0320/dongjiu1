package com.dongjiu.utils.NoDoubleClickListener;

import android.view.View;

import com.dongjiu.BaseActivity;

import java.util.Calendar;

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 3000;

    private long lastClickTime = 0;
    public abstract void onNoDoubleClick(View v);
    @Override
    public void onClick(View v) {

        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {

            lastClickTime = currentTime;

            onNoDoubleClick(v);

        }else {
            BaseActivity.showToastView("请不要连续点击！");
        }

    }


}
