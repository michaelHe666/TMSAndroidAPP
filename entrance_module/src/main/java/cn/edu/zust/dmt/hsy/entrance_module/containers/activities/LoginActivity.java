package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyFormBar;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.response.LoginResponseData;
import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.LoginViewModelListener;
import cn.edu.zust.dmt.hsy.entrance_module.viewmodels.LoginViewModel;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
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
public final class LoginActivity extends MyActivity<LoginViewModelListener, LoginViewModel> {

    private final LoginActivity THIS = this;

    private cm_MyTopBar mTopBar;
    private cm_MyFormBar mVoucherBar;
    private cm_MyFormBar mPasswordBar;
    private Button mConfirmButton;

    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_login;
    }

    @NonNull
    @Override
    protected Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @NonNull
    @Override
    protected LoginViewModelListener getViewModelListener() {
        return new LoginViewModelListener() {
            @NonNull
            @Override
            public final cm_MyFormBar getVoucherBar() {
                return mVoucherBar;
            }

            @NonNull
            @Override
            public final cm_MyFormBar getPasswordBar() {
                return mPasswordBar;
            }

            @NonNull
            @Override
            public final View getLoginClickableView() {
                return mConfirmButton;
            }

            @NonNull
            @Override
            public final BaseNetworkCallback<LoginResponseData> getLoginCallback() {
//                return new BaseNetworkCallback<LoginResponseData>() {
//                    @Override
//                    public void onResult(@NonNull BaseNetworkResponse<LoginResponseData> response) {
//                        callMyRouter(MyRouterPaths.HOME_PATH, null);
//                        showMyToast(response.toString());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                };
                return new MyBaseNetworkCallBack(THIS);
            }

            @Override
            public final void sendVoucherInputError() {
                showMyToast(R.string.em_string_universal_voucher_error);
            }

            @Override
            public final void sendPasswordInputError() {
                showMyToast(R.string.em_string_universal_password_error);
            }

            @Override
            public final void sendLoginError() {
                showMyToast(R.string.em_string_login_error);
            }
        };
    }

    public static final class MyBaseNetworkCallBack implements BaseNetworkCallback<LoginResponseData> {
        private final LoginActivity mLoginActivity;

        public MyBaseNetworkCallBack(LoginActivity loginActivity) {
            mLoginActivity = loginActivity;
        }

        @Override
        public void onResult(@NonNull BaseNetworkResponse<LoginResponseData> response) {
            mLoginActivity.showMyToast(response.toString());
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    @Override
    protected void findViews() {
        mTopBar = findViewById(R.id.em_activity_login_my_top_bar);
        mVoucherBar = findViewById(R.id.em_activity_login_voucher_my_form_bar);
        mPasswordBar = findViewById(R.id.em_activity_login_password_my_form_bar);
        mConfirmButton = findViewById(R.id.em_activity_login_confirm_button);
    }

    @Override
    protected void loadActorsToViews() {
        mTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.onBackPressed();
            }
        });
    }
}
