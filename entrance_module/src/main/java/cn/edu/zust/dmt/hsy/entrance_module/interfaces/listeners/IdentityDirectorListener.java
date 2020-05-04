package cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseDirectorListener;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;

/**
 * @projectName TMS
 * @author MR.M
 * @since 4/6/2020 15:15
 * @version 1.0
 * @description $
 *
 * copyright(c) all rights reserved by MR.M
 **/
public interface IdentityDirectorListener extends BaseDirectorListener {

    /**
     * @return container ViewGroup for baseFragment
     */
    @NonNull
    ViewGroup getIdentityFragmentContainer();

    /**
     * @return MyTopBar view for
     */
    @NonNull
    cm_MyTopBar getMyTopBar();
}
