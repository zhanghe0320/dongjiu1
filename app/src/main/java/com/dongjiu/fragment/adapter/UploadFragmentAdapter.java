package com.dongjiu.fragment.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.dongjiu.fragment.MsgContentFragment;
import com.dongjiu.fragment.UploadFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息内容子页面适配器
 */
public class UploadFragmentAdapter extends FragmentPagerAdapter {
    private List<String> names;
    private FragmentManager mFragmentManager;

    public UploadFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
        this.names = new ArrayList<>();
    }

    /**
     * 数据列表
     *
     * @param datas
     */
    public void setList(List<String> datas) {
        this.names.clear();
        this.names.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        UploadFragmentView fragment = new UploadFragmentView();
        Bundle bundle = new Bundle();
        bundle.putString("name", names.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = names.get(position);
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 3) {
            plateName = plateName.substring(0, 3) + "...";
        }
        return plateName;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        super.destroyItem(container, position, object);
        names.remove(makeFragmentName(container.getId(), getItemId(position)));
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    public void update(int position){
        Fragment fragment = (Fragment)mFragmentManager.findFragmentByTag(names.get(position));
        if(fragment == null){
            return;
        }
        if(fragment instanceof UpdateAble){//这里唯一的要求是Fragment要实现UpdateAble接口
            ((UpdateAble)fragment).update();
        }
    }

    public interface UpdateAble {
        public void update();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
