package com.dongjiu.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.BaseActivity;
import com.dongjiu.R;
import com.dongjiu.fragment.DownloadFragment;
import com.dongjiu.fragment.MainFragment;
import com.dongjiu.fragment.QrcodeFragment2;
import com.dongjiu.fragment.QueryFragment;
import com.dongjiu.fragment.UploadFragment;
import com.dongjiu.fragment.adapter.MainFragmentAdapter;
import com.dongjiu.fragment.changeFragment.ChangeFragment;
import com.dongjiu.fragment.changeFragment.GlobalParms;
import com.dongjiu.utils.CheckPermissionUtils;
import com.dongjiu.utils.zxing.CaptureActivity;
import com.dongjiu.utils.zxing.Intents;
import com.dongjiu.utils.zxing.util.CodeUtils;
import com.dongjiu.utils.zxing.util.UriUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 主框架
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, CustomAdapt {


    private void initData() {
        GlobalParms.setFragmentSelected(new ChangeFragment() {
            @Override
            public void changge(int postion) {
                //调用BottomNavigationBar的setlecTab方法来改变Tab
                //mBottomNavigationBar.selectTab(postion);
            }
        });
    }
    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
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
    public static boolean isContinuousScan =false;


    /**
     * 菜单标题
     */
    private final int[] TAB_TITLES = new int[]{R.string.menu_main, R.string.menu_download, R.string.menu_qrcode, R.string.menu_upload, R.string.menu_query};
    /**
     * 菜单图标
     */
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_main_main_selector, R.drawable.tab_main_download_selector, R.drawable.tab_main_qrcode_selector, R.drawable.tab_main_upload_selector
            , R.drawable.tab_main_query_selector};

   // @BindView(R.id.view_pager)
   public  static ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    /**
     * 页卡适配器
     */
    private PagerAdapter adapter;
    /**
     * 退出时间
     */
    private long exitTime;

    public static Context context;


    public static FragmentManager mFragmentManager;
    //public static TransitionManager transitionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        ButterKnife.bind(this);
        viewPager = findViewById(R.id.view_pager);
        mFragmentManager = getSupportFragmentManager();
        //transitionManager= getContentTransitionManager();
        context = this;

        // 初始化页卡
        initPager();

        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);



        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        if(bundle != null ){
            int id = bundle.getInt("id"); //通过key得到value
        if (id == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainfragment,new MainFragment())
                    .addToBackStack(null)
                    .commit();
        }
        //initPermission();

//            switch (id){
//                case 0:
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.mainfragment,new MainFragment())
//                            .addToBackStack(null)
//                            .commit();
//                case 1:
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.downloadragment,new DownloadFragment())
//                            .addToBackStack(null)
//                            .commit();
//                case 2:
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.qrcodefragment,new QueryFragment())
//                            .addToBackStack(null)
//                            .commit();
//                case 3:
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.uploadfragment,new UploadFragment())
//                            .addToBackStack(null)
//                            .commit();
//                case 4:
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.queryfragment,new QueryFragment())
//                            .addToBackStack(null)
//                            .commit();
//            }

        }




        qrcodeFragment2.setOnButtonClick(new QrcodeFragment2.OnButtonClick() {

            @Override
            public void onClick(View view) {
                //切换到TwoFragment
                getSupportFragmentManager().beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.mainfragment,new MainFragment())
                        .commit();
            }
        });
    }

    private QrcodeFragment2 qrcodeFragment2 = new QrcodeFragment2();

    /**
     * 初始化权限事件
     */
//    private void initPermission() {
//        //检查权限
//        String[] permissions = CheckPermissionUtils.checkPermission(this);
//        if (permissions.length == 0) {
//            //权限都申请了
//            //是否登录
//        } else {
//            //申请权限
//            ActivityCompat.requestPermissions(this, permissions, 100);
//        }
//    }
    /**
     * 设置页卡显示效果
     * @param tabLayout
     * @param inflater
     * @param tabTitlees
     * @param tabImgs
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs) {
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            // 使用自定义视图，目的是为了便于修改，也可使用自带的视图
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
        }
    }

    private void initPager() {
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);



        // 关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            // 重写键盘事件分发，onKeyDown方法某些情况下捕获不到，只能在这里写
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar snackbar = Snackbar.make(viewPager, "再按一次退出程序", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundResource(R.color.colorPrimary);
                snackbar.show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表  
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     *  
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法 
     *
     * @param listener 
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     *  
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法 
     *
     * @param listener 
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove(listener);
    }

    /**
     *  
     * 分发触摸事件给所有注册了MyTouchListener的接口 
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
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
    }

    @Override
    public void refresh() {
        super.refresh();
    }

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

    private Context getContext(){
        return this;
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
            startScan(cls,title);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera),
                    RC_CAMERA, perms);
        }
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
        intent.putExtra(KEY_IS_CONTINUOUS,isContinuousScan);
        ActivityCompat.startActivityForResult(this,intent,REQUEST_CODE_SCAN,optionsCompat.toBundle());
    }

    /**
     * 生成二维码/条形码
     * @param isQRCode
     */
    private void startCode(boolean isQRCode){
        Intent intent = new Intent(this,ScanQrcodeActivity.class);
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

    @AfterPermissionGranted(RC_READ_PHOTO)
    private void checkExternalStoragePermissions(){
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {//有权限
            startPhotoCode();
        }else{
            EasyPermissions.requestPermissions(this, getString(R.string.permission_external_storage),
                    RC_READ_PHOTO, perms);
        }
    }

    public void onClick(View v){
        isContinuousScan = false;
//        switch (v.getId()){
//            case R.id.btn0:
//                this.cls = CaptureActivity.class;
//                this.title = ((Button)v).getText().toString();
//                checkCameraPermissions();
//                break;
//            case R.id.btn1:
//                this.cls = CustomCaptureActivity.class;
//                this.title = ((Button)v).getText().toString();
//                isContinuousScan = true;
//                checkCameraPermissions();
//                break;
//            case R.id.btn2:
//                this.cls = CaptureFragmentActivity.class;
//                this.title = ((Button)v).getText().toString();
//                checkCameraPermissions();
//                break;
//            case R.id.btn3:
//                this.cls = EasyCaptureActivity.class;
//                this.title = ((Button)v).getText().toString();
//                checkCameraPermissions();
//                break;
//            case R.id.btn4:
//                this.cls = CustomActivity.class;
//                this.title = ((Button)v).getText().toString();
//                checkCameraPermissions();
//                break;
//            case R.id.btn5:
//                startCode(false);
//                break;
//            case R.id.btn6:
//                startCode(true);
//                break;
//            case R.id.btn7:
//                checkExternalStoragePermissions();
//                break;
//        }

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
