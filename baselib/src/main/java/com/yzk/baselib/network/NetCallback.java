package com.yzk.baselib.network;

import android.os.Handler;
import android.os.Looper;

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
                T result = parseObject(response, entityClass, function);
                handlerResponse(result);
            } else {
                handlerFailure(resultCode, response.message());
            }
        } catch (final Exception e) {
            //数据解析异常
            LogUtil.w(TAG + " onResponseError：" + e.getMessage());
            handlerFailure(NetConstant.ON_RESPONSE_ERROR, e.getMessage());

        } finally {
            if (response.body() != null) {
                response.close();
            }
        }
    }

    public void onFailure(Call call, IOException e, String function) {
        LogUtil.d(TAG + " OKHTTP_ERROR: \n fail url: " + function + "\n message:" + e.getMessage());
        //toast net error may be
        handlerFailure(NetConstant.ON_OKHTTP_REQUEST_ERROR, e.getMessage());
    }

    public T parseObject(Response response, Class<T> entityClass, String function) {
        try {
            T resObj = null;
            String resString = response.body().string();
            LogUtil.all(TAG + " OKHTTP_RESPONSE: \n function:" + function + "\n result:" + resString);
            resObj = JSON.parseObject(resString, entityClass);
            return resObj;
        } catch (Exception e) {
            LogUtil.d(TAG + " 转对象失败: " + e.getMessage());
            handlerFailure(NetConstant.ON_PARSE_OBJECT_ERROR, e.getMessage());
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
