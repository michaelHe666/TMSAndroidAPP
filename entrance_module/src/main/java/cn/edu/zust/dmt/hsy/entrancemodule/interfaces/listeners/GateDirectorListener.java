package cn.edu.zust.dmt.hsy.entrancemodule.interfaces.listeners;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners.BaseDirectorListener;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/5/2020 21:54
 **/
public interface GateDirectorListener extends BaseDirectorListener {

    /**
     * @return view which could trigger login router event
     */
    @NonNull
    View getLoginRouterView();

    /**
     * @return view which could trigger register router event
     */
    @NonNull
    View getRegisterRouterView();
}
