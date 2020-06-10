package cn.edu.zust.dmt.hsy.my_base_library.presenters;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.BasePresenterListener;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/24/2020 14:09
 **/
public abstract class BasePresenter<T extends BasePresenterListener> {

    private WeakReference<T> mViewModelListenerHandler = null;

    /**
     * @description {@link BasePresenter} should only be initialized in child class
     */
    protected BasePresenter() {
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
     * @return get {@link BasePresenterListener} safely from {@link #mViewModelListenerHandler}
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
     * @description bind {@link BasePresenter} with current listener
     */
    protected abstract void loadViewModelToListener();
}
