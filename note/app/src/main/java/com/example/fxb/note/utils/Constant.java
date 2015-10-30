package com.example.fxb.note.utils;

/*import com.gammainfo.cycares.BuildConfig;*/

public final class Constant {
   /* public static final boolean DEBUG = BuildConfig.DEBUG;*/
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    //收藏类型（1：医生，2：医院，3：帖子，4：资讯，5：产品）
    public static final int FAV_TYPE_DOCTOR = 1;
    public static final int FAV_TYPE_HOSPITAL = 2;
    public static final int FAV_TYPE_POSTS = 3;
    public static final int FAV_TYPE_NEWS = 4;
    public static final int FAV_TYPE_PRODUCT = 5;

    // 修改代码值
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILURE = 1;
    public static final int CODE_TIMEOUT = 2;
    public static final int CODE_DATA_ERROR = 3;
    public static final int CODE_DB_ERROR = 4;
    public static final int CODE_SERVICE_ERROR = 5;
    public static final int CODE_USER_PERMISSIONS = 6;
    public static final int CODE_SERVICE_UNAVAILABLE = 7;
    public static final int CODE_MISSING_METHOD = 8;
    public static final int CODE_ASIGN_UNVALID = 9;
    public static final int CODE_MISSING_API_VERSION = 10;
    public static final int CODE_API_VERSION_ERROR = 11;
    public static final int CODE_TOKEN_EXPIRES = 12;
    public static final int CODE_DATA_CONFLICT = 13;
    public static final int CODE_PARAM_MISSING = 14;
    public static final int CODE_NEED_CHARGE = 15;
    public static final int CODE_USER_LOCKED = 16;
    public static final int CODE_PACKAGE_ERROR = 17;
    public static final int CODE_RELOGIN = 18;

    public static final int PAGE_SIZE = 4;
    // api url
    private static final String API_URL_PRE = Constant.DEBUG ? "http://192.168.1.200:8090/Api/Android/"
            : "http://api.cycares.com/Api/Android/";
    public static final String API_GET_BANNER = API_URL_PRE
            + "Banner/banners";
    public static final String API_SERVICE_SERVICES = API_URL_PRE
            + "Service/services";
    public static final String API_LOGIN = API_URL_PRE
            + "User/login";
    public static final String API_GET_VALID_CODE = API_URL_PRE
            + "User/code";
    public static final String API_USER_REGISTER = API_URL_PRE
            + "User/register";
    public static final String API_GET_HOT_PRODUCT = API_URL_PRE
            + "Product/hotProducts";
    public static final String API_FORGETPASS_REPASS = API_URL_PRE
            + "User/retrieve_password";
    public static final String API_PRODUCT_RELAPRODUCTS = API_URL_PRE
            + "Product/relaProducts";
    public static final String API_GET_PRODUCT_DETAIL = API_URL_PRE
            + "Product/productDetail";
    public static final String API_CHANGE_REPASS = API_URL_PRE
            + "User/password";
    public static final String API_FAVORITE_LIST = API_URL_PRE
            + "User/favorite";
    public static final String API_GET_HOSPITAL_DETAIL = API_URL_PRE
            + "Hospital/detail";
    public static final String API_GET_HOSPITAL_PRODUCT = API_URL_PRE
            + "Hospital/products";
    public static final String API_GET_HOSPITAL_DOCTOR = API_URL_PRE
            + "Hospital/doctors";
    public static final String API_USER_SETTING = API_URL_PRE
            + "User/setting";
}
