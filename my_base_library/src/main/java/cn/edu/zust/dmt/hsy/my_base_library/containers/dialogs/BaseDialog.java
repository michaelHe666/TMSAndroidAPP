package cn.edu.zust.dmt.hsy.my_base_library.containers.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseExtrasListener;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 14:17
 **/
public abstract class BaseDialog extends DialogFragment {

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    /**
     * @description ensure {@link BaseDialog} is attached to {@link BaseActivity} and initialize itself
     */
    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseActivity)) {
            MyErrorHelper.showMyArgumentException("BaseDialog only support BaseActivity!");
        }
    }

    /**
     * @return inflate {@link #getLayoutRId()} to view
     */
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRId(), container);
    }

    /**
     * @description inject {@link #findViews()}
     */
    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        loadActorsToViews();
        final Bundle myExtras = getArguments();
        if (myExtras != null) {
            for (BaseExtrasListener myExtrasParser : mExtrasParserList) {
                myExtrasParser.parseMyExtras(myExtras);
            }
        }
    }

    /**
     * @description reset RootWindow LayoutParams
     */
    @Override
    public final void onStart() {
        super.onStart();
        final Dialog dialog = getDialog();
        if (dialog == null) {
            MyErrorHelper.showMyNullPointerException("Dialog of BaseDialog is not initialized!");
        } else {
            final Window window = dialog.getWindow();
            if (window == null) {
                MyErrorHelper.showMyNullPointerException("Dialog of BaseDialog not attached to window!");
            } else {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }
    }

    /**
     * @description release all resources related with parent {@link BaseActivity}
     */
    @Override
    public final void onDetach() {
        super.onDetach();
    }

    /**
     * @return {@link LayoutRes}  for {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @LayoutRes
    protected abstract int getLayoutRId();

    /**
     * @description find {@link View} from layout which needs add actions on
     */
    protected abstract void findViews();

    /**
     * @description load actors to views to ensure its motion attributes
     */
    protected abstract void loadActorsToViews();
}
