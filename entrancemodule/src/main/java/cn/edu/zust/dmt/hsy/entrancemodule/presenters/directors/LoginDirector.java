package cn.edu.zust.dmt.hsy.entrancemodule.presenters.directors;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.contracts.listeners.LoginDirectorListener;
import cn.edu.zust.dmt.hsy.entrancemodule.contracts.suppliers.LoginFairSupplier;
import cn.edu.zust.dmt.hsy.entrancemodule.models.remote.response.LoginResponseModel;
import cn.edu.zust.dmt.hsy.entrancemodule.presenters.fairs.LoginFair;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.constants.MyExtraConstants;
import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others.BaseFairCallback;
import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others.BaseViewListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.dialogs.ProcessingDialog;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 10:34
 **/
public final class LoginDirector extends BaseDirector<LoginDirectorListener> {
    /**
     * @param baseViewListener      base view listener
     * @param loginDirectorListener current specialized view listener
     */
    public void loadActors(@NonNull final BaseViewListener baseViewListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        setLoginVoucherBar(baseViewListener, loginDirectorListener);
        setLoginTokenBar(baseViewListener, loginDirectorListener);
        setLoginRequestView(baseViewListener, loginDirectorListener);
    }

    /**
     * @description help check whether voucher input is illegal or not
     */
    private void setLoginVoucherBar(@NonNull final BaseViewListener baseViewListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        loginDirectorListener.getVoucherBar().setBarContentWatcher(
                new MyVoucherTextWatcher(baseViewListener, loginDirectorListener));
    }

    /**
     * @description help check whether token input is illegal or not
     */
    private void setLoginTokenBar(@NonNull final BaseViewListener baseViewListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        loginDirectorListener.getTokenBar().setBarContentWatcher(
                new MyTokenTextWatcher(baseViewListener, loginDirectorListener));
    }

    /**
     * @description set action possibly triggered by login request view
     */
    private void setLoginRequestView(@NonNull final BaseViewListener baseViewListener
            , @NonNull final LoginDirectorListener loginDirectorListener) {
        loginDirectorListener.getLoginRequestView().setOnClickListener(
                new MyLoginRequestListener(baseViewListener, loginDirectorListener));
    }

    private static final class MyVoucherTextWatcher extends SafeDirectorListener<LoginDirectorListener>
            implements TextWatcher {
        private MyVoucherTextWatcher(@NonNull final BaseViewListener baseViewListener
                , @NonNull final LoginDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
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

    private static final class MyTokenTextWatcher extends SafeDirectorListener<LoginDirectorListener>
            implements TextWatcher {
        private MyTokenTextWatcher(@NonNull final BaseViewListener baseViewListener
                , @NonNull final LoginDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
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

    private static final class MyLoginRequestListener extends SafeDirectorListener<LoginDirectorListener>
            implements View.OnClickListener {
        private MyLoginRequestListener(@NonNull final BaseViewListener baseViewListener
                , @NonNull final LoginDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(MyExtraConstants.TAG_PROCESSING_HINT), "login");
            getViewWeakReference().showBaseDialog(ProcessingDialog.class, bundle);
            new LoginFair().loadFair(new LoginFairSupplier<LoginResponseModel>() {
                @NonNull
                @Override
                public String getVoucher() {
                    return getDirectorWeakReference().getVoucherBar().getBarContent();
                }

                @NonNull
                @Override
                public String getToken() {
                    return getDirectorWeakReference().getTokenBar().getBarContent();
                }

                @NonNull
                @Override
                public BaseFairCallback<LoginResponseModel> getBaseFairCallback() {
                    return new BaseFairCallback<LoginResponseModel>() {
                        @Override
                        public void onModel(@NonNull LoginResponseModel response) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            getViewWeakReference().dismissBaseDialog(ProcessingDialog.class);
                            getViewWeakReference().callMyRouter(MyRouterPaths.HOME_PATH, null);
                        }
                    };
                }
            });
        }
    }
}
