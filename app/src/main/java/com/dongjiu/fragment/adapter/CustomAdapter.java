package com.dongjiu.fragment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.AppManager;
import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.R;

import com.dongjiu.activity.MainActivity;
import com.dongjiu.activity.ScanQrcodeActivity;
import com.dongjiu.fragment.QrcodeFragment2;

import java.util.LinkedList;
import java.util.Random;

public class CustomAdapter extends BaseAdapter/*RecyclerView.Adapter*/ {
    private LinkedList<OrderMess> aData;
    private Context mContext;
    private ViewHolder holder;

  //  @BindView(R.id.postordername)
    private TextView postordername; //发货单

   // @BindView(R.id.postordername)
    private TextView postordernum_1;//发货单单号

   // @BindView(R.id.postordername)
    private TextView postorderday_1;//发货单日期

   // @BindView(R.id.postordername)
    private TextView postorder_warehouse;//发货单仓库

   // @BindView(R.id.postordername)
    private TextView postorder_name;//发货单产品名称

   // @BindView(R.id.postordername)
    private TextView postorder_woarhouse_num;//发货单件数

    //@BindView(R.id.postordername)
    private TextView postorder_num;//已经的扫码件数

   // @BindView(R.id.postordername)
    private TextView postorder_receive;//售货商人

   // @BindView(R.id.postordername)
    private TextView postorder_receive_add;//发货地址;

    //@BindView(R.id.postorder_scan)
    private Button postorder_scan;//扫码发货;

   // @BindView(R.id.postorder_finish)
    private Button postorder_finish;//扫码完成;


   // @BindView(R.id.postorderimg)
    private ImageView postorderimg;//扫码完成;


    private ListView order_list_item;//扫码完成;

    LinearLayout home_page_order;
    public CustomAdapter(LinkedList<OrderMess> aData, Context mContext){
        this.aData = aData;
        this.mContext = mContext;

    }
    @Override
    public int getCount(){
        return aData.size();
    }
    @Override
    public Object getItem(int position){
        return null;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    private void showDialog(String s ){
        AlertDialog.Builder builder=new AlertDialog.Builder(MApplication.getContext());
       // builder.setIcon(R.drawable.picture);
        builder.setTitle("温馨提示");
        builder.setMessage(s);
        builder.setPositiveButton("我知道了",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
         holder = null;


        if (convertView==null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_page_order,parent,false);
//            int a = convertView.getHeight();
//            convertView.setMinimumHeight(a+aData.get(position).getList().size()*50);
//            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
//            layoutParams.height = a+aData.get(position).getList().size()*50;
//            convertView.setLayoutParams(layoutParams);
//            Log.i("---", String.valueOf(a+aData.get(position).getList().size()*50));
            holder = new ViewHolder();
            holder.postorderimg = (ImageView)convertView.findViewById(R.id.postorderimg);
            holder.postordername = (TextView)convertView.findViewById(R.id.postordername);
            holder.postordernum_1 = (TextView)convertView.findViewById(R.id.postordernum_1);
            holder.postorderday_1 = (TextView)convertView.findViewById(R.id.postorderday_1);
            holder.postorder_warehouse = (TextView)convertView.findViewById(R.id.postorder_warehouse);
            holder.postorder_name = (TextView)convertView.findViewById(R.id.postorder_name);
            holder.postorder_woarhouse_num = (TextView)convertView.findViewById(R.id.postorder_woarhouse_num);
            holder.postorder_woarhouse_num = (TextView)convertView.findViewById(R.id.postorder_woarhouse_num);
            holder.postorder_num = (TextView)convertView.findViewById(R.id.postorder_num);
            holder.postorder_receive = (TextView)convertView.findViewById(R.id.postorder_receive);
            holder.postorder_receive_add = (TextView)convertView.findViewById(R.id.postorder_receive_add);
            holder.postorder_scan = (Button) convertView.findViewById(R.id.postorder_scan);
            holder.postorder_finish = (Button) convertView.findViewById(R.id.postorder_finish);
            holder.order_list_mess = (Button) convertView.findViewById(R.id.order_list_mess);
            holder.order_list_item = (ListView) convertView.findViewById(R.id.order_list_item);


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.postorderimg.setBackgroundResource(R.drawable.postorder_nopost); // 未发货
     //   holder.postorderimg.setBackgroundResource(R.drawable.postorder_postscan);//扫码完成
     //   holder.postorderimg.setBackgroundResource(R.drawable.postorder_receive);//已收货
        holder.postordername.setText(aData.get(position).getPostorder_name());
        holder.postordernum_1.setText(aData.get(position).getPostordernum_1());
        holder.postorderday_1.setText(aData.get(position).getPostorderday_1());
        holder.postorder_warehouse.setText(aData.get(position).getPostorder_warehouse());
        holder.postorder_name.setText(aData.get(position).getPostorder_name());
        holder.postorder_woarhouse_num.setText(aData.get(position).getPostorder_woarhouse_num());
        holder.postorder_num.setText(aData.get(position).getPostorder_num());
        holder.postorder_receive.setText(aData.get(position).getPostorder_receive());
        holder.postorder_receive_add.setText(aData.get(position).getPostorder_receive_add());
        OrederMess_Pro_Ada orederMess_pro_ada = new OrederMess_Pro_Ada(MApplication.getContext(),aData.get(position).getList());
        holder.order_list_item.setAdapter(orederMess_pro_ada);
        //postorder_scan
        //postorder_finish
       // holder.order_list_item.setPressed(true);
      //  holder.order_list_item.setEnabled(true);
      /*
        holder.order_list_item.setOnTouchListener(new View.OnTouchListener() {
              @Override
              public boolean onTouch(View v, MotionEvent event) {
                  holder.order_list_item.getOverscrollFooter();

                  holder.order_list_item.setScrollX(100);
                  return false;
              }
        });*/
      /*  holder.order_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String s =  aData.get(position).getPostorder_woarhouse_num();
                //BaseActivity.showToastView("详情信息"+s );



               // holder.order_list_item.setScrollY(50);

                showDialog("滑动50，详情信息"+s);
            }
        });*/

        holder.order_list_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  aData.get(position).getPostorder_woarhouse_num();
                //BaseActivity.showToastView("详情信息"+s );
            /*   MyAlertDialog alertDialog = new MyAlertDialog.
                        Builder(BaseActivity.context,s).create();
                        alertDialog.show();*/

                MainActivity.isContinuousScan= true;
                MainActivity.viewPager.setCurrentItem(2, false);

//                Intent intent = new Intent(BaseActivity.context, ScanQrcodeActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("orderid",s);  //key-"sff",通过key得到value-"value值"(String型)
//                bundle.putInt("ordernum", 175);           //key-"iff",value-175
//                intent.putExtras(bundle);            //通过intent将bundle传到另个Activity
//
//                BaseActivity.context.startActivity(intent);
//
//                AppManager.getInstance().killActivity(MainActivity.class);


//                MainActivity.mFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.qrcodefragment_1, new QrcodeFragment2(), null)
//                        .addToBackStack(null)
//                        .commit();


//                QrcodeFragment2 qrcodeFragment2=new QrcodeFragment2();//实例化要跳转的fragment对象（也就是你要跳转的下一个碎片）
//                FragmentTransaction transaction=MainActivity.mFragmentManager.beginTransaction();
//                Bundle bundle=new Bundle();//声明一个Bundle对象
//                bundle.putString("orderid",s);  //key-"sff",通过key得到value-"value值"(String型)
//                bundle.putInt("ordernum", 175);           //key-"iff",value-175
//                qrcodeFragment2.setArguments(bundle);//将bundle对象绑定fragment
//                //transaction.add(R.id.qrcodefragment2,qrcodeFragment2).addToBackStack(null).commit();
//                if(qrcodeFragment2.isAdded()){
//                    transaction
//                            // 隐藏fragment1，即当前碎片
//                            //.hide(fragment1)
//                            // 显示已经添加过的碎片，即fragment2
//                            .show(qrcodeFragment2)
//                            // 加入返回栈
//                            .addToBackStack(null)
//                            // 提交事务
//                            .commitAllowingStateLoss();
//                }else {
//                    transaction.add(R.id.qrcodefragment2,qrcodeFragment2)
//                            //加入返回栈，这样你点击返回键的时候就会回退到fragment1了
//                            .addToBackStack(null)
//                            // 提交事务
//                            .commitAllowingStateLoss();
//                };




//                transaction.replace(R.id.qrcodefragment2,qrcodeFragment2);
//                transaction.addToBackStack(null);
//                transaction.commit();

//                Intent intent= new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(url);
//                intent.setData(content_url);
//                BaseActivity.context.startActivity(intent);

               // Toast.makeText(MainActivity.context,"详情信息"+s, Toast.LENGTH_LONG).show();
            }
        });

/*
        holder.order_list_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  aData.get(position).getPostorder_woarhouse_num();
                BaseActivity.showToastView("详情信息"+s );
            }
        });*/

        holder.postorder_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  aData.get(position).getPostorder_woarhouse_num();
               // BaseActivity.showToastView("开始扫码"+s );
                //Toast.makeText(MainActivity.context,"开始扫码"+s, Toast.LENGTH_LONG).show();

                BaseActivity.showToastView("开始扫描");
//                Intent intent = new Intent(BaseActivity.context, ScanQrcodeActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("orderid",s);  //key-"sff",通过key得到value-"value值"(String型)
//                bundle.putInt("ordernum", 175);           //key-"iff",value-175
//                intent.putExtras(bundle);            //通过intent将bundle传到另个Activity
//                intent.putExtra(MainActivity.KEY_TITLE,"title");
//                intent.putExtra(MainActivity.KEY_IS_CONTINUOUS,MainActivity.isContinuousScan);
//                BaseActivity.context.startActivity(intent);

                MainActivity.viewPager.setCurrentItem(2, false);

//                AppManager.getInstance().killActivity(MainActivity.class);

//MainActivity.mFragmentManager.beginTransaction().replace(R.id.qrcodefragment2,new QrcodeFragment2());


//                QrcodeFragment2 qrcodeFragment2=new QrcodeFragment2();//实例化要跳转的fragment对象（也就是你要跳转的下一个碎片）
//                FragmentTransaction transaction=MainActivity.mFragmentManager.beginTransaction();
//                Bundle bundle=new Bundle();//声明一个Bundle对象
//                bundle.putString("orderid",s);  //key-"sff",通过key得到value-"value值"(String型)
//                bundle.putInt("ordernum", 175);           //key-"iff",value-175
//                qrcodeFragment2.setArguments(bundle);//将bundle对象绑定fragment
//                transaction.add(R.id.qrcodefragment2,qrcodeFragment2).addToBackStack(null).commit();

//                MainActivity.mFragmentManager.beginTransaction()
//                        .replace(R.id.activity_scan_qrcode, new QrcodeFragment2(), null)
//                        .addToBackStack(null)
//                        .commit();


//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(BaseActivity.context,R.anim.in,R.anim.out);
//                Intent intent = new Intent(BaseActivity.context, CustomCaptureActivity.class);
//                intent.putExtra(MainActivity.KEY_TITLE,"title");
//                intent.putExtra(MainActivity.KEY_IS_CONTINUOUS,MainActivity.isContinuousScan);
//                ActivityCompat.startActivityForResult(MainActivity.class,intent,MainActivity.REQUEST_CODE_SCAN,optionsCompat.toBundle());
            }
        });

        holder.postorder_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  aData.get(position).getPostorder_woarhouse_num();;
             //   BaseActivity.showToastView("扫码完成"+s );
                Toast.makeText(MApplication.getContext(),"扫码完成"+s, Toast.LENGTH_LONG).show();

            }
        });
        return convertView;
    }
    static class ViewHolder{
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
    }

    @Override
    public void notifyDataSetChanged() {
       // super.notifyDataSetChanged();
        initCityList();

    }

    @Override
    public void notifyDataSetInvalidated() {
       // super.notifyDataSetInvalidated();
        initCityList();

    }



    private void initCityList() {
       /* if (cities != null) {
            for (Iterator it = cities.iterator(); it.hasNext();) {
                OfflineMapCity i = (OfflineMapCity) it.next();
                it.remove();
            }

        }
        cities.addAll(ByteOfflineMapManager.getInstance(mContext).getDownloadOfficeMapCityList());
        cities.addAll(ByteOfflineMapManager.getInstance(mContext).getDownloadingCityList());
*/

        LinkedList aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {

            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }
            OrderMess orderMess = new OrderMess(
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    "南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    list
            );
            aData.add(orderMess);
            Log.i("---", orderMess.getPostorder_name());


        }


        notifyDataSetChanged();


    }








}