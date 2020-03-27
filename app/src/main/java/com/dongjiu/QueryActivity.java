package com.dongjiu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import me.jessyan.autosize.internal.CustomAdapt;

public class QueryActivity extends BaseActivity implements CustomAdapt {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
       /* openActivity(QueryActivity.class);
        AppManager.getInstance().killAllActivityNotQuery();*/
    }


    //返回上一个页面
    public void main2upload(View view) {

        openActivity(UploadActivity.class);
        AppManager.getInstance().killAllActivityNotUpload();
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