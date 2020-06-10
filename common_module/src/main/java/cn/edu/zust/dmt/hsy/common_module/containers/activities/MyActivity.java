package cn.edu.zust.dmt.hsy.common_module.containers.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import cn.edu.zust.dmt.hsy.common_module.R;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.BasePresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.BasePresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/1/2020 19:24
 **/
public abstract class MyActivity<T extends BasePresenterListener, K extends BasePresenter<T>>
        extends BaseActivity<T, K> {
    /**
     * @param message string for toast message
     */
    protected final void showMyToast(@NonNull final String message) {
        Toast toast = new Toast(this);
        View mView = LayoutInflater.from(this).inflate(R.layout.cm_activity_my_toast, null);
        ((TextView) mView.findViewById(R.id.my_activity_toast_message)).setText(message);
        toast.setView(mView);
        toast.show();
    }

    /**
     * @param messageRId string RId for toast message
     */
    protected final void showMyToast(@StringRes final int messageRId) {
        showMyToast(getResources().getString(messageRId));
    }
}
