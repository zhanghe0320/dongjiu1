package com.dongjiu.utils;


import okhttp3.MediaType;

public class GetName {


    public static  String getOpenfireIpAddress() {//获取OpenfireIpAddress  地址  截取IP
        //    Log.i(TAG, "getOpenfireIpAddress: "+systemValuesDao.dbQueryOneByName(ConstantValue.netAddress).getValue());
        String ipAddress = "";

        //
        //  Log.i(TAG, "getOpenfireIpAddress: "+str);

        boolean status = ipAddress.contains("/");
        String str="";
        if(status){
            str = ipAddress.substring(0, ipAddress.indexOf("/"));
        }else {
            str = ipAddress;
        }

        return str;
    }


    public static  String getPHPIpAddress() {//获取php地址   完整IP

        return  "";
    }


    public static  String getuserName() {//获取设备号

        return  "";
    }



    public static  String getEquipmentID() {

        return "";
    }
    //请求类型定义
    /**
     * "application/x-www-form-urlencoded"，是默认的MIME内容编码类型，一般可以用于所有的情况，但是在传输比较大的二进制或者文本数据时效率低。
     这时候应该使用"multipart/form-data"。如上传文件或者二进制数据和非ASCII数据。
     */
    public static final MediaType MEDIA_TYPE_NORAML_FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    //既可以提交普通键值对，也可以提交(多个)文件键值对。
    public static final MediaType MEDIA_TYPE_MULTIPART_FORM = MediaType.parse("multipart/form-data;charset=utf-8");//普通form
    //只能提交二进制，而且只能提交一个二进制，如果提交文件的话，只能提交一个文件,后台接收参数只能有一个，而且只能是流（或者字节数组）
    public static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
    public static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");//json
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致



    public static final int PHP_Back_Success = 0;//P出烟信息是否需要记录
    public static final int PHP_Back_Fail = 1;//P出烟信息是否需要记录

    public static final int Okhttp_Back_Success = 200;//P出烟信息是否需要记录
}
