package cn.edu.zust.dmt.hsy.my_base_library.viewmodels;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseViewModelListener;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/24/2020 14:09
 **/
public abstract class BaseViewModel<T extends BaseViewModelListener> {

    private WeakReference<T> mViewModelListenerHandler = null;

    /**
     * @description {@link BaseViewModel} should only be initialized in child class
     */
    protected BaseViewModel() {
    }

    /**
     * @param viewModelListener expected current listener as {@link #mViewModelListenerHandler}
     */
    public final void setCurrentListener(@NonNull final T viewModelListener) {
        if (mViewModelListenerHandler == null || mViewModelListenerHandler.get() != viewModelListener) {
            mViewModelListenerHandler = new WeakReference<>(viewModelListener);
            loadViewModelToListener();
        }
    }

    /**
     * @return get {@link BaseViewModelListener} safely from {@link #mViewModelListenerHandler}
     */
    @NonNull
    protected final T getViewModelListener() {
        if (mViewModelListenerHandler != null) {
            T viewModelListener = mViewModelListenerHandler.get();
            if (viewModelListener != null) {
                return viewModelListener;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * @description release handler of {@link #mViewModelListenerHandler}
     */
    public final void onViewModelDestroyed() {
        mViewModelListenerHandler = null;
    }

    /**
     * @description bind {@link BaseViewModel} with current listener
     */
    protected abstract void loadViewModelToListener();
}
