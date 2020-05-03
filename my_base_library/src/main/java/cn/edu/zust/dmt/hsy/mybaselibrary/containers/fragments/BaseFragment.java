package cn.edu.zust.dmt.hsy.mybaselibrary.containers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseContainerListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.helpers.MyErrorHelper;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.dialogs.BaseDialog;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/6/2020 13:53
 **/
public abstract class BaseFragment extends Fragment {
    /**
     * @description this field should only be initialized once in {@link #onAttach(Context)}
     */
    private BaseContainerListener mBaseContainerListener = null;

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    /**
     * @description innerClass to avoid cache leak
     */
    protected static abstract class BaseFragmentDirectorListener {
        private final WeakReference<BaseActivity> mActivityWeakReference;
        private final WeakReference<BaseFragment> mWeakReference;

        /**
         * @param baseFragment initialize {@link #mActivityWeakReference,#mWeakReference}
         */
        protected BaseFragmentDirectorListener(@NonNull final BaseFragment baseFragment) {
            final Context context = baseFragment.getContext();
            if (context instanceof BaseActivity) {
                mActivityWeakReference = new WeakReference<>((BaseActivity) context);
                mWeakReference = new WeakReference<>(baseFragment);
            } else {
                throw new IllegalArgumentException("BaseFragment only allow BaseActivity attach!");
            }
        }

        /**
         * @param viewRId ResId for view
         * @param <T>     type of view
         * @return targetView
         */
        protected final <T extends View> T getWeakReferenceView(@IdRes int viewRId) {
            final BaseFragment baseFragment = mWeakReference.get();
            if (mActivityWeakReference.get() != null && baseFragment != null) {
                final View view = baseFragment.getView();
                if (view != null) {
                    return baseFragment.getView().findViewById(viewRId);
                } else {
                    throw new NullPointerException("BaseFragment has no layout");
                }
            } else {
                throw new NullPointerException("BaseFragment or BaseActivity no longer exist!");
            }
        }
    }

    /**
     * @description initialization of {@link BaseContainerListener}
     */
    private static final class BaseFragmentListener implements BaseContainerListener {
        /**
         * @description parent holder with {@link WeakReference} to avoid cache leak
         */
        private final WeakReference<BaseActivity> mActivityWeakReference;
        private final WeakReference<BaseFragment> mWeakReference;

        /**
         * @param baseActivity attach {@link #mActivityWeakReference} to {@link BaseActivity}
         * @param baseFragment attach {@link #mWeakReference} to {@link BaseFragment}
         */
        private BaseFragmentListener(@NonNull final BaseActivity baseActivity
                , @NonNull final BaseFragment baseFragment) {
            mActivityWeakReference = new WeakReference<>(baseActivity);
            mWeakReference = new WeakReference<>(baseFragment);
        }

        private BaseActivity getBaseActivity() {
            final BaseActivity baseActivity = mActivityWeakReference.get();
            final BaseFragment baseFragment = mWeakReference.get();
            if (baseActivity == null && baseFragment == null) {
                throw new NullPointerException("BaseActivity or BaseFragment no longer exist!");
            } else {
                return baseActivity;
            }
        }

        private BaseFragment getBaseFragment() {
            final BaseActivity baseActivity = mActivityWeakReference.get();
            final BaseFragment baseFragment = mWeakReference.get();
            if (baseActivity == null && baseFragment == null) {
                throw new NullPointerException("BaseActivity or BaseFragment no longer exist!");
            } else {
                return baseFragment;
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
            getBaseFragment().mExtrasParserList.add(myExtrasParser);
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
     * @description ensure {@link BaseFragment} is attached to {@link BaseActivity} and initialize itself
     */
    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mBaseContainerListener = new BaseFragmentListener((BaseActivity) context, this);
        } else {
            MyErrorHelper.showMyArgumentException("BaseFragment only support BaseActivity!");
        }
    }

    /**
     * @return inflate {@link #getViewRId()} to view
     */
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getViewRId(), container, false);
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
