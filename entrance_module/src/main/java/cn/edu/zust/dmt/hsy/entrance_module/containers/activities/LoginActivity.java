package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyFormBar;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.response.LoginResponseData;
import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.LoginViewModelListener;
import cn.edu.zust.dmt.hsy.entrance_module.viewmodels.LoginViewModel;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.response.BaseNetworkResponse;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseNetworkCallback;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 15:53
 **/
@MyRouter(path = MyRouterPaths.LOGIN_PATH)
public final class LoginActivity extends BaseActivity {

    private final LoginActivity THIS = this;

    private cm_MyTopBar mTopBar;
    private cm_MyFormBar mVoucherBar;
    private cm_MyFormBar mPasswordBar;
    private Button mConfirmButton;

    private LoginViewModel mLoginViewModel = new LoginViewModel();

    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_login;
    }

    @Override
    protected void findViews() {
        mTopBar = findViewById(R.id.em_activity_login_my_top_bar);
        mVoucherBar = findViewById(R.id.em_activity_login_voucher_my_form_bar);
        mConfirmButton = findViewById(R.id.em_activity_login_confirm_button);
    }

    @Override
    protected void loadActorsToViews() {
        loadMyTopBarActors();
        loadMyVoucherBarActor();
    }

    @Override
    protected void refreshViewModelListener() {
        mLoginViewModel.setViewModelListener(new LoginViewModelListener() {
            @NonNull
            @Override
            public cm_MyFormBar getVoucherTextView() {
                return mVoucherBar;
            }

            @NonNull
            @Override
            public cm_MyFormBar getPasswordTextView() {
                return mPasswordBar;
            }

            @NonNull
            @Override
            public View getLoginClickableView() {
                return mConfirmButton;
            }

            @NonNull
            @Override
            public BaseNetworkCallback<LoginResponseData> getLoginCallback() {
                return new BaseNetworkCallback<LoginResponseData>() {
                    @Override
                    public void onResult(@NonNull BaseNetworkResponse<LoginResponseData> response) {
                        callMyRouter(MyRouterPaths.HOME_PATH, null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
            }
        });
    }

    private void loadMyTopBarActors() {
        mTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.onBackPressed();
            }
        });
    }

    private void loadMyVoucherBarActor() {
        mVoucherBar.setContentWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
