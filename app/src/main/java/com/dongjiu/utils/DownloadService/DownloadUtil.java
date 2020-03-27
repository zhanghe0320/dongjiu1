//package com.dongjiu.utils.DownloadService;
//
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.FileProvider;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.dongjiu.R;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.RandomAccessFile;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
////参数格式文件
////        “Code”: 0,
////        “Msg”: “”,
////        “UpdateStatus”: 1,
////        “VersionCode”: 3,
////        “VersionName”: “1.0.2”,
////        “ModifyContent”: “1、优化api接口。\r\n2、添加使用demo演示。\r\n3、新增自定义更新服务API接口。\r\n4、优化更新提示界面。”,
////        “DownloadUrl”: “https://raw.githubusercontent.com/xuexiangjys/XUpdate/master/apk/xupdate_demo_1.0.2.apk”,
////        “ApkSize”: 2048
////        “ApkMd5”: “…” //md5值没有的话，就无法保证apk是否完整，每次都会重新下载。
//public class DownloadUtil {
//
//    private Disposable downDisposable;
//    private ProgressBar progressBar;
//    private TextView textView4;
//    private Button upgrade;
//    private long downloadLength=0;
//    private long contentLength=0;
//    private String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
//
//
//
//    //判断版本是否最新，如果不是最新版本则更新
////    private void test(){
////        Observable.create(new ObservableOnSubscribe<String>() {
////            @Override
////            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
////                OkHttpClient client = new OkHttpClient();
////                Request request = new Request.Builder()
////                        .url("url")
////                        .build();
////
////                client.newCall(request).enqueue(new okhttp3.Callback() {
////                    @Override
////                    public void onFailure(Call call, IOException e) {
////                        emitter.onError(e);
////                    }
////
////                    @Override
////                    public void onResponse(Call call, Response response) throws IOException {
////                        String result="";
////                        if (response.body()!=null) {
////                            result=response.body().string();
////                        }else {
////                            //返回数据错误
////                            return;
////                        }
////                        emitter.onNext(result);
////                    }
////                });
//////                emitter.onNext("123");
////            }
////        }).subscribeOn(Schedulers.io())// 将被观察者切换到子线程
////                .observeOn(AndroidSchedulers.mainThread())// 将观察者切换到主线程
////                .subscribe(new Observer<String>() {
////                    private Disposable mDisposable;
////                    @Override
////                    public void onSubscribe(Disposable d) {
////                        mDisposable = d;
////                    }
////                    @Override
////                    public void onNext(String result) {
////                        if (result.isEmpty()){
////                            return;
////                        }
////                        //2.判断版本是否最新，如果不是最新版本则更新
////                        String downloadUrl="https://raw.githubusercontent.com/xuexiangjys/XUpdate/master/apk/xupdate_demo_1.0.2.apk";
////                        String title="是否升级到4.1.1版本？";
////                        String size="新版本大小：未知";
////                        String msg="1、优化api接口。\r\n2、添加使用demo演示。\r\n3、新增自定义更新服务API接口。\r\n4、优化更新提示界面。";
////                        int versionCode=20000;
////                        try {
////                            int version = getPackageManager().
////                                    getPackageInfo(getPackageName(), 0).versionCode;
////                            if (versionCode>version){
////                                LayoutInflater inflater = LayoutInflater.from(TestActivity.this);
////                                View view = inflater.inflate(R.layout.layout_dialog, null);
////                                AlertDialog.Builder mDialog = new AlertDialog.Builder(TestActivity.this,R.style.Translucent_NoTitle);
////                                mDialog.setView(view);
////                                mDialog.setCancelable(true);
////                                mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
////                                    @Override
////                                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
////                                        return keyCode == KeyEvent.KEYCODE_BACK;
////                                    }
////                                });
////                                upgrade= view.findViewById(R.id.button);
////                                TextView textView1= view.findViewById(R.id.textView1);
////                                TextView textView2= view.findViewById(R.id.textView2);
////                                TextView textView3= view.findViewById(R.id.textView3);
////                                textView4= view.findViewById(R.id.textView4);
////                                ImageView iv_close= view.findViewById(R.id.iv_close);
////                                progressBar= view.findViewById(R.id.progressBar);
////                                progressBar.setMax(100);
////                                textView1.setText(title);
////                                textView2.setText(size);
////                                textView3.setText(msg);
////                                upgrade.setOnClickListener(new View.OnClickListener() {
////                                    @Override
////                                    public void onClick(View v) {
////                                        //动态询问是否授权
////                                        int permission = ActivityCompat.checkSelfPermission(getApplication(),
////                                                Manifest.permission.WRITE_EXTERNAL_STORAGE);
////                                        if (permission != PackageManager.PERMISSION_GRANTED) {
////                                            ActivityCompat.requestPermissions(TestActivity.this, PERMISSIONS_STORAGE,
////                                                    1);
////                                        }else {
////                                            upgrade.setVisibility(View.INVISIBLE);
////                                            down(downloadUrl);
////                                        }
////                                    }
////                                });
////                                iv_close.setOnClickListener(new View.OnClickListener() {
////                                    @Override
////                                    public void onClick(View v) {
////                                        finish();
////                                    }
////                                });
////                                mDialog.show();
////                            }else {
////
////                            }
////                        } catch (PackageManager.NameNotFoundException e) {
////                            e.printStackTrace();
////                        }
////                        mDisposable.dispose();
////                    }
////                    @Override
////                    public void onError(Throwable e) {
////                        test();
////                    }
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
////    }
//
//
//    //下载apk并更新进度条
////    private void down(String downloadUrl){
////        Observable.create(new ObservableOnSubscribe<Integer>() {
////            @Override
////            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
////                downApk(downloadUrl,emitter);
////            }
////        }).subscribeOn(Schedulers.io())// 将被观察者切换到子线程
////                .observeOn(AndroidSchedulers.mainThread())// 将观察者切换到主线程
////                .subscribe(new Observer<Integer>() {
////
////                    @Override
////                    public void onSubscribe(Disposable d) {
////                        downDisposable = d;
////                    }
////                    @Override
////                    public void onNext(Integer result) {
////                        //设置ProgressDialog 进度条进度
////                        progressBar.setProgress(result);
////                        textView4.setText(result+"%");
////                    }
////                    @Override
////                    public void onError(Throwable e) {
////                        Toast.makeText(getApplication(),"网络异常！请重新下载！",Toast.LENGTH_SHORT).show();
////                        upgrade.setEnabled(true);
////                    }
////                    @Override
////                    public void onComplete() {
////                        Toast.makeText(getApplication(),"服务器异常！请重新下载！",Toast.LENGTH_SHORT).show();
////                        upgrade.setEnabled(true);
////                    }
////                });
////    }
//
//
//
//    //下载apk
////    private void downApk(String downloadUrl,ObservableEmitter<Integer> emitter){
////        OkHttpClient client = new OkHttpClient();
////        Request request = new Request.Builder()
////                .url(downloadUrl)
////                .build();
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                //下载失败
////                breakpoint(downloadUrl,emitter);
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if (response.body() == null) {
////                    //下载失败
////                    breakpoint(downloadUrl,emitter);
////                    return;
////                }
////                InputStream is = null;
////                FileOutputStream fos = null;
////                byte[] buff = new byte[2048];
////                int len;
////                try {
////                    is = response.body().byteStream();
////                    File file = createFile();
////                    fos = new FileOutputStream(file);
////                    long total = response.body().contentLength();
////                    contentLength=total;
////                    long sum = 0;
////                    while ((len = is.read(buff)) != -1) {
////                        fos.write(buff,0,len);
////                        sum+=len;
////                        int progress = (int) (sum * 1.0f / total * 100);
////                        //下载中，更新下载进度
////                        emitter.onNext(progress);
////                        downloadLength=sum;
////                    }
////                    fos.flush();
////                    //4.下载完成，安装apk
////                    installApk(TestActivity.this,file);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                    breakpoint(downloadUrl,emitter);
////                } finally {
////                    try {
////                        if (is != null)
////                            is.close();
////                        if (fos != null)
////                            fos.close();
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        });
////
////    }
//
//
//
//
//    //断点续传
////    private void breakpoint(String downloadUrl,ObservableEmitter<Integer> emitter){
////        OkHttpClient client = new OkHttpClient();
////        Request request = new Request.Builder()
////                .url(downloadUrl)
////                .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
////                .build();
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                //下载失败
////                breakpoint(downloadUrl,emitter);
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if (response.body() == null) {
////                    //下载失败
////                    breakpoint(downloadUrl,emitter);
////                    return;
////                }
////                InputStream is = null;
////                RandomAccessFile randomFile = null;
////                byte[] buff = new byte[2048];
////                int len;
////                try {
////                    is = response.body().byteStream();
////                    String root = Environment.getExternalStorageDirectory().getPath();
////                    File file = new File(root,"updateDemo.apk");
////                    randomFile = new RandomAccessFile(file, "rwd");
////                    randomFile.seek(downloadLength);
////                    long total = contentLength;
////                    long sum = downloadLength;
////                    while ((len = is.read(buff)) != -1) {
////                        randomFile.write(buff,0,len);
////                        sum+=len;
////                        int progress = (int) (sum * 1.0f / total * 100);
////                        //下载中，更新下载进度
////                        emitter.onNext(progress);
////                        downloadLength=sum;
////                    }
////                    //4.下载完成，安装apk
////                    installApk(TestActivity.this,file);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                    breakpoint(downloadUrl,emitter);
////                } finally {
////                    try {
////                        if (is != null)
////                            is.close();
////                        if (randomFile != null)
////                            randomFile.close();
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        });
////    }
//
//
//
//
//
//
//    /**
//
//     路径为根目录
//     创建文件名称为 updateDemo.apk
//     */
//    private File createFile() {
//        String root = Environment.getExternalStorageDirectory().getPath();
//        File file = new File(root,"updateDemo.apk");
//        if (file.exists())
//            file.delete();
//        try {
//            file.createNewFile();
//            return file;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null ;
//    }
//
//
//
//  // 新建xml 文件夹    // file_paths.xml
////    <?xml version="1.0" encoding="utf-8"?>
////    <paths  xmlns:android="http://schemas.android.com/apk/res/android">
////    <files-path name="name1" path="test1" />
////    </paths>
////
//
//
////    在清单文件中配置   application       androidmanifest.xml
////            <provider
////    android:name="android.support.v4.content.FileProvider"
////    android:authorities="com.mydomain.fileprovider"
////    android:exported="false"
////    android:grantUriPermissions="true">
////    <meta-data
////    android:name="android.support.FILE_PROVIDER_PATHS"
////    android:resource="@xml/file_paths" />
////</provider>
//
//
//
//
//    //安装apk，包含7.0
//
////    public void installApk(Context context, File file) {
////        if (context == null) {
////            return;
////        }
////        String authority = getApplicationContext().getPackageName() + ".fileProvider";
////        Uri apkUri = FileProvider.getUriForFile(context, authority, file);
////        Intent intent = new Intent(Intent.ACTION_VIEW);
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        //判读版本是否在7.0以上
////        if (Build.VERSION.SDK_INT >= 24) {
////            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
////        } else {
////            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
////        }
////
////        context.startActivity(intent);
////        //弹出安装窗口把原程序关闭。
////        //避免安装完毕点击打开时没反应
////        Process.killProcess(android.os.Process.myPid());
////    }
////
////
////
////    //取消订阅
////    @Override
////    protected void onDestroy() {
////        super.onDestroy();
////        downDisposable.dispose();//取消订阅
////    }
//
//
//
//    //自定义布局
////
////    <?xml version="1.0" encoding="utf-8"?>
////     <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
////    xmlns:app="http://schemas.android.com/apk/res-auto"
////    xmlns:tools="http://schemas.android.com/tools"
////    android:layout_width="match_parent"
////    android:layout_height=“match_parent”>
////    <ImageView
////    android:id="@+id/imageView1"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:background="@drawable/lib_update_app_top_bg"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toTopOf="parent" />
////
////<View
////    android:id="@+id/view"
////    android:layout_width="0dp"
////    android:layout_height="0dp"
////    android:background="@drawable/lib_update_app_info_bg"
////    app:layout_constraintBottom_toTopOf="@+id/line"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/imageView1" />
////
////<TextView
////    android:id="@+id/textView1"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:layout_marginStart="16dp"
////    android:layout_marginTop="16dp"
////    android:text="是否升级到1.0版本？"
////    android:textColor="@android:color/black"
////    android:textSize="15sp"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/imageView1" />
////
////<TextView
////    android:id="@+id/textView2"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:layout_marginStart="16dp"
////    android:layout_marginTop="16dp"
////    android:text="新版本大小："
////    android:textColor="#666"
////    android:textSize="14sp"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/textView1" />
////
////
////<ScrollView
////    android:id="@+id/scrollView2"
////    android:layout_width="0dp"
////    android:layout_height="60dp"
////    android:layout_marginStart="16dp"
////    android:layout_marginTop="16dp"
////    android:layout_marginEnd="16dp"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/textView2">
////
////    <LinearLayout
////    android:layout_width="match_parent"
////    android:layout_height="wrap_content"
////    android:orientation="vertical">
////
////        <TextView
////    android:id="@+id/textView3"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:text="1，xxxxxxxx\n2，ooooooooo"
////    android:textColor="#666"
////    android:textSize="14sp"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/textView2" />
////    </LinearLayout>
////</ScrollView>
////
////<Button
////    android:id="@+id/button"
////    android:layout_width="0dp"
////    android:layout_height="wrap_content"
////    android:layout_marginStart="8dp"
////    android:layout_marginTop="16dp"
////    android:layout_marginEnd="16dp"
////    android:background="@drawable/textview_round_red"
////    android:gravity="center"
////    android:minHeight="40dp"
////    android:text="升级"
////    android:textColor="@android:color/white"
////    android:textSize="15sp"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/scrollView2" />
////
////<ProgressBar
////    android:id="@+id/progressBar"
////    style="?android:attr/progressBarStyleHorizontal"
////    android:layout_width="0dp"
////    android:layout_height="wrap_content"
////    android:layout_marginStart="16dp"
////    android:layout_marginEnd="16dp"
////    app:layout_constraintBottom_toBottomOf="@+id/button"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent" />
////
////<TextView
////    android:id="@+id/textView4"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:text="0%"
////    android:textColor="#E94339"
////    app:layout_constraintBottom_toTopOf="@+id/progressBar"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent" />
////
////<android.support.constraint.Guideline
////    android:id="@+id/guideline1"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:orientation="vertical"
////    app:layout_constraintGuide_percent="0.5" />
////
////<View
////    android:id="@+id/line"
////    android:layout_width="1dp"
////    android:layout_height="50dp"
////    android:layout_marginStart="8dp"
////    android:layout_marginTop="16dp"
////    android:layout_marginEnd="8dp"
////    android:background="#d8d8d8"
////    android:visibility="visible"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintHorizontal_bias="0.501"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/button" />
////
////<ImageView
////    android:id="@+id/iv_close"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:layout_marginStart="8dp"
////    android:layout_marginEnd="8dp"
////    android:src="@mipmap/lib_update_app_close"
////    app:layout_constraintEnd_toEndOf="parent"
////    app:layout_constraintStart_toStartOf="parent"
////    app:layout_constraintTop_toBottomOf="@+id/line" />
////
////<android.support.constraint.Guideline
////    android:id="@+id/guideline2"
////    android:layout_width="wrap_content"
////    android:layout_height="wrap_content"
////    android:orientation="horizontal"
////    app:layout_constraintGuide_percent="0.2" />
////
////    </android.support.constraint.ConstraintLayout>
//
//
//
//    ///textview_round_red.xml
////
////    <?xml version="1.0" encoding="utf-8"?>
////<shape  xmlns:android="http://schemas.android.com/apk/res/android">
////
////    <!-- view背景色 -->
////    <solid android:color="#E94339" />
////    <!-- 边框颜色 宽度 -->
////    <stroke
////    android:width="1dip"
////    android:color="#E94339" />
////    <!-- 边框圆角 -->
////    <corners
////    android:bottomRightRadius="5dp"
////    android:topRightRadius="5dp"
////    android:bottomLeftRadius="5dp"
////    android:topLeftRadius="5dp"/>
////</shape >
//
//    //lib_update_app_info_bg.xml
////    <?xml version="1.0" encoding="utf-8"?>
////<shape xmlns:android="http://schemas.android.com/apk/res/android"
////    android:shape="rectangle">
////    <corners
////    android:bottomLeftRadius="5dp"
////    android:bottomRightRadius="5dp"/>
////    <solid android:color="@android:color/white"/>
////</shape>
//
//    //style文件
////    <style name="Translucent_NoTitle" parent="android:style/Theme.Dialog">
////    <item name="android:background">#00000000</item> <!-- 设置自定义布局的背景透明 -->
////    <item name="android:windowBackground">@android:color/transparent</item>  <!-- 设置window背景透明，也就是去边框 -->
////</style>
//
//}
