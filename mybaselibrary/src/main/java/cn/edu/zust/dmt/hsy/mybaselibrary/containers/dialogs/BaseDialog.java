package cn.edu.zust.dmt.hsy.mybaselibrary.containers.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseContainerListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.utils.MyErrorUtils;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.fragments.BaseFragment;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 14:17
 **/
public abstract class BaseDialog extends DialogFragment {

    private BaseContainerListener mBaseContainerListener = null;

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    /**
     * @description innerClass to avoid cache leak
     */
    protected static abstract class BaseDialogDirectorListener {
        private final WeakReference<BaseActivity> mActivityWeakReference;
        private final WeakReference<BaseDialog> mWeakReference;

        /**
         * @param baseDialog initialize {@link #mActivityWeakReference,#mWeakReference}
         */
        protected BaseDialogDirectorListener(@NonNull final BaseDialog baseDialog) {
            final Context context = baseDialog.getContext();
            if (context instanceof BaseActivity) {
                mActivityWeakReference = new WeakReference<>((BaseActivity) context);
                mWeakReference = new WeakReference<>(baseDialog);
            } else {
                throw new IllegalArgumentException("BaseDialog only allow BaseActivity attach!");
            }
        }

        /**
         * @param viewRId ResId for view
         * @param <T>     type of view
         * @return targetView
         */
        protected final <T extends View> T getWeakReferenceView(@IdRes int viewRId) {
            final BaseDialog baseDialog = mWeakReference.get();
            if (mActivityWeakReference.get() != null && baseDialog != null) {
                final View view = baseDialog.getView();
                if (view != null) {
                    return baseDialog.getView().findViewById(viewRId);
                } else {
                    throw new NullPointerException("BaseDialog has no layout");
                }
            } else {
                throw new NullPointerException("BaseDialog or BaseActivity no longer exist!");
            }
        }
    }

    /**
     * @description this inner class should only be initialized to {@link #mBaseContainerListener}
     */
    private static final class BaseDialogListener implements BaseContainerListener {
        /**
         * @description parent holder with {@link WeakReference} to avoid cache leak
         */
        private final WeakReference<BaseActivity> mActivityWeakReference;
        private final WeakReference<BaseDialog> mWeakReference;

        /**
         * @param baseActivity attach {@link #mActivityWeakReference} to {@link BaseActivity}
         * @param baseDialog   attach {@link #mWeakReference} to {@link BaseDialog}
         */
        private BaseDialogListener(@NonNull final BaseActivity baseActivity
                , @NonNull final BaseDialog baseDialog) {
            mActivityWeakReference = new WeakReference<>(baseActivity);
            mWeakReference = new WeakReference<>(baseDialog);
        }

        private BaseActivity getBaseActivity() {
            final BaseActivity baseActivity = mActivityWeakReference.get();
            final BaseDialog baseDialog = mWeakReference.get();
            if (baseActivity == null && baseDialog == null) {
                throw new NullPointerException("BaseActivity or BaseFragment no longer exist!");
            } else {
                return baseActivity;
            }
        }

        private BaseDialog getBaseDialog() {
            final BaseActivity baseActivity = mActivityWeakReference.get();
            final BaseDialog baseDialog = mWeakReference.get();
            if (baseActivity == null && baseDialog == null) {
                throw new NullPointerException("BaseActivity or BaseFragment no longer exist!");
            } else {
                return baseDialog;
            }
        }

        /**
         * @param path     Path of targetClass annotated with
         *                 {@link MyRouter}
         * @param myExtras extra info for path targetClass
         */
        @Override
        public final void callMyRouter(@NonNull final MyRouterPaths path, @Nullable final Bundle myExtras) {
            getBaseActivity().getBaseContainerListener().callMyRouter(path, myExtras);
        }

        /**
         * @param targetClass      class of {@link BaseFragment} wanted on stage
         * @param containerViewRId targetClass instance view container RId
         * @param myExtras         {@link Bundle} contains extra information for targetClass
         * @description ensure view with containerViewRId show correct fragment view
         */
        @Override
        public void showBaseFragment(@NonNull final Class<? extends BaseFragment> targetClass
                , final int containerViewRId, final @Nullable Bundle myExtras) {
            getBaseActivity().getBaseContainerListener().showBaseFragment(targetClass, containerViewRId, myExtras);
        }

        /**
         * @param targetClass class of {@link BaseDialog} wanted on {@link BaseActivity}
         * @param myExtras    {@link Bundle} contains extra information for targetClass
         */
        @Override
        public void showBaseDialog(@NonNull final Class<? extends BaseDialog> targetClass
                , final @Nullable Bundle myExtras) {
            getBaseActivity().getBaseContainerListener().showBaseDialog(targetClass, myExtras);
        }

        /**
         * @param targetClass class of {@link BaseDialog} wanted on {@link BaseActivity}
         */
        @Override
        public void dismissBaseDialog(@NonNull final Class<? extends BaseDialog> targetClass) {
            getBaseActivity().getBaseContainerListener().dismissBaseDialog(targetClass);
        }

        /**
         * @param myExtrasParser add new parser for myExtras
         */
        @Override
        public final void addMyExtrasParser(@NonNull final BaseExtrasListener myExtrasParser) {
            getBaseDialog().mExtrasParserList.add(myExtrasParser);
        }

        /**
         * @description trigger {@link BaseActivity#onBackPressed()}
         */
        @Override
        public void triggerBackPressedEvent() {
            getBaseActivity().getBaseContainerListener().triggerBackPressedEvent();
        }
    }

    /**
     * @description ensure {@link BaseDialog} is attached to {@link BaseActivity} and initialize itself
     */
    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mBaseContainerListener = new BaseDialogListener((BaseActivity) context, this);
        } else {
            MyErrorUtils.showMyArgumentException("BaseDialog only support BaseActivity!");
        }
    }

    /**
     * @return inflate {@link #getViewRId()} to view
     */
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getViewRId(), container);
    }

    /**
     * @description inject {@link #loadDirectorsToView()}
     */
    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDirectorsToView();
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
            MyErrorUtils.showMyNullPointerException("Dialog of BaseDialog is not initialized!");
        } else {
            final Window window = dialog.getWindow();
            if (window == null) {
                MyErrorUtils.showMyNullPointerException("Dialog of BaseDialog not attached to window!");
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
     * @description supposed to be call only in child class
     */
    protected final BaseContainerListener getBaseContainerListener() {
        return mBaseContainerListener;
    }

    /**
     * @return {@link LayoutRes}  for {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @LayoutRes
    protected abstract int getViewRId();

    /**
     * @description load {@link BaseDirector} to view
     */
    protected abstract void loadDirectorsToView();
}
