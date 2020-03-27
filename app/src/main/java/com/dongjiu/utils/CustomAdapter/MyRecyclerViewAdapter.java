package com.dongjiu.utils.CustomAdapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private static final String TAG = "MyRecyclerViewAdapter";
    private static Context context;
    private ArrayList<OrderMess> list;
    private List<Boolean> booleanlist=new ArrayList<>();

    public MyRecyclerViewAdapter(Context context) {
        this.context= MApplication.getContext();
        list=new ArrayList<>();
    }

    public MyRecyclerViewAdapter(Context context,ArrayList list) {
        this.context=MApplication.getContext();
        this.list=list;
        for (int i = 0; i < list.size(); i++) {
            //设置默认的显示
            booleanlist.add(false);
        }
    }
    public void addData(List  strings){

        list.addAll(strings);
        for (int i = 0; i < strings.size(); i++) {
            booleanlist.add(false);
        }
        notifyDataSetChanged();
    }
    //更改集合内部存储的状态
    public void initCheck(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            //更改指定位置的数据
            booleanlist.set(i,flag);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= MApplication.getContext();
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MApplication.getContext()).inflate(R.layout.upload_listview, parent, false));

        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText("数据"+list.get(position).getPostorder_name());//.getPostorder_name()
        Log.i(TAG, String.valueOf(list.size()));

        holder.postorderimg.setBackgroundResource(R.drawable.postorder_nopost); // 未发货
        //   holder.postorderimg.setBackgroundResource(R.drawable.postorder_postscan);//扫码完成
        //   holder.postorderimg.setBackgroundResource(R.drawable.postorder_receive);//已收货
        holder.postordername.setText("名称"+list.get(position).getPostorder_name());
        holder.postordernum_1.setText("数据"+list.get(position).getPostordernum_1());
        holder.postorderday_1.setText("总数"+list.get(position).getPostorderday_1());
        holder.postorder_warehouse.setText("仓库"+list.get(position).getPostorder_warehouse());
        holder.postorder_name.setText("地址"+list.get(position).getPostorder_name());
        holder.postorder_woarhouse_num.setText("仓库信息"+list.get(position).getPostorder_woarhouse_num());
        holder.postorder_num.setText("123456"+list.get(position).getPostorder_num());
        holder.postorder_receive.setText("56"+list.get(position).getPostorder_receive());
        holder.postorder_receive_add.setText("实时生"+list.get(position).getPostorder_receive_add());
        OrederMess_Pro_Ada orederMess_pro_ada = new OrederMess_Pro_Ada(context,list.get(position).getList());
        holder.order_list_item.setAdapter(orederMess_pro_ada);
        //postorder_scan
        //postorder_finish
        holder.order_list_item.setPressed(true);
       holder.order_list_item.setEnabled(true);


        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用集合保存当前的状态
                booleanlist.set(position,isChecked);
                //holder.tv.setTextColor(Color.parseColor("#000000"));
            }
        });
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "dianji", Toast.LENGTH_SHORT).show();
                booleanlist.set(position,holder.cb.isChecked()?false:true);
                holder.cb.setChecked(booleanlist.get(position));

            }
        });

        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booleanlist.set(position,holder.cb.isChecked()?false:true);
                holder.cb.setChecked(booleanlist.get(position));
            }
        });
        holder.cb.setChecked(booleanlist.get(position));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //清空所有数据
    public void deleteAllData() {
        for (int i = 0; i < list.size(); i++) {

                Log.d("--", list.get(i).toString());

            OrderMess orderMess = list.get(i);
            Log.i(TAG, orderMess.getPostorder_warehouse());

        }
        list.clear();
        booleanlist.clear();
        notifyDataSetChanged();
    }
    //删除选中的数据
    public void deletingData() {
        int y=0;
        for (int i = 0; i < list.size(); i++) {
            if(booleanlist.get(i)!=null && booleanlist.get(i) ) {
                Log.d("--", list.get(i).toString());
                OrderMess orderMess = list.get(i);
                Log.i(TAG, orderMess.getPostorder_warehouse());
                list.remove(i);
                booleanlist.remove(i);
                y++;
                i--;
            }
        }
        notifyDataSetChanged();
        if(y==0){
            BaseActivity.showToastView("没有选中的要删除的数据");
            //Toast.makeText(context, "没有选中的要删除的数据", Toast.LENGTH_SHORT).show();
        }else {
            BaseActivity.showToastView("正在上传");

        }
    }
    public void selectAll(){
        initCheck(true);
        notifyDataSetChanged();
    }
    public void unSelectAll(){
        initCheck(false);
        notifyDataSetChanged();
    }
    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        TextView tv;

        LinearLayout tv1;


        ImageView postorderimg;
        TextView postordername;
        TextView postordernum_1;
        TextView postorderday_1;
        TextView postorder_warehouse;
        TextView postorder_name;
        TextView postorder_woarhouse_num;
        TextView postorder_num;
        TextView postorder_receive;
        TextView postorder_receive_add;
        Button postorder_scan;
        Button postorder_finish;
        Button order_list_mess;
        ListView order_list_item;
        LinearLayout home_page_order;


        public MyViewHolder(View view) {
            super(view);
            cb = (CheckBox) view.findViewById(R.id.chb_select_way_point);
            tv = (TextView) view.findViewById(R.id.tv_data);
            tv1 =  view.findViewById(R.id.tv_data1);

             postorderimg = view.findViewById(R.id.postorderimg);
             postordername= view.findViewById(R.id.postordername);
             postordernum_1= view.findViewById(R.id.postordernum_1);
             postorderday_1= view.findViewById(R.id.postorderday_1);
             postorder_warehouse= view.findViewById(R.id.postorder_warehouse);
             postorder_name= view.findViewById(R.id.postorder_name);
             postorder_woarhouse_num= view.findViewById(R.id.postorder_woarhouse_num);
             postorder_num= view.findViewById(R.id.postorder_num);
             postorder_receive= view.findViewById(R.id.postorder_receive);
             postorder_receive_add= view.findViewById(R.id.postorder_receive_add);
             postorder_scan= view.findViewById(R.id.postorder_scan);
             postorder_finish= view.findViewById(R.id.postorder_finish);
             order_list_mess= view.findViewById(R.id.order_list_mess);
             order_list_item= view.findViewById(R.id.order_list_item);
           //  home_page_order= view.findViewById(R.id.chb_select_way_point);





        }
    }
}