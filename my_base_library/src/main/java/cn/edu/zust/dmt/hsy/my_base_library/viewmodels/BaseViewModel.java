package cn.edu.zust.dmt.hsy.my_base_library.viewmodels;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseViewModelListener;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/24/2020 14:09
 **/
public abstract class BaseViewModel<T extends BaseViewModelListener> {

    private T mViewModelListener = null;

    /**
     * @description {@link BaseViewModel} should only be initialized in child class
     */
    protected BaseViewModel() {
    }

    /**
     * @param viewModelListener expected recent listener holder
     */
    public final void setViewModelListener(@NonNull final T viewModelListener) {
        if (mViewModelListener != viewModelListener) {
            mViewModelListener = viewModelListener;
            loadViewModelToListener();
        }
    }

    /**
     * @return {@link #mViewModelListener} for child class
     */
    @NonNull
    protected final T getViewModelListener() {
        return mViewModelListener;
    }

    /**
     * @description release handler of {@link #mViewModelListener}
     */
    public final void onViewModelDestroy() {
        mViewModelListener = null;
    }

    protected abstract void loadViewModelToListener();
}
