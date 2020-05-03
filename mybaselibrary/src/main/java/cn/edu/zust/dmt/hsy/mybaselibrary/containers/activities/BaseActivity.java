package cn.edu.zust.dmt.hsy.mybaselibrary.containers.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseContainerListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.helpers.MyRouterHelper;
import cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.utils.MyErrorUtils;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.dialogs.BaseDialog;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.fragments.BaseFragment;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/1/2020 20:16
 **/
public abstract class BaseActivity extends AppCompatActivity {

    private final BaseContainerListener mBaseContainerListener = new BaseActivityListener(this);

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    /**
     * @description innerClass to avoid cache leak
     */
    protected static abstract class BaseActivitySafeDirectorListener {
        private final WeakReference<BaseActivity> mWeakReference;

        /**
         * @param baseActivity initialize {@link #mWeakReference} to ensure cache security
         */
        protected BaseActivitySafeDirectorListener(@NonNull final BaseActivity baseActivity) {
            mWeakReference = new WeakReference<>(baseActivity);
        }

        /**
         * @param viewRId ResId for view
         * @param <T>     type of view
         * @return targetView
         */
        protected final <T extends View> T getSafeView(@IdRes int viewRId) {
            final BaseActivity baseActivity = mWeakReference.get();
            if (baseActivity != null) {
                return baseActivity.findViewById(viewRId);
            } else {
                throw new NullPointerException("BaseActivity is not existed!");
            }
        }
    }

    /**
     * @description this inner class should only be initialized to {@link #mBaseContainerListener}
     */
    private static final class BaseActivityListener implements BaseContainerListener {
        /**
         * @description parent holder with {@link WeakReference} to avoid cache leak
         */
        private final WeakReference<BaseActivity> mWeakReference;

        /**
         * @param baseActivity attach {@link #mWeakReference} to {@link BaseActivity}
         */
        private BaseActivityListener(@NonNull final BaseActivity baseActivity) {
            mWeakReference = new WeakReference<>(baseActivity);
        }

        private BaseActivity getBaseActivity() {
            final BaseActivity baseActivity = mWeakReference.get();
            if (baseActivity == null) {
                throw new NullPointerException("BaseActivity no longer exist!");
            } else {
                return baseActivity;
            }
        }

        /**
         * @param path     Path of targetClass annotated with
         *                 {@link MyRouter}
         * @param myExtras extra info for path targetClass
         */
        @Override
        public void callMyRouter(@NonNull final MyRouterPaths path, @Nullable final Bundle myExtras) {
            MyRouterHelper.INSTANCE.startBaseActivity(getBaseActivity(), path, myExtras);
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
            final FragmentManager fragmentManager = getBaseActivity().getSupportFragmentManager();
            final String tag = targetClass.getCanonicalName() + containerViewRId;
            final Fragment targetFragment = fragmentManager.findFragmentByTag(tag);
            if (targetFragment == null) {
                try {
                    final BaseFragment baseFragment = targetClass.newInstance();
                    if (myExtras != null) {
                        baseFragment.setArguments(myExtras);
                    }
                    fragmentManager.beginTransaction()
                            .replace(containerViewRId, baseFragment, tag)
                            .addToBackStack(tag)
                            .commit();
                } catch (IllegalAccessException | InstantiationException e) {
                    MyErrorUtils.showMyArgumentException("Illegal argument for showBaseFragment()");
                }
            }
        }

        /**
         * @param targetClass class of {@link BaseDialog} wanted on {@link BaseActivity}
         * @param myExtras    {@link Bundle} contains extra information for targetClass
         */
        @Override
        public void showBaseDialog(@NonNull final Class<? extends BaseDialog> targetClass
                , final @Nullable Bundle myExtras) {
            final FragmentManager fragmentManager = getBaseActivity().getSupportFragmentManager();
            final String tag = targetClass.getCanonicalName();
            final Fragment targetFragment = fragmentManager.findFragmentByTag(tag);
            if (targetFragment == null) {
                try {
                    final BaseDialog baseDialog = targetClass.newInstance();
                    baseDialog.setCancelable(false);
                    if (myExtras != null) {
                        baseDialog.setArguments(myExtras);
                    }
                    baseDialog.show(fragmentManager, tag);
                } catch (IllegalAccessException | InstantiationException e) {
                    MyErrorUtils.showMyArgumentException("Illegal argument for showBaseDialog()!");
                }
            }
        }

        /**
         * @param targetClass class of {@link BaseDialog} wanted on {@link BaseActivity}
         */
        @Override
        public final void dismissBaseDialog(@NonNull final Class<? extends BaseDialog> targetClass) {
            final Fragment targetFragment = getBaseActivity().getSupportFragmentManager()
                    .findFragmentByTag(targetClass.getCanonicalName());
            if (targetFragment != null) {
                ((BaseDialog) targetFragment).dismiss();
            } else {
                MyErrorUtils.showMyNullPointerException("Dismiss an non-exist BaseDialogFragment!");
            }
        }

        /**
         * @param myExtrasParser add new parser for myExtras
         */
        @Override
        public final void addMyExtrasParser(@NonNull final BaseExtrasListener myExtrasParser) {
            getBaseActivity().mExtrasParserList.add(myExtrasParser);
        }

        /**
         * @description trigger {@link BaseActivity#onBackPressed()}
         */
        @Override
        public final void triggerBackPressedEvent() {
            getBaseActivity().onBackPressed();
        }
    }

    /**
     * @description inject {@link #getViewRId(),#loadDirectorsToView()}
     * inject methods provided by {@link #mExtrasParserList}
     */
    @Override
    protected final void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewRId());
        loadDirectorsToView();
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            for (BaseExtrasListener myExtrasParser : mExtrasParserList) {
                myExtrasParser.parseMyExtras(bundle);
            }
        }
    }

    /**
     * @description add BaseActivity Filter to {@link android.app.Activity#startActivity(Intent)}
     */
    @Override
    public final void startActivity(final Intent intent) {
        startActivity(intent, null);
    }

    /**
     * @description add BaseActivity Filter to {@link android.app.Activity#startActivity(Intent, Bundle)}
     */
    @Override
    public final void startActivity(final Intent intent, @Nullable final Bundle options) {
        try {
            final ComponentName componentName = intent.getComponent();
            if (componentName == null) {
                MyErrorUtils.showMyNullPointerException("TargetClass for this method is empty!");
            } else {
                if (BaseActivity.class.isAssignableFrom(Class.forName(componentName.getClassName()))) {
                    super.startActivity(intent, options);
                } else {
                    MyErrorUtils.showMyNullPointerException("Illegal targetClass for this method!");
                }
            }
        } catch (ClassNotFoundException e) {
            MyErrorUtils.showMyNullPointerException("TargetClass for this method not found!");
        }
    }

    /**
     * @param intent inject {@link #mExtrasParserList} methods
     */
    @Override
    protected final void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        final Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (BaseExtrasListener myExtrasParser : mExtrasParserList) {
                myExtrasParser.parseMyExtras(bundle);
            }
        }
    }

    /**
     * @description public for {@link BaseFragment} and {@link BaseDialog}
     */
    public final BaseContainerListener getBaseContainerListener() {
        return mBaseContainerListener;
    }

    /**
     * @return {@link LayoutRes} contains view layout for {@link #setContentView(int)}
     */
    @LayoutRes
    protected abstract int getViewRId();

    /**
     * @description load {@link BaseDirector} to view
     */
    protected abstract void loadDirectorsToView();
}