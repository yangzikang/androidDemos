package com.yzk.baselib.network;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yzk.baselib.tools.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtil {

    public static String TAG = "NET_UTIL";

    public static <T extends ResponseBase> void post(String function, Map<String, String> params, final NetCallback<T> callback) {

        params = addCommonParams(params);
        Request request = new Request.Builder()
                .url(NetConstant.BASE_URL + function)
                .post(getRequestBody(params))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() { //在子线程
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e, function);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(call, response, function);
            }
        });

        log(function, params);
    }

    public static <T extends ResponseBase> void get(String function, Map<String, String> params, final NetCallback<T> callback) {

        params = addCommonParams(params);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(NetConstant.BASE_URL + function).newBuilder();
        for (String key : params.keySet()) {
            if (!TextUtils.isEmpty(params.get(key))) {
                urlBuilder.addQueryParameter(key, params.get(key));
            }
        }
        Request request = new Request.Builder().url(urlBuilder.build()).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d(TAG + " OKHTTP: \n fail url: " + function + "\n message:" + e.getMessage());
                callback.onFailure(call, e, function);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //子线程
                callback.onResponse(call, response, function);
            }
        });
        log(function, params);
    }

    private static void log(String function, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(function + "\n");
        sb.append("-----------请求参数------------\n");
        for (String key : params.keySet()) {
            sb.append("|    " + key + " : " + params.get(key) + "\n");
        }
        sb.append("------------------------------");
        Log.d(TAG, sb.toString());
    }

    private static RequestBody getRequestBody(Map<String, String> params) {
        MediaType typeJson = MediaType.parse("application/json; charset=utf-8");
        String jsonString = JSON.toJSONString(params);
        RequestBody requestBody = RequestBody.create(typeJson, jsonString);
        return requestBody;
    }

    private static Map addCommonParams(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

}
