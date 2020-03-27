package com.dongjiu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;


import com.dongjiu.MApplication;
import com.dongjiu.R;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.fragment.adapter.CustomAdapter;
import com.dongjiu.fragment.adapter.MsgContentFragmentAdapter;
import com.dongjiu.fragment.adapter.OrderMess;
import com.dongjiu.fragment.adapter.OrderMess_product;
import com.dongjiu.fragment.adapter.UploadFragmentAdapter;
import com.dongjiu.utils.CustomAdapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 发现
 * <p>展示平均分配位置的tab页卡</p>
 */
public class UploadFragment extends Fragment implements CustomAdapt {
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;


//    @BindView(R.id.txt_content)
//    TextView tvContent;
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

   // private UploadFragmentAdapter adapter;
    private List<String> names;

    private MyRecyclerViewAdapter adapter;
    private ArrayList list = new ArrayList<>();



    /**
     * 切换刷新
     */
    protected boolean isCreated = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("---", String.valueOf(Math.random()*10000));
        initData();
        // 标记
        isCreated = true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        ButterKnife.bind(this, view);

//        adapter = new UploadFragmentAdapter(getChildFragmentManager());
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//
//        // 更新适配器数据
//        adapter.setList(names);


        list.clear();
        for (int i = 0; i <15 ; i++) {

            LinkedList list0= new LinkedList();
            for (int j = 0; j <10; j++) {
                com.dongjiu.utils.CustomAdapter.OrderMess_product orderMess_product = new com.dongjiu.utils.CustomAdapter.OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list0.add(orderMess_product);
            }

            com.dongjiu.utils.CustomAdapter.OrderMess orderMess = new com.dongjiu.utils.CustomAdapter.OrderMess(
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
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
       // tvContent.setText();
        return view;
    }

    /**
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isCreated) {
//            setUI();
            return;
        }

        if (isVisibleToUser) {
            setUI();
        }

    }

    //用来设置UI，更新UI时重新从数据库获取数据，进行设置
    public void setUI(){
        list.clear();
        for (int i = 0; i <15 ; i++) {

            LinkedList list0= new LinkedList();
            for (int j = 0; j <10; j++) {
                com.dongjiu.utils.CustomAdapter.OrderMess_product orderMess_product = new com.dongjiu.utils.CustomAdapter.OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list0.add(orderMess_product);
            }
            com.dongjiu.utils.CustomAdapter.OrderMess orderMess = new com.dongjiu.utils.CustomAdapter.OrderMess(
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
                    "南京-"+i+"-"+ String.valueOf(Math.random()*100),
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
//        int a  = view.getHeight();
//        Log.i("高度", String.valueOf(a) );
    }
    private void initData() {
        names = new ArrayList<>();
        names.add("平均1");
        names.add("平均2");
        names.add("平均3");
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

       // setUI();
        Log.i("下载界面+++", "刷新数据");

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
