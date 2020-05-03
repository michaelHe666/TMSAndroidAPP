package cn.edu.zust.dmt.hsy.mybaselibrary.presenters.fairs;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseFairCallback;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseNetworkCallback;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.suppliers.BaseFairSupplier;
import cn.edu.zust.dmt.hsy.mybaselibrary.models.remote.response.BaseResponseModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/26/2020 9:42
 **/
public abstract class BaseFair<T extends BaseFairSupplier<?>> {
    protected static abstract class SafeNetWorkCallBack<K extends BaseResponseModel>
            implements BaseNetworkCallback<K> {
        private final WeakReference<BaseFairCallback<K>> mWeakReference;

        protected SafeNetWorkCallBack(@NonNull final BaseFairCallback<K> baseNetworkCallback) {
            mWeakReference = new WeakReference<>(baseNetworkCallback);
        }

        protected final BaseFairCallback<K> getBaseFairCallBack() {
            final BaseFairCallback<K> baseFairCallback = mWeakReference.get();
            if (baseFairCallback == null) {
                throw new NullPointerException("BaseNetworkCallback no longer exist!");
            } else {
                return baseFairCallback;
            }
        }

        @Override
        public final void onError(@NonNull Throwable e) {
            getBaseFairCallBack().onError(e);
        }

        @Override
        public final void onComplete() {
            getBaseFairCallBack().onComplete();
        }
    }

    /**
     * @param currentFairSupplier specialized supplier for implement fair
     */
    public abstract void loadFair(@NonNull T currentFairSupplier);
}