package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/12/2020 9:52
 **/
@MyRouter(path = MyRouterPaths.HOME_PATH)
public final class HomeActivity extends MyActivity<NullPresenterListener, NullPresenter> {

    private cm_MyTopBar mTopBar;
    private ViewPager2 mViewPager2;
    private ImageView mFrontImage;
    private ImageView mManagementImage;
    private ImageView mMessageImage;
    private ImageView mMineImage;
    private TextView mFrontText;
    private TextView mManagementText;
    private TextView mMessageText;
    private TextView mMineText;

    private MineFragment mMineFragment;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_activity_home;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {
        mTopBar = findViewById(R.id.mm_activity_home_my_top_bar);
        mViewPager2 = findViewById(R.id.mm_activity_home_view_pager2);
        mFrontImage = findViewById(R.id.mm_activity_home_front_image_view);
        mManagementImage = findViewById(R.id.mm_activity_home_management_image_view);
        mMessageImage = findViewById(R.id.mm_activity_home_message_image_view);
        mMineImage = findViewById(R.id.mm_activity_home_mine_image_view);
        mFrontText = findViewById(R.id.mm_activity_home_front_text_view);
        mManagementText = findViewById(R.id.mm_activity_home_management_text_view);
        mMessageText = findViewById(R.id.mm_activity_home_message_text_view);
        mMineText = findViewById(R.id.mm_activity_home_mine_text_view);
    }

    @Override
    protected void loadActorsToViews() {
        addHomeExtrasParser();
        mTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyRouter(MyRouterPaths.TEST_PATH, null);
            }
        });
        loadActorToViewPager();
        loadActorToBottomViews();
    }

    /**
     * @description initialize {@link #mViewPager2}
     */
    private void loadActorToViewPager() {
        final List<BaseFragment<?, ?>> fragmentList = new ArrayList<>();
        fragmentList.add(new FrontFragment());
        fragmentList.add(new ManagementFragment());
        fragmentList.add(new MessageFragment());
        mMineFragment = new MineFragment();
        fragmentList.add(mMineFragment);
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
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mTopBar.setTitle(R.string.mm_string_activity_home_front);
                        mTopBar.showRightButton(R.drawable.cm_icon_universal_settings);
                        mFrontImage.setImageResource(R.drawable.mm_icon_activity_home_front_selected);
                        mManagementImage.setImageResource(R.drawable.mm_icon_activity_home_management);
                        mMessageImage.setImageResource(R.drawable.mm_icon_activity_home_message);
                        mMineImage.setImageResource(R.drawable.mm_icon_activity_home_mine);
                        mFrontText.setTextColor(getResources()
                                .getColor(R.color.mm_color_activity_home_bottom_icon_selected));
                        mManagementText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mMessageText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mMineText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        break;
                    case 1:
                        mTopBar.setTitle(R.string.mm_string_activity_home_management);
                        mTopBar.showRightButton(R.drawable.cm_icon_universal_settings);
                        mFrontImage.setImageResource(R.drawable.mm_icon_activity_home_front);
                        mManagementImage.setImageResource(R.drawable.mm_icon_activity_home_management_selected);
                        mMessageImage.setImageResource(R.drawable.mm_icon_activity_home_message);
                        mMineImage.setImageResource(R.drawable.mm_icon_activity_home_mine);
                        mFrontText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mManagementText.setTextColor(getResources()
                                .getColor(R.color.mm_color_activity_home_bottom_icon_selected));
                        mMessageText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mMineText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        break;
                    case 2:
                        mTopBar.setTitle(R.string.mm_string_activity_home_message);
                        mTopBar.showRightButton(R.drawable.cm_icon_universal_settings);
                        mFrontImage.setImageResource(R.drawable.mm_icon_activity_home_front);
                        mManagementImage.setImageResource(R.drawable.mm_icon_activity_home_management);
                        mMessageImage.setImageResource(R.drawable.mm_icon_activity_home_message_selected);
                        mMineImage.setImageResource(R.drawable.mm_icon_activity_home_mine);
                        mFrontText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mManagementText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mMessageText.setTextColor(getResources()
                                .getColor(R.color.mm_color_activity_home_bottom_icon_selected));
                        mMineText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        break;
                    case 3:
                        mTopBar.setTitle(R.string.mm_string_activity_home_mine);
                        mTopBar.hideRightButton();
                        mFrontImage.setImageResource(R.drawable.mm_icon_activity_home_front);
                        mManagementImage.setImageResource(R.drawable.mm_icon_activity_home_management);
                        mMessageImage.setImageResource(R.drawable.mm_icon_activity_home_message);
                        mMineImage.setImageResource(R.drawable.mm_icon_activity_home_mine_selected);
                        mFrontText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mManagementText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mMessageText.setTextColor(getResources()
                                .getColor(R.color.cm_color_universal_app_theme_color));
                        mMineText.setTextColor(getResources()
                                .getColor(R.color.mm_color_activity_home_bottom_icon_selected));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        mViewPager2.setCurrentItem(0);
    }

    /**
     * @description initialize {@link #mFrontImage ,#mManagementView,#mMessageView,#mMineView}
     */
    private void loadActorToBottomViews() {
        mFrontImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager2.setCurrentItem(0);
            }
        });
        mManagementImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager2.setCurrentItem(1);
            }
        });
        mMessageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager2.setCurrentItem(2);
            }
        });
        mMineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager2.setCurrentItem(3);
            }
        });
    }

    private void addHomeExtrasParser() {
        addMyExtrasParser(new BaseExtrasListener() {
            @Override
            public void parseMyExtras(@NonNull Bundle myExtras) {
                Bundle bundle = new Bundle();
                bundle.putString("PERMISSION", myExtras.getString("PERMISSION"));
                bundle.putString("USER_ID", myExtras.getString("USER_ID"));
                mMineFragment.setArguments(bundle);
            }
        });
    }
}
