package cn.edu.zust.dmt.hsy.common_module.containers.activities;

import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.BaseViewModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/1/2020 19:24
 **/
public abstract class MyActivity<T extends BaseViewModelListener, K extends BaseViewModel<T>>
        extends BaseActivity<T, K> {
}
