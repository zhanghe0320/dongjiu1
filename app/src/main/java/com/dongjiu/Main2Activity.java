package com.dongjiu;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dongjiu.activity.MainActivity;
import com.dongjiu.utils.CustomAdapter.CustomAdapter;
import com.dongjiu.utils.CustomAdapter.OrderMess;
import com.dongjiu.utils.CustomAdapter.OrderMess_product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindInt;
import android.support.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class Main2Activity extends BaseActivity implements CustomAdapt {

     @BindView(R.id.list_item)
     ListView list_item;


     LinkedList <OrderMess> aData;
    @BindView(R.id.tzhsmjm)
    TextView tzhsmjm;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        context = MApplication.getContext();
        AppManager.getInstance().addActivity(this);
       // list_item =findViewById(R.id.list_item);

        Bundle bundle = this.getIntent().getExtras(); //读取intent的数据给bundle对象
        String str1 = bundle.getString("sff"); //通过key得到value
        int int1 = bundle.getInt("iff");

        BaseActivity.showToastView(str1);
        aData = new LinkedList<>();

        for (int i = 0; i <5 ; i++) {

            LinkedList list= new LinkedList();
           for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list.add(orderMess_product);
            }
            OrderMess orderMess = new OrderMess(
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    "南京-"+i,
                    list
           );
            aData.add(orderMess);
            Log.i("---", orderMess.getPostorder_name());


         }

         RecyclerView recyclerView = new RecyclerView(this);

        CustomAdapter customAdapter = new CustomAdapter( aData, context);
        list_item.setAdapter(customAdapter);


        tzhsmjm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(Main2Activity.this, MainActivity.class);
               // Bundle bundle = new Bundle();
               // bundle.putString("sff", "传递的参数信息");  //key-"sff",通过key得到value-"value值"(String型)
              //  bundle.putInt("id", 2);           //key-"iff",value-175
               // intent.putExtras(bundle);            //通过intent将bundle传到另个Activity
              AppManager.getInstance().killActivity(Main2Activity.class);
               // startActivity(intent);
            }
        });

    }




    //到主界面  返回按钮
    public void back_to_main(View view) {

        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();

    }
    //到主界面
    public void main2main(View view) {

        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();
    }

    //到达下载
    public void main2down(View view) {
        openActivity(DownActivity.class);
        AppManager.getInstance().killAllActivityNotDown();
    }


    //返回上一个页面
    public void main2query(View view) {
        openActivity(QueryActivity.class);
        AppManager.getInstance().killAllActivityNotQuery();
    }


    //返回上一个页面
    public void main2upload(View view) {

        openActivity(UploadActivity.class);
        AppManager.getInstance().killAllActivityNotUpload();
    }


    //返回上一个页面
    public void main2all(View view) {
       /* openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();*/

    }

    //返回上一个页面
    public void main2waitfor(View view) {


        openActivity(Main2Activity_2.class);
        AppManager.getInstance().killAllActivityNotMain2_2();

    }

    //返回上一个页面
    public void main2received(View view) {

        openActivity(Main2Activity_3.class);
        AppManager.getInstance().killAllActivityNotMain2_3();

    }
    //返回上一个页面
    public void main2finish(View view) {

        openActivity(Main2Activity_4.class);
        AppManager.getInstance().killAllActivityNotMain2_4();

    }

    //系统设置页面
    public void main2system(View view) {

        openActivity(Main2Activity_4.class);
        AppManager.getInstance().killAllActivityNotMain2_4();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
