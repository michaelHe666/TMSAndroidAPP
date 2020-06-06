package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.containers.fragments.FrontFragment;
import cn.edu.zust.dmt.hsy.main_module.containers.fragments.ManagementFragment;
import cn.edu.zust.dmt.hsy.main_module.containers.fragments.MessageFragment;
import cn.edu.zust.dmt.hsy.main_module.containers.fragments.MineFragment;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.NullViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.NullViewModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/12/2020 9:52
 **/
@MyRouter(path = MyRouterPaths.HOME_PATH)
public final class HomeActivity extends MyActivity<NullViewModelListener, NullViewModel> {

    private cm_MyTopBar mTopBar;
    private ViewPager2 mViewPager2;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_activity_home;
    }

    @NonNull
    @Override
    protected Class<NullViewModel> getViewModelClass() {
        return NullViewModel.class;
    }

    @NonNull
    @Override
    protected NullViewModelListener getViewModelListener() {
        return new NullViewModelListener() {
        };
    }

    @Override
    protected void findViews() {
        mTopBar = findViewById(R.id.mm_activity_home_my_top_bar);
        mViewPager2 = findViewById(R.id.mm_activity_home_container_view_pager2);
    }

    @Override
    protected void loadActorsToViews() {
        mTopBar.setTitle(R.string.mm_string_activity_home_front);
        mTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyRouter(MyRouterPaths.OPTIONS_PATH, null);
            }
        });
        final List<BaseFragment<?, ?>> fragmentList = new ArrayList<>();
        fragmentList.add(new FrontFragment());
        fragmentList.add(new ManagementFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new MineFragment());
        mViewPager2.setAdapter(new FragmentStateAdapter(this) {
            private List<BaseFragment<?, ?>> mFragmentList = fragmentList;

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return mFragmentList.size();
            }
        });
        mViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }
}
