package com.dongjiu.utils.DBUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;



/**
 *
 * 数据库信息
 */

public class  DBOpenHelper extends SQLiteOpenHelper {
    private String TAG = "数据库初始信息0：";
    private static SQLiteDatabase sqliteDatabase = null;


    private static DBOpenHelper dbOpenHelper = null;
    private static SharedPreferences sp ;
    private static String DBname= "njlsj.db";

    private static final int DATABASE_VERSION = 1;//用来测试的时候的数据库版本
    private static final int DATABASE_NEW_VERSION = 2;//用来测试的时候的数据库版本
    private static final int DATABASE_NEW_VERSION2OUTSMOKE = 3;//用来测试的时候的数据库版本
    private static final int DATABASE_NEW_VERSION2OUTSMOKE2 = 4;//用来测试的时候的数据库版本

    private static final int DATABASE_NEW_VERSION2OUTSMOKE3 = 5;//用来测试的时候的数据库版本


    //大部分版本号为 2
    //初始数据库信息为1
    //数据库版本参数不正确  会导致全部数据信息清除


    public synchronized static DBOpenHelper getInstance(Context context) {//获取DBOpenHelper，没有就生成，有直接返回
        if (dbOpenHelper == null) {
            dbOpenHelper = new DBOpenHelper(context,DBname, null, DATABASE_NEW_VERSION2OUTSMOKE2);//正在安装的数据库等级
        }/**
         myDatabase =SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.NO_LOCALIZED_COLLATORS |SQLiteDatabase.OPEN_READWRITE);
         */

        return dbOpenHelper;
    };



    public synchronized static SQLiteDatabase getInstanceSQLiteDatabase() {
        if (sqliteDatabase == null) {//获取sqliteDabase   没有 重新生成，有直接返回
            sqliteDatabase = dbOpenHelper.getWritableDatabase();
        }
        return sqliteDatabase;
    };

    private static boolean mainTmpDirSet = false;
    @Override
    public SQLiteDatabase getReadableDatabase() {
        if (!mainTmpDirSet) {//设置临时文件  存放数据库信息
            boolean rs = new File("/data/data/com.onesmock/databases/main").mkdir();
          //  Log.d("ahang", rs + "");
            super.getReadableDatabase().execSQL("PRAGMA temp_store_directory = '/data/data/com.onesmock/databases/main'");
            mainTmpDirSet = true;
            return super.getReadableDatabase();
        }
        return super.getReadableDatabase();
    }


    public DBOpenHelper(Context context, String name, CursorFactory factory,
                        int version) {//构造函数

        super(context, DBname, null, DATABASE_NEW_VERSION2OUTSMOKE2);
        String databaseName = getDatabaseName();//数据库名字
     //   Log.i(TAG, "创建数据库1");
        //向系统申请一个SqliteTest.db文件存这个数据库，其中1是数据库版本。
    }


 /*  private final String t_advertisement =
            "create table if not exists t_advertisement (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "goods_img_id VARCHAR(255)," +
                    "adv_url VARCHAR(255)" +
                    ")";*/

    private final String t_video =
            "create table if not exists t_video (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "equipmenthost VARCHAR(255)," +
                    "adv_url VARCHAR(255)" +
                    ")";
 /*   private final String t_author =
            "create table if not exists t_author(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "author_name VARCHAR(255)," +
                    "author_phone VARCHAR(255)," +
                    "equipmenthost VARCHAR(255)NOT NULL," +
                    "CreatedTime Long " +//CURRENT_TIMESTAMP
                    ")";*/

    private final String t_product =
            "create table if not exists t_product(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "product_name VARCHAR(255)," +
                    "productDaysum VARCHAR(255)," +
                    "productTotal VARCHAR(255)," +
                    "productId INTEGER," +
                    "CreatedTime Long ," +//CURRENT_TIMESTAMP
                    "imgUrl VARCHAR(255)," +
                    "equipmenthost VARCHAR(255) NOT NULL," +
                    "equipmentbase VARCHAR(255) NOT NULL," +
                    "prematchImgurl VARCHAR(255)," +
                    "prematchProductname VARCHAR(255)," +
                    "productMess VARCHAR(255)," +// 产品介绍的文字信息
                    "lackProduct VARCHAR(255) ," +
                    "textSpeak VARCHAR(255) ," +//广告语
                    "shelfState VARCHAR(255) " +
                    ")";

    private final String t_systemvalues =
            "create table if not exists t_systemvalues(" +
                    "id INTEGER NOT NULL PRIMARY KEY  ," +
                    "name VARCHAR(255)," +
                    "value VARCHAR(255)," +
                    "equipmenthost VARCHAR(255) NOT NULL," +
                    "proviceID VARCHAR(255) ," +
                    "cityID VARCHAR(255) ," +
                    "CreatedTime Long  " +//CURRENT_TIMESTAMP
                    ")";

    private final String t_equipment =
            "create table if not exists t_equipment(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "equipmentname VARCHAR(255)," +
                    "equipmentbase VARCHAR(255)NOT NULL," +
                    "equipmenthost VARCHAR(255)NOT NULL," +
                    "equipmentid VARCHAR(255) ," +
                    "shelfState VARCHAR(255) ," +
                    "lackProduct VARCHAR(255) ," +
                    "CreatedTime Long  " +//CURRENT_TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
                    ")";


/*
    private final String t_productmess =
            "create table if not exists t_productmess (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "equipmentbase VARCHAR(255) NOT NULL," +
                    "imgUrl VARCHAR(255)," +
                    "videoUrl VARCHAR(255)," +
                    "CreatedTime Long  " +//CURRENT_TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
                    ")";
*/


    private final String t_images =
            "create table if not exists t_images (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "imgUrl VARCHAR(255)," +
                    "equipmentbase VARCHAR(255)," +
                    "shelfState VARCHAR(255)," +
                    "lackProduct VARCHAR(255) ," +
                    "productName VARCHAR(255) ," +
                    "CreatedTime Long  " +//CURRENT_TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
                    ")";



    private final String t_outsmoke =
            "create table if not exists t_outsmoke (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "equipmenthost VARCHAR(255)," +
                    "equipmentbase VARCHAR(255)," +
                    "orderid VARCHAR(255)," +
                    "CreatedTime Long  " +//CURRENT_TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
                    ")";


    //运输单表   出库仓库  收货人 发货地址  下单日期
    private final String t_transportsheet =
            "create table if not exists t_transportsheet (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "productCode VARCHAR(255)," +          // 产品代号编码
                    "productName VARCHAR(255)," +          //产品名称
                    "sendCount VARCHAR(255)," +            //发送总数
                    "count VARCHAR(255)," +                //现在扫描的数目
                    "productCode_num VARCHAR(255)," +      //现在扫录入的编码
                    "CreatedTime Long  " +//CURRENT_TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
                    ")";


    //设备表更新 保留数据
    private final String CREATE_EQUIPMENT = "create table book(bookId integer ,bookName text);";//暂时不用
    private final String CREATE_TEMP_EQUIPMENT = "alter table t_equipment rename to _temp_equipment";
    private final String INSERT_DATA_EQUIPMENT = "insert into t_equipment select id,equipmentname,equipmentbase,equipmenthost,equipmentid,shelfState,lackProduct,CreatedTime from _temp_equipment";
    private final String DROP_TEMP_EQUIPMENT = "drop table _temp_equipment";

    //图片表更新 保留数据
    private final String CREATE_IMAGE = "create table t_image(bookId integer ,bookName text);";//暂时不用
    private final String CREATE_TEMP_IMAGE = "alter table t_images rename to _temp_image";
    private final String INSERT_DATA_IMAGE = "insert into t_image select id,imgurl,equipmentbase,shelfState,lackProduct,productName,'2222',CreatedTime from _temp_image";
    private final String DROP_IMAGE = "drop table _temp_image";

    //产品表更新 保留数据
    private final String CREATE_PRODUCT = "create table t_product(bookId integer ,bookName text);";//暂时不用
    private final String CREATE_TEMP_PRODUCT = "alter table t_product rename to _temp_product";
    private final String INSERT_DATA_PRODUCT = "insert into t_product select id,product_name,productDaysum,productTotal,productId,CreatedTime,imgUrl,equipmenthost,equipmentbase,prematchImgurl,prematchProductname,productMess,lackProduct,textSpeak,shelfState from _temp_product";
    private final String DROP_PRODUCT = "drop table _temp_product";

    //视频表更新 保留数据
    private final String CREATE_VIDEO = "create table t_video(bookId integer ,bookName text);";//暂时不用
    private final String CREATE_TEMP_VIDEO = "alter table t_video rename to _temp_video";
    private final String INSERT_DATA_VIDEO = "insert into t_video select id,equipmenthost,,adv_url from _temp_video";
    private final String DROP_VIDEO = "drop table _temp_video";



    //视频表更新 保留数据
    private final String CREATE_OUTSMOKE = "create table t_outsmoke(bookId integer ,bookName text);";//暂时不用
    private final String CREATE_TEMP_OUTSMOKE = "alter table t_outsmoke rename to _temp_outsmoke";
    private final String INSERT_DATA_OUTSMOKE = "insert into t_outsmoke select id,equipmenthost,equipmentbase,orderid ,CreatedTime from _temp_outsmoke";
    private final String DROP_OUTSMOKE = "drop table _temp_outsmoke";

    // private final String DELETE_COLUMN = "alter table t_image drop column productName2;";// 无法删除字段
   // private final String ADD_COLUMN = "alter table t_image add column productName2 integer";//增加字段

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {

        final int FIRST_DATABASE_VERSION = 1;//     第一版本数据库版本号   对应java版本还没有出货，需要重新修改

    /*    String t_advertisement = "create table if not exists t_advertisement(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "equipment VARCHAR(255)," +
                "CreatedTime Long  " +//CURRENT_TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
                ")";*/
        //如果初次运行，建立一张t_user表，建表的时候注意，自增是AUTOINCREMENT，而不是mysql的AUTO_INCREMENT
        boolean isTableExist = true;

        String sql0 = "select count(*) as c from Sqlite_master  where type = 'table'  and  name =  '`' ";
        Cursor cursor = null;
        int a = 0;
        try {
            cursor = sqliteDatabase.rawQuery(sql0, null);


            if (cursor.moveToNext()) {//查看是否拥有数据，新的设置，如果有，就不再重新建立数据库，否则创建数据库
                int count = cursor.getInt(0);
                if (count > 0) {
                    isTableExist = false;
                }
            }


            if (cursor.moveToNext()) {// 判断Cursor中是否有数据
                a = cursor.getInt(0);// 返回总记录数
            } else {
                a = 0;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

/*
        tabbleIsExist("t_systemvalues");//判断数据表是否存在
        if(tabbleIsExist("t_systemvalues")){//系统数据
            Log.i(TAG, "onCreate: 已经存在");
        }else{
            sqliteDatabase.execSQL(t_systemvalues);
            Log.i(TAG , t_systemvalues);
        }


        if(tabbleIsExist("t_video")){//视频数据
            Log.i(TAG, "onCreate: 已经存在");

        }else{
            sqliteDatabase.execSQL(t_video);
            Log.i(TAG , t_video);
        }

        if(tabbleIsExist("t_equipment")){//设备数据
            Log.i(TAG, "onCreate: 已经存在");

        }else{
            sqliteDatabase.execSQL(t_equipment);
            Log.i(TAG, t_equipment);
        }

        if(tabbleIsExist("t_product")){//产品数据
            Log.i(TAG, "onCreate: 已经存在");

        }else{
            sqliteDatabase.execSQL(t_product);
            Log.i(TAG, t_product);
        }
        if(tabbleIsExist("t_author")){//管理员数据
            Log.i(TAG, "onCreate: 已经存在");

        }else{
            sqliteDatabase.execSQL(t_author);
            Log.i(TAG, t_author);
        }
        if(tabbleIsExist("t_advertisement")){//设备数据
            Log.i(TAG, "onCreate: 已经存在");

        }else{
            sqliteDatabase.execSQL(t_advertisement);
            Log.i(TAG, t_advertisement);
        }*/


        sqliteDatabase.execSQL("drop table if exists t_advertisement");
        sqliteDatabase.execSQL("drop table if exists t_author");    // 增加一个表,创建表语句.
        sqliteDatabase.execSQL("drop table if exists t_equipment");
        sqliteDatabase.execSQL("drop table if exists t_images");    // 增加一个表,创建表语句.
        sqliteDatabase.execSQL("drop table if exists t_product");
        sqliteDatabase.execSQL("drop table if exists t_productmess");    // 增加一个表,创建表语句.
        sqliteDatabase.execSQL("drop table if exists t_video");
        sqliteDatabase.execSQL("drop table if exists t_outsmoke");//首先删除表
      //  sqliteDatabase.execSQL(t_systemvalues);
      //  sqliteDatabase.execSQL(t_equipment);
      //  sqliteDatabase.execSQL(t_product);
      // sqliteDatabase.execSQL(t_author);
      // sqliteDatabase.execSQL(t_advertisement);
      //  sqliteDatabase.execSQL(t_video);//升级数据库，建立新的表信息
       // sqliteDatabase.execSQL(t_productmess);//产品的详情信息
      //  sqliteDatabase.execSQL(t_images);//产品的信息
     //   sqliteDatabase.execSQL(t_outsmoke);//订单信息数据


    /*           if (isTableExist) {
            Log.i(TAG, "onCreate: 创建表格");
            sqliteDatabase.execSQL(t_advertisement);
            Log.i(TAG, t_advertisement);
            sqliteDatabase.execSQL(t_equipment);
            Log.i(TAG, t_equipment);
            sqliteDatabase.execSQL(t_product);
            Log.i(TAG, t_product);
            sqliteDatabase.execSQL(t_systemvalues);
            Log.i(TAG , t_systemvalues);
            sqliteDatabase.execSQL(t_author);
            Log.i(TAG , t_author);
            sqliteDatabase.execSQL(t_video);
            Log.i(TAG , t_video);


        }
*/

        if (0 == a) {

            sqliteDatabase.execSQL("insert into t_equipment (equipmentid,equipmenthost,equipmentbase,equipmentname,CreatedTime)values ('0','020000000000000001','020000000000000001','主设备','')");

         /*   sqliteDatabase.execSQL("insert into t_equipment (equipmenthost,equipmentbase,equipmentname,CreatedTime)values ('010100000001','020200000001','设备0','')");
            sqliteDatabase.execSQL("insert into t_equipment (equipmenthost,equipmentbase,equipmentname,CreatedTime)values ('010100000001','020200000010','设备1','')");
          */


            //product_total ,product_daysum,    0,0,
            //sqliteDatabase.execSQL("insert into t_product (productId,equipmenthost,equipmentbase,imgUrl,productTotal ,productDaysum,product_name,CreatedTime)values (1,'0000000000000001','0000000000000001', '', '0','0','主设备','')");
           /* sqliteDatabase.execSQL("insert into t_product (productId,equipmenthost,equipmentbase,imgUrl,product_total ,product_daysum,product_name,CreatedTime)values (1,'010100000001','020200000001', 'http://img.zcool.cn/community/01fca557a7f5f90000012e7e9feea8.jpg', 0,0,'红河','')");

*/
          //    Log.i(TAG + "插入数据1", "");


            sqliteDatabase.execSQL("insert into t_systemvalues(id,name,value,equipmenthost,CreatedTime) values (99,'初始密码','666666','020000000000000001','')");
            sqliteDatabase.execSQL("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values ('njlsj','666666','020000000000000001','')");

            Log.i(TAG + "插入数据2", "");
            sqliteDatabase.execSQL("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values ('本机编号','020000000000000001','020000000000000001','')");
            sqliteDatabase.execSQL("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values ('超级密码','njlsj.1234','020000000000000001','')");
            sqliteDatabase.execSQL("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values ('网络地址','www.xxxx.com/Taste','020000000000000001','')");


         //   Log.i(TAG + "插入数据3", "");
           // sqliteDatabase.execSQL("insert into t_author (author_name,author_phone,equipmenthost,CreatedTime)values ('张三', '12345678901', '0000000000000001','')");
         //   Log.i(TAG + "插入数据4", "");
            // 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
            //Object bindArgs[] = new Object[] { uuid };
            // 执行这条无返回值的sql语句
            // sqliteDatabase.execSQL(t_equipment_sql, bindArgs);

            // String[] items = 7uj

            String sql=("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values (?,?,?,?)");
            sqliteDatabase.execSQL("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values  ('串口号码', '/dev/ttyS2', '020000000000000001', '')");
            sqliteDatabase.execSQL("insert into t_systemvalues(id,name,value,equipmenthost,CreatedTime) values  (0,'wifi', '00000000', '020000000000000001', '')");
            sqliteDatabase.execSQL("insert into t_systemvalues(name,value,equipmenthost,CreatedTime) values  ('设备横屏', 'N', '020000000000000001', '')");  //  默认竖屏


            //保存串口号码为固定数值  禁止修改

          //  Log.i(TAG + "串口号码5","");


        }

        onUpgrade(sqliteDatabase, DATABASE_NEW_VERSION2OUTSMOKE,DATABASE_NEW_VERSION2OUTSMOKE2);//数据库升级信息   初始版本，现在版本，sql语句信息
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//数据库升级的方法 在这里进行操作
        //这里是更新数据库版本时所触发的方法
       // db.execSQL("drop table if exists Book");
       // db.execSQL("drop table if exists Category");    // 增加一个表,创建表语句.
      //  onCreate(db);//
        //t_author +t_equipment + t_images +t_product + t_productmess +t_systemvalues+ t_video; +t_advertisement
        if(newVersion==DATABASE_NEW_VERSION2OUTSMOKE2){
           // db.execSQL("drop table if exists t_advertisement");
          //  db.execSQL("drop table if exists t_author");    // 增加一个表,创建表语句.

         //   db.execSQL("drop table if exists t_equipment");
         //   db.execSQL("drop table if exists t_images");    // 增加一个表,创建表语句.

         //   db.execSQL("drop table if exists t_product");
         //   db.execSQL("drop table if exists t_productmess");    // 增加一个表,创建表语句.

          //  db.execSQL("drop table if exists t_video");
          //  db.execSQL("drop table if exists Category");    // 增加一个表,创建表语句.

           // sqliteDatabase.execSQL(t_systemvalues);
          //  db.execSQL(t_equipment);
         //   db.execSQL(t_product);
          //  sqliteDatabase.execSQL(t_author);
          //  sqliteDatabase.execSQL(t_advertisement);
          //  db.execSQL(t_video);//升级数据库，建立新的表信息
          //  sqliteDatabase.execSQL(t_productmess);//产品的详情信息
         //   db.execSQL(t_images);//产品的信息


          //  sqliteDatabase.execSQL("insert into t_equipment (equipmentid,equipmenthost,equipmentbase,equipmentname,CreatedTime)values ('0','020000000000000001','020000000000000001','主设备','')");

        //    Log.i(TAG, "从1到2, 升级成功！");



        }

        switch (newVersion) {//进行数据库数据升级  1. 建立临时表  2.创建新表  3.迁移数据  4.删除临时表
            case DATABASE_NEW_VERSION2OUTSMOKE2:
                //db.execSQL(CREATE_TEMP_BOOK);
                //db.execSQL(t_images2);
               // db.execSQL(INSERT_DATA
                // );
                //db.execSQL(DROP_BOOK);
                //db.execSQL(DELETE_COLUMN);//删除某一个字段
                //db.execSQL(ADD_COLUMN);//添加某一个字段



                if( HaveData(db,"t_outsmoke")){
                     db.execSQL(CREATE_TEMP_OUTSMOKE);//更改为临时表
                     db.execSQL(t_outsmoke);//重新创建表
                     db.execSQL(INSERT_DATA_OUTSMOKE);//数据迁移
                     db.execSQL(DROP_OUTSMOKE);//删除临时表
                    Log.i(TAG, "onUpgrade: 迁移表数据");
                }else {
                    db.execSQL(t_outsmoke);//重新创建表
                    Log.i(TAG, "onUpgrade: 创建表");
                }


/*

                db.execSQL(CREATE_TEMP_OUTSMOKE);//更改为临时表
                db.execSQL(t_outsmoke);//重新创建表
                db.execSQL(INSERT_DATA_OUTSMOKE);//数据迁移
                db.execSQL(DROP_OUTSMOKE);//删除临时表



                db.execSQL(CREATE_TEMP_OUTSMOKE);//更改为临时表
                db.execSQL(t_outsmoke);//重新创建表
                db.execSQL(INSERT_DATA_OUTSMOKE);//数据迁移
                db.execSQL(DROP_OUTSMOKE);//删除临时表


                db.execSQL(CREATE_TEMP_OUTSMOKE);//更改为临时表
                db.execSQL(t_outsmoke);//重新创建表
                db.execSQL(INSERT_DATA_OUTSMOKE);//数据迁移
                db.execSQL(DROP_OUTSMOKE);//删除临时表


*/




                break;
        }

       // db.execSQL(t_equipment);



        /**
         *     if (oldVersion == 2) {
         *             String sql_upgrade_1 = "alter table t_message add column isdel bit default 0;";
         *             db.execSQL(sql_upgrade_1);
         *
         *             Log.i("db", "从2到3, 升级成功！");
         *         }
         *         if (oldVersion == 1) {
         *             String sql_upgrade_1 = "alter table t_message add column isdel bit default 0;";
         *             db.execSQL(sql_upgrade_1);
         *
         *             String sql_init_1 = "insert into t_message values(1,'abc','aaa1','11.11','hi1',0)";
         *             db.execSQL(sql_init_1);
         *
         *             String sql_init_2 = "insert into t_message values(2,'abc','aaa2','11.11','hi1',0)";
         *             db.execSQL(sql_init_2);
         *
         *             String sql_init_3 = "insert into t_message values(3,'abc','aaa3','11.11','hi1',0)";
         *             db.execSQL(sql_init_3);
         *
         *             Log.i("db", "从一到3, 升级成功！");
         *
         *         }
         */

    }



    public boolean tabbleIsExist(String tableName) {//判断数据表是否存在
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            String sql = "select count(*) as c from " + DBname+ " where type ='table' and name ='" + tableName + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;//存在
                }
            }

        } catch (Exception e) {
            // TODO: handle exception

        }/*finally {
            cursor.close();
        }*/

        return result;
    }




    // 模拟从3.0 降低回2.0
    // 执行数据库的降级操作
    // 1、只有新版本比旧版本低的时候才会执行
    // 2、如果不执行降级操作，会抛出异常
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 正常来讲大于2.0的，应该有t_message 这张表，且2.0有的字段. 3.0都有
        try {
            // 第一、先把t_message 未来的表，改名
            String rename_sql = "alter table t_message rename to t_message_bak";
            db.execSQL(rename_sql);
         //   Log.i("down", "1.改名成功");

            // 第二、建立2.0的表结构
            String sql_message = "create table t_message (id int primary key,tou1  varchar(50),userName varchar(50),lastMessage varchar(50),datetime  varchar(50))";
            db.execSQL(sql_message);
         //   Log.i("down", "2.建立2.0表结构成功");

            // 第三、把备份的数据，copy到 新建的2.0的表
            String sql_copy = "insert into t_message select id,tou1,userName,lastMessage,datetime from t_message_bak";
            db.execSQL(sql_copy);
          //  Log.i("down", "3.copy到用户数据到 2.0的表");

            // 第四、把备份表drop掉
            String drop_sql = "drop table if exists t_message_bak";
            db.execSQL(drop_sql);
         //   Log.i("down", "4.把备份表drop掉");

        } catch (Exception e) {
            // 失败
          //  Log.i("hi", "降级失败，重新建立");
            String sql_drop_old_table = "drop table if exists t_message";
            db.execSQL(sql_drop_old_table);

            String sql_message = "create table t_message (id int primary key,tou1  varchar(50),userName varchar(50),lastMessage varchar(50),datetime  varchar(50))";
            db.execSQL(sql_message);

            String sql_init_1 = "insert into t_message values (1,'abc','abc1','abcd1','hi1')";
            db.execSQL(sql_init_1);

            String sql_init_2 = "insert into t_message values (2,'abc','abc2','abcd2','hi1')";
            db.execSQL(sql_init_2);

            String sql_init_3 = "insert into t_message values (3,'abc','abc2','abcd2','hi1')";
            db.execSQL(sql_init_3);
        }

    }





    public static boolean HaveData(SQLiteDatabase db,String tablename){
        Cursor cursor;
        boolean a=false;
        cursor = db.rawQuery("select name from sqlite_master where type='table' ", null);
        while(cursor.moveToNext()){
            //遍历出表名
            String name = cursor.getString(0);
            if(name.equals(tablename))
            {
                a=true;
            }
            Log.i("System.out", name);
        }
   /*     if(a) {
            cursor=db.query(tablename,null,null,null,null,null,null);
            //检查是不是空表
            if(cursor.getCount()>0){
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
         */
        return a;

    }

}