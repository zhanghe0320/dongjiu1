package com.dongjiu.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.AppManager;
import com.dongjiu.BaseActivity;
import com.dongjiu.MApplication;
import com.dongjiu.R;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.activity.ScanQrcodeActivity;
import com.dongjiu.fragment.PickValueView.PickValueView;
import com.dongjiu.fragment.adapter.CustomAdapter;
import com.dongjiu.fragment.adapter.MsgContentFragmentAdapter;
import com.dongjiu.fragment.adapter.OrderMess;
import com.dongjiu.fragment.adapter.OrderMess_product;
import com.dongjiu.utils.WebTool.WebTool;
import com.dongjiu.utils.zxing.CaptureHelper;
import com.dongjiu.utils.zxing.Intents;
import com.dongjiu.utils.zxing.OnCaptureCallback;
import com.dongjiu.utils.zxing.ViewfinderView;
import com.dongjiu.utils.zxing.camera.CameraManager;
import com.dongjiu.utils.zxing.util.StatusBarUtils;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class QrcodeFragment2 extends Fragment implements CustomAdapt, OnCaptureCallback,PickValueView.onSelectedChangeListener {
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;


//    @BindView(R.id.qrcode_select_order)
//    TextView qrcode_select_order;
//    @BindView(R.id.qrcode_start_scan)
//    TextView qrcode_start_scan;
//    @BindView(R.id.qrcode_select_product)
//    TextView qrcode_select_product;

    private static final String TAG = "ScanQrcodeActivity";
    private boolean isContinuousScan;
    public static final String KEY_RESULT = Intents.Scan.RESULT;

    private CaptureHelper mCaptureHelper;

    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;

    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;

    @BindView(R.id.ivFlash)
    View ivTorch;

    View mRootView;
    @BindView(R.id.qrcodescan_productmess)
    TextView qrcodescan_productmess;//产品信息
    @BindView(R.id.qrcodescan_allmess)
    ListView qrcodescan_allmess;//列表信息
    @BindView(R.id.qrcodescan_back)
    TextView qrcodescan_back;//返回
    @BindView(R.id.qrcodescan_selectorder)
    TextView qrcodescan_selectorder;//选择订单
    @BindView(R.id.qrcodescan_selectproduct)
    TextView qrcodescan_selectproduct;//选择产品

    @BindView(R.id.qrcodescan_noworder)
    TextView qrcodescan_noworder;//现在订单编号

    @BindView(R.id.tvTitle)
    TextView tvTitle;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;



    private Dialog dialog;
    private View inflate;
    PickValueView pickString;


    //    @BindView(R.id.view_pager)
//    ViewPager viewPager;
    private MsgContentFragmentAdapter adapter;
    private List<String> names;

    public static QrcodeFragment2 newInstance() {
        return new QrcodeFragment2();
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
            //initUI();
           // mCaptureHelper.onResume();
            mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
        }else{
          //  mCaptureHelper.onPause();
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


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =    inflater.inflate(R.layout.fragment_qrcode2, container, false);
        ButterKnife.bind(this, view);
        //initUI();
        Log.i(TAG, "onCreateView: ");
        Bundle bundle1=QrcodeFragment2.this.getArguments();
        String orderid= "0";
        if (bundle1!=null){//切记要判断内容是否为空，否则程序运行getString方法会报空指针的错误。
            qrcodescan_noworder.setText(bundle1.getString("orderid"));//msga在oneFragment设置好的key值，在此处获取传递过来的内容。
           // textView2.setText(bundle1.getString("msgg"));
            orderid = bundle1.getString("orderid");
        }

        //qrcodescan_noworder.setText(a);
        // toolbar = view.findViewById(R.id.toolbar);
        //StatusBarUtils.immersiveStatusBar(getActivity(),toolbar,0.2f);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(getActivity().getIntent().getStringExtra(MainActivity.KEY_TITLE));


        surfaceView = view.findViewById(R.id.surfaceView);
        viewfinderView = view.findViewById(R.id.viewfinderView);
        ivTorch = view.findViewById(R.id.ivFlash);
        ivTorch.setVisibility(View.INVISIBLE);

        isContinuousScan = getActivity().getIntent().getBooleanExtra(MainActivity.KEY_IS_CONTINUOUS,true);
        Log.i(TAG, String.valueOf(isContinuousScan));
        mCaptureHelper = new CaptureHelper(this,surfaceView,viewfinderView,ivTorch);
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.onCreate();
        mCaptureHelper.vibrate(true)
                .fullScreenScan(true)//全屏扫码
                .supportVerticalCode(true)//支持扫垂直条码，建议有此需求时才使用。
                .supportLuminanceInvert(true)//是否支持识别反色码（黑白反色的码），增加识别率
                .continuousScan(isContinuousScan)
                .supportAutoZoom(false)//对焦
                .autoRestartPreviewAndDecode(false);


        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
//        bundle.getChar("orderid","a");
//        String a =bundle.get("orderid","a");
//        Log.i(TAG, a);
        //String a = "初始化数据";

        LinkedList aData = new LinkedList<>();
        for (int i = 0; i <5 ; i++) {
            qrcodescan_noworder.getText();
            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product(orderid+qrcodescan_noworder.getText()+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }

            OrderMess orderMess = new OrderMess(
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    orderid+"南京-"+i+"-"/*+ String.valueOf(Math.random()*100)*/,
                    list
            );
            aData.add(orderMess);
            //  Log.i("---", orderMess.getPostorder_name());


        }

        CustomAdapter customAdapter = new CustomAdapter(aData, MApplication.getContext());

        qrcodescan_allmess.setAdapter(customAdapter);




        qrcodescan_productmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BaseActivity.showToastView("产品信息");
                AlertDialog alert=new AlertDialog.Builder(getContext()).create();//使用AlertDialog类实例化一个对话框并创建对话框
                // alert.setIcon(R.drawable.ic_launcher);//设置对话框的图标
                alert.setTitle("对话框...");//设置对话框的标题
                alert.setMessage("带取消、中立和确定按钮的对话框!!!");//设置要显示的内容

                //添加取消按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), "你点击了取消按钮", Toast.LENGTH_SHORT).show();//弹出Toast显示消息提示框
                    }
                });

                //添加确定按钮
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(), "你点击了确定按钮", Toast.LENGTH_SHORT).show();//弹出Toast显示消息提示框
                    }
                });

                //添加中立按钮
                alert.setButton(DialogInterface.BUTTON_NEUTRAL, "中立", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                    }
                });
                boolean a = getActivity().isFinishing();
//                if(a){
//                    BaseActivity.showToastView("已停止");
//                }
//                if(alert != null && alert.isShowing()) {
                    alert.show();//显示对话框
               // }


            }
        });


        qrcodescan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.showToastView("返回");
//                Intent intent=new Intent(BaseActivity.context,MainActivity.class);
//                intent.putExtra("id", 1);
//                startActivity(intent);

                //AppManager.getInstance().killActivity(ScanQrcodeActivity.this);
//                if(onButtonClick!=null){
//                    onButtonClick.onClick(qrcodescan_back);
//                }
               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.queryframent,new QueryFragment());

//                MainActivity.mFragmentManager
//                        .beginTransaction()
//                        //替换为TwoFragment
//                        .replace(R.id.mainfragment,new MainFragment())
//                        .commit();
                MainActivity.viewPager.setCurrentItem(0, false);
//                getFragmentManager().beginTransaction().replace(R.id.queryframent,new QueryFragment())
//                        .addToBackStack(null)
//                        .commit();


            }
        });


        qrcodescan_selectorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BaseActivity.showToastView("选择订单");

                //选择对话框
                dialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
                View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cj_num, null);
                //获取组件
                TextView tv_cancel = contentView.findViewById(R.id.tv_cancel);
                pickString = contentView.findViewById(R.id.pickString);
                //获取Dialog的监听
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                pickString.setOnSelectedChangeListener(QrcodeFragment2.this);

//                pickString.setOnSelectedChangeListener(new PickValueView.onSelectedChangeListener() {
//                    @Override
//                    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
//                        qrcodescan_noworder = view.findViewById(R.id.qrcodescan_noworder);
//                        qrcodescan_noworder.setText(rightValue.toString());
//
//                        LinkedList aData = new LinkedList<>();
//                        String a = rightValue.toString();
//                        for (int i = 0; i <5 ; i++) {
//                            qrcodescan_noworder.getText();
//                            LinkedList list= new LinkedList();
//                            for (int j = 0; j <10; j++) {
//                                OrderMess_product orderMess_product = new OrderMess_product(a+qrcodescan_noworder.getText()+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
//                                list.add(orderMess_product);
//                            }
//
//                            OrderMess orderMess = new OrderMess(
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
//                                    a+"南京-"+i+"-"/*+ String.valueOf(Math.random()*100)*/,
//                                    list
//                            );
//                            aData.add(orderMess);
//                            //  Log.i("---", orderMess.getPostorder_name());
//
//
//                        }
//
//                        CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);
//                        qrcodescan_allmess.setAdapter(customAdapter);
//
//                    }
//                });
                String[] valueStr = new String[]{"选择订单", "1人", "2人", "3人", "4人", "5人",
                        "6人", "7人", "8人"};
                Log.i(TAG, "onClick: "+valueStr.toString());
                pickString.setValueData(valueStr, valueStr[1]);
                dialog.setContentView(contentView);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                layoutParams.width = getResources().getDisplayMetrics().widthPixels;
                contentView.setLayoutParams(layoutParams);
                dialog.getWindow().setGravity(Gravity.BOTTOM);//弹窗位置
                dialog.getWindow().setWindowAnimations(R.style.ActionSheetDialogStyle);//弹窗样式
              //  if(dialog != null && dialog.isShowing()) {
                    dialog.show();
               // }
                //dialog.show();//显示弹窗
            }
        });

//        qrcodescan_allmess.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                BaseActivity.showToastView("所有信息");
//                return false;
//            }
//        });

        qrcodescan_selectproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.showToastView("选择产品");

            }
        });

        return view;
    }

    private OnButtonClick onButtonClick;

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
    public interface OnButtonClick{
        public void onClick(View view);
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
        mCaptureHelper.onResume();
        mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
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
        mCaptureHelper.onPause();


    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();

    }



    /**
     * 返回true时会自动初始化{@link#setContentView(int)}，返回为false是需自己去初始化{@link#setContentView(int)}
     * @param layoutId
     * @return 默认返回true
     */
    public boolean isContentView(@LayoutRes int layoutId){
        return true;
    }

    /**
     * 布局id
     * @return
     */
    public int getLayoutId(){
        return R.layout.zxl_capture;
    }

    /**
     * {@link #viewfinderView} 的 ID
     * @return
     */
    public int getViewfinderViewId(){
        return R.id.viewfinderView;
    }


    /**
     * 预览界面{@link #surfaceView} 的ID
     * @return
     */
    public int getSurfaceViewId(){
        return R.id.surfaceView;
    }

    /**
     * 获取 {@link #ivTorch} 的ID
     * @return  默认返回{@code R.id.ivTorch}, 如果不需要手电筒按钮可以返回0
     */
    public int getIvTorchId(){
        return R.id.ivTorch;
    }

    /**
     * Get {@link CaptureHelper}
     * @return {@link #mCaptureHelper}
     */
    public CaptureHelper getCaptureHelper(){
        return mCaptureHelper;
    }


    /**
     * Get {@link CameraManager} use {@link #getCaptureHelper()#getCameraManager()}
     * @return {@link #mCaptureHelper#getCameraManager()}
     */
    @Deprecated
    public CameraManager getCameraManager(){
        return mCaptureHelper.getCameraManager();
    }

    private void initUI(){

      //  toolbar = viewfinderView.findViewById(R.id.toolbar);
       // StatusBarUtils.immersiveStatusBar(getActivity(),toolbar,0.2f);
        tvTitle = viewfinderView.findViewById(R.id.tvTitle);
        tvTitle.setText(getActivity().getIntent().getStringExtra(MainActivity.KEY_TITLE));


        surfaceView = viewfinderView.findViewById(R.id.surfaceView);
        viewfinderView = viewfinderView.findViewById(R.id.viewfinderView);
        ivTorch = viewfinderView.findViewById(R.id.ivFlash);
        ivTorch.setVisibility(View.INVISIBLE);

        isContinuousScan = getActivity().getIntent().getBooleanExtra(MainActivity.KEY_IS_CONTINUOUS,true);
        Log.i(TAG, String.valueOf(isContinuousScan));
        mCaptureHelper = new CaptureHelper(this,surfaceView,viewfinderView,null);
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.onCreate();
        mCaptureHelper.vibrate(true)
                .fullScreenScan(true)//全屏扫码
                .supportVerticalCode(true)//支持扫垂直条码，建议有此需求时才使用。
                .supportLuminanceInvert(true)//是否支持识别反色码（黑白反色的码），增加识别率
                .continuousScan(isContinuousScan)
                .autoRestartPreviewAndDecode(false);

    }







/**
        * 获取重定向地址
 *
         * @param path
 * @return
         * @throws Exception
 */
    public static  String getRedirectUrl(String path) {
        String url = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(5000);
            url = conn.getHeaderField("Location");
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 接收扫码结果回调
     * @param result 扫码结果
     * @return 返回true表示拦截，将不自动执行后续逻辑，为false表示不拦截，默认不拦截
     */
    @Override
    public boolean onResultCallback(String result) {

        if(MainActivity.viewPager.getCurrentItem()==2) {//检测当前显示的界面是否在扫码界面。非扫码界面不解析。不做处理，不重置解码器等操作
            if (isContinuousScan) {

                // 箱码扫码得到的信息
                //必须保证联网，否则失败，无法获取信息
               // https://c.l66.co/app/s?c=15483316925461603937195561779965096
                //包含特定信息的采用截取方式获取相关信息。其他的处理方式待定
                if(result.contains("c.l66.co/app")){
                    String ss= WebTool.GetString(result);
                    if(ss==""||ss==null){
                        BaseActivity.showToastView("二维码失效或扫码数据出错，请尝试重新扫码！");
                        mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
                        Log.i(TAG, "二维码失效或扫码数据出错，请尝试重新扫码！");
                    }else{
                        Log.i(TAG, ss);


                        //通过截取这种方式不可取 ，第三方不在提供支持，获取的数据还是有问题。数据时效的问题
                        // 截取先获取的数据
                        //<script type="text/javascript"> window.location.href='
                        // https://www.qccvas.com/scanweb/index.html?bizid=
                        // dongjiu&vid=1&cid=176&tid=8889&pid=144932&tidLength=4&pidLength=12&tpid=8889000000144932&
                        // scantype=0&counterfeit=&codeType=0&scanmode=0&lang=&lon=&lat=&model=linux; u; android 9;
                        // bnd-al10 build/honorbnd-al1&userid=-1&imei=&osversion=&type=&isBlacklist=&userpoint=&redPacket=&addr=null';</script>

                        //截取之后的字符
                        String userId = "";
                        String userIdJiequ = ss.substring(ss.indexOf("https"));//  开始截取代码信息
                        //截取#之前的字符串
                        //String str = "sdfs#d";
                        userId=  userIdJiequ.substring(0, userIdJiequ.indexOf("';</script>"));//
                        Log.i(TAG, userId);
                        // 截取后的最终结果信息
                        // https://www.qccvas.com/scanweb/index.html?bizid=
                        // dongjiu&vid=1&cid=176&tid=8889&pid=144932&tidLength=4&pidLength=12&tpid=8889000000144932     // 可能使用部分
                        // &scantype=0&counterfeit=&codeType=0&scanmode=0&lang=&lon=&lat=&model=linux;u; android 9;
                        // bnd-al10 build/honorbnd-al1&userid=-1&imei=&osversion=&type=&isBlacklist=&userpoint=&redPacket=&addr=null


                        String vid="";
                        String cid="";
                        String tid="";
                        String pid="";
                        String tidLength="";
                        String pidLength="";
                        String tpid="";

                        String[] strarray=userId.split("&");
                        for (int i = 0; i < strarray.length; i++){
                            Log.i(TAG,strarray[i]);
                            if(strarray[i].contains("vid=")){
                                vid= strarray[i].substring(4);
                            }else if(strarray[i].contains("cid=")){
                                cid= strarray[i].substring(4);
                            }else if(strarray[i].contains("tid=")){
                                tid= strarray[i].substring(4);
                            }else if(strarray[i].contains("pid=")){
                                if(strarray[i].contains("t")){
                                    tpid= strarray[i].substring(5);

                                }else {
                                    pid= strarray[i].substring(4);
                                }

                            }else if(strarray[i].contains("tidLength=")){
                                tidLength= strarray[i].substring(10);
                            }else if(strarray[i].contains("pidLength=")){
                                pidLength= strarray[i].substring(10);
                            }
//                            else if(strarray[i].contains("tpid=")){
//                                tpid= strarray[i].substring(5);
//
//                            }
                        }
//                        String vid="";
//                        String cid="";
//                        String cid="";
//                        String pid="";
//                        String tidLength="";
//                        String pidLength="";
//                        String tpid="";
                        Log.i(TAG,"vid="+vid+"/"+
                                "cid="+cid+"/"+
                                "tid="+tid+"/"+
                                "pid="+pid+"/"+
                                "tidLength="+tidLength+"/"+
                                "pidLength="+pidLength+"/"+
                                "tpid="+tpid+"/");



                        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.context);
                        builder.setCancelable(false);
                        builder.setTitle("普通对话框");// 设置标题
                        // builder.setIcon(R.drawable.ic_launcher);//设置图标
                        builder.setMessage("基本信息：tpid=" + tpid + "\n"
                                + "产品编号：tpid=" + tpid);// 为对话框设置内容
                        // 为对话框设置取消按钮
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub
                                mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
                            }
                        });
                        // 为对话框设置中立按钮
                        builder.setNeutralButton("删除", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub
                                BaseActivity.showToastView("您点击了中立按钮");
                                mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
                            }
                        });
                        // 为对话框设置确定按钮
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub
                                String vid="";
                                String cid="";
                                String tid="";
                                String pid="";
                                String tidLength="";
                                String pidLength="";
                                String tpid="";
                                String userId = "";
                                String userIdJiequ = ss.substring(ss.indexOf("https"));//  开始截取代码信息
                                //截取#之前的字符串
                                //String str = "sdfs#d";
                                userId=  userIdJiequ.substring(0, userIdJiequ.indexOf("';</script>"));//

                                String[] strarray=userId.split("&");
                                for (int i = 0; i < strarray.length; i++){
                                    Log.i(TAG,strarray[i]);
                                    if(strarray[i].contains("vid=")){
                                        vid= strarray[i].substring(4);
                                    }else if(strarray[i].contains("cid=")){
                                        cid= strarray[i].substring(4);
                                    }else if(strarray[i].contains("tid=")){
                                        tid= strarray[i].substring(4);
                                    }else if(strarray[i].contains("pid=")){
                                        if(strarray[i].contains("t")){
                                            tpid= strarray[i].substring(5);

                                        }else {
                                            pid= strarray[i].substring(4);
                                        }

                                    }else if(strarray[i].contains("tidLength=")){
                                        tidLength= strarray[i].substring(10);
                                    }else if(strarray[i].contains("pidLength=")){
                                        pidLength= strarray[i].substring(10);
                                    }
//                            else if(strarray[i].contains("tpid=")){
//                                tpid= strarray[i].substring(5);
//
//                            }
                                }
//                        String vid="";
//                        String cid="";
//                        String cid="";
//                        String pid="";
//                        String tidLength="";
//                        String pidLength="";
//                        String tpid="";
                                Log.i(TAG,"vid="+vid+"/"+
                                        "cid="+cid+"/"+
                                        "tid="+tid+"/"+
                                        "pid="+pid+"/"+
                                        "tidLength="+tidLength+"/"+
                                        "pidLength="+pidLength+"/"+
                                        "tpid="+tpid+"/");

                                LinkedList aData = new LinkedList<>();

                                for (int i = 0; i < 5; i++) {
                                    qrcodescan_noworder.getText();
                                    LinkedList list = new LinkedList();
                                    for (int j = 0; j < 10; j++) {
                                        OrderMess_product orderMess_product = new OrderMess_product(tpid + qrcodescan_noworder.getText() + "董酒-" + i + "-" + j, 500 + "箱", "已经扫码" + 300);
                                        list.add(orderMess_product);
                                    }

                                    OrderMess orderMess = new OrderMess(
                                            tpid + "南京-" + i + "-" + String.valueOf(Math.random() * 100),
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-" /*+ String.valueOf(Math.random()*100)*/,
                                            tpid + "南京-" + i + "-"/*+ String.valueOf(Math.random()*100)*/,
                                            list
                                    );
                                    aData.add(orderMess);
                                    //  Log.i("---", orderMess.getPostorder_name());


                                }

                                CustomAdapter customAdapter = new CustomAdapter(aData, MApplication.getContext());

                                qrcodescan_allmess.setAdapter(customAdapter);
                                mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
                            }
                        });

                        if (builder != null && builder.create().isShowing()) {
                            builder.create().dismiss();
                        } else {
                            builder.create().show();// 使用show()方法显示对话框
                        }

                    }

                }else{
                    BaseActivity.showToastView("其他处理方式");

                    mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
                }





            }

        }
      //  mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器

        return false;
    }

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        String selectedStr = (String) leftValue;
        qrcodescan_noworder.setText(selectedStr);


        LinkedList aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {
            qrcodescan_noworder.getText();
            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product(selectedStr+qrcodescan_noworder.getText()+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }

            OrderMess orderMess = new OrderMess(
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    selectedStr+"南京-"+i+"-"/*+ String.valueOf(Math.random()*100)*/,
                    list
            );
            aData.add(orderMess);
            //  Log.i("---", orderMess.getPostorder_name());


        }

        CustomAdapter customAdapter = new CustomAdapter(aData, MApplication.getContext());

        qrcodescan_allmess.setAdapter(customAdapter);

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
     * <p>  720 * 1280   720 / 1.5 =533
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
