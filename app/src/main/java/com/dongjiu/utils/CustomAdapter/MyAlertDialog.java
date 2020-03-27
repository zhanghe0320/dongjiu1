package com.dongjiu.utils.CustomAdapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.dongjiu.R;

import java.util.LinkedList;

public class MyAlertDialog extends Dialog {

    private MyAlertDialog(Context context, int themeResId ) {
        super(context, themeResId);
    }

    public static class Builder {

        private View mLayout;

        private ListView listView;//品名

        private Button button_i_know;

        private View.OnClickListener mButtonClickListener;

        private MyAlertDialog mDialog;

        private LinkedList mylist;

        public Builder(Context context,String a) {
            mDialog = new MyAlertDialog(context,R.style.FullScreenDialog);
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.alert_dialog, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            listView = mLayout.findViewById(R.id.orde_all_mess);
            mylist = new LinkedList();
            for (int i = 0; i <10 ; i++) {
                OrderMess_product orderMess_product = new OrderMess_product();
                orderMess_product.setProductName(a+"-"+i);
                orderMess_product.setProductNum(a+"-"+i);
                orderMess_product.setProductNumFinish(a+"-"+i);
                mylist.add(orderMess_product);
            }
            OrederMess_Pro_Ada orederMess_pro_ada = new OrederMess_Pro_Ada(context,mylist);
            listView.setAdapter(orederMess_pro_ada);
            button_i_know = mLayout.findViewById(R.id.button_i_know);

        }

        /**
         * 通过 ID 设置 Dialog 图标
         */
        public Builder setIcon(int resId) {

            return this;
        }

        /**
         * 用 Bitmap 作为 Dialog 图标
         */
        public Builder setIcon(Bitmap bitmap) {

            return this;
        }

        /**
         * 设置 Dialog 标题
         */
        public Builder setTitle(@NonNull String title) {

            return this;
        }

        /**
         * 设置 Message
         */
        public Builder setMessage(@NonNull String message) {

            return this;
        }

        /**
         * 设置按钮文字和监听
         */
        public Builder setButton(@NonNull String text, View.OnClickListener listener) {
            //button_i_know.setText(text);
           // mButtonClickListener = listener;
            return this;
        }

        public MyAlertDialog create() {
            button_i_know.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                   // mButtonClickListener.onClick(v);
                }
            });
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
            mDialog.setCanceledOnTouchOutside(true);   //用户不可以点击外部来关闭 Dialog
            return mDialog;
        }
    }
}