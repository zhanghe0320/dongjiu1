package com.dongjiu.utils.CustomAdapter;

import android.support.annotation.NonNull;

public class OrderMess_product {
    private String ProductName;
    private String ProductNum;
    private String ProductNumFinish;

    public OrderMess_product() {
        super();
    }

    public OrderMess_product(String ProductName,String ProductNum,String ProductNumFinish) {
    this.ProductName = ProductName;
    this.ProductNum = ProductNum;
    this.ProductNumFinish = ProductNumFinish;

    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductNum() {
        return ProductNum;
    }

    public void setProductNum(String productNum) {
        ProductNum = productNum;
    }

    public String getProductNumFinish() {
        return ProductNumFinish;
    }

    public void setProductNumFinish(String productNumFinish) {
        ProductNumFinish = productNumFinish;
    }
}
