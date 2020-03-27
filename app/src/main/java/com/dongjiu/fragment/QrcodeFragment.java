package com.dongjiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.dongjiu.AppManager;
import com.dongjiu.BaseActivity;
import com.dongjiu.R;
import com.dongjiu.activity.ScanQrcodeActivity;
import com.dongjiu.fragment.adapter.MsgContentFragmentAdapter;


import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class QrcodeFragment extends Fragment implements CustomAdapt {
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;


    @BindView(R.id.qrcode_select_order)
    TextView qrcode_select_order;
    @BindView(R.id.qrcode_start_scan)
    TextView qrcode_start_scan;
    @BindView(R.id.qrcode_select_product)
    TextView qrcode_select_product;


//    @BindView(R.id.view_pager)
//    ViewPager viewPager;
    private MsgContentFragmentAdapter adapter;
    private List<String> names;

    public static QrcodeFragment newInstance() {
        return new QrcodeFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("---", String.valueOf(Math.random()*10000));
        initData();
        // 标记
        isCreated = true;
    }
    /**
     * 切换刷新
     */
    protected boolean isCreated = false;

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
            setUI();
        }
    }


    //用来设置UI，更新UI时重新从数据库获取数据，进行设置
    public void setUI(){
//        BaseActivity.showToastView(String.valueOf(new Random().nextLong())+"随机数");
//        Intent intent = new Intent(BaseActivity.context, ScanQrcodeActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("orderid","请选择订单");  //key-"sff",通过key得到value-"value值"(String型)
//        bundle.putInt("ordernum", 175);           //key-"iff",value-175
//        intent.putExtras(bundle);            //通过intent将bundle传到另个Activity
//        intent.putExtra(MainActivity.KEY_TITLE,"title");
//        intent.putExtra(MainActivity.KEY_IS_CONTINUOUS,MainActivity.isContinuousScan);
//        BaseActivity.context.startActivity(intent);
//        AppManager.getInstance().killActivity(MainActivity.class);

    }
//    public void replaceFragment(Fragment fragment){
//
//        replaceFragment( R.id.qrcodefragment_1,fragment);
//    }
//
//    public void replaceFragment(@IdRes int id, Fragment fragment) {
//       MainActivity.mFragmentManager.beginTransaction().replace(id, fragment).commit();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =    inflater.inflate(R.layout.fragment_qrcode, container, false);
        ButterKnife.bind(this, view);
       // replaceFragment(CaptureFragment2.newInstance());
//        adapter = new MsgContentFragmentAdapter(getChildFragmentManager());
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//
//        // 更新适配器数据
//        adapter.setList(names);

         // qrcode_select_order
         //qrcode_start_scan
        // qrcode_select_product

//        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
//        captureFragment.setAnalyzeCallback(analyzeCallback);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
       BaseActivity.showToastView(String.valueOf(new Random().nextLong())+"随机数");
//        Intent intent = new Intent(BaseActivity.context, ScanQrcodeActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("orderid","请选择订单");  //key-"sff",通过key得到value-"value值"(String型)
//        bundle.putInt("ordernum", 175);           //key-"iff",value-175
//        intent.putExtras(bundle);            //通过intent将bundle传到另个Activity
//        intent.putExtra(MainActivity.KEY_TITLE,"title");
//        intent.putExtra(MainActivity.KEY_IS_CONTINUOUS,MainActivity.isContinuousScan);
//        BaseActivity.context.startActivity(intent);
//        AppManager.getInstance().killActivity(MainActivity.class);

        qrcode_select_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.showToastView("选择订单");

            }
        });

        qrcode_start_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BaseActivity.showToastView("开始扫描");
                Intent intent = new Intent(getActivity(), ScanQrcodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("orderid", "传递的参数信息");  //key-"sff",通过key得到value-"value值"(String型)
                bundle.putInt("orderidnum", 175);           //key-"iff",value-175
                intent.putExtras(bundle);            //通过intent将bundle传到另个Activity

                startActivity(intent);
               // AppManager.getInstance().killActivity(MainActivity.class);
            }
        });


        qrcode_select_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.showToastView("选择产品");

            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    private void initData() {
//        names = new ArrayList<>();
//        names.add("平均1");
//        names.add("平均2");
//        names.add("平均3");
    }

    @Override
    public void onResume() {
        super.onResume();
        //showToast("刷新数据1");
        Log.i("扫描二维码界面+++", "刷新数据");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
      /*  if (fragmentView != null && !hidden) {
            showToast("刷新数据2");
        }*/

        Log.i("扫描二维码界面+++", "刷新数据");

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
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
