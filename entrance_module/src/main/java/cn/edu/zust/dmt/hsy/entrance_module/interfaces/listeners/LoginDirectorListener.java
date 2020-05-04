package cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseDirectorListener;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyFormBar;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 10:35
 **/
public interface LoginDirectorListener extends BaseDirectorListener {
    /**
     * @return {@link cm_MyFormBar} for voucher input
     */
    @NonNull
    cm_MyFormBar getVoucherBar();

    /**
     * @return {@link cm_MyFormBar} for token input
     */
    @NonNull
    cm_MyFormBar getTokenBar();

    /**
     * @return {@link View} for trigger login event
     */
    @NonNull
    View getLoginRequestView();
}
