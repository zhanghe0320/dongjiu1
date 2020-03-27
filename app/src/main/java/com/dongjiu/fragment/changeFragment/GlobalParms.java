package com.dongjiu.fragment.changeFragment;

import com.dongjiu.fragment.DownloadFragment;
import com.dongjiu.fragment.MainFragment;
import com.dongjiu.fragment.QrcodeFragment2;
import com.dongjiu.fragment.QueryFragment;
import com.dongjiu.fragment.UploadFragment;

public class GlobalParms {

    private static MainFragment sMainFragment; //主页fragemnt
    private static DownloadFragment sDownloadFragment; //下载fragemnt
    private static QrcodeFragment2 sQrcodeFragment2; //扫码fragemnt
    private static UploadFragment sUploadFragment; //上传fragemnt
    public static QueryFragment sQueryFragment;  //查询

    public static ChangeFragment sChangeFragment;  //改变选中Frangment的接口


    /**
     * 获取主页Fragment
     *
     * @return
     */
    public static MainFragment getMainFragment() {
        if (sMainFragment == null) {
            sMainFragment = new MainFragment();
        }
        return sMainFragment;
    }

    /**
     * 下载fragemnt
     *
     * @return
     */
    public static DownloadFragment getDownloadFragment() {
        if (sDownloadFragment == null) {
            sDownloadFragment = new DownloadFragment();
        }
        return sDownloadFragment;
    }

    /**
     * 查询fragemnt
     *
     * @return
     */
    public static QrcodeFragment2 getQrcodeFragment2() {
        if (sQrcodeFragment2 == null) {
            sQrcodeFragment2 = new QrcodeFragment2();
        }
        return sQrcodeFragment2;
    }

    /**
     * //下载fragemnt
     *
     * @return
     */
    public static UploadFragment getUploadFragment() {
        if (sUploadFragment == null) {
            sUploadFragment = new UploadFragment();
        }
        return sUploadFragment;
    }


    /**
     * //下载fragemnt
     *
     * @return
     */
    public static QueryFragment getQueryFragment() {
        if (sQueryFragment == null) {
            sQueryFragment = new QueryFragment();
        }
        return sQueryFragment;
    }

    /**
     * 设置被选中的Fragment
     * @param changeFragment
     */
    public static void setFragmentSelected(ChangeFragment changeFragment) {
        sChangeFragment = changeFragment;

    }
}
