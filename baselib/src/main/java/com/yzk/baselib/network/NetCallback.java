package com.yzk.baselib.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yzk.baselib.tools.LogUtil;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Response;

public abstract class NetCallback<T extends ResponseBase> {

    private static String TAG = "NET_CALLBACK";
    private static Handler handler = new Handler(Looper.getMainLooper());

    final void onResponse(Call request, final Response response, String function) throws IOException {
        try {
            final int resultCode = response.code();
            if (resultCode == 200) {
                Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                T result = parseObject(response, entityClass);
                handlerResponse(result);
            } else {
                handlerFailure(resultCode, response.message());
            }
        } catch (final Exception e) {
            //数据解析异常
            Log.e(TAG, "解析异常：" + e.getMessage());
            handlerFailure(-200, e.getMessage());

        } finally {
            if (response.body() != null) {
                response.close();
            }
        }
    }

    public void onFailure(Call call, IOException e, String function) {
        LogUtil.d(TAG + " OKHTTP: \n fail url: " + function + "\n message:" + e.getMessage());
        //toast net error may be
    }

    public T parseObject(Response response, Class<T> entityClass) {
        try {
            T resObj = null;
            String resString = response.body().string();
            Log.d(TAG, "result: " + resString + "  time:" + System.currentTimeMillis());
            resObj = JSON.parseObject(resString, entityClass);
            return resObj;
        } catch (Exception e) {
            handlerFailure(-100, e.getMessage());
            Log.d(TAG, "解析response: " + e.getMessage());
            return null;
        }
    }


    private void handlerResponse(final T response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onResponse(response);
            }
        });
    }

    private void handlerFailure(int resultCode, final String resultMsg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(resultCode, resultMsg);
            }
        });
    }

    public abstract void onFailure(int resultCode, String resultMsg);

    public abstract void onResponse(T response);

}
