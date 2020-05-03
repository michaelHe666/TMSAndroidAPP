package cn.edu.zust.dmt.hsy.entrancemodule.interfaces.listeners;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners.BaseDirectorListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.combined.mbl_MyFormBar;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 10:35
 **/
public interface LoginDirectorListener extends BaseDirectorListener {
    /**
     * @return {@link mbl_MyFormBar} for voucher input
     */
    @NonNull
    mbl_MyFormBar getVoucherBar();

    /**
     * @return {@link mbl_MyFormBar} for token input
     */
    @NonNull
    mbl_MyFormBar getTokenBar();

    /**
     * @return {@link View} for trigger login event
     */
    @NonNull
    View getLoginRequestView();
}
