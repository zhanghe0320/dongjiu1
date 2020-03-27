//package com.dongjiu.utils.DownloadService;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.dongjiu.R;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class UpdateAppService extends Service {
//    // 标题
//    private int titleId;
//
//    //版本
////    public static final String APK_VERSION="APK_VERSION";
//    public static final String APK_UIL="APK_UIL";
//
//    // 文件存储
//    private File updateDir = null;
//    private File updateFile = null;
//
//    // 通知栏
//    private NotificationManager updateNotificationManager = null;
//    private Notification updateNotification = null;
//
//    private String strAppUrl;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // TODO Auto-generated method stub
//        // 获取传值
//        titleId = intent.getIntExtra("titleId", 0);
////        String strVersion=intent.getStringExtra(APK_VERSION);
//        strAppUrl = intent.getStringExtra(APK_UIL);
////        strAppUrl = MyShareUtil.getSharedString(R.string.APP_UPDATE_URL);
//        // 创建文件
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            updateDir = new File(Environment.getExternalStorageDirectory(), "data/com.tianxin.mapclient.liteapp");
//            updateFile = new File(updateDir.getPath(), getResources().getString(titleId)  +"_V"+".apk");
//            Log.i("lgq","file======"+updateFile);
//        }
//        this.updateNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        this.updateNotification = new Notification();
//        this.updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
//
//        new Thread(new UpdateRunner()).start();
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    private Handler updateHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    // 自动安装新版本
//                    Log.e("自动安装新版本", updateFile.getName());
//                    updateNotificationManager.cancel(0);
//                    Intent installIntent = new Intent();
//                    installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    installIntent.setAction(Intent.ACTION_VIEW);
//                    installIntent.setDataAndType(Uri.fromFile(updateFile), "application/vnd.android.package-archive");
//                    startActivity(installIntent);
//
//                    // 停止服务
//                    stopSelf();
//                    break;
//                case 0:
//                    // 下载失败
////                    Notification.Builder builder = new Notification.Builder(this);//新建Notification.Builder对象
////                    PendingIntent  tintent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
////                    builder.setContentTitle("开始下载");
////                    builder.setContentText( "圆心部落  0%");
////                    builder.setSmallIcon(R.mipmap.app);
////                    builder.setContentIntent(tintent);//执行intent
////                    updateNotification = builder.getNotification();//将builder对象转换为普通的notification
////                    updateNotification.setLatestEventInfo(UpdateAppService.this, getApplicationContext().getResources().getString(R.string.app_name), "下载失败", null);
////                    updateNotificationManager.notify(0, updateNotification);
//                    stopSelf();
//                    break;
//                default:
//                    stopSelf();
//            }
//        }
//    };
//
//    class UpdateRunner implements Runnable {
//        Message message = updateHandler.obtainMessage();
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            message.what = 1;
//            try {
//                if (!updateDir.exists()) {
//                    updateDir.mkdirs();
//                }
//                if (!updateFile.exists()) {
//                    updateFile.createNewFile();
//                }
//                Log.i("lgq下载地址", strAppUrl);
//                long downloadSize = downloadUpdateFile(strAppUrl, updateFile);
//                if (downloadSize > 0) {
//                    // 下载成功
//                    updateHandler.sendMessage(message);
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                message.what = 0;
//                // 下载失败
//                updateHandler.sendMessage(message);
//            }
//        }
//
//    }
//
//    public long downloadUpdateFile(String downloadUrl, File saveFile)
//            throws Exception {
//        int downloadCount = 0;
//        int currentSize = 0;
//        long totalSize = 0;
//        int updateTotalSize = 0;
//        PendingIntent Pendingintent = PendingIntent.getActivity(UpdateAppService.this, 0, new Intent(Intent.ACTION_VIEW), 0);
//
//        HttpURLConnection httpConnection = null;
//        InputStream is = null;
//        FileOutputStream fos = null;
//
//        try {
//            URL url = new URL(downloadUrl);
////            URL url = new URL("http://image.baidu.com/search/detail?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&word=%E7%BD%91%E9%99%85%E9%A3%9E%E4%BE%A0%E7%9A%84%E4%BD%9C%E5%93%81&step_word=&ie=utf-8&in=&cl=undefined&lm=undefined&st=undefined&cs=97365977,1969139888&os=&simid=&pn=0&rn=1&di=0&fr=&fmq=1527155755045_R&fm=&ic=undefined&s=undefined&se=undefined&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=undefined&bdtype=-1&spn=0&pi=49125372204&gsm=0&objurl=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg&rpstart=0&rpnum=0&adpicid=0&catename=%E9%A3%8E%E5%85%89");
//            httpConnection = (HttpURLConnection) url.openConnection();
//            httpConnection.setRequestProperty("User-Agent", "PacificHttpClient");
//            if (currentSize > 0) {
//                httpConnection.setRequestProperty("RANGE", "bytes=" + currentSize + "-");
//            }
//            httpConnection.setConnectTimeout(10000);
//            httpConnection.setReadTimeout(20000);
//            updateTotalSize = httpConnection.getContentLength();
//            if (httpConnection.getResponseCode() == 404) {
//                throw new Exception("fail!");
//            }
//            is = httpConnection.getInputStream();
//            fos = new FileOutputStream(saveFile, false);
//            byte buffer[] = new byte[4096];
//            int readsize = 0;
//            int persent = 0;
//            while ((readsize = is.read(buffer)) > 0) {
//                fos.write(buffer, 0, readsize);
//                totalSize += readsize;
//                persent = (int) totalSize * 100 / updateTotalSize;
//                if ((downloadCount == 0) || persent - 1 > downloadCount) {
//                    Notification.Builder builder = new Notification.Builder(this);//新建Notification.Builder对象
//                    PendingIntent Pendingintentt = PendingIntent.getActivity(UpdateAppService.this, 0, new Intent(Intent.ACTION_VIEW), 0);
//                    builder.setContentTitle("开始下载");
//                    builder.setContentText( "正在下载"+ persent + "%");
//                    builder.setSmallIcon(R.mipmap.ic_launcher);
//                    builder.setContentIntent(Pendingintentt);//执行intent
//                    updateNotification = builder.getNotification();//将builder对象转换为普通的notification
//
//                    Log.i("lgqq","body=====MyServiceTestActivity====="+persent);
////                    updateNotification.setLatestEventInfo(UpdateAppService.this, "正在下载", persent >= 0 ? persent + "%" :"努力下载中~", null);
//                    downloadCount += 1;
//                    Log.v("lgq", persent + "%");
//                    updateNotificationManager.notify(0, updateNotification);
//                }
//            }
//        } finally {
//            if (httpConnection != null) {
//                httpConnection.disconnect();
//            }
//            if (is != null) {
//                is.close();
//            }
//            if (fos != null) {
//                fos.close();
//            }
//        }
//        return totalSize;
//    }
//
//
//
//
//
//
////    private void getwebvison() {
////        String url = Url.oneupdversion;//云版本控制链接
////        RequestParams params = new RequestParams();
////        AsyncHttpClient client = new AsyncHttpClient();
////        client.get(url, params, new TextHttpResponseHandler() {
////            @Override
////            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
////                Toast.makeText(getContext(), "网络连接异常", Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void onSuccess(int i, Header[] headers, String s) {
////                try {
////                    JSONObject object = new JSONObject(s);
////                    contentlg = object.getString("content");//更新内容
////                    urllg = object.getString("url");//apk下载链接
////                    int versionlg = object.getInt("version");//云版本号
////                    int loca = TxApplication.getVersionCode();//本地版本号
////                    if (versionlg > loca) {
////                        Intent updateIntent = new Intent(getContext(), UpdateAppService.class);
////                        updateIntent.putExtra("titleId", R.string.app_name);
////                        updateIntent.putExtra(UpdateAppService.APK_UIL, urllg);
////                        getActivity().startService(updateIntent);//开始下载
////                    }
////
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////
////    }
//
//    //     版本号
////    public static int getVersionCode() {
////        try {
////            return mTotalContext.getPackageManager().getPackageInfo(mTotalContext.getPackageName(), 0).versionCode;
////        } catch (PackageManager.NameNotFoundException e) {
////            e.printStackTrace();
////        }
////        return 0;
////    }
//}
