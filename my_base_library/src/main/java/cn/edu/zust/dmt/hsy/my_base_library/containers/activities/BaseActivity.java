package cn.edu.zust.dmt.hsy.my_base_library.containers.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyExtrasHelper;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyRouterHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.BasePresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.BasePresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/1/2020 20:16
 **/
public abstract class BaseActivity<T extends BasePresenterListener, K extends BasePresenter<T>>
        extends AppCompatActivity {

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    private final K mPresenter;

    /**
     * @description initialize {@link #mPresenter} by {@link Class<K>}
     */
    protected BaseActivity() {
        try {
            final Type genericSuperclass = this.getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                mPresenter = ((Class<K>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1])
                        .newInstance();
                return;
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed on initialize Presenter!");
    }

    /**
     * @description inject {@link #getLayoutRId(),#findViews()}
     * inject methods provided by {@link #mExtrasParserList}
     * inject {@link #mPresenter} with {@link #getPresenterListener()}
     */
    @Override
    protected final void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRId());
        findViews();
        loadActorsToViews();
        //set listener while views are created
        mPresenter.setCurrentListener(getPresenterListener());
        //trigger extras parser while views are created
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            for (BaseExtrasListener myExtrasParser : mExtrasParserList) {
                myExtrasParser.parseMyExtras(bundle);
            }
        }
    }

    /**
     * @description release all resources here to ensure {@link BaseActivity} could be finalized
     */
    @Override
    protected final void onDestroy() {
        super.onDestroy();
        mPresenter.onViewModelDestroyed();
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
                MyErrorHelper.showMyNullPointerException("TargetClass for this method is empty!");
            } else {
                if (BaseActivity.class.isAssignableFrom(Class.forName(componentName.getClassName()))) {
                    super.startActivity(intent, options);
                } else {
                    MyErrorHelper.showMyNullPointerException("Illegal targetClass for this method!");
                }
            }
        } catch (ClassNotFoundException e) {
            MyErrorHelper.showMyNullPointerException("TargetClass for this method not found!");
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
     * @param path           Path of targetClass annotated with {@link MyRouter}
     * @param myExtras extra info for path targetClass
     */
    protected final void callMyRouter(@NonNull final MyRouterPaths path
            , @Nullable final MyExtrasHelper.MyExtras myExtras) {
        if (myExtras == null) {
            MyRouterHelper.INSTANCE.startBaseActivity(this, path, null);
        } else {
            MyRouterHelper.INSTANCE.startBaseActivity(this, path, myExtras.getBundle());
        }
    }

    /**
     * @param myExtrasParser add new parser for {@link #mExtrasParserList}
     */
    protected final void addMyExtrasParser(@NonNull final BaseExtrasListener myExtrasParser) {
        mExtrasParserList.add(myExtrasParser);
    }

    /**
     * @return {@link LayoutRes} contains view layout for {@link #setContentView(int)}
     */
    @LayoutRes
    protected abstract int getLayoutRId();

    /**
     * @return {@link BasePresenterListener} for {@link #mPresenter}
     */
    @NonNull
    protected abstract T getPresenterListener();

    /**
     * @description find {@link View} from layout which needs add actions on
     */
    protected abstract void findViews();

    /**
     * @description load actors to views to ensure its motion attributes
     */
    protected abstract void loadActorsToViews();
}