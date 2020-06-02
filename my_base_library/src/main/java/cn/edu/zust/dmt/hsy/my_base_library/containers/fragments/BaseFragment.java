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

import java.util.ArrayList;
import java.util.Objects;

import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.BaseViewModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/6/2020 13:53
 **/
public abstract class BaseFragment<T extends BaseViewModelListener, K extends BaseViewModel<T>>
        extends Fragment {

    private final K mViewModel;

    private final ArrayList<BaseExtrasListener> mExtrasParserList = new ArrayList<>();

    protected BaseFragment() {
        try {
            mViewModel = getViewModelClass().newInstance();
        } catch (IllegalAccessException | java.lang.InstantiationException e) {
            throw new IllegalArgumentException("Failed on initialize ViewModel!");
        }
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
        mViewModel.setCurrentListener(getViewModelListener());
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
        mViewModel.onViewModelDestroyed();
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
    protected final <A extends View> A findViewByRId(@IdRes int viewRId) {
        return Objects.requireNonNull(getView()).findViewById(viewRId);
    }

    /**
     * @return {@link LayoutRes}  for {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @LayoutRes
    protected abstract int getLayoutRId();

    /**
     * @return {@link BaseViewModel} for {@link BaseActivity}
     */
    @NonNull
    protected abstract Class<K> getViewModelClass();

    /**
     * @return {@link BaseViewModelListener} for {@link #getViewModelClass()}
     */
    @NonNull
    protected abstract T getViewModelListener();

    /**
     * @description find {@link View} from layout which needs add actions on
     */
    protected abstract void findViews();

    /**
     * @description load actors to views to ensure its motion attributes
     */
    protected abstract void loadActorsToViews();
}
