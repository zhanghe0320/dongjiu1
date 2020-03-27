package com.dongjiu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongjiu.utils.CustomAdapter.CustomAdapter;
import com.dongjiu.utils.CustomAdapter.MyRecyclerViewAdapter;
import com.dongjiu.utils.CustomAdapter.OrderMess;
import com.dongjiu.utils.CustomAdapter.OrderMess_product;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class UploadActivity extends BaseActivity implements CustomAdapt   /* implements View.OnClickListener */{


    //private TextView mTvSelectedDate, mTvSelectedTime;
  //  private CustomDatePicker mDatePicker, mTimerPicker;


    //初始化控件
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nochexbox)
    TextView nochexbox ;
    @BindView(R.id.allchexbox)
    TextView allchexbox ;
    @BindView(R.id.delete)
    TextView delete ;
    @BindView(R.id.alldelete)
    TextView alldelete;

    private ArrayList list = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;

    private static final String TAG = "UploadActivity";
//    private ListView lvData;
//    private LinearLayout mLlEditBar;//控制下方那一行的显示与隐藏
//    private UploadAdapter adapter;
//    private List<String> mData = new ArrayList<>();//所有数据
//    private List<String> mCheckedData = new ArrayList<>();//将选中数据放入里面
//    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中
//    private boolean isSelectedAll = true;//用来控制点击全选，全选和全不选相互切换

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);




       /* findViewById(R.id.ll_date).setOnClickListener(this);
        mTvSelectedDate = findViewById(R.id.tv_selected_date);
        initDatePicker();

        findViewById(R.id.ll_time).setOnClickListener(this);
        mTvSelectedTime = findViewById(R.id.tv_selected_time);
        initTimerPicker();*/



//        recyclerView = findViewById(R.id.recyclerView);
//        TextView nochexbox = findViewById(R.id.nochexbox);
//        TextView allchexbox = findViewById(R.id.allchexbox);
//        TextView delete = findViewById(R.id.delete);
//        TextView alldelete = findViewById(R.id.alldelete);
//        for (int c = 0; c < 20; c++) {
//            list.add("条目" + c);
//        }



//        list = new ArrayList<>();

        for (int i = 0; i <15 ; i++) {

            LinkedList list0= new LinkedList();
            for (int j = 0; j <10; j++) {
                OrderMess_product orderMess_product = new OrderMess_product("董酒-"+i+"-"+j,500+"箱","已经扫码"+300);
                list0.add(orderMess_product);
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
                   list0
            );
            list.add(orderMess);
            Log.i(TAG, orderMess.getPostorder_name());


        }

     //   RecyclerView recyclerView = new RecyclerView(this);

      //  CustomAdapter customAdapter = new CustomAdapter( list, BaseActivity.context);

        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //初始化适配器
        adapter = new MyRecyclerViewAdapter(getApplicationContext(), list);
        //给recyclerView添加适配器
        recyclerView.setAdapter(adapter);
        //设置分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,1));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        nochexbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.unSelectAll();
            }
        });
        allchexbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter. selectAll();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deletingData();
            }
        });
        alldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deleteAllData();
            }
        });

    }


    //到主界面  返回按钮
    public void back_to_main(View view) {

        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();

    }

    //到主界面  返回按钮
    public void back_to_main_1(View view) {

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
/*
        openActivity(UploadActivity.class);
        AppManager.getInstance().killAllActivityNotUpload();*/
    }


    //返回上一个页面
    public void main2all(View view) {
        openActivity(Main2Activity.class);
        AppManager.getInstance().killAllActivityNotMain2();

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
