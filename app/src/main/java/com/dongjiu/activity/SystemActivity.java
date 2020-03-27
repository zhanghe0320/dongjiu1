package com.dongjiu.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.AppManager;
import com.dongjiu.BaseActivity;
import com.dongjiu.R;
import com.dongjiu.LoginActivity;
import com.dongjiu.utils.ConstantValue;
import com.dongjiu.utils.GetName;

import junit.runner.Version;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import me.jessyan.autosize.internal.CustomAdapt;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SystemActivity extends BaseActivity implements CustomAdapt {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
    }




    //设备 管理信息 进行货架的产品的更新
    public void updateapp(View view) {
        // Log.i(TAG, "onCreate: "+BaseActivity.getLocalVersionName()+"-----------------------"+BaseActivity.getLocalVersion());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        //SystemValuesDao systemValuesDao = new SystemValuesDao(BaseActivity.context);
        //post方式提交的数据
        FormBody formBody = new FormBody.Builder()
                .add("55","55"/*ConstantValue.equipment_host, systemValuesDao.dbQueryOneByName(ConstantValue.localNumber).getValue()*/)
                .build();

        RequestBody requestBody=RequestBody.create(ConstantValue.MEDIA_TYPE_NORAML_FORM,
                "ConstantValue.equipmentCode"+"="+ GetName.getuserName());
        Request request = new Request.Builder()
                .url(/*ConstantValue.https+xmppConnect.getPHPIpAddress() + ConstantValue.phpgetGoodsImgUrl*/
                        /* ConstantValue.https+ xmppConnect.getPHPIpAddress()+ConstantValue.javaGetAllPhotodo*/
                       "ConstantValue.https"+ "GetName.getPHPIpAddress()"+"ConstantValue.JavaGetEdition"
                        // "http://192.168.0.161:8080/Taste/getEdition.do"
                )//请求的url
                // .get()
                .build();
        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new okhttp3.Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                //     Log.i(TAG, "onFailure: 连接失败");
//                Message message = Message.obtain();
//                message.what=ConstantValue.XMPP_MSG_PACKET_CALLBACK;
//                mHandler.sendMessage(message);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == ConstantValue.Okhttp_Back_Success) {

                    ResponseBody responseBody = response.body();
                    String s = responseBody.string();


                    JSONObject myJsonObject = null;
                    //       Log.i(TAG, "onResponse: "+s);


                    JSONObject myJsonObject0 = null;
                    JSONArray jsonArray = null;
                    try {
                        myJsonObject = new JSONObject(s);


                        //  String code = myJsonObject.getString("code");
                        //  String msg = myJsonObject.getString("msg");
                        String data = myJsonObject.getString("data");
                        //   String count = myJsonObject.getString("count");



                        //    Log.i(TAG, "onResponse: 视频全部信息"+s);
                        //   Log.i(TAG, "onResponse: "+code+msg+count+"------"+data);
                        //    myJsonObject0 = new JSONObject(data);

                        //  Log.i(TAG, "onResponse: "+list.toString());
                        String list = myJsonObject.getString("data");
                        jsonArray = new JSONArray(list);
                        //         Log.i(TAG, "onResponse: "+jsonArray.length()+"-------"+jsonArray.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {


                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String editionIP = jsonObject.getString("editionIP");
                            String edition = jsonObject.getString("edition");

                            //imagesDao.dbDeleteImage(imgUrl,equipmentbase);


                            //apkIP=editionIP;
                            //          Log.i(TAG, "onResponse: "+edition+"---"+editionIP);
                            if(edition.equals("2")){
                                //   getPersimmions();
                                // checkUpdate();
                                //   showDialogUpdate();
                                //             Log.i(TAG, "onResponse: 开始下载");
//                                Message message = Message.obtain();
//                                message.what=ConstantValue.XMPP_MSG_CHAT_CALLBACK;
//                                mHandler.sendMessage(message);

                            }else if(edition.equals("1")){
                                // Toast.makeText(getApplicationContext(), "下载完成！", Toast.LENGTH_LONG).show();
                                //     Log.i(TAG, "onResponse: 最新版本");
//                                Message message = Message.obtain();
//                                message.what=ConstantValue.XMPP_MSG_CONNECT_INFO;
//                                mHandler.sendMessage(message);
                            }


                        }

                    } catch (Exception e) {
                    }

                }
            }
        });

    }


    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case ConstantValue.XMPP_MSG_CONNECT_INFO:
//                    BaseActivity.showToastView("这是最新版本！无需更新！");
//                    break;
//
//                case ConstantValue.XMPP_MSG_CHAT_CALLBACK:
//
//                    showDialogUpdate();
//
//
//                    break;
//                case ConstantValue.XMPP_MSG_PACKET_CALLBACK:
//
//                    BaseActivity.showToastView("下载失败！请重新尝试！");
//
//
//                    break;
//                default:
//                    break;
//            }

        }
    };

    //检测本程序的版本，这里假设从服务器中获取到最新的版本号为3
    public void checkVersion(View view) {
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新
        if (getVersionCode() < 3) {
            showDialogUpdate();//弹出提示版本更新的对话框


        }else{
            //否则吐司，说现在是最新的版本
            Toast.makeText(this,"当前已经是最新的版本",Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        View view_dialog = getLayoutInflater().from(SystemActivity.this).inflate(R.layout.activity_alerttext, null);
        final AlertDialog alertDialog2 = new AlertDialog.Builder(SystemActivity.this,R.style.FullScreenDialog).create();
        alertDialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog2.show();
        alertDialog2.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // 设置点击可取消
        alertDialog2.setCancelable(true);
        alertDialog2.setContentView(view_dialog);
        TextView title =  alertDialog2.getWindow().findViewById(R.id.title);
        TextView text =  alertDialog2.getWindow().findViewById(R.id.text);
        title.setText("发现新版本!");
        text.setText("是否更新新版本?");
        Button ok = (Button)  alertDialog2.getWindow().findViewById(R.id.confirm);
        Button cancle = (Button)  alertDialog2.getWindow().findViewById(R.id.cancel);



        //确认
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadNewVersionProgress("ipdizhi ");//下载最新的版本程序
                BaseActivity.showToastView("开始下载，请等待！");
                alertDialog2.dismiss();

            }
        });
        //取消
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                alertDialog2.dismiss();

            }
        });

    }

    /**
     * 下载新版本程序
     */
    private void loadNewVersionProgress(final String ip) {
        final   String uri="ConstantValue.https+GetName.getPHPIpAddress()+ConstantValue.updateApp";
        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");


        //  pd.show();
        //启动子线程下载任务
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(ip, pd);

                    // sleep(3000);
                    //BaseActivity.showToastView("下载完成！");
                    // Toast.makeText(getApplicationContext(), "下载完成！", Toast.LENGTH_LONG).show();

                    //     Log.i(TAG, "run: 下载完成！");


                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    // Toast.makeText(getApplicationContext(), "下载新版本失败！", Toast.LENGTH_LONG).show();
                    //     Log.i(TAG, "run: 下载新版本失败");
                    //BaseActivity.showToastView("下载新版本失败");
//                    Message message = Message.obtain();
//                    message.what=ConstantValue.XMPP_MSG_PACKET_CALLBACK;
//                    mHandler.sendMessage(message);
                    e.printStackTrace();
                }
            }}.start();
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

        //关键点：
        //安装完成后执行打开
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }


    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(uri);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time= System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory()+"/Download", "app.apk");
            //   Log.i(TAG, "getFileFromServer: "+Environment.getExternalStorageDirectory()+"/Download");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;


            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
                pd.setProgress(total);


            }

            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }


    /*
     * 获取当前程序的版本名
     */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        // Log.e("TAG", "版本号" + packInfo.versionCode);
        // Log.e("TAG", "版本名" + packInfo.versionName);
        return packInfo.versionName;

    }


    /*
     * 获取当前程序的版本号
     */
    private int getVersionCode() {
        try {

            //获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //   Log.e("TAG", "版本号" + packInfo.versionCode);
            //  Log.e("TAG", "版本名" + packInfo.versionName);
            return packInfo.versionCode;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return  1;
    }

//
//    //切换屏幕横竖屏
//    public void landSpanceY(View view) {
//      //  BaseActivity.SendMessage(MainActivity.getByteMess2());
//    }
//
//
//    //设备 管理信息 进行货架的产品的更新
//    public void operationManagementActivity(View view) {
////        openActivity(operationManagementActivity.class);
////        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);
//
//    }
//
//    //出货等相关信息  数据的简单统计
///*
//    public void operationDataActivity(View view) {
//        openActivity(operationDataActivity.class);
//        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);
//
//    }
//*/
//
//    //管理员信息查看  只能查看
//  /*  public void authorActivity(View view) {
//        openActivity(AuthorActivity.class);
//        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);
//
//    }*/
//
//
//    //检查设备编号  添加更改设备
//    public void shelvesManagementActivity(View view) {
////        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);
////        openActivity(shelvesManagementActivity.class);
//
//
//    }
//
//
//    //wifi
//    public void wifiActivity(View view) {
////        Bundle bundle = new Bundle();
////        bundle.putString("name", ConstantValue.wifi);
////        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);
////        openActivity(WifiActivity2.class,bundle);
//
//
//    }

    //网络设置
    public void internetInformationActivity(View view) {
        // Bundle bundle = new Bundle();
        // bundle.putString("name", ConstantValue.netAddress);
        // AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);
        Long time=  System.currentTimeMillis();


//        SystemValuesDao systemValuesDao = new SystemValuesDao(BaseActivity.context);
//        SystemValues pass = systemValuesDao.dbQueryOneByName(ConstantValue.netAddress);
        // 构建dialog显示的view布局
        View view_dialog = getLayoutInflater().from(SystemActivity.this).inflate(R.layout.activity_alerttext, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(SystemActivity.this,R.style.FullScreenDialog).create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // 设置点击可取消
        alertDialog.setCancelable(true);
        alertDialog.setContentView(view_dialog);

        //这一句消除白块
        // window.setBackgroundDrawable(new BitmapDrawable());


        final EditText tv_InputPwd = (EditText)  alertDialog.getWindow().findViewById(R.id.editInputPwd);
        final TextView tv_InputName = (TextView)  alertDialog.getWindow().findViewById(R.id.editInputName);

        final TextView textviewTitle3 =(TextView)  alertDialog.getWindow().findViewById(R.id.textviewTitle3);
//        systemValuesDao.dbQueryOneByID(ConstantValue.codeValue99);//用户名称
//        tv_InputName.setText( pass.getValue());
//        textviewTitle3.setText(pass.getName());
//        tv_InputPwd.setText( pass.getValue());

        //    mDevList.setVisibility(View.GONE);

        Button btn_pwdOk = (Button)  alertDialog.getWindow().findViewById(R.id.btnPwdOK);
        Button btn_cancle = (Button)  alertDialog.getWindow().findViewById(R.id.btnCancle);

        tv_InputPwd.setFocusable(true);
        tv_InputPwd.setFocusableInTouchMode(true);
        tv_InputPwd.requestFocus();
        final InputMethodManager imm = (InputMethodManager)SystemActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);    //InputMethodManager.SHOW_FORCED



        //确认
        btn_pwdOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                View view_dialog = getLayoutInflater().from(SystemActivity.this).inflate(R.layout.confirm_dialog, null);
                final AlertDialog alertDialog2 = new AlertDialog.Builder(SystemActivity.this,R.style.FullScreenDialog).create();
                alertDialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog2.show();
                alertDialog2.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

                // 设置点击可取消
                alertDialog2.setCancelable(true);
                alertDialog2.setContentView(view_dialog);
                TextView title =  alertDialog2.getWindow().findViewById(R.id.title);
                TextView text =  alertDialog2.getWindow().findViewById(R.id.text);
                title.setText("确定修改吗？");
                text.setText("请注意，修改网络地址后会自动重启软件！");
                Button ok = (Button)  alertDialog2.getWindow().findViewById(R.id.confirm);
                Button cancle = (Button)  alertDialog2.getWindow().findViewById(R.id.cancel);



                //确认
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //关闭软键盘
                        imm.hideSoftInputFromWindow(tv_InputPwd.getWindowToken(), 0);

                        onResume();
                        alertDialog2.dismiss();
                        alertDialog.dismiss();
                    }
                });
                //取消
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        onResume();
                        alertDialog2.dismiss();
                        alertDialog.dismiss();
                    }
                });




            }
        });
        //取消
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //关闭软键盘
                imm.hideSoftInputFromWindow(tv_InputPwd.getWindowToken(), 0);
                onResume();
                alertDialog.dismiss();
            }
        });



    }


    //网络设置
/*    public void initPassword(View view) {

        openActivity(initPasswordActivity.class);
        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);

    }*/
    //测试
    public void testActivity(View view) {
//        openActivity(messageActivity.class);
//        AppManager.getInstance().killActivity(MainActivity_main_systemCorrelation.class);


    }



    //返回主页
    public void back_to_main(View view) {

        openActivity(LoginActivity.class);
        AppManager.getInstance().killActivity(this);
//        openActivity(MainActivity_main.class);
//        BaseActivity.orderID = "0";
//        bundle.remove(ConstantValue.employeeId);//执行返回操作的时候，清除用户登陆信息..用户登陆信息
//        AppManager.getInstance().killActivity(this);
    }


    //返回主页
    public void back_to_main2(View view) {
//        orderID = "0";
//        bundle.remove(ConstantValue.employeeId);//执行返回操作的时候，清除用户登陆信息..用户登陆信息
//        AppManager.getInstance().killAllActivityNotMain();//回到最前
        // openActivity(MainActivity.class);
    }

    /**
     * 调用onCreate(), 目的是刷新数据,  从另一activity界面返回到该activity界面时, 此方法自动调用
     */
    @Override
    protected void onResume() {

        super.onResume();


    }





    @Override
    protected void onPause() {
        super.onPause();
    }






/////////////////////////////////////////去除工具信息

    private void checkUpdate() {
        if(true){
//            Version version = new Version();
//            version.setUri("http://192.168.1.106:8080"+ConstantValue.updateApp);
//
//            download(version);
        }
    }


    private void download(final Version version) {


        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("版本更新")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                      //  UpdateAppManager.downloadApk(MainActivity_main_systemCorrelation.this,version.getUri(),"版本升级","AppName");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }





    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
             */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }

        }else{
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        // AppManager.getInstance().killActivity(this);
        //  Application.getInstance().removeActivity(this);
        //  RefWatcher refWatcher = Application.getRefWatcher(this);
        //  refWatcher.watch(this);
    }


    private String permissionInfo;
    private final int SDK_PERMISSION_REQUEST = 127;



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
