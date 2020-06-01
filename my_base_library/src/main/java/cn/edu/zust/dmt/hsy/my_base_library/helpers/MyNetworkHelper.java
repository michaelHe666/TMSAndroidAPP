package cn.edu.zust.dmt.hsy.my_base_library.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseNetworkCallback;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.request.BaseNetworkRequest;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.request.BaseRequestData;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.response.BaseNetworkResponse;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.response.BaseResponseData;
import cn.edu.zust.dmt.hsy.my_base_library.utils.MyHttpUtils;

import static java.lang.Thread.sleep;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/23/2020 16:18
 **/
public final class MyNetworkHelper {
    /**
     * @description this method should only used for {@link MyNetworkHelperHolder#INSTANCE}
     */
    private MyNetworkHelper() {
    }

    /**
     * @description instance holder for {@link MyNetworkHelper}
     */
    private static class MyNetworkHelperHolder {
        /**
         * @description {@link MyNetworkHelperHolder} should not be initialized
         */
        private MyNetworkHelperHolder() {
            throw new AssertionError();
        }

        private static final MyNetworkHelper INSTANCE = new MyNetworkHelper();
    }

    /**
     * @return singleton instance of {@link MyNetworkHelper}
     */
    public static MyNetworkHelper getInstance() {
        return MyNetworkHelperHolder.INSTANCE;
    }

    public <T extends BaseRequestData, K extends BaseResponseData> void post(
            @NonNull final String path, @NonNull final BaseNetworkRequest<T> baseNetworkRequest
            , @NonNull final BaseNetworkCallback<K> baseNetworkCallback) {
        final Type type = baseNetworkCallback.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            final Class clazz = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            if (BaseResponseData.class.isAssignableFrom(clazz)) {
                final Class<K> modelClass = (Class<K>) clazz;
                MyThreadHelper.INSTANCE.runMyTask(new MyNetworkRunnable<>(path, baseNetworkRequest,
                        new MyNetworkHandler<>(baseNetworkCallback), new MyNetworkResponseType<>(modelClass)));
            }
        }
    }

    /**
     * @description handler for {@link BaseNetworkCallback}
     */
    private static final class MyNetworkHandler<T extends BaseResponseData> {
        private final WeakReference<BaseNetworkCallback<T>> mCallbackWeakReference;

        private MyNetworkHandler(@NonNull final BaseNetworkCallback<T> callbackWeakReference) {
            mCallbackWeakReference = new WeakReference<>(callbackWeakReference);
        }

        private void handleResponse(@NonNull final BaseNetworkResponse<T> response) {
            final BaseNetworkCallback<T> callback = mCallbackWeakReference.get();
            if (callback == null) {
                MyErrorHelper.showMyNullPointerException("BaseNetworkCallback instance no longer exist!");
            } else {
                callback.onResult(response);
                callback.onComplete();
            }
        }
    }

    /**
     * @description package contains task for {@link MyThreadHelper}
     */
    private static final class MyNetworkRunnable<T extends BaseRequestData, K extends BaseResponseData>
            implements Runnable {
        private final String mPath;
        private final BaseNetworkRequest<T> mBaseNetworkRequest;
        private final WeakReference<MyNetworkHandler<K>> mHandlerWeakReference;
        private final MyNetworkResponseType<K> mResponseType;

        private MyNetworkRunnable(@NonNull final String path
                , @NonNull final BaseNetworkRequest<T> baseNetworkRequest
                , @NonNull final MyNetworkHandler<K> handlerWeakReference
                , @NonNull final MyNetworkResponseType<K> responseType) {
            mPath = path;
            mBaseNetworkRequest = baseNetworkRequest;
            mHandlerWeakReference = new WeakReference<>(handlerWeakReference);
            mResponseType = responseType;
        }

        @Override
        public void run() {
            final MyNetworkHandler<K> handler = mHandlerWeakReference.get();
            if (handler != null) {
                final Gson jsonUtils = new Gson();
                Log.d("ppx", "start net");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("ppx", "end net");
                final String result = MyHttpUtils.getInstance()
                        .postReturnString(mPath, jsonUtils.toJson(mBaseNetworkRequest));
                final BaseNetworkResponse<K> baseNetworkResponse = jsonUtils.fromJson(result, mResponseType);
                if (baseNetworkResponse != null) {
                    handler.handleResponse(baseNetworkResponse);
                } else {
                    MyErrorHelper.showMyNullPointerException("MyNetworkPostCallbackHandler get empty msg!");
                }
            } else {
                MyErrorHelper.showMyNullPointerException("MyNetworkPostCallbackHandler no longer exist!");
            }
        }
    }

    //todo:build my error&exception process framework

    /**
     * @description type builder for {@link BaseNetworkResponse}
     */
    private static final class MyNetworkResponseType<T extends BaseResponseData>
            implements ParameterizedType {
        private final Class<T> mModelClass;

        private MyNetworkResponseType(@NonNull final Class<T> clazz) {
            mModelClass = clazz;
        }

        @NonNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{mModelClass};
        }

        @NonNull
        @Override
        public Type getRawType() {
            return BaseNetworkResponse.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}