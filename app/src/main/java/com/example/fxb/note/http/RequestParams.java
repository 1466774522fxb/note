package com.example.fxb.note.http;

import android.net.Uri;

import com.example.fxb.note.db.Preferences;
/*import com.gammainfo.cycares.db.Preferences;*/

import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;

public class RequestParams extends com.loopj.android.http.RequestParams {

    private static final long serialVersionUID = -5579602099273482314L;

    public RequestParams() {
        super();
       /* put("device_id", Preferences.getDeviceId());
        put("api_version", "1.0");
        put("platform", "android");
        put("app_version", Preferences.getVersionName());
        put("token", Preferences.getAccessToken());
        put("timestamp", System.currentTimeMillis());*/
    }

    /**
     * key=encode(value)&key=encode(value)
     */
    @Override
    public String toString() {
        List<BasicNameValuePair> params = this.getParamsList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName()).append("=")
                    .append(Uri.encode(params.get(i).getValue())).append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public RequestParams(Map<String, String> arg0) throws Exception {
        throw new Exception("不支持");
    }

    public RequestParams(Object... arg0) throws Exception {
        throw new Exception("不支持");
    }

    public RequestParams(String arg0, String arg1) throws Exception {
        throw new Exception("不支持");
    }

}
