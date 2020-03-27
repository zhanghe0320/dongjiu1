package com.dongjiu.utils.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.R;

import java.util.LinkedList;

public class OrederMess_Pro_Ada extends BaseAdapter {
    private LinkedList<OrderMess_product> list;
    private Context mContext;

    public OrederMess_Pro_Ada(Context mContext, LinkedList list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(MApplication.getContext()).inflate(R.layout.home_page_order_1, parent, false);

            holder = new ViewHolder();
            holder.ProductNumFinish = (TextView) convertView.findViewById(R.id.postorder_num_1);
            holder.ProductName = (TextView) convertView.findViewById(R.id.postorder_name_1);
            holder.ProductNum = (TextView) convertView.findViewById(R.id.postorder_woarhouse_num_1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.ProductNumFinish.setText(list.get(position).getProductNumFinish());
        holder.ProductName.setText(list.get(position).getProductName());
        holder.ProductNum.setText(list.get(position).getProductNum());

        return convertView;
    }

    static class ViewHolder {

        TextView ProductName;
        TextView ProductNum;
        TextView ProductNumFinish;

    }
}