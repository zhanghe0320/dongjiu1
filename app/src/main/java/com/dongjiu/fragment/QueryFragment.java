package com.dongjiu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.Main2Activity;
import com.dongjiu.R;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.fragment.adapter.CustomAdapter;
import com.dongjiu.fragment.adapter.OrderMess;
import com.dongjiu.fragment.adapter.OrderMess_product;
import com.dongjiu.utils.commonUtils.KeyBoardUtils;

import java.util.LinkedList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;


/**
 * 我
 */
public class QueryFragment extends Fragment implements CustomAdapt {

    public QueryFragment() {
    }

    @BindView(R.id.edite_ordernum)
    EditText edite_ordernum;
    @BindView(R.id.queryfromdb)
    TextView queryfromdb;
    @BindView(R.id.queryfragment_mess)
    LinearLayout queryfragment_mess;

    @BindView(R.id.queryfragment_listview)
    ListView queryfragment_listview;




    /**
     * 切换刷新
     */
    protected boolean isCreated = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Bundle bundle = getArguments();
//        name = bundle.getString("name");
//        if (name == null) {
//            name = "参数非法";
//        }

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



    //用来设置UI，更新UI时重新从数据库获取数据，进行设置
    public void setUI(){

 //       edite_ordernum.setText("");
//        aData.clear();
//        customAdapter= new CustomAdapter(aData, MainActivity.context);
//        customAdapter.notifyDataSetChanged();
//        queryfragment_listview.setAdapter(customAdapter);



        edite_ordernum .setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    // 此处为得到焦点时的处理内容
//                } else {
//                    // 此处为失去焦点时的处理内容
////                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//                    InputMethodManager imm =  (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    if(imm != null) {
//                        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//                    }
//                }
                if(hasFocus){
                    KeyBoardUtils.openKeybord(getView());

                }else {

                }
            }
        });
    }
    public void showSoftInputFromWindow(EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }
    //    @BindView(R.id.queryback)
//    TextView queryback;
    LinkedList aData = new LinkedList<>();
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.i("---", String.valueOf(Math.random()*10000));
        final View view = inflater.inflate(R.layout.fragment_query, container, false);
        ButterKnife.bind(this, view);


        /** 
             * Fragment中，注册 
             * 接收MainActivity的Touch回调的对象 
             * 重写其中的onTouchEvent函数，并进行该Fragment的逻辑处理 
             */
//        MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener() {
//            @Override
//            public void onTouchEvent(MotionEvent event) {
//                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//
//                   // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）  
//                    View v = getCurrentFocus();
//
//                    if (isShouldHideInput(v, ev)) {
//                        hideSoftInput(v.getWindowToken());
//                    }
//                }
//            }
//        };  // 将myTouchListener注册到分发列表  
//        ((MainActivity) this.getActivity()).registerMyTouchListener(myTouchListener);
        // 弹窗软键盘
      //  getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
      //  edite_ordernum.requestFocus();  // 注意不能丢，否则“搜索”还是默认的回车，并 edittext.setOnKeyListener() 监听无效

        edite_ordernum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                showSoftInputFromWindow(edite_ordernum);
                //
                //                if(!edite_ordernum.isInputMethodTarget()){
                //                    edite_ordernum.clearFocus();
                //                    // ...other actions
                //                }
               // BaseActivity.showToastView("出现软键盘");
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.queryfragment, new DownloadFragment(), null)
//                        .addToBackStack(null)
//                        .commit();
              //  InputMethodManager imm = (InputMethodManager)BaseActivity.context.getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);    //InputMethodManager.SHOW_FORCED

                edite_ordernum.setText("");

            }
        });

        edite_ordernum .setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    // 此处为得到焦点时的处理内容
//                } else {
//                    // 此处为失去焦点时的处理内容
////                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//                    InputMethodManager imm =  (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    if(imm != null) {
//                        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//                    }
//                }
                    if(hasFocus){
                        KeyBoardUtils.openKeybord(getView());

                    }else {
                        KeyBoardUtils.closeKeybord(getView());

                    }
            }
        });

        queryfromdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtils.closeKeybord(getView());

                edite_ordernum.getText();
                BaseActivity.showToastView(edite_ordernum.getText() + "0");
                aData.clear();
                for (int i = 0; i <5 ; i++) {

                    LinkedList list= new LinkedList();
                    for (int j = 0; j <10; j++) {
                        OrderMess_product orderMess_product = new OrderMess_product(edite_ordernum.getText()+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                        list.add(orderMess_product);
                    }
                    int a =new Random().nextInt(100);
                    OrderMess orderMess = new OrderMess(
                            "南京-"+i+"-" + String.valueOf(Math.random()*100),
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
                    //  Log.i("---", orderMess.getPostorder_name());


                }


                CustomAdapter customAdapter = new CustomAdapter(aData, MApplication.getContext());

                queryfragment_listview.setAdapter(customAdapter);
            }
        });

        queryfragment_listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KeyBoardUtils.closeKeybord(getView());
                return false;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //showToast("刷新数据1");
        Log.i("查询界面+++", "刷新数据");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
      /*  if (fragmentView != null && !hidden) {
            showToast("刷新数据2");
        }*/

        Log.i("查询界面+++", "刷新数据");

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
