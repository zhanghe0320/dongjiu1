package com.dongjiu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import org.bouncycastle.crypto.tls.MACAlgorithm;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 消息内容页
 */
public class MsgContentFragment extends Fragment implements CustomAdapt {
    @BindView(R.id.txt_content)
    TextView tvContent;

    @BindView(R.id.listview)
    ListView listview ;

    private String name;



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
           setUI();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_content, container, false);
        ButterKnife.bind(this, view);
        int a  = view.getHeight();
        Log.i("高度", String.valueOf(a) );
        tvContent.setText(name);

      //  setUI();
        LinkedList aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {

            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product(name+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }

            OrderMess orderMess = new OrderMess(
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    list
            );
            aData.add(orderMess);
            //  Log.i("---", orderMess.getPostorder_name());


        }


        CustomAdapter customAdapter = new CustomAdapter(aData, MApplication.getContext());

        listview.setAdapter(customAdapter);


        return view;
    }



    @Override
    public void onResume() {

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
    public void setUI(){
        LinkedList aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {

            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product(name+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }

            OrderMess orderMess = new OrderMess(
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    name+"南京-"+i+"-" + String.valueOf(Math.random()*100),
                    list
            );
            aData.add(orderMess);
            //  Log.i("---", orderMess.getPostorder_name());


        }


        CustomAdapter customAdapter = new CustomAdapter(aData, MApplication.getContext());

        listview.setAdapter(customAdapter);
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
        return 720;
    }
}
