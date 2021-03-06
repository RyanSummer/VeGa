package com.jingwei.vega;

import android.os.Environment;

public class Constants {

    /******************************** HOST **************************/
    public static String HOST = "https://api.vegarhome.com/api/";//服务器host
    public static String IMAGEHOST = "https://image.vegarhome.com/";//图片host

    /******************************** 搜索条件 **************************/
    public static Integer DEMOACTIVITY = 0;
    public static Integer HOMEFRAGMENT = 1;
    public static Integer GOODSLIBACTIVITY = 2;
    public static Integer CLASSIFICATIONFRAGMENT = 3;
    public static Integer FOCUSFRAGMENT = 4;

    public static Integer MARKSHOP = 5;
    public static Integer MYCOLLECT = 6;


    /******************************** 图片路径 **************************/
    public static String IMAGEPATH = Environment.getExternalStorageDirectory().getPath() + "/vegar";


    /******************************** 微信支付 **************************/
    public static String WX_APPID = "wxb0cc9488e32e0271";
}
