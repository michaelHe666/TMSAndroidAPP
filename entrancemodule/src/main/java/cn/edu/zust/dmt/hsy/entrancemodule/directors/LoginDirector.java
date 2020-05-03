package cn.edu.zust.dmt.hsy.entrancemodule.directors;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.interfaces.listeners.LoginDirectorListener;
import cn.edu.zust.dmt.hsy.entrancemodule.interfaces.suppliers.LoginFairSupplier;
import cn.edu.zust.dmt.hsy.entrancemodule.models.remote.response.LoginResponseModel;
import cn.edu.zust.dmt.hsy.entrancemodule.fairs.LoginFair;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.constants.MyExtrasConstants;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseFairCallback;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseContainerListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.dialogs.ProcessingDialog;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 10:34
 **/
public final class LoginDirector extends BaseDirector<LoginDirectorListener> {
    /**
     * @param baseContainerListener      base view listener
     * @param loginDirectorListener current specialized view listener
     */
    public void loadActors(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        setLoginVoucherBar(baseContainerListener, loginDirectorListener);
        setLoginTokenBar(baseContainerListener, loginDirectorListener);
        setLoginRequestView(baseContainerListener, loginDirectorListener);
    }

    /**
     * @description help check whether voucher input is illegal or not
     */
    private void setLoginVoucherBar(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        loginDirectorListener.getVoucherBar().setBarContentWatcher(
                new MyVoucherTextWatcher(baseContainerListener, loginDirectorListener));
    }

    /**
     * @description help check whether token input is illegal or not
     */
    private void setLoginTokenBar(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        loginDirectorListener.getTokenBar().setBarContentWatcher(
                new MyTokenTextWatcher(baseContainerListener, loginDirectorListener));
    }

    /**
     * @description set action possibly triggered by login request view
     */
    private void setLoginRequestView(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        loginDirectorListener.getLoginRequestView().setOnClickListener(
                new MyLoginRequestListener(baseContainerListener, loginDirectorListener));
    }

    private static final class MyVoucherTextWatcher extends SafeDirectorInnerClass<LoginDirectorListener>
            implements TextWatcher {
        private MyVoucherTextWatcher(@NonNull final BaseContainerListener baseContainerListener
                , @NonNull final LoginDirectorListener baseDirectorListener) {
            super(baseContainerListener, baseDirectorListener);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private static final class MyTokenTextWatcher extends SafeDirectorInnerClass<LoginDirectorListener>
            implements TextWatcher {
        private MyTokenTextWatcher(@NonNull final BaseContainerListener baseContainerListener
                , @NonNull final LoginDirectorListener baseDirectorListener) {
            super(baseContainerListener, baseDirectorListener);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private static final class MyLoginRequestListener extends SafeDirectorInnerClass<LoginDirectorListener>
            implements View.OnClickListener {
        private MyLoginRequestListener(@NonNull final BaseContainerListener baseContainerListener
                , @NonNull final LoginDirectorListener baseDirectorListener) {
            super(baseContainerListener, baseDirectorListener);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(MyExtrasConstants.TAG_PROCESSING_HINT), "login");
            getSafeContainer().showBaseDialog(ProcessingDialog.class, bundle);
            new LoginFair().loadFair(new LoginFairSupplier<LoginResponseModel>() {
                @NonNull
                @Override
                public String getVoucher() {
                    return getSafeDirector().getVoucherBar().getBarContent();
                }

                @NonNull
                @Override
                public String getToken() {
                    return getSafeDirector().getTokenBar().getBarContent();
                }

                @NonNull
                @Override
                public BaseFairCallback<LoginResponseModel> getBaseFairCallback() {
                    return new BaseFairCallback<LoginResponseModel>() {
                        @Override
                        public void onReturnModel(@NonNull final LoginResponseModel response) {

                        }

                        @Override
                        public void onReturnError(@NonNull final Throwable e) {

                        }

                        @Override
                        public void onTheEnd() {
                            getSafeContainer().dismissBaseDialog(ProcessingDialog.class);
                            getSafeContainer().callMyRouter(MyRouterPaths.HOME_PATH, null);
                        }
                    };
                }
            });
        }
    }
}
