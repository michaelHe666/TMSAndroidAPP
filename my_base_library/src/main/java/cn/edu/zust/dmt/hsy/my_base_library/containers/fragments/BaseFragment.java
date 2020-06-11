package cn.edu.zust.dmt.hsy.my_base_library.containers.fragments;

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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
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
 * @since 4/6/2020 13:53
 **/
public abstract class BaseFragment<T extends BasePresenterListener, K extends BasePresenter<T>>
        extends Fragment {
    /**
     * @description {@link BasePresenter<T>} for {@link BaseFragment<>}
     */
    private final K mPresenter;

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    /**
     * @description initialize {@link #mPresenter} by {@link Class<K>}
     */
    protected BaseFragment() {
        try {
            final Type genericSuperclass = this.getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                mPresenter = ((Class<K>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1])
                        .newInstance();
                return;
            }
        } catch (IllegalAccessException | java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed on initialize Presenter!");
    }


    /**
     * @description ensure {@link BaseFragment} is attached to {@link BaseActivity} and initialize itself
     */
    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseActivity)) {
            MyErrorHelper.showMyArgumentException("BaseFragment only support BaseActivity!");
        }
    }

    /**
     * @return inflate {@link #getLayoutRId()} to view
     */
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRId(), container, false);
    }

    /**
     * @description inject {@link #findViews()}
     */
    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        loadActorsToViews();
        //set listener while views are created
        mPresenter.setCurrentListener(getPresenterListener());
        //trigger extras parser while views are created
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
        mPresenter.onViewModelDestroyed();
    }

    /**
     * @param myExtrasParser add new parser for {@link #mExtrasParserList}
     */
    protected final void addMyExtrasParser(@NonNull final BaseExtrasListener myExtrasParser) {
        mExtrasParserList.add(myExtrasParser);
    }

    /**
     * @param viewRId resources id for view
     * @param <A>     ? extends view
     * @return ? extends view
     */
    protected final <A extends View> A findViewById(@IdRes int viewRId) {
        return Objects.requireNonNull(getView()).findViewById(viewRId);
    }

    /**
     * @param path     Path of targetClass annotated with {@link MyRouter}
     * @param myExtras extra info for path targetClass
     */
    protected final void callMyRouter(@NonNull final MyRouterPaths path
            , @Nullable final MyExtrasHelper.MyExtras myExtras) {
        final Context context = getContext();
        if (context instanceof BaseActivity) {
            if (myExtras == null) {
                MyRouterHelper.INSTANCE.startBaseActivity(context, path, null);
            } else {
                MyRouterHelper.INSTANCE.startBaseActivity(context, path, myExtras.getBundle());
            }
        } else {
            MyErrorHelper.showMyArgumentException("BaseFragment is not attached to BaseActivity!");
        }
    }

    /**
     * @return {@link LayoutRes}  for {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
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
