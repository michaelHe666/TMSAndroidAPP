package cn.edu.zust.dmt.hsy.main_module.containers.fragments;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/2/2020 15:48
 **/
public final class MineFragment extends BaseFragment<NullPresenterListener, NullPresenter> {
    @Override
    protected int getLayoutRId() {
        return R.layout.mm_fragment_mine;
    }

    @NonNull
    @Override
    protected Class<NullPresenter> getViewModelClass() {
        return NullPresenter.class;
    }

    @NonNull
    @Override
    protected NullPresenterListener getViewModelListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void loadActorsToViews() {

    }
}
