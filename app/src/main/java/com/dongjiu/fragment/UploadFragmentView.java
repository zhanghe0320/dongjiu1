package com.dongjiu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ListView;
import android.widget.TextView;

import com.dongjiu.MApplication;
import com.dongjiu.R;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.fragment.adapter.CustomAdapter;
import com.dongjiu.fragment.adapter.OrderMess;
import com.dongjiu.fragment.adapter.OrderMess_product;
import com.dongjiu.utils.CustomAdapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;


/**
 * 消息内容页
 */
public class UploadFragmentView extends Fragment implements CustomAdapt {
    @BindView(R.id.txt_content)
    TextView tvContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.listview)
//    ListView listview ;
//初始化控件
//@BindView(R.id.recyclerView)
//RecyclerView recyclerView;
    @BindView(R.id.nochexbox)
    TextView nochexbox ;
    @BindView(R.id.allchexbox)
    TextView allchexbox ;
    @BindView(R.id.delete)
    TextView delete ;
    @BindView(R.id.alldelete)
    TextView alldelete;



    private String name;

    private MyRecyclerViewAdapter adapter;
    private ArrayList list = new ArrayList<>();
    /**
     * 切换刷新
     */
    protected boolean isCreated = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        if (name == null) {
            name = "参数非法";
        }

      //  Toast.makeText(MainActivity.context,name,Toast.LENGTH_LONG).show();

        // 标记
        isCreated = true;


    }
    /**
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isCreated) {
            return;
        }

        if (isVisibleToUser) {
           //setUI();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_view, container, false);
        ButterKnife.bind(this, view);


        for (int i = 0; i <15 ; i++) {

            LinkedList list0= new LinkedList();
            for (int j = 0; j <10; j++) {
                com.dongjiu.utils.CustomAdapter.OrderMess_product orderMess_product = new com.dongjiu.utils.CustomAdapter.OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list0.add(orderMess_product);
            }
            com.dongjiu.utils.CustomAdapter.OrderMess orderMess = new com.dongjiu.utils.CustomAdapter.OrderMess(
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    list0
            );
            list.add(orderMess);



        }
        recyclerView.setLayoutManager(new LinearLayoutManager(MApplication.getContext()));
        //初始化适配器
        adapter = new MyRecyclerViewAdapter(MApplication.getContext(), list);
        //给recyclerView添加适配器
        recyclerView.setAdapter(adapter);
        //设置分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(MApplication.getContext(),1));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        nochexbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.unSelectAll();
            }
        });
        allchexbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter. selectAll();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deletingData();
            }
        });
        alldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deleteAllData();
            }
        });
        int a  = view.getHeight();
        Log.i("高度", String.valueOf(a) );
        tvContent.setText(name);

      //  setUI();
//        LinkedList aData = new LinkedList<>();
//
//        for (int i = 0; i <5 ; i++) {
//
//            LinkedList list= new LinkedList();
//            for (int j = 0; j <10; j++) {
//                OrderMess_product orderMess_product = new OrderMess_product(name+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
//                list.add(orderMess_product);
//            }
//
//            OrderMess orderMess = new OrderMess(
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    list
//            );
//            aData.add(orderMess);
//            //  Log.i("---", orderMess.getPostorder_name());
//
//
//        }
//
//
//        CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);
//
//        listview.setAdapter(customAdapter);


        return view;
    }



    @Override
    public void onResume() {
       /* LinkedList aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {

            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }

            OrderMess orderMess = new OrderMess(
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    "南京-"+i+"-" +String.valueOf(Math.random()*100),
                    list
            );
            aData.add(orderMess);
            Log.i("---", orderMess.getPostorder_name());


        }


        CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);

        listview.setAdapter(customAdapter);*/
        super.onResume();
        //showToast("刷新数据1");

      //  setUI();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
      /*  if (fragmentView != null && !hidden) {
            showToast("刷新数据2");
        }*/
        //setUI();
    }

    private boolean isGetData = false;


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


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
        getView();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }




    //用来设置UI，更新UI时重新从数据库获取数据，进行设置
//    public void setUI(){
//        LinkedList aData = new LinkedList<>();
//
//        for (int i = 0; i <5 ; i++) {
//
//            LinkedList list= new LinkedList();
//            for (int j = 0; j <10; j++) {
//                OrderMess_product orderMess_product = new OrderMess_product(name+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
//                list.add(orderMess_product);
//            }
//
//            OrderMess orderMess = new OrderMess(
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
//                    list
//            );
//            aData.add(orderMess);
//            //  Log.i("---", orderMess.getPostorder_name());
//
//
//        }
//
//
//        CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);
//
//        listview.setAdapter(customAdapter);
//    }


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
