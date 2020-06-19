package cn.edu.zust.dmt.hsy.main_module.containers.fragments;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

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

    private ImageView mPhotoImageView;
    private TextView mNameTextView;
    private TextView mPermissionTextView;

    private String PERMISSION;
    private String USER_ID;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_fragment_mine;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {
        mPhotoImageView = findViewById(R.id.mm_fragment_mine_photo_image_view);
        mNameTextView = findViewById(R.id.mm_fragment_mine_name_text_view);
        mPermissionTextView = findViewById(R.id.mm_fragment_mine_permission_text_view);
    }

    @Override
    protected void loadActorsToViews() {
        mPhotoImageView.setImageDrawable(getResources().getDrawable(R.drawable.mm_icon_activity_home_mine));
        mNameTextView.setText(USER_ID);
        mPermissionTextView.setText(PERMISSION);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        PERMISSION = Objects.requireNonNull(args).getString("PERMISSION");
        USER_ID = Objects.requireNonNull(args).getString("USER_ID");
    }
}
