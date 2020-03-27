package com.dongjiu.utils.CustomAdapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class OrderMess {
    private  String postordername; //发货单
    private  String postordernum_1;//发货单单号
    private  String postorderday_1;//发货单日期
    private  String postorder_warehouse;//发货单仓库
    private String postorder_name;//发货单产品名称
    private String postorder_woarhouse_num;//发货单件数
    private String postorder_num;//已经的扫码件数
    private String postorder_receive;//售货商人
    private String postorder_receive_add;//发货地址

    private LinkedList<OrderMess_product> list;

    public LinkedList<OrderMess_product> getList() {
        return list;
    }

    public void setList(LinkedList<OrderMess_product> list) {
        this.list = list;
    }

    public String getPostordername() {
        return postordername;
    }

    public void setPostordername(String postordername) {
        this.postordername = postordername;
    }

    public String getPostordernum_1() {
        return postordernum_1;
    }

    public void setPostordernum_1(String postordernum_1) {
        this.postordernum_1 = postordernum_1;
    }

    public String getPostorderday_1() {
        return postorderday_1;
    }

    public void setPostorderday_1(String postorderday_1) {
        this.postorderday_1 = postorderday_1;
    }

    public String getPostorder_warehouse() {
        return postorder_warehouse;
    }

    public void setPostorder_warehouse(String postorder_warehouse) {
        this.postorder_warehouse = postorder_warehouse;
    }

    public String getPostorder_name() {
        return postorder_name;
    }

    public void setPostorder_name(String postorder_name) {
        this.postorder_name = postorder_name;
    }

    public String getPostorder_woarhouse_num() {
        return postorder_woarhouse_num;
    }

    public void setPostorder_woarhouse_num(String postorder_woarhouse_num) {
        this.postorder_woarhouse_num = postorder_woarhouse_num;
    }

    public String getPostorder_num() {
        return postorder_num;
    }

    public void setPostorder_num(String postorder_num) {
        this.postorder_num = postorder_num;
    }

    public String getPostorder_receive() {
        return postorder_receive;
    }

    public void setPostorder_receive(String postorder_receive) {
        this.postorder_receive = postorder_receive;
    }

    public String getPostorder_receive_add() {
        return postorder_receive_add;
    }

    public void setPostorder_receive_add(String postorder_receive_add) {
        this.postorder_receive_add = postorder_receive_add;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public OrderMess(String postordername //发货单
            ,  String postordernum_1//发货单单号
            ,  String postorderday_1//发货单日期
            ,  String postorder_warehouse//发货单仓库
            , String postorder_name//发货单产品名称
            , String postorder_woarhouse_num//发货单件数
            , String postorder_num//已经的扫码件数
            , String postorder_receive//售货商人
            , String postorder_receive_add//发货地址
                 // ,   LinkedList list
                      ) {

        this.postordername=postordername;
        this.postordernum_1=postordernum_1;
        this.postorderday_1=postorderday_1;
        this.postorder_warehouse=postorder_warehouse;
        this.postorder_name=postorder_name;
        this.postorder_woarhouse_num=postorder_woarhouse_num;
        this.postorder_num=postorder_num;
        this.postorder_receive=postorder_receive;
        this.postorder_receive_add=postorder_receive_add;
       // this.list = list;

    }


    public OrderMess(String postordername //发货单
            ,  String postordernum_1//发货单单号
            ,  String postorderday_1//发货单日期
            ,  String postorder_warehouse//发货单仓库
            , String postorder_name//发货单产品名称
            , String postorder_woarhouse_num//发货单件数
            , String postorder_num//已经的扫码件数
            , String postorder_receive//售货商人
            , String postorder_receive_add//发货地址
                     ,   LinkedList list
    ) {

        this.postordername=postordername;
        this.postordernum_1=postordernum_1;
        this.postorderday_1=postorderday_1;
        this.postorder_warehouse=postorder_warehouse;
        this.postorder_name=postorder_name;
        this.postorder_woarhouse_num=postorder_woarhouse_num;
        this.postorder_num=postorder_num;
        this.postorder_receive=postorder_receive;
        this.postorder_receive_add=postorder_receive_add;
         this.list = list;

    }

    public OrderMess() {
        super();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}