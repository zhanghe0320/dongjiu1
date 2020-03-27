package com.dongjiu.fragment;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;



import com.dongjiu.R;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.fragment.adapter.MsgContentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 消息
 * <p>在这个界面中实现类似今日头条的头部tab</p>
 */
public class MainFragment extends Fragment implements CustomAdapt {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

/*    @BindView(R.id.listview)
    ListView listview;*/

    private MsgContentFragmentAdapter adapter;
    private List<String> names;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        adapter = new MsgContentFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // 更新适配器数据
        adapter.setList(names);
//        LinkedList aData = new LinkedList<>();
//
//        for (int i = 0; i <5 ; i++) {
//
//            LinkedList list= new LinkedList();
//
//            OrderMess orderMess = new OrderMess(
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    "南京-"+i,
//                    list
//            );
//            aData.add(orderMess);
//            Log.i("---", orderMess.getPostorder_name());
//
//
//        }
//
//
//        CustomAdapter customAdapter = new CustomAdapter(aData,MainActivity.context);
//
//        listview.setAdapter(customAdapter);



        return view;
    }

    private void initData() {
        names = new ArrayList<>();
        names.add("主页");
        names.add("未扫码");
        names.add("扫码中");
        names.add("已完成");
//        names.add("小说");
//        names.add("娱乐");
//        names.add("问答");
//        names.add("图片");
//        names.add("科技");
//        names.add("懂车帝");
//        names.add("体育");
//        names.add("财经");
//        names.add("军事");
//        names.add("国际");
//        names.add("健康");
    }


    @Override
    public void onResume() {
        super.onResume();
        //showToast("刷新数据1");
        Log.i("主界面+++", "刷新数据");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
      /*  if (fragmentView != null && !hidden) {
            showToast("刷新数据2");
        }*/

        Log.i("主界面+++", "刷新数据");

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

    }

    @Override
    public void onStop() {
        //this.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
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
