package com.dongjiu.fragment.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.dongjiu.BaseActivity;
import com.dongjiu.activity.MainActivity;
import com.dongjiu.activity.ScanQrcodeActivity;
import com.dongjiu.fragment.DownloadFragment;
import com.dongjiu.fragment.MainFragment;
import com.dongjiu.fragment.QrcodeFragment;
import com.dongjiu.fragment.QrcodeFragment2;
import com.dongjiu.fragment.QueryFragment;
import com.dongjiu.fragment.UploadFragment;


/**
 * 主界面底部菜单适配器
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new DownloadFragment();
                break;
            case 2:
                fragment = new QrcodeFragment2();


//                BaseActivity.openActivity(Main2Activity.class);
//                AppManager.getInstance().killAllActivityNotMain2();
                break;
            case 3:
                fragment = new UploadFragment();
                break;
            case 4:
                fragment = new QueryFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }




    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    public void update(int position){
//        Fragment fragment = (Fragment)mFragmentManager.findFragmentByTag(names.get(position));
//        if(fragment == null){
//            return;
//        }
//        if(fragment instanceof MsgContentFragmentAdapter.UpdateAble){//这里唯一的要求是Fragment要实现UpdateAble接口
//            ((MsgContentFragmentAdapter.UpdateAble)fragment).update();
//        }
    }

    public interface UpdateAble {
        public void update();
    }
}
