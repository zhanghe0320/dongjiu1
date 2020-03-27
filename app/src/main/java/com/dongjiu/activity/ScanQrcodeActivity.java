package com.dongjiu.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.AppManager;
import com.dongjiu.BaseActivity;
import com.dongjiu.R;
import com.dongjiu.fragment.PickValueView.PickValueView;
import com.dongjiu.fragment.adapter.CustomAdapter;
import com.dongjiu.fragment.adapter.OrderMess;
import com.dongjiu.fragment.adapter.OrderMess_product;
import com.dongjiu.utils.zxing.CaptureFragment;
import com.dongjiu.utils.zxing.CaptureHelper;
import com.dongjiu.utils.zxing.Intents;
import com.dongjiu.utils.zxing.OnCaptureCallback;
import com.dongjiu.utils.zxing.ViewfinderView;
import com.dongjiu.utils.zxing.camera.CameraManager;
import com.dongjiu.utils.zxing.util.StatusBarUtils;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class ScanQrcodeActivity extends BaseActivity implements OnCaptureCallback , CustomAdapt ,PickValueView.onSelectedChangeListener{

    private static final String TAG = "ScanQrcodeActivity";
    private boolean isContinuousScan;
    public static final String KEY_RESULT = Intents.Scan.RESULT;

    private CaptureHelper mCaptureHelper;

    private SurfaceView surfaceView;

    private ViewfinderView viewfinderView;

    private View ivTorch;


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
    @BindView(R.id.toolbar)
    Toolbar toolbar;



    private Dialog dialog;
    private View inflate;
    PickValueView pickString;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_scan_qrcode);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
//        int layoutId = getLayoutId();
//        if(isContentView(layoutId)){
//            setContentView(layoutId);
//        }
//        initUI();

        //isContinuousScan = getIntent().getBooleanExtra(MainActivity.KEY_IS_CONTINUOUS,false);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String a =bundle.get("orderid").toString();
        Log.i(TAG, a);
        initUI();
        Log.i(TAG, "扫码结果");
        qrcodescan_noworder.setText(a);

        LinkedList aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {
            qrcodescan_noworder.getText();
            LinkedList list= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product(a+qrcodescan_noworder.getText()+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }

            OrderMess orderMess = new OrderMess(
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                    a+"南京-"+i+"-"/*+ String.valueOf(Math.random()*100)*/,
                    list
            );
            aData.add(orderMess);
            //  Log.i("---", orderMess.getPostorder_name());


        }

        CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);

        qrcodescan_allmess.setAdapter(customAdapter);

        qrcodescan_productmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // BaseActivity.showToastView("产品信息");
                AlertDialog alert=new AlertDialog.Builder(ScanQrcodeActivity.this).create();//使用AlertDialog类实例化一个对话框并创建对话框
               // alert.setIcon(R.drawable.ic_launcher);//设置对话框的图标
                alert.setTitle("对话框...");//设置对话框的标题
                alert.setMessage("带取消、中立和确定按钮的对话框!!!");//设置要显示的内容

                //添加取消按钮
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(ScanQrcodeActivity.this, "你点击了取消按钮", Toast.LENGTH_SHORT).show();//弹出Toast显示消息提示框
                    }
                });

                //添加确定按钮
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(ScanQrcodeActivity.this, "你点击了确定按钮", Toast.LENGTH_SHORT).show();//弹出Toast显示消息提示框
                    }
                });

                //添加中立按钮
                alert.setButton(DialogInterface.BUTTON_NEUTRAL, "中立", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                    }
                });
                alert.show();//显示对话框、
            }
        });


        qrcodescan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // BaseActivity.showToastView("返回");
//                Intent intent=new Intent(ScanQrcodeActivity.this,MainActivity.class);
//                intent.putExtra("id", 1);
//                startActivity(intent);
                AppManager.getInstance().killActivity(ScanQrcodeActivity.this);
            }
        });


        qrcodescan_selectorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // BaseActivity.showToastView("选择订单");

                //选择对话框
                dialog = new Dialog(ScanQrcodeActivity.this, R.style.ActionSheetDialogStyle);
                View contentView = LayoutInflater.from(ScanQrcodeActivity.this).inflate(R.layout.dialog_cj_num, null);
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
                pickString.setOnSelectedChangeListener(ScanQrcodeActivity.this);
                String[] valueStr = new String[]{"无需餐具", "1人", "2人", "3人", "4人", "5人",
                        "6人", "7人", "8人"};
                pickString.setValueData(valueStr, valueStr[1]);
                dialog.setContentView(contentView);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                layoutParams.width = getResources().getDisplayMetrics().widthPixels;
                contentView.setLayoutParams(layoutParams);
                dialog.getWindow().setGravity(Gravity.BOTTOM);//弹窗位置
                dialog.getWindow().setWindowAnimations(R.style.ActionSheetDialogStyle);//弹窗样式
                dialog.show();//显示弹窗
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




    }
    /**
     * 返回true时会自动初始化{@link #setContentView(int)}，返回为false是需自己去初始化{@link #setContentView(int)}
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

        toolbar = findViewById(R.id.toolbar);
        StatusBarUtils.immersiveStatusBar(this,toolbar,0.2f);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(getIntent().getStringExtra(MainActivity.KEY_TITLE));


        surfaceView = findViewById(R.id.surfaceView);
        viewfinderView = findViewById(R.id.viewfinderView);
        ivTorch = findViewById(R.id.ivFlash);
        ivTorch.setVisibility(View.INVISIBLE);

        isContinuousScan = getIntent().getBooleanExtra(MainActivity.KEY_IS_CONTINUOUS,true);
        Log.i(TAG, String.valueOf(isContinuousScan));
        mCaptureHelper = new CaptureHelper(this,surfaceView,viewfinderView,ivTorch);
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.onCreate();
        mCaptureHelper.vibrate(true)
                .fullScreenScan(true)//全屏扫码
                .supportVerticalCode(true)//支持扫垂直条码，建议有此需求时才使用。
                .supportLuminanceInvert(true)//是否支持识别反色码（黑白反色的码），增加识别率
                .continuousScan(isContinuousScan)
                .autoRestartPreviewAndDecode(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    /**
     * 扫码结果回调
     * @param result 扫码结果
     * @return
     */
    @Override
    public boolean onResultCallback(String result) {
        if(isContinuousScan){
            Log.i(TAG, result);
//            View view_dialog = getLayoutInflater().from(ScanQrcodeActivity.this).inflate(R.layout.activity_alerttext, null);
//
//            AlertDialog alert=new AlertDialog.Builder(ScanQrcodeActivity.this).create();//使用AlertDialog类实例化一个对话框并创建对话框
//            // alert.setIcon(R.drawable.ic_launcher);//设置对话框的图标
//            alert.setCancelable(false);
//            alert.setContentView(view_dialog);
//            TextView title =  alert.getWindow().findViewById(R.id.title);
//            TextView text =  alert.getWindow().findViewById(R.id.text);
//            title.setText("发现新版本!");
//            text.setText("是否更新新版本?");
//            Button ok = (Button)  alert.getWindow().findViewById(R.id.confirm);
//            Button cancle = (Button)  alert.getWindow().findViewById(R.id.cancel);
//
//
//
//            //确认
//            ok.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    BaseActivity.showToastView("开始下载，请等待！");
//                    alert.dismiss();
//
//                }
//            });
//            //取消
//            cancle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onResume();
//                    alert.dismiss();
//
//                }
//            });


//            alert.setTitle("对话框...");//设置对话框的标题
//            alert.setMessage("带取消、中立和确定按钮的对话框!!!");//设置要显示的内容
//
//            //添加取消按钮
//            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    // TODO Auto-generated method stub
//                    Toast.makeText(ScanQrcodeActivity.this, "你点击了取消按钮", Toast.LENGTH_SHORT).show();//弹出Toast显示消息提示框
//                }
//            });
//
//            //添加确定按钮
//            alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    // TODO Auto-generated method stub
//                    Toast.makeText(ScanQrcodeActivity.this, "你点击了确定按钮", Toast.LENGTH_SHORT).show();//弹出Toast显示消息提示框
//                }
//            });
//
//            //添加中立按钮
//            alert.setButton(DialogInterface.BUTTON_NEUTRAL, "中立", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    // TODO Auto-generated method stub
//                }
//            });
//            alert.show();//显示对话框、


            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(ScanQrcodeActivity.this);
            builder.setCancelable(false);
            builder.setTitle("普通对话框");// 设置标题
            // builder.setIcon(R.drawable.ic_launcher);//设置图标
            builder.setMessage("基本信息："+result+"\n"
                               +"产品编号："+result);// 为对话框设置内容
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

                                LinkedList aData = new LinkedList<>();

            for (int i = 0; i <5 ; i++) {
                qrcodescan_noworder.getText();
                LinkedList list= new LinkedList();
                for (int j = 0; j <10; j++) {
                    OrderMess_product orderMess_product = new OrderMess_product(result+qrcodescan_noworder.getText()+"董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                    list.add(orderMess_product);
                }

                OrderMess orderMess = new OrderMess(
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-" /*+ String.valueOf(Math.random()*100)*/,
                        result+"南京-"+i+"-"/*+ String.valueOf(Math.random()*100)*/,
                        list
                );
                aData.add(orderMess);
                //  Log.i("---", orderMess.getPostorder_name());


            }

            CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);

            qrcodescan_allmess.setAdapter(customAdapter);
                    mCaptureHelper.restartPreviewAndDecode();//重新启动扫码和解码器
                }
            });
            builder.create().show();// 使用show()方法显示对话框
        }

        return false;
    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.ivLeft:
                onBackPressed();
                break;
        }
    }


    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate(String result ) {

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

        CustomAdapter customAdapter = new CustomAdapter(aData, MainActivity.context);

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
