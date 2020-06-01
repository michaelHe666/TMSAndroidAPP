package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.SingleMethodWrapper;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 15:53
 **/
@MyRouter(path = MyRouterPaths.LOGIN_PATH)
public final class LoginActivity extends BaseActivity<LoginViewModelListener, LoginViewModel> {

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
            public cm_MyFormBar getVoucherBar() {
                return mVoucherBar;
            }

            @NonNull
            @Override
            public cm_MyFormBar getPasswordBar() {
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

            @NonNull
            @Override
            public SingleMethodWrapper getVoucherInputErrorMethod() {
                return new SingleMethodWrapper() {
                    @Override
                    public void startWrappedMethod() {
                        Toast.makeText(THIS, R.string.cm_string_universal_app_name, Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @NonNull
            @Override
            public SingleMethodWrapper getPasswordInputErrorMethod() {
                return new SingleMethodWrapper() {
                    @Override
                    public void startWrappedMethod() {
                        Toast.makeText(THIS, R.string.cm_string_universal_app_name, Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @NonNull
            @Override
            public SingleMethodWrapper getLoginErrorMethod() {
                return new SingleMethodWrapper() {
                    @Override
                    public void startWrappedMethod() {
                        Toast.makeText(THIS, R.string.cm_string_universal_app_name, Toast.LENGTH_SHORT).show();
                    }
                };
            }
        };
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
