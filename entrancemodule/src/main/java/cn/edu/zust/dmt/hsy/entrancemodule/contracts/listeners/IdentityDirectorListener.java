package cn.edu.zust.dmt.hsy.entrancemodule.contracts.listeners;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.listeners.BaseDirectorListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.combined.mbl_MyTopBar;

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
    mbl_MyTopBar getMyTopBar();
}
