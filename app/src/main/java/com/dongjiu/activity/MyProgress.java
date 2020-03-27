package com.dongjiu.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyProgress extends View {

    private AlertDialog.Builder builder = null;
    private AlertDialog dialog = null;
    private LinearLayout linear = null;

    public MyProgress(Context context) {
        super(context);
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        linear = new LinearLayout(context);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linear.setBackgroundColor(Color.BLACK);
        linear.setGravity(Gravity.CENTER);
        linear.setPadding(5, 5, 5, 5);
        ProgressBar pb = new ProgressBar(context);
        linear.addView(pb);

        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setText("取消登录");
        tv.setTextColor(Color.WHITE);
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        linear.addView(tv);
        builder.setView(linear);
    }

    public void setBackgroundDrawableByresourceId(int resourceId) {
        linear.setBackgroundResource(resourceId);
    }

    public void start() {
        dialog = builder.show();
    }

    public void stop() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }
}
