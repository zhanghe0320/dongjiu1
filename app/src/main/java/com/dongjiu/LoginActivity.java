package com.dongjiu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.LogTime;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.activity.ScanQrcodeActivity;
import com.dongjiu.activity.SystemActivity;
import com.dongjiu.utils.ConstantValue;
import com.dongjiu.utils.GetName;
import com.dongjiu.utils.NoDoubleClickListener.NoDoubleClickListener;
import com.dongjiu.utils.commonUtils.NetWorkUtils;
import com.dongjiu.utils.zxing.Intents;
import com.dongjiu.utils.zxing.util.CodeUtils;
import com.dongjiu.utils.zxing.util.UriUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends BaseActivity implements  EasyPermissions.PermissionCallbacks, CustomAdapt {



//    WebView webview = (WebView)findViewById(R.id.webview);
//     webview.loadUrl("file:///android_asset/normal.html");
//    访问网络的html文件,只需这样：
//    webview.loadUrl("http://www.hao123.com");
//    WebView常用方法：
//     ** //不使用Android默认浏览器打开Web，就在App内部打开Web**
//
//    WebView webView = (WebView) findViewById(R.id.webview);
//      webView.loadUrl("http://www.baidu.com");
//      webView.setWebViewClient(new WebViewClient() {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    });
//
//  //支持App内部javascript交互
//webview.getSettings().setJavaScriptEnabled(true);
////自适应屏幕
//webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
//webview.getSettings().setLoadWithOverviewMode(true);
////设置可以支持缩放
//webview.getSettings().setSupportZoom(true);
////扩大比例的缩放
//webview.getSettings().setUseWideViewPort(true);
////设置是否出现缩放工具
//webview.getSettings().setBuiltInZoomControls(true);


    // 格式规定为:file:////android_asset/文件名.html
    //webView.loadUrl("file:////android_asset/javascript.html");

    // 加载apk包中的html页面
    //webView.loadUrl("file:////android_asset/index.html");
    private static final String TAG = "LoginActivity";
    @BindView(R.id.editInputPwd)
    TextView editInputPwd;
    @BindView(R.id.editInputName)
    TextView editInputName;

    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);

        checkExternalStoragePermissions();
        checkCameraPermissions();
        new Handler().postDelayed(new Runnable(){
            public void run(){
                BaseActivity.isAvailableByPing = NetWorkUtils.isAvailableByPing();
                if( BaseActivity.isAvailableByPing){
                    BaseActivity.showToastView("设备可以使用");

                }else{
                    BaseActivity.showToastView("设备无法连接网络禁止使用！");
                }
            }
        },10*1000);

        login.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

                openActivity(MainActivity.class);
                AppManager.getInstance().killActivity(LoginActivity.class);
            }
        });

    }

    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_QR_CODE = "key_code";
    public static final String KEY_IS_CONTINUOUS = "key_continuous_scan";

    public static final int REQUEST_CODE_SCAN = 0X01;
    public static final int REQUEST_CODE_PHOTO = 0X02;

    public static final int RC_CAMERA = 0X01;

    public static final int RC_READ_PHOTO = 0X02;
    private Class<?> cls;
    private String title;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case REQUEST_CODE_SCAN:
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
                    break;
                case REQUEST_CODE_PHOTO:
                    parsePhoto(data);
                    break;
            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
    }

    /**
     * 检测拍摄权限
     */
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(){
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {//有权限
           // startScan(cls,title);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera),
                    RC_CAMERA, perms);
        }
    }

    @AfterPermissionGranted(RC_READ_PHOTO)
    private void checkExternalStoragePermissions(){
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {//有权限
           // startPhotoCode();
        }else{
            EasyPermissions.requestPermissions(this, getString(R.string.permission_external_storage),
                    RC_READ_PHOTO, perms);
        }
    }

    private void parsePhoto(Intent data){
        final String path = UriUtils.getImagePath(this,data);
        Log.d("Jenly","path:" + path);
        if(TextUtils.isEmpty(path)){
            return;
        }
        //异步解析
        asyncThread(new Runnable() {
            @Override
            public void run() {
                final String result = CodeUtils.parseCode(path);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Jenly","result:" + result);
                        Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


    private void asyncThread(Runnable runnable){
        new Thread(runnable).start();
    }

    /**
     * 扫码
     * @param cls
     * @param title
     */
    private void startScan(Class<?> cls,String title){
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this,R.anim.in,R.anim.out);
        Intent intent = new Intent(this, cls);
        intent.putExtra(KEY_TITLE,title);
        intent.putExtra(KEY_IS_CONTINUOUS,MainActivity.isContinuousScan);
        ActivityCompat.startActivityForResult(this,intent,REQUEST_CODE_SCAN,optionsCompat.toBundle());
    }

    /**
     * 生成二维码/条形码
     * @param isQRCode
     */
    private void startCode(boolean isQRCode){
        Intent intent = new Intent(this, ScanQrcodeActivity.class);
        intent.putExtra(KEY_IS_QR_CODE,isQRCode);
        intent.putExtra(KEY_TITLE,isQRCode ? getString(R.string.qr_code) : getString(R.string.bar_code));
        startActivity(intent);
    }

    private void startPhotoCode(){
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, REQUEST_CODE_PHOTO);
    }

    private Context getContext(){
        return this;
    }

    public  OkHttpClient okHttpClient;
    public  FormBody formBody;
    public  Call call;
    public  Request request;
    public  ResponseBody responseBody;
    public  RequestBody requestBody;

    public void login(View view) {




      openActivity(MainActivity.class);
      AppManager.getInstance().killActivity(this);

        //openActivity(MainActivity.class);

      /*  OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        //post方式提交的数据
        FormBody   formBody = new FormBody.Builder()
                .add("", GetName.getuserName())
                //.add(ConstantValue.equipment_base, messFromBase.getEquipment_base())
                .add("", "")
                .add("","")
                .build();

        RequestBody requestBody = RequestBody.create(ConstantValue.MEDIA_TYPE_NORAML_FORM,"ConstantValue.equipmentCode"+"="+GetName.getuserName()+"&"
                +"ConstantValue.password"+"="+"passWord"+"&"+"ConstantValue.loginName"+"="+"employeeId");
        Log.i(TAG, "onClick: "+"employeeId"+"------------------"+"passWord"+"----"+"ConstantValue.equipmentCode"+"="+GetName.getuserName()+"&"
                +"ConstantValue.password"+"="+"passWord"+"&"+"ConstantValue.loginName"+"="+"employeeId");
        Request request = new Request.Builder()
                .url("ConstantValue.https+ GetName.getPHPIpAddress()+ ConstantValue.javaMechineLogin2")//请求的url
                .post(requestBody)
                .build() ;
        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                //     Log.i(TAG, "onFailure: 连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == ConstantValue.Okhttp_Back_Success) {
                    ResponseBody responseBody = response.body();
                    String s = responseBody.string();

                    //        Log.i("okhttp里面的responseBody", s);
                    String code =null;
                    String msg0=null ;
                    String data=null;
                    String count =null;
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        code = jsonObject.getString("code");
                        msg0 = jsonObject.getString("msg");
                        data = jsonObject.getString("data");
                        count = jsonObject.getString("count");

                    } catch (JSONException e) {


                        e.printStackTrace();
                    }


                    if (code.equals("0") ) {


                    } else {

                    }
                }
            }
        });
*/

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //两种方式进行销毁acitvity和手动释放；
        //MApplication.exitApp();
       // MApplication.removeActivity(this);
    }

    public void loginsystem(View view) {


        openActivity(SystemActivity.class);
        AppManager.getInstance().killActivity(this);

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
