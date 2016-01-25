package com.example.fxb.note.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

/*import com.gammainfo.cycares.model.User;*/

public final class Preferences {
    private static SharedPreferences sSHARED_REFERENCES = null;
    private static Context sAPPLICATION_CONTEXT;
    private static String sDEVICE_ID;
    private static String sVERSION_NAME;

    public Preferences() {
    }

    public static void init(Context context) {
        if (sSHARED_REFERENCES == null) {
            sAPPLICATION_CONTEXT = context.getApplicationContext();
            sSHARED_REFERENCES = PreferenceManager
                    .getDefaultSharedPreferences(sAPPLICATION_CONTEXT);

        }
    }

    public static Context getApplicationContext() {
        return sAPPLICATION_CONTEXT;
    }

    public static SharedPreferences getSharedPreferences() {
        return sSHARED_REFERENCES;
    }

    public static boolean isShowWelcome() {
        try {
            PackageInfo info = sAPPLICATION_CONTEXT.getPackageManager()
                    .getPackageInfo(sAPPLICATION_CONTEXT.getPackageName(), 0);
            // 当前版本的版本号
            int versionCode = info.versionCode;
            int saveVersionCode = sSHARED_REFERENCES.getInt("version_code", 0);
            return versionCode != saveVersionCode;
        } catch (NameNotFoundException e) {
            return true;
        }
    }

    public static boolean updateVersionCode() {
        try {
            PackageInfo info = sAPPLICATION_CONTEXT.getPackageManager()
                    .getPackageInfo(sAPPLICATION_CONTEXT.getPackageName(), 0);
            // 当前版本的版本号
            int versionCode = info.versionCode;
            Editor editor = sSHARED_REFERENCES.edit();
            editor.putInt("version_code", versionCode);
            return editor.commit();
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String getDeviceId() {
        if (sDEVICE_ID == null) {
            sDEVICE_ID = ((TelephonyManager) sAPPLICATION_CONTEXT
                    .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }
        return sDEVICE_ID;
    }

    public static String getVersionName() {
        if (sVERSION_NAME == null) {
            try {
                PackageInfo info = sAPPLICATION_CONTEXT.getPackageManager()
                        .getPackageInfo(sAPPLICATION_CONTEXT.getPackageName(),
                                0);
                // 当前应用的版本名称
                sVERSION_NAME = info.versionName;
                // 当前版本的版本号
                // int versionCode = info.versionCode;

                // 当前版本的包名
                // String packageNames = info.packageName;
            } catch (NameNotFoundException e) {
            }
        }
        return sVERSION_NAME;
    }


    public static String getAccessToken() {
        return sSHARED_REFERENCES.getString("access_token", null);
    }


    public static boolean setAccessToken(String accessToken) {
        Editor editor = sSHARED_REFERENCES.edit();
        editor.putString("access_token", accessToken);
        if (editor.commit()) {
            return true;
        }
        return false;
    }


    public static boolean isLogin() {
        return sSHARED_REFERENCES.getString("access_token", null) == null ? false : true;
    }

    public static boolean clear() {
        // 清除数据库表内容
        DatabaseManager.clear();
        // clear token & user info & order settting
        Editor editor = sSHARED_REFERENCES.edit();
        editor.remove("access_token");
        editor.remove("login_user");
        return editor.commit();
    }

    /*public static boolean setLoginUser(User user) {
        Editor editor = sSHARED_REFERENCES.edit();
        editor.putString("login_user", User.toJson(user));
        if (editor.commit()) {
            return true;
        }
        return false;
    }

    public static User getLoginUser() {
        //TODO 测试，如果字段不一致会不会抛异常
        return User.fromJson(sSHARED_REFERENCES.getString("login_user", null));
    }*/

}
