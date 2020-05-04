package cn.edu.zust.dmt.hsy.my_base_library.interfaces.others;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.containers.dialogs.BaseDialog;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;

/**
 * @author MR.M
 * @projectName TMS
 * @packageName cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners
 * @description $
 * @time 4/6/2020 10:05
 * copyright(c) all rights reserved by MR.M
 **/
public interface BaseContainerListener {
    /**
     * @param path     enum path in {@link MyRouterPaths}
     * @param myExtras extra info for path targetClass
     */
    void callMyRouter(@NonNull MyRouterPaths path, @Nullable Bundle myExtras);

    /**
     * @param targetClass      class of {@link BaseFragment} wanted on stage
     * @param containerViewRId targetClass instance view container RId
     * @param myExtras         {@link Bundle} contains extra information for targetClass
     */
    void showBaseFragment(@NonNull Class<? extends BaseFragment> targetClass, @IdRes int containerViewRId
            , @Nullable Bundle myExtras);

    /**
     * @param targetClass class of {@link BaseDialog} wanted on {@link BaseActivity}
     * @param myExtras    {@link Bundle} contains extra information for targetClass
     */
    void showBaseDialog(@NonNull Class<? extends BaseDialog> targetClass, @Nullable Bundle myExtras);

    /**
     * @param targetClass class of {@link BaseDialog} wanted on {@link BaseActivity}
     */
    void dismissBaseDialog(@NonNull Class<? extends BaseDialog> targetClass);

    /**
     * @param myExtrasParser add new parser for myExtras
     */
    void addMyExtrasParser(@NonNull BaseExtrasListener myExtrasParser);

    //todo:whether can I remove following ugly codes?

    /**
     * @description use this method to trigger {@link Activity#onBackPressed()}
     */
    void triggerBackPressedEvent();
}
